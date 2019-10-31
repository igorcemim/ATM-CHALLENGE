package br.com.ibm.challenge.domain;

import br.com.ibm.challenge.domain.contract.Movimento;
import br.com.ibm.challenge.domain.enumeration.TipoContabilEnum;
import br.com.ibm.challenge.domain.enumeration.TipoMovimentoEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Deposito implements Movimento {

    private Conta conta;

    private BigDecimal valor;

    private LocalDateTime dataHora;

    @Override
    public TipoContabilEnum getTipoContabil() {
        return TipoContabilEnum.CREDITO;
    }

    @Override
    public TipoMovimentoEnum getTipoMovimento() {
        return TipoMovimentoEnum.DEPOSITO;
    }

}
