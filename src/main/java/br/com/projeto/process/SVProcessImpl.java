package br.com.projeto.process;

import br.com.projeto.payment.PaymentAdapter;
import br.com.projeto.payment.Payments;
import br.com.projeto.utils.DateUtil;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

final class SVProcessImpl extends FileProcessImpl {

    private static final String NEW_LINE = "\n";
    private static final int CLIENT_ID = 0;
    private static final int PAYMENT = 1;
    private static final int PAYMENT_DATE = 2;
    private static final int PAYMENT_TIME = 3;

    private final String delimiter;

    SVProcessImpl(final Payments payments, final String delimiter) {
        super(payments);
        this.delimiter = delimiter;
    }

    @Override
    public Collection<PaymentAdapter> process(String rawStringFile) {

        final String[] strings = rawStringFile.split(NEW_LINE);

        return Arrays.stream(strings).map(line -> {

            final String[] payments = line.split(delimiter);

            return new PaymentAdapter(
                    Integer.valueOf(payments[CLIENT_ID]),
                    new BigDecimal(payments[PAYMENT]),
                    DateUtil.localDateStringToLocalDate(payments[PAYMENT_DATE]),
                    DateUtil.localTimeStringToLocalDate(payments[PAYMENT_TIME]));

        }).collect(Collectors.toList());

    }

}
