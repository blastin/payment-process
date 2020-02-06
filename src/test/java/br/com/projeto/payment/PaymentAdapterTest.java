package br.com.projeto.payment;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class PaymentAdapterTest {

    @Test
    public void whenConstructOneObjectPaymentAdapter() {

        final LocalDate paymentDate = LocalDate.now();

        final LocalTime paymentTime = LocalTime.now();

        final Integer clientId = 1;

        final BigDecimal value = BigDecimal.TEN;

        final PaymentAdapter paymentAdapter = new PaymentAdapter(clientId, value, paymentDate, paymentTime);

        Assert.assertEquals("Client id Should be equals", clientId, paymentAdapter.getClientId());

        Assert.assertEquals("Value Payment Should be equals", value, paymentAdapter.getValue());

        Assert.assertEquals("Payment Date Should be equals", paymentDate, paymentAdapter.getPaymentDate());

        Assert.assertEquals("Payment Time Should be equals", paymentTime, paymentAdapter.getPaymentTime());

    }
}
