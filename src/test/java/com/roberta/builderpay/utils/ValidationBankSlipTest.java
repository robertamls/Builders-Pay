package com.roberta.builderpay.utils;

import com.roberta.builderpay.exception.InvalidException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ValidationBankSlipTest {
    private ValidationBankSlip validationBankSlip;

    @BeforeEach
    public void setUp() {
        validationBankSlip = new ValidationBankSlip();
    }

    //<editor-fold desc="Cenário T1 - Dados válidos">
    private final String T1_CODE = "34191790010104351004791020150008291070026000";
    private final String T1_DUE = "2022-09-03";
    private final String T1_PAYMENT = "2022-11-03";
    private final String T1_TYPE = "NPC";
    //</editor-fold>

    //<editor-fold desc="Cenário T2 - Dados inválidos, code possui um caracter a mais">
    private final String T2_CODE = "341917900101043510047910201500082910700260000";
    private final String T2_DUE = "2022-09-03";
    private final String T2_PAYMENT = "2022-11-03";
    private final String T2_TYPE = "NPC";
    //</editor-fold>

    //<editor-fold desc=" Cenário T3 - Dados inválidos, code vazio">
    private final String T3_CODE = "";
    private final String T3_DUE = "2022-09-03";
    private final String T3_PAYMENT = "2022-11-03";
    private final String T3_TYPE = "NPC";
    //</editor-fold>

    //<editor-fold desc=" Cenário T4 - Dados inválidos, code possui letra">
    private final String T4_CODE = "34191790010104351004791020150008291070026o000";
    private final String T4_DUE = "2022-09-03";
    private final String T4_PAYMENT = "2022-11-03";
    private final String T4_TYPE = "NPC";
    //</editor-fold>

    //<editor-fold desc=" Cenário T5 - Dados inválidos, boleto não está vencido">
    private final String T5_CODE = "34191790010104351004791020150008291070026000";
    private final String T5_DUE = "2022-09-03";
    private final String T5_PAYMENT = "2022-08-03";
    private final String T5_TYPE = "NPC";
    //</editor-fold>

    //<editor-fold desc=" Cenário T6 - Dados inválidos, tipo de boleto normal">
    private final String T6_CODE = "34191790010104351004791020150008291070026000";
    private final String T6_DUE = "2022-09-03";
    private final String T6_PAYMENT = "2022-11-03";
    private final String T6_TYPE = "NORMAL";
    //</editor-fold>

    //<editor-fold desc=" Cenário T7 - Dados inválidos, tipo de boleto em branco">
    private final String T7_CODE = "34191790010104351004791020150008291070026000";
    private final String T7_DUE = "2022-09-03";
    private final String T7_PAYMENT = "2022-11-03";
    private final String T7_TYPE = "";
    //</editor-fold>

    //<editor-fold desc=" Cenário T8 - Dados inválidos, boleto possui caracter a mais, não está vencido e tipo normal">
    private final String T8_CODE = "341917900101043510047910201500082910700260000";
    private final String T8_DUE = "2022-09-03";
    private final String T8_PAYMENT = "2022-07-03";
    private final String T8_TYPE = "NORMAL";
    //</editor-fold>

    //<editor-fold desc="Testes Cenário T1">
    @Test
    void isBankSlipValidT1() {
        ValidationBankSlip.isBankSlipValid(T1_CODE);
    }

    @Test
    void isBankSlipExpiredT1() {
        ValidationBankSlip.isBankSlipExpired(T1_DUE, T1_PAYMENT);
    }

    @Test
    void isBankSlipNPCT1() {
        ValidationBankSlip.isBankSlipNPC(T1_TYPE);
    }
    //</editor-fold>

    //<editor-fold desc="Testes Cenário T2">
    @Test
    void isBankSlipValidT2() {
        Assertions.assertThrows(InvalidException.class, () -> ValidationBankSlip.isBankSlipValid(T2_CODE));
    }

    @Test
    void isBankSlipExpiredT2() {
        ValidationBankSlip.isBankSlipExpired(T2_DUE, T2_PAYMENT);
    }

    @Test
    void isBankSlipNPCT2() {
        ValidationBankSlip.isBankSlipNPC(T2_TYPE);
    }
    //</editor-fold>

    //<editor-fold desc="Testes Cenário T3">
    @Test
    void isBankSlipValidT3() {
        Assertions.assertThrows(InvalidException.class, () -> ValidationBankSlip.isBankSlipValid(T3_CODE));
    }

    @Test
    void isBankSlipExpiredT3() {
        ValidationBankSlip.isBankSlipExpired(T3_DUE, T3_PAYMENT);
    }

    @Test
    void isBankSlipNPCT3() {
        ValidationBankSlip.isBankSlipNPC(T3_TYPE);
    }
    //</editor-fold>

    //<editor-fold desc="Testes Cenário T4">
    @Test
    void isBankSlipValidT4() {
        Assertions.assertThrows(InvalidException.class, () -> ValidationBankSlip.isBankSlipValid(T4_CODE));
    }

    @Test
    void isBankSlipExpiredT4() {
        ValidationBankSlip.isBankSlipExpired(T4_DUE, T4_PAYMENT);
    }

    @Test
    void isBankSlipNPCT4() {
        ValidationBankSlip.isBankSlipNPC(T4_TYPE);
    }
    //</editor-fold>

    //<editor-fold desc="Testes Cenário T5">
    @Test
    void isBankSlipValidT5() {
        ValidationBankSlip.isBankSlipValid(T5_CODE);
    }

    @Test
    void isBankSlipExpiredT5() {
        Assertions.assertThrows(RuntimeException.class, () -> ValidationBankSlip.isBankSlipExpired(T5_DUE, T5_PAYMENT));
    }

    @Test
    void isBankSlipNPCT5() {
        ValidationBankSlip.isBankSlipNPC(T5_TYPE);
    }
    //</editor-fold>

    //<editor-fold desc="Testes Cenário T6">
    @Test
    void isBankSlipValidT6() {
        ValidationBankSlip.isBankSlipValid(T6_CODE);
    }

    @Test
    void isBankSlipExpiredT6() {
        ValidationBankSlip.isBankSlipExpired(T6_DUE, T6_PAYMENT);
    }

    @Test
    void isBankSlipNPCT6() {
        Assertions.assertThrows(InvalidException.class, () -> ValidationBankSlip.isBankSlipNPC(T6_TYPE));
    }
    //</editor-fold>

    //<editor-fold desc="Testes Cenário T7">
    @Test
    void isBankSlipValidT7() {
        ValidationBankSlip.isBankSlipValid(T7_CODE);
    }

    @Test
    void isBankSlipExpiredT7() {
        ValidationBankSlip.isBankSlipExpired(T7_DUE, T7_PAYMENT);
    }

    @Test
    void isBankSlipNPCT7() {
        Assertions.assertThrows(InvalidException.class, () -> ValidationBankSlip.isBankSlipNPC(T7_TYPE));

    }
    //</editor-fold>

    //<editor-fold desc="Testes Cenário T8">
    @Test
    void isBankSlipValidT8() {
        Assertions.assertThrows(InvalidException.class, () -> ValidationBankSlip.isBankSlipValid(T8_CODE));
    }

    @Test
    void isBankSlipExpiredT8() {
        Assertions.assertThrows(RuntimeException.class, () -> ValidationBankSlip.isBankSlipExpired(T8_DUE, T8_PAYMENT));
    }

    @Test
    void isBankSlipNPCT8() {
        Assertions.assertThrows(InvalidException.class, () -> ValidationBankSlip.isBankSlipNPC(T8_TYPE));
    }
    //</editor-fold>
}
