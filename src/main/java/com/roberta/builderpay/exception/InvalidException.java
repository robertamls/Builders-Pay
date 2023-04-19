package com.roberta.builderpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidException extends RuntimeException {

    /**
     * Exception criada para personalizar menasgens de boletos inválidos
     * @param message
     */
    public InvalidException(String message) {
        super(message);
    }
}
