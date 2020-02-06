package br.com.projeto.payment;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

/**
 * @author jefferson
 */
public interface Payments {

    /**
     * @param paymentAdapters Collection of Payment Adapter
     * @return map with key : client id and value : total payment
     */
    Map<Integer, BigDecimal> constructPaymentMapByClient(final Collection<PaymentAdapter> paymentAdapters);

}
