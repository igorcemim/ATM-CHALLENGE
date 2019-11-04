package br.com.ibm.challenge.domain;

import br.com.ibm.challenge.domain.enumeration.StatusCaixaEnum;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
public class StatusCaixa {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Caixa caixa;

    @Enumerated(EnumType.STRING)
    private StatusCaixaEnum status;

}
