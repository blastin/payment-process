package br.com.projeto.payment;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class should be stateless
 *
 * @author jefferson
 */
final class Payment implements Payments {

    @Override
    public Map<Integer, BigDecimal> constructPaymentMapByClient(final Collection<PaymentAdapter> paymentAdapters) {

        return paymentAdapters
                .stream()
                .collect(Collectors.toMap(PaymentAdapter::getClientId, PaymentAdapter::getValue, BigDecimal::add));
    }

}
