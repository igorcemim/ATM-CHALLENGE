package br.com.ibm.challenge.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class MovimentoSaqueRequestDto {

    @NotNull
    private BigDecimal valor;

    @NotNull
    private Integer saqueContaOrigemNumero;

    @NotNull
    private Integer saqueContaOrigemAgencia;

}
