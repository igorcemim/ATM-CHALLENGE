package br.com.ibm.challenge.domain.movimento;

import br.com.ibm.challenge.domain.enumeration.TipoMovimentoEnum;
import br.com.ibm.challenge.domain.exception.BusinessException;

public interface Operacao {

    void processar(Movimento movimento) throws BusinessException;

    TipoMovimentoEnum tipo();

}
