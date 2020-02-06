package br.com.projeto.payment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author jefferson
 */
public final class PaymentAdapter {

    private final Integer clientId;

    private final BigDecimal value;

    private final LocalDate paymentDate;

    private final LocalTime paymentTime;

    public PaymentAdapter(final Integer clientId, final BigDecimal value, final LocalDate paymentDate, final LocalTime paymentTime) {
        this.clientId = clientId;
        this.value = value;
        this.paymentDate = paymentDate;
        this.paymentTime = paymentTime;
    }

    public Integer getClientId() {
        return clientId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public LocalTime getPaymentTime() {
        return paymentTime;
    }

}
