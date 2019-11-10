package br.com.ibm.challenge.service;

import br.com.ibm.challenge.domain.enumeration.TipoMovimentoEnum;
import br.com.ibm.challenge.domain.exception.BusinessException;
import br.com.ibm.challenge.domain.movimento.Movimento;
import br.com.ibm.challenge.domain.movimento.Operacao;
import br.com.ibm.challenge.factory.MovimentoFactory;
import org.springframework.stereotype.Component;

@Component
public class MovimentoService {

    private MovimentoFactory factory;

    public MovimentoService(MovimentoFactory factory) {
        this.factory = factory;
    }

    public void processar(TipoMovimentoEnum tipo, Movimento movimento) throws BusinessException {
        Operacao operacao = factory.getInstance(tipo);
        operacao.processar(movimento);
    }

}
