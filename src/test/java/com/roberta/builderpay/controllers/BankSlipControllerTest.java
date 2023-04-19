package com.roberta.builderpay.controllers;

import com.roberta.builderpay.domain.dto.BankSlipDto;
import com.roberta.builderpay.payload.request.BankSlipRequest;
import com.roberta.builderpay.payload.request.CalculateRequest;
import com.roberta.builderpay.payload.response.BankSlipResponse;
import com.roberta.builderpay.services.AuthService;
import com.roberta.builderpay.services.BankSlipService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
class BankSlipControllerTest {

    @InjectMocks
    private BankSlipController bankSlipController;

    @Mock
    private AuthService authService;

    @Mock
    private BankSlipService bankSlipService;

    //<editor-fold desc="Cenario de teste - Dados válidos (Passa se a resposta for um BankSlipDto)">
    private static final String token = "68e326df-6526-4cab-b25a-44534105a9e3"; //Trocar token a cada uma hora
    private static final String code = "34191790010104351004791020150008291070026000";
    private static final String dueDate = "2022-09-03";
    private static final double originalAmount = 260.0;
    private static final String recipientName = "Microhouse Informática S/C Ltda";
    private static final String recipientDocument = "83698283000114";
    private static final String type = "NPC";
    private static final double amountUpdated = 280.64;
    private static final String paymentDate = "2022-09-21";
    private static final double interestCalculated = 15.44;
    private static final double fineCalculated = 5.2;
    //</editor-fold>

    private CalculateRequest calculateRequest;
    private BankSlipRequest bankSlipRequest;
    private BankSlipResponse bankSlipResponse;
    private BankSlipDto bankSlipDto;

    @BeforeEach
    public void setUp() {
        bankSlipController = new BankSlipController();
        MockitoAnnotations.openMocks(this);
        startData();
    }

    private void startData() {

        calculateRequest = new CalculateRequest();
        calculateRequest.setBar_code(code);
        calculateRequest.setPayment_date(paymentDate);

        bankSlipRequest = new BankSlipRequest();
        bankSlipRequest.setCode(code);

        bankSlipResponse = new BankSlipResponse();
        bankSlipResponse.setCode(code);
        bankSlipResponse.setDue_date(dueDate);
        bankSlipResponse.setAmount(originalAmount);
        bankSlipResponse.setRecipient_name(recipientName);
        bankSlipResponse.setRecipient_document(recipientDocument);
        bankSlipResponse.setType(type);

        bankSlipDto = new BankSlipDto();
        bankSlipDto.setOriginal_amount(originalAmount);
        bankSlipDto.setAmount(amountUpdated);
        bankSlipDto.setDue_date(dueDate);
        bankSlipDto.setPayment_date(paymentDate);
        bankSlipDto.setInterest_amount_calculated(interestCalculated);
        bankSlipDto.setFine_amount_calculated(fineCalculated);
    }

    @Test
    void calculateRates() {
        Mockito.when(authService.authenticateClient()).thenReturn(token);
        Mockito.when(bankSlipService.searchBankSlip(bankSlipRequest, token)).thenReturn(bankSlipResponse);
        Mockito.when(bankSlipService.generateRates(bankSlipResponse, paymentDate)).thenReturn(bankSlipDto);

        ResponseEntity<?> response = bankSlipController.calculateRates(calculateRequest);

        Assertions.assertEquals(BankSlipDto.class, response.getBody().getClass());

    }

    @Test
    void allCalculations(){
        Assertions.assertDoesNotThrow(() -> bankSlipController.allCalculations());

    }
}