package br.com.ibm.challenge.domain.movimento;

import br.com.ibm.challenge.domain.cliente.Conta;
import br.com.ibm.challenge.domain.EntityId;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
public class Saque implements EntityId<UUID> {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Conta contaOrigem;

    @OneToOne
    @ToString.Exclude
    private Movimento movimento;

}
