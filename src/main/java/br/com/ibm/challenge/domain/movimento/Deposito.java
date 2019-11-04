package br.com.ibm.challenge.domain.movimento;

import br.com.ibm.challenge.domain.Conta;
import br.com.ibm.challenge.domain.enumeration.TipoDepositoEnum;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
public class Deposito {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Conta contaOrigem;

    @OneToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    private Movimento movimento;

    @Enumerated(EnumType.STRING)
    private TipoDepositoEnum tipoDeposito;

}
