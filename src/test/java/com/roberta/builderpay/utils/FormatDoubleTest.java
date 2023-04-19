package com.roberta.builderpay.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class FormatDoubleTest {

    private FormatDouble formatDouble;

    @BeforeEach
    public void setUp() {
        formatDouble = new FormatDouble();
    }

    @Test
    void formatDoublesT1() {
    double value = FormatDouble.formatDoubles(9.43);
        Assertions.assertEquals(9.43, value);
    }


    @Test
    void formatDoublesT2() {
        double value = FormatDouble.formatDoubles(5.071);
        Assertions.assertEquals(5.07, value);
    }


    @Test
    void formatDoublesT3() {
        double value = FormatDouble.formatDoubles(5.666);
        Assertions.assertEquals(5.67, value);
    }

    @Test
    void formatDoublesT4() {
        double value = FormatDouble.formatDoubles(3.1234);
        Assertions.assertEquals(3.12, value);
    }
}