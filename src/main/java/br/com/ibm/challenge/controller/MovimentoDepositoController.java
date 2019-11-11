package br.com.ibm.challenge.controller;

import br.com.ibm.challenge.converter.MovimentoDepositoRequestConverter;
import br.com.ibm.challenge.converter.MovimentoDepositoResponseConverter;
import br.com.ibm.challenge.domain.enumeration.TipoMovimentoEnum;
import br.com.ibm.challenge.domain.movimento.Movimento;
import br.com.ibm.challenge.dto.MovimentoDepositoRequestDto;
import br.com.ibm.challenge.dto.MovimentoDepositoResponseDto;
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
public class MovimentoDepositoController {

    private MovimentoService service;
    private MovimentoDepositoRequestConverter requestConverter;
    private MovimentoDepositoResponseConverter responseConverter;

    public MovimentoDepositoController(
            MovimentoService service,
            MovimentoDepositoRequestConverter requestConverter,
            MovimentoDepositoResponseConverter responseConverter) {
        this.service = service;
        this.requestConverter = requestConverter;
        this.responseConverter = responseConverter;
    }

    @PostMapping("/movimento/deposito")
    @ResponseStatus(HttpStatus.CREATED)
    public MovimentoDepositoResponseDto criarDeposito(@Valid @RequestBody MovimentoDepositoRequestDto request) {
        Movimento movimento = requestConverter.convert(request);
        service.processar(TipoMovimentoEnum.DEPOSITO, movimento);
        return responseConverter.convert(movimento);
    }

}
