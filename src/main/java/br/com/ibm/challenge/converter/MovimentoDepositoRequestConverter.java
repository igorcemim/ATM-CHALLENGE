package br.com.ibm.challenge.converter;

import br.com.ibm.challenge.domain.cliente.Conta;
import br.com.ibm.challenge.domain.movimento.Deposito;
import br.com.ibm.challenge.domain.movimento.Movimento;
import br.com.ibm.challenge.dto.MovimentoDepositoRequestDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MovimentoDepositoRequestConverter implements Converter<MovimentoDepositoRequestDto, Movimento> {

    @Override
    public Movimento convert(MovimentoDepositoRequestDto dto) {
        Movimento entity = new Movimento();
        entity.setValor(dto.getValor());

        Conta conta = new Conta();
        conta.setNumero(dto.getDepositoContaOrigemNumero());
        conta.setAgencia(dto.getDepositoContaOrigemAgencia());

        Deposito deposito = new Deposito();
        deposito.setContaOrigem(conta);
        deposito.setMovimento(entity);
        deposito.setTipoDeposito(dto.getDepositoTipoDeposito());
        entity.setDeposito(deposito);

        return entity;
    }

}
