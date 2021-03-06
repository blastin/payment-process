package br.com.projeto.process;

import br.com.projeto.payment.PaymentAdapter;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

public interface FileProcess {

    Map<Integer, BigDecimal> rawStringFileToClientMapByTotalPayment(final String rawStringFile);

    Collection<PaymentAdapter> process(final String rawStringFile);
}
