package com.roberta.builderpay.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FormatDouble {

    /**
     * Formata o valor para duas casas decimais
     * @param value
     * @return valor formatado
     */
    public static double formatDoubles(double value) {
        BigDecimal bd = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
