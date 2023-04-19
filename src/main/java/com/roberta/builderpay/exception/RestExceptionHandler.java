package com.roberta.builderpay.exception;

import com.roberta.builderpay.exception.InvalidException;
import com.roberta.builderpay.exception.InvalidExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(InvalidException.class)
    public ResponseEntity<InvalidExceptionDetails> handlerInvalidExcepetion(InvalidException exception){
        return new ResponseEntity<>(
                InvalidExceptionDetails.builder()
                        .title("Boleto inválido")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message(exception.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build(), HttpStatus.BAD_REQUEST);
    }
}
