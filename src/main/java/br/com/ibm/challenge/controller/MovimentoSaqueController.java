package br.com.ibm.challenge.controller;

import br.com.ibm.challenge.converter.MovimentoSaqueRequestConverter;
import br.com.ibm.challenge.converter.MovimentoSaqueResponseConverter;
import br.com.ibm.challenge.domain.enumeration.TipoMovimentoEnum;
import br.com.ibm.challenge.domain.exception.BusinessException;
import br.com.ibm.challenge.domain.movimento.Movimento;
import br.com.ibm.challenge.dto.MovimentoSaqueRequestDto;
import br.com.ibm.challenge.dto.MovimentoSaqueResponseDto;
import br.com.ibm.challenge.service.MovimentoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
public class MovimentoSaqueController {

    private MovimentoService service;
    private MovimentoSaqueRequestConverter requestConverter;
    private MovimentoSaqueResponseConverter responseConverter;

    public MovimentoSaqueController(
            MovimentoService service,
            MovimentoSaqueRequestConverter requestConverter,
            MovimentoSaqueResponseConverter responseConverter) {
        this.service = service;
        this.requestConverter = requestConverter;
        this.responseConverter = responseConverter;
    }

    @PostMapping("/movimento/saque")
    @ResponseStatus(HttpStatus.CREATED)
    public MovimentoSaqueResponseDto criarSaque(@Valid @RequestBody MovimentoSaqueRequestDto request) {
        Movimento movimento = requestConverter.convert(request);
        service.processar(TipoMovimentoEnum.SAQUE, movimento);
        return responseConverter.convert(movimento);
    }

}
