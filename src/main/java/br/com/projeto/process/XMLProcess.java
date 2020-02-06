package br.com.projeto.process;

import br.com.projeto.payment.PaymentAdapter;
import br.com.projeto.payment.Payments;
import br.com.projeto.utils.DateUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

final class XMLProcess extends FileProcessImpl {

    private static final String ROOT = "record";
    private static final String CLIENT_ID = "id_cliente";
    private static final String PAYMENT = "pagamento";
    private static final String PAYMENT_DATE = "data";
    private static final String PAYMENT_TIME = "horario";

    XMLProcess(final Payments payments) {
        super(payments);
    }

    @Override
    public Collection<PaymentAdapter> process(final String rawStringFile) {

        final DocumentBuilderFactory instance = DocumentBuilderFactory.newInstance();

        try {

            final Document document = instance.newDocumentBuilder().parse(new InputSource(new StringReader(rawStringFile)));

            final NodeList nodeList = document.getElementsByTagName(ROOT);

            final ArrayList<PaymentAdapter> paymentAdapters = new ArrayList<>();

            int i = 0;

            while (i < nodeList.getLength()) {

                final Element element = (Element) nodeList.item(i);

                final PaymentAdapter paymentAdapter = new PaymentAdapter(
                        Integer.valueOf(element.getElementsByTagName(CLIENT_ID).item(0).getTextContent()),
                        new BigDecimal(element.getElementsByTagName(PAYMENT).item(0).getTextContent()),
                        DateUtil.rawStringToLocalDate(element.getElementsByTagName(PAYMENT_DATE).item(0).getTextContent()),
                        DateUtil.rawStringToLocalTime(element.getElementsByTagName(PAYMENT_TIME).item(0).getTextContent())
                );

                paymentAdapters.add(paymentAdapter);

                i++;

            }

            return paymentAdapters;

        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new IllegalArgumentException();
        }

    }
}
