package br.com.ibm.challenge.converter;

import br.com.ibm.challenge.domain.cliente.Conta;
import br.com.ibm.challenge.domain.movimento.Movimento;
import br.com.ibm.challenge.domain.movimento.Saque;
import br.com.ibm.challenge.dto.MovimentoSaqueRequestDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MovimentoSaqueRequestConverter implements Converter<MovimentoSaqueRequestDto, Movimento> {

    @Override
    public Movimento convert(MovimentoSaqueRequestDto dto) {
        Movimento entity = new Movimento();
        entity.setValor(dto.getValor());

        Conta conta = new Conta();
        conta.setNumero(dto.getSaqueContaOrigemNumero());
        conta.setAgencia(dto.getSaqueContaOrigemAgencia());

        Saque saque = new Saque();
        saque.setContaOrigem(conta);
        saque.setMovimento(entity);
        entity.setSaque(saque);

        return entity;
    }

}
