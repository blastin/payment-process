package br.com.projeto.process;

import br.com.projeto.payment.PaymentFactory;
import br.com.projeto.payment.Payments;
import br.com.projeto.utils.FileUtil;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class CSVProcessTest {

    @Test
    public void whenCSVProcessBuilderThreeClientMapByTotalPayment() throws IOException {

        final Payments payments = PaymentFactory.get();

        final String fileName = "pagamento-tipo-csv-3-pessoas.csv";

        final String rawStringFile = FileUtil.toString("csv/".concat(fileName));

        final FileProcess fileProcess = FileProcessFactory.get(fileName, payments);

        final Map<Integer, BigDecimal> map = fileProcess.rawStringFileToClientMapByTotalPayment(rawStringFile);

        Assert.assertEquals("Must be 3 clients", 3, map.size());

        Assert.assertEquals(
                "Client number 1 must be total payment equals",
                BigDecimal.valueOf(147.74).setScale(2, RoundingMode.CEILING),
                map.get(1)
        );

        Assert.assertEquals(
                "Client number 2 must be total payment equals",
                BigDecimal.valueOf(194.86).setScale(2, RoundingMode.CEILING),
                map.get(2)
        );

        Assert.assertEquals(
                "Client number 3 must be total payment equals",
                BigDecimal.valueOf(206.94).setScale(2, RoundingMode.CEILING),
                map.get(3)
        );

    }

    @Test
    public void whenCSVProcessBuilderClientSMapByTotalPayment() throws IOException {

        final Payments payments = PaymentFactory.get();

        final String fileName = "pagamento-tipo-csv-28-01-2020.csv";

        final String rawStringFile = FileUtil.toString("csv/".concat(fileName));

        final FileProcess fileProcess = FileProcessFactory.get(fileName, payments);

        final Map<Integer, BigDecimal> map = fileProcess.rawStringFileToClientMapByTotalPayment(rawStringFile);

        Assert.assertEquals("Must be 1000 clients", 1000, map.size());
        
    }

}
