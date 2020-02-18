package br.com.projeto.process;

import br.com.projeto.payment.PaymentAdapter;
import br.com.projeto.payment.Payments;
import br.com.projeto.utils.DateUtil;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.stream.Collectors;

final class SVProcess extends FileProcessImpl {

    private static final int CLIENT_ID = 0;
    private static final int PAYMENT = 1;
    private static final int PAYMENT_DATE = 2;
    private static final int PAYMENT_TIME = 3;

    private final String delimiter;

    SVProcess(final Payments payments, final String delimiter) {
        super(payments);
        this.delimiter = delimiter;
    }

    @Override
    public Collection<PaymentAdapter> process(final String rawStringFile) {

        return rawStringFile.lines().map(this::buildPayment).collect(Collectors.toSet());

    }

    private PaymentAdapter buildPayment(final String line) {

        final String[] payments = line.split(delimiter);

        return new PaymentAdapter(
                Integer.valueOf(payments[CLIENT_ID]),
                new BigDecimal(payments[PAYMENT]),
                DateUtil.rawStringToLocalDate(payments[PAYMENT_DATE]),
                DateUtil.rawStringToLocalTime(payments[PAYMENT_TIME]));

    }

}
