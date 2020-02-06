package br.com.projeto.process;

import br.com.projeto.payment.PaymentAdapter;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

final class FileProcessProxy implements FileProcess {

    private final FileProcess fileProcess;

    FileProcessProxy(final FileProcess fileProcess) {
        this.fileProcess = fileProcess;
    }

    @Override
    public Map<Integer, BigDecimal> rawStringFileToClientMapByTotalPayment(String rawStringFile) {
        return fileProcess.rawStringFileToClientMapByTotalPayment(rawStringFile);
    }

    @Override
    public Collection<PaymentAdapter> process(final String rawStringFile) {
        throw new RuntimeException("Shouldn't get here");
    }

}
