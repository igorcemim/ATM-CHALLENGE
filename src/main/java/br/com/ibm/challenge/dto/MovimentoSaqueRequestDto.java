package br.com.ibm.challenge.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class MovimentoSaqueRequestDto {

    @NotNull(message = "O campo Valor é de preenchimento obrigatório.")
    private BigDecimal valor;

    @NotNull(message = "O campo Número da Conta é de preenchimento obrigatório.")
    private Integer saqueContaOrigemNumero;

    @NotNull(message = "O campo Agência da Conta é de preenchimento obrigatório.")
    private Integer saqueContaOrigemAgencia;

}
