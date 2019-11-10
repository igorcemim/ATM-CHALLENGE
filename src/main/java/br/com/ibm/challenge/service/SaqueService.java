package br.com.ibm.challenge.service;

import br.com.ibm.challenge.domain.cliente.Conta;
import br.com.ibm.challenge.domain.enumeration.TipoContabilEnum;
import br.com.ibm.challenge.domain.enumeration.TipoMovimentoEnum;
import br.com.ibm.challenge.domain.exception.BusinessException;
import br.com.ibm.challenge.domain.exception.EntityNotFoundException;
import br.com.ibm.challenge.domain.movimento.Movimento;
import br.com.ibm.challenge.domain.movimento.Operacao;
import br.com.ibm.challenge.domain.movimento.Saque;
import br.com.ibm.challenge.repository.MovimentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class SaqueService extends CrudService<Movimento, UUID> implements Operacao {

    private MovimentoRepository repository;
    private ContaService contaService;

    public SaqueService(MovimentoRepository repository, ContaService contaService) {
        this.repository = repository;
        this.contaService = contaService;
    }

    @Override
    public TipoMovimentoEnum tipo() {
        return TipoMovimentoEnum.SAQUE;
    }

    @Override
    protected MovimentoRepository getRepository() {
        return repository;
    }

    @Override
    @Transactional(rollbackFor = { BusinessException.class, RuntimeException.class, Error.class })
    public void processar(Movimento entity) throws BusinessException {
        entity.setDataHora(LocalDateTime.now());
        entity.setTipoContabil(TipoContabilEnum.DEBITO);
        entity.setTipoMovimento(TipoMovimentoEnum.SAQUE);

        Saque saque = entity.getSaque();
        Conta conta;
        try {
            conta = contaService.findByNumeroAndAgencia(
                    saque.getContaOrigem().getNumero(),
                    saque.getContaOrigem().getAgencia()
            );
        } catch (EntityNotFoundException e) {
            throw new BusinessException("Conta nao encontrada.");
        }

        saque.setContaOrigem(conta);
        conta.getSaldo().subtrair(entity.getValor());

        if (conta.getSaldo().isNegativo()) {
            throw new BusinessException("Saldo insuficiente para o valor informado para saque.");
        }

        try {
            contaService.update(saque.getContaOrigem());
        } catch (EntityNotFoundException e) {
            throw new BusinessException("Falha ao atualizar o saldo da conta.");
        }

        create(entity);
    }

}
