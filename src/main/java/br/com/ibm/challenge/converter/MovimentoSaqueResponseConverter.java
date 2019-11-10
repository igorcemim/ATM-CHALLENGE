package br.com.ibm.challenge.converter;

import br.com.ibm.challenge.domain.movimento.Movimento;
import br.com.ibm.challenge.dto.MovimentoSaqueResponseDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MovimentoSaqueResponseConverter implements Converter<Movimento, MovimentoSaqueResponseDto> {
    @Override
    public MovimentoSaqueResponseDto convert(Movimento entity) {
        MovimentoSaqueResponseDto dto = new MovimentoSaqueResponseDto();

        dto.setId(entity.getId());
        dto.setDataHora(entity.getDataHora());
        dto.setTipoContabil(entity.getTipoContabil());
        dto.setTipoMovimento(entity.getTipoMovimento());
        dto.setValor(entity.getValor());

        dto.setSaqueId(entity.getSaque().getId());
        dto.setSaqueContaOrigemNumero(entity.getSaque().getContaOrigem().getNumero());
        dto.setSaqueContaOrigemAgencia(entity.getSaque().getContaOrigem().getAgencia());

        return dto;
    }
}
