package br.com.ibm.challenge.domain.cliente;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class Saldo {
    private BigDecimal saldo;

    public static final Integer SALDO_NEGATIVO = -1;

    public void somar(BigDecimal valor) {
        BigDecimal novoSaldo = saldo.add(valor);
        saldo = novoSaldo;
    }

    public void subtrair(BigDecimal valor) {
        BigDecimal novoSaldo = saldo.subtract(valor);
        saldo = novoSaldo;
    }

    public Boolean isNegativo() {
        return saldo.compareTo(BigDecimal.ZERO) == SALDO_NEGATIVO;
    }
}
