package br.com.ibm.challenge.domain.movimento;

import br.com.ibm.challenge.domain.cliente.Conta;
import br.com.ibm.challenge.domain.EntityId;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

/**
 * O movimento de transferencia deve ser criado na conta de origem e de destino, mudando o tipoContabil.
 */
@Entity
@Data
public class Transferencia implements EntityId<UUID> {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Conta contaOrigem;

    @ManyToOne
    private Conta contaDestino;

    @OneToOne
    @ToString.Exclude
    private Movimento movimento;

}
