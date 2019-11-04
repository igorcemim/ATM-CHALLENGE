package br.com.ibm.challenge.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
public class Conta {

    @Id
    @GeneratedValue
    private UUID id;

    private Integer numero;

    private Integer agencia;

    private BigDecimal saldo;

}
