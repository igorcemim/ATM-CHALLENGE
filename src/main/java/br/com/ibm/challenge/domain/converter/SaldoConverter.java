package br.com.ibm.challenge.domain.converter;

import br.com.ibm.challenge.domain.cliente.Saldo;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.math.BigDecimal;

@Converter
public class SaldoConverter implements AttributeConverter<Saldo, BigDecimal> {
    @Override
    public BigDecimal convertToDatabaseColumn(Saldo saldo) {
        return saldo.getSaldo();
    }

    @Override
    public Saldo convertToEntityAttribute(BigDecimal valor) {
        return new Saldo(valor);
    }
}
