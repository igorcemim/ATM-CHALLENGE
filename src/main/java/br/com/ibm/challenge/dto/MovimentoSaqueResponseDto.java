package br.com.ibm.challenge.dto;

import br.com.ibm.challenge.domain.enumeration.TipoContabilEnum;
import br.com.ibm.challenge.domain.enumeration.TipoMovimentoEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class MovimentoSaqueResponseDto {

    private UUID id;

    private TipoContabilEnum tipoContabil;

    private TipoMovimentoEnum tipoMovimento;

    private BigDecimal valor;

    private LocalDateTime dataHora;

    private UUID saqueId;

    private Integer saqueContaOrigemNumero;

    private Integer saqueContaOrigemAgencia;

}
