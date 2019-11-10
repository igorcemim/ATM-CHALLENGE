package br.com.ibm.challenge.factory;

import br.com.ibm.challenge.domain.enumeration.TipoMovimentoEnum;
import br.com.ibm.challenge.domain.exception.BusinessException;
import br.com.ibm.challenge.domain.movimento.Operacao;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovimentoFactory {

    List<Operacao> tiposMovimento;

    public MovimentoFactory(List<Operacao> tiposMovimento) {
        this.tiposMovimento = tiposMovimento;
    }

    public Operacao getInstance(TipoMovimentoEnum tipo) throws BusinessException {
        return tiposMovimento.stream()
                .filter(t -> t.tipo().equals(tipo))
                .findFirst()
                .orElseThrow(() -> new BusinessException("Tipo de movimento invalido."));
    }
}
