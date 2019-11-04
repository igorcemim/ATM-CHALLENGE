package br.com.ibm.challenge.domain.movimento;

import br.com.ibm.challenge.domain.EntityId;
import br.com.ibm.challenge.domain.enumeration.TipoContabilEnum;
import br.com.ibm.challenge.domain.enumeration.TipoMovimentoEnum;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class Movimento implements EntityId<UUID> {

    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.STRING)
    private TipoContabilEnum tipoContabil;

    @Enumerated(EnumType.STRING)
    private TipoMovimentoEnum tipoMovimento;

    private BigDecimal valor;

    private LocalDateTime dataHora;

    @OneToOne(mappedBy = "movimento", cascade = CascadeType.ALL)
    private Deposito deposito;

    @OneToOne(mappedBy = "movimento", cascade = CascadeType.ALL)
    private Saque saque;

    @OneToOne(mappedBy = "movimento", cascade = CascadeType.ALL)
    private Transferencia transferencia;

}

