package br.com.ibm.challenge.dto;

import br.com.ibm.challenge.domain.enumeration.TipoDepositoEnum;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class MovimentoDepositoRequestDto {

    @NotNull(message = "O campo Valor é de preenchimento obrigatório.")
    private BigDecimal valor;

    @NotNull(message = "O campo Número da Conta é de preenchimento obrigatório.")
    private Integer depositoContaOrigemNumero;

    @NotNull(message = "O campo Agência da Conta é de preenchimento obrigatório.")
    private Integer depositoContaOrigemAgencia;

    @NotNull(message = "O campo Tipo de Depósito é de preenchimento obrigatório.")
    private TipoDepositoEnum depositoTipoDeposito;

}
