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

public class TSVProcessTest {

    @Test
    public void whenTSVProcessBuilderClientsMapByTotalPayment() throws IOException {

        final Payments payments = PaymentFactory.get();

        final String fileName = "pagamento-tipo-tsv-5-pessoas.tsv";

        final String rawStringFile = FileUtil.toString("tsv/".concat(fileName));

        final FileProcess fileProcess = FileProcessFactory.get(fileName, payments);

        final Map<Integer, BigDecimal> map = fileProcess.rawStringFileToClientMapByTotalPayment(rawStringFile);

        Assert.assertEquals("Must be 5 clients", 5, map.size());

        Assert.assertEquals(
                "Client number 1 must be total payment equals",
                BigDecimal.valueOf(51.63).setScale(2, RoundingMode.CEILING),
                map.get(1)
        );

        Assert.assertEquals(
                "Client number 2 must be total payment equals",
                BigDecimal.valueOf(846.48).setScale(2, RoundingMode.CEILING),
                map.get(2)
        );

        Assert.assertEquals(
                "Client number 3 must be total payment equals",
                BigDecimal.valueOf(172.36).setScale(2, RoundingMode.CEILING),
                map.get(3)
        );

        Assert.assertEquals(
                "Client number 4 must be total payment equals",
                BigDecimal.valueOf(237.10).setScale(2, RoundingMode.CEILING),
                map.get(4)
        );

        Assert.assertEquals(
                "Client number 5 must be total payment equals",
                BigDecimal.valueOf(630.78).setScale(2, RoundingMode.CEILING),
                map.get(5)
        );
    }

}
