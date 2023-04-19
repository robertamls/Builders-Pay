package com.roberta.builderpay.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthRequest {
    private String client_id;
    private String client_secret;

}