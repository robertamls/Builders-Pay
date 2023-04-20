package com.roberta.builderpay.utils;

import com.roberta.builderpay.exception.InvalidException;
import org.joda.time.DateTime;
import org.joda.time.Duration;

public class ValidationBankSlip {

    private static final String regex = "[0-9]{44}";

    /**
     * Valida se a string possui apenas números e 44 caracteres
     *
     * @param code código de barras que será passado no payload da request
     * @throws InvalidException
     */
    public static void bankSlipValidate(String code) {
        if (!code.matches(regex)) {
            throw new InvalidException("Apenas os 44 números do boleto são aceitos.");
        }
    }

    /**
     * Verifica se o boleto está vencido, e caso não esteja retorna os dias vencidos
     *
     * @param dueDate     data de vencimento
     * @param paymentDate data de pagamento
     * @return retorna dias vencidos ou exceção personalizada
     * @throws InvalidException
     * @throws RuntimeException
     */
    public static long bankSlipExpired(String dueDate, String paymentDate) {
        try {
            DateTime due = new DateTime(DateTime.parse(dueDate));
            DateTime payment = new DateTime(DateTime.parse(paymentDate));

            Duration dur = new Duration(due, payment);
            if (!(due.compareTo(payment) < 0)) {
                throw new InvalidException("O boleto não está vencido.");
            }
            return dur.getStandardDays();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Verifica se o boleto é NPC ou normal
     *
     * @param type tipo de boleto obtido pelo bankSlipResponse
     */
    public static void bankSlipType(String type) {
        if (!type.equals("NPC")) {
            throw new InvalidException("O boleto não é do tipo NPC.");
        }
    }
}
