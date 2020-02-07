package br.com.projeto.process;

import br.com.projeto.payment.PaymentAdapter;
import br.com.projeto.payment.Payments;
import br.com.projeto.utils.DateUtil;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

final class SVProcess extends FileProcessImpl {

    private static final String NEW_LINE = "\n";
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
    public Collection<PaymentAdapter> process(String rawStringFile) {

        final String[] strings = rawStringFile.split(NEW_LINE);

        return Arrays.stream(strings).map(this::buildPayment).collect(Collectors.toList());

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
