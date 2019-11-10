package br.com.ibm.challenge.domain.cliente;

import br.com.ibm.challenge.domain.EntityId;
import br.com.ibm.challenge.domain.converter.SaldoConverter;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
public class Conta implements EntityId<UUID> {

    @Id
    @GeneratedValue
    private UUID id;

    private Integer numero;

    private Integer agencia;

    @Convert(converter = SaldoConverter.class)
    private Saldo saldo;

}
