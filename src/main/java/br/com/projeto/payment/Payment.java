package br.com.projeto.payment;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * This class should be stateless
 *
 * @author jefferson
 */
final class Payment implements Payments {

    @Override
    public Map<Integer, BigDecimal> constructPaymentMapByClient(final Collection<PaymentAdapter> paymentAdapters) {

        final HashMap<Integer, BigDecimal> map = new HashMap<>();

        paymentAdapters.forEach(paymentAdapter -> construct(map, paymentAdapter));

        return map;

    }

    private void construct(final HashMap<Integer, BigDecimal> map, final PaymentAdapter paymentAdapter) {

        final Integer clientId = paymentAdapter.getClientId();

        map.put(clientId, map.getOrDefault(clientId, BigDecimal.ZERO).add(paymentAdapter.getValue()));

    }

}
