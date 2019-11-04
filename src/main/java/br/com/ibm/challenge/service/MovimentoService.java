package br.com.ibm.challenge.service;

import br.com.ibm.challenge.domain.cliente.Conta;
import br.com.ibm.challenge.domain.enumeration.TipoContabilEnum;
import br.com.ibm.challenge.domain.enumeration.TipoMovimentoEnum;
import br.com.ibm.challenge.domain.exception.BusinessException;
import br.com.ibm.challenge.domain.exception.EntityNotFoundException;
import br.com.ibm.challenge.domain.movimento.Movimento;
import br.com.ibm.challenge.repository.MovimentoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class MovimentoService extends CrudService<Movimento, UUID> {

    private final MovimentoRepository repository;
    private ContaService contaService;

    public MovimentoService(MovimentoRepository repository, ContaService contaService) {
        this.repository = repository;
        this.contaService = contaService;
    }

    @Override
    protected JpaRepository<Movimento, UUID> getRepository() {
        return repository;
    }

    private void validarSaldo(Conta conta) throws BusinessException {
        if (conta.getSaldo().compareTo(BigDecimal.ZERO) == -1) {
            throw new BusinessException("Saldo insuficiente");
        }
    }

    /**
     * @TODO Aplicar extract class para cada tipo de movimento.
     */
    @Override
    @Transactional(rollbackFor = { BusinessException.class, RuntimeException.class, Error.class })
    public Movimento create(Movimento entity) throws BusinessException {
        entity.setDataHora(LocalDateTime.now());

        if (entity.getTipoMovimento().equals(TipoMovimentoEnum.DEPOSITO)) {
            entity.setTipoContabil(TipoContabilEnum.CREDITO);

            BigDecimal novoSaldo = entity.getDeposito().getContaOrigem().getSaldo().add(entity.getValor());
            entity.getDeposito().getContaOrigem().setSaldo(novoSaldo);

            entity.getDeposito().setMovimento(entity);

            try {
                contaService.update(entity.getDeposito().getContaOrigem());
            } catch (EntityNotFoundException e) {
                throw new BusinessException("Falha ao atualizar o saldo da conta");
            }
        }

        if (entity.getTipoMovimento().equals(TipoMovimentoEnum.SAQUE)) {
            entity.setTipoContabil(TipoContabilEnum.DEBITO);

            BigDecimal novoSaldo = entity.getSaque().getContaOrigem().getSaldo().subtract(entity.getValor());
            entity.getSaque().getContaOrigem().setSaldo(novoSaldo);

            entity.getSaque().setMovimento(entity);
            validarSaldo(entity.getSaque().getContaOrigem());

            try {
                contaService.update(entity.getSaque().getContaOrigem());
            } catch (EntityNotFoundException e) {
                throw new BusinessException("Falha ao atualizar o saldo da conta");
            }
        }

        if (entity.getTipoMovimento().equals(TipoMovimentoEnum.TRANSFERENCIA)) {
            entity.setTipoContabil(TipoContabilEnum.DEBITO);

            BigDecimal novoSaldo = entity.getTransferencia().getContaOrigem().getSaldo().subtract(entity.getValor());
            entity.getTransferencia().getContaOrigem().setSaldo(novoSaldo);

            validarSaldo(entity.getTransferencia().getContaOrigem());

            /**
             * Faz o movimento inverso na conta de destino
             */
            Movimento movimentoCredito = entity;
            movimentoCredito.setTipoContabil(TipoContabilEnum.CREDITO);

            BigDecimal novoSaldoDestino = movimentoCredito.getTransferencia().getContaDestino().getSaldo().add(entity.getValor());
            entity.getTransferencia().getContaDestino().setSaldo(novoSaldoDestino);

            movimentoCredito.getTransferencia().setMovimento(movimentoCredito);

            entity.getTransferencia().setMovimento(entity);

            try {
                contaService.update(entity.getTransferencia().getContaOrigem());
                contaService.update(entity.getTransferencia().getContaDestino());
            } catch (EntityNotFoundException e) {
                throw new BusinessException("Falha ao atualizar o saldo das contas");
            }
            super.create(movimentoCredito);
        }

        return super.create(entity);
    }

    @Override
    public Movimento update(Movimento entity) throws BusinessException {
        throw new BusinessException("Operaçao nao permitida.");
    }

    @Override
    public void delete(UUID id) throws BusinessException {
        throw new BusinessException("Operaçao nao permitida.");
    }
}
