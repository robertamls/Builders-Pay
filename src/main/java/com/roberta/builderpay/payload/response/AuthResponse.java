package com.roberta.builderpay.payload.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AuthResponse {
    private String token;
    private LocalDateTime expiration;
}
