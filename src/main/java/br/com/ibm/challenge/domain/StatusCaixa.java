package br.com.ibm.challenge.domain;

import br.com.ibm.challenge.domain.enumeration.StatusCaixaEnum;
import lombok.Data;

@Data
public class StatusCaixa {
    private StatusCaixaEnum status;
}
