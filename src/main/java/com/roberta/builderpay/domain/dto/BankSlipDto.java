package com.roberta.builderpay.domain.dto;

import lombok.Data;

@Data
public class BankSlipDto {
    private double original_amount;
    private double amount;
    private String due_date;
    private String payment_date;
    private double interest_amount_calculated;
    private double fine_amount_calculated;
}
