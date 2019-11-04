package br.com.ibm.challenge.domain.caixa;

import br.com.ibm.challenge.domain.EntityId;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class Caixa implements EntityId<UUID> {

    @Id
    @GeneratedValue
    private UUID id;

}
