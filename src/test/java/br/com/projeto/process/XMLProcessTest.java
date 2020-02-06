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

public class XMLProcessTest {

    @Test
    public void whenXMLProcessBuilderSixClientsMapByTotalPayment() throws IOException {

        final Payments payments = PaymentFactory.get();

        final String fileName = "pagamento-tipo-xml-6-pessoas.xml";

        final String rawStringFile = FileUtil.toString("xml/".concat(fileName));

        final FileProcess fileProcess = FileProcessFactory.get(fileName, payments);

        final Map<Integer, BigDecimal> map = fileProcess.rawStringFileToClientMapByTotalPayment(rawStringFile);

        Assert.assertEquals("Must be 6 clients", 6, map.size());

        Assert.assertEquals(
                "Client number 2 must be total payment equals",
                BigDecimal.valueOf(286.36).setScale(2, RoundingMode.CEILING),
                map.get(2)
        );

    }

}
