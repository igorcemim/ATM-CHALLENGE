package br.com.ibm.challenge.controller;

import br.com.ibm.challenge.domain.exception.BusinessException;
import br.com.ibm.challenge.domain.exception.EntityNotFoundException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class ApiControllerAdvice {

    @Data
    class ApiError {
        private Date timestamp;
        private int status;
        private String error;
        private String message;
        private Map<String, List<String>> errors;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage(), ex);

        HttpStatus status = HttpStatus.BAD_REQUEST;

        ApiError body = new ApiError();
        body.setTimestamp(new Date());
        body.setStatus(status.value());
        body.setError(status.getReasonPhrase());

        Map<String, List<String>> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(
                        Collectors.groupingBy(
                                FieldError::getField,
                                Collectors.mapping(f -> f.getDefaultMessage(), Collectors.toList())
                        )
                );

        body.setErrors(errors);

        return new ResponseEntity<>(body, null, status);
    }

    @ExceptionHandler({ BusinessException.class })
    public ResponseEntity<ApiError> handleRuntime(Exception ex) {
        log.error(ex.getMessage(), ex);

        HttpStatus status = HttpStatus.BAD_REQUEST;

        ApiError body = new ApiError();
        body.setTimestamp(new Date());
        body.setStatus(status.value());
        body.setError(status.getReasonPhrase());
        body.setMessage(ex.getMessage());

        return new ResponseEntity<>(body, null, status);
    }

    @ExceptionHandler({ EntityNotFoundException.class })
    public ResponseEntity<ApiError> handleEntityNotFound(Exception ex) {
        log.error(ex.getMessage(), ex);

        HttpStatus status = HttpStatus.NOT_FOUND;

        ApiError body = new ApiError();
        body.setTimestamp(new Date());
        body.setStatus(status.value());
        body.setError(status.getReasonPhrase());
        body.setMessage(ex.getMessage());

        return new ResponseEntity<>(body, null, status);
    }

}
