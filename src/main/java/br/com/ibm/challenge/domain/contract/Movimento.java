package br.com.ibm.challenge.domain.contract;

import br.com.ibm.challenge.domain.enumeration.TipoContabilEnum;
import br.com.ibm.challenge.domain.enumeration.TipoMovimentoEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface Movimento {
    TipoContabilEnum getTipoContabil();
    TipoMovimentoEnum getTipoMovimento();
    BigDecimal getValor();
    LocalDateTime getDataHora();
}
