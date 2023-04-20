package com.roberta.builderpay.payload.response;

import lombok.Data;

@Data
public class BankSlipResponse {
    private String code;
    private String due_date;
    private double amount;
    private String recipient_name;
    private String recipient_document;
    private String type;
}
