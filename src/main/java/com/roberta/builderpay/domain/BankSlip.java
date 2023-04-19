package com.roberta.builderpay.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import java.io.Serializable;

@Document("bankslip")
@Data
@NoArgsConstructor
public class BankSlip implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private double original_amount;
    private double amount;
    private String due_date;
    private String payment_date;
    private double interest_amount_calculated;
    private double fine_amount_calculated;

}
