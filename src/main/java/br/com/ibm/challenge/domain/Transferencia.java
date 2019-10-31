package br.com.ibm.challenge.domain;

import br.com.ibm.challenge.domain.contract.Movimento;
import br.com.ibm.challenge.domain.enumeration.TipoContabilEnum;
import br.com.ibm.challenge.domain.enumeration.TipoMovimentoEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * O movimento de transferencia deve ser criado na conta de origem e de destino, mudando o tipoContabil.
 */
@Data
public class Transferencia implements Movimento {

    private Conta contaOrigem;

    private Conta contaDestino;

    private TipoContabilEnum tipoContabil;

    private BigDecimal valor;

    private LocalDateTime dataHora;

    @Override
    public TipoContabilEnum getTipoContabil() {
        return tipoContabil;
    }

    @Override
    public TipoMovimentoEnum getTipoMovimento() {
        return TipoMovimentoEnum.TRANSFERENCIA;
    }
}
