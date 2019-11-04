package br.com.ibm.challenge.domain.caixa;

import br.com.ibm.challenge.domain.EntityId;
import br.com.ibm.challenge.domain.enumeration.StatusCaixaEnum;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
public class StatusCaixa implements EntityId<UUID> {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Caixa caixa;

    private LocalDate data;

    @Enumerated(EnumType.STRING)
    private StatusCaixaEnum status;

}
