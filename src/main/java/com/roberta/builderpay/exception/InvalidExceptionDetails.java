package com.roberta.builderpay.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class InvalidExceptionDetails {
    private String title;
    private int status;
    private String message;
    private LocalDateTime timestamp;
}
