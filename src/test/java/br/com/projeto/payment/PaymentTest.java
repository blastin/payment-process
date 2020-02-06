package br.com.projeto.payment;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * @author jefferson
 */
public class PaymentTest {

    private Payments payments = PaymentFactory.get();

    @Test
    public void whenGetMapOfClientWithTotalPayment() {

        final int clientId = 1;

        final BigDecimal value = BigDecimal.TEN;

        final Collection<PaymentAdapter> collection = Collections.singletonList(new PaymentAdapter(clientId, value, LocalDate.now(), LocalTime.now()));

        final Map<Integer, BigDecimal> map = payments.constructPaymentMapByClient(collection);

        Assert.assertNotNull("should be not null", map);

        Assert.assertEquals("Should return only a single object", 1, map.size());

        Assert.assertTrue("Must have key equals", map.containsKey(clientId));

        Assert.assertEquals("Total value should be equals", value, map.get(clientId));

    }

    @Test
    public void whenGetMapOfClientsWithTotalPayment() {

        final int clientId = 1;

        final BigDecimal value = BigDecimal.TEN;

        final LocalDate paymentDate = LocalDate.now();

        final LocalTime paymentTime = LocalTime.now();

        final PaymentAdapter A = new PaymentAdapter(clientId, value, paymentDate, paymentTime);

        final PaymentAdapter B = new PaymentAdapter(clientId, value, paymentDate, paymentTime.plusMinutes(2));

        final Collection<PaymentAdapter> collection = Arrays.asList(A, B);

        final Map<Integer, BigDecimal> map = payments.constructPaymentMapByClient(collection);

        Assert.assertNotNull("should be not null", map);

        Assert.assertEquals("Should return only a one object", 1, map.size());

        Assert.assertTrue("Must have key equals", map.containsKey(clientId));

        Assert.assertEquals("Total value should be equals", value.add(value), map.get(clientId));

    }
}
