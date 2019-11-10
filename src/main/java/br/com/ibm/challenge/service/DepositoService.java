package br.com.ibm.challenge.service;

import br.com.ibm.challenge.domain.cliente.Conta;
import br.com.ibm.challenge.domain.enumeration.TipoContabilEnum;
import br.com.ibm.challenge.domain.enumeration.TipoMovimentoEnum;
import br.com.ibm.challenge.domain.exception.BusinessException;
import br.com.ibm.challenge.domain.exception.EntityNotFoundException;
import br.com.ibm.challenge.domain.movimento.Deposito;
import br.com.ibm.challenge.domain.movimento.Movimento;
import br.com.ibm.challenge.domain.movimento.Operacao;
import br.com.ibm.challenge.repository.MovimentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class DepositoService extends CrudService<Movimento, UUID> implements Operacao {

    private MovimentoRepository repository;
    private ContaService contaService;

    public DepositoService(MovimentoRepository repository, ContaService contaService) {
        this.repository = repository;
        this.contaService = contaService;
    }

    @Override
    @Transactional(rollbackFor = { BusinessException.class, RuntimeException.class, Error.class })
    public void processar(Movimento entity) throws BusinessException {
        entity.setDataHora(LocalDateTime.now());
        entity.setTipoContabil(TipoContabilEnum.CREDITO);
        entity.setTipoMovimento(TipoMovimentoEnum.DEPOSITO);

        Deposito deposito = entity.getDeposito();
        Conta conta;
        try {
            conta = contaService.findByNumeroAndAgencia(
                    deposito.getContaOrigem().getNumero(),
                    deposito.getContaOrigem().getAgencia()
            );
        } catch (EntityNotFoundException e) {
            throw new BusinessException("Conta nao encontrada");
        }

        deposito.setContaOrigem(conta);
        conta.getSaldo().somar(entity.getValor());

        try {
            contaService.update(deposito.getContaOrigem());
        } catch (EntityNotFoundException e) {
            throw new BusinessException("Falha ao atualizar o saldo da conta");
        }

        create(entity);
    }

    @Override
    public TipoMovimentoEnum tipo() {
        return TipoMovimentoEnum.DEPOSITO;
    }

    @Override
    protected MovimentoRepository getRepository() {
        return repository;
    }
}
