package com.roberta.builderpay.payload.request;

import lombok.Data;

@Data
public class CalculateRequest {
    public String bar_code;
    public String payment_date;
}
