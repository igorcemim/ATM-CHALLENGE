package br.com.ibm.challenge.dto;

import br.com.ibm.challenge.domain.enumeration.TipoDepositoEnum;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class MovimentoDepositoRequestDto {

    @NotNull
    private BigDecimal valor;

    @NotNull
    private Integer depositoContaOrigemNumero;

    @NotNull
    private Integer depositoContaOrigemAgencia;

    @NotNull
    private TipoDepositoEnum depositoTipoDeposito;

}
