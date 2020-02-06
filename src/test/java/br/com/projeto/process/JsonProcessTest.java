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

public class JsonProcessTest {

    @Test
    public void whenJsonProcessBuilderAClientMapByTotalPayment() throws IOException {

        final Payments payments = PaymentFactory.get();

        final String fileName = "pagamento-tipo-json-14-pessoas.json";

        final String rawStringFile = FileUtil.toString("json/".concat(fileName));

        final FileProcess fileProcess = FileProcessFactory.get(fileName, payments);

        final Map<Integer, BigDecimal> map = fileProcess.rawStringFileToClientMapByTotalPayment(rawStringFile);

        Assert.assertEquals("Must be 14 clients", 14, map.size());

        Assert.assertEquals(
                "Client number 1 must be total payment equals",
                BigDecimal.valueOf(179.08).setScale(2, RoundingMode.CEILING),
                map.get(1)
        );

        Assert.assertEquals(
                "Client number 13 must be total payment equals",
                BigDecimal.valueOf(157.14).setScale(2, RoundingMode.CEILING),
                map.get(13)
        );

        Assert.assertEquals(
                "Client number 4 must be total payment equals",
                BigDecimal.valueOf(137.71).setScale(2, RoundingMode.CEILING),
                map.get(4)
        );

    }

}
