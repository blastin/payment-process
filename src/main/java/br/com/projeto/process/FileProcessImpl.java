package br.com.projeto.process;

import br.com.projeto.payment.PaymentAdapter;
import br.com.projeto.payment.Payments;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

abstract class FileProcessImpl implements FileProcess {

    private final Payments payments;

    protected FileProcessImpl(final Payments payments) {
        this.payments = payments;
    }

    @Override
    public Map<Integer, BigDecimal> rawStringFileToClientMapByTotalPayment(final String rawStringFile) {

        final Collection<PaymentAdapter> paymentAdapters = process(rawStringFile);

        return payments.constructPaymentMapByClient(paymentAdapters);

    }

}
