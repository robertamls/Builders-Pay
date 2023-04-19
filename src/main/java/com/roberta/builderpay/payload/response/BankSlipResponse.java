package com.roberta.builderpay.payload.response;

import lombok.Data;

@Data
public class BankSlipResponse {
    public String code;
    public String due_date;
    public double amount;
    public String recipient_name;
    public String recipient_document;
    public String type;
}
