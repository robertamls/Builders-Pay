package com.roberta.builderpay.services;

import com.roberta.builderpay.payload.request.AuthRequest;
import com.roberta.builderpay.payload.response.AuthResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class AuthService {
    @Value("${url.auth}")
    private String url;

    @Value("${cred.id}")
    private String clientId;

    @Value("${cred.secret}")
    private String clientSecret;

    /**
     * Consome a API de autenticação para obtenção do token de acesso
     * @return AuthResponse com o objetivo de pegar o token
     */
    public String authenticateClient() {
        AuthRequest authRequest = new AuthRequest(clientId, clientSecret);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<AuthResponse> response = restTemplate.postForEntity(url, authRequest, AuthResponse.class);
        return Objects.requireNonNull(response.getBody()).getToken();
    }
}
