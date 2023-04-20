package com.roberta.builderpay.payload.request;

import lombok.Data;

@Data
public class CalculateRequest {
    private String bar_code;
    private String payment_date;
}
