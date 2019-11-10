package br.com.ibm.challenge.converter;

import br.com.ibm.challenge.domain.movimento.Movimento;
import br.com.ibm.challenge.dto.MovimentoDepositoResponseDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MovimentoDepositoResponseConverter implements Converter<Movimento, MovimentoDepositoResponseDto> {
    @Override
    public MovimentoDepositoResponseDto convert(Movimento entity) {
        MovimentoDepositoResponseDto dto = new MovimentoDepositoResponseDto();

        dto.setId(entity.getId());
        dto.setDataHora(entity.getDataHora());
        dto.setTipoContabil(entity.getTipoContabil());
        dto.setTipoMovimento(entity.getTipoMovimento());
        dto.setValor(entity.getValor());

        dto.setDepositoId(entity.getDeposito().getId());
        dto.setDepositoContaOrigemNumero(entity.getDeposito().getContaOrigem().getNumero());
        dto.setDepositoContaOrigemAgencia(entity.getDeposito().getContaOrigem().getAgencia());
        dto.setDepositoTipoDeposito(entity.getDeposito().getTipoDeposito());

        return dto;
    }
}
