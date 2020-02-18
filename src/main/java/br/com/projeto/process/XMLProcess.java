package br.com.projeto.process;

import br.com.projeto.functions.FunctionalMap;
import br.com.projeto.payment.PaymentAdapter;
import br.com.projeto.payment.Payments;
import br.com.projeto.process.xml.document.DocumentFactory;
import br.com.projeto.process.xml.document.DocumentWrapper;
import br.com.projeto.utils.DateUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.stream.Collectors;

final class XMLProcess extends FileProcessImpl {

    private static final String ROOT = "record";
    private static final String CLIENT_ID = "id_cliente";
    private static final String PAYMENT = "pagamento";
    private static final String PAYMENT_DATE = "data";
    private static final String PAYMENT_TIME = "horario";

    private final DocumentWrapper documentWrapper;

    XMLProcess(final Payments payments) {
        super(payments);
        documentWrapper = DocumentFactory.get();
    }

    @Override
    public Collection<PaymentAdapter> process(final String rawStringFile) {

        final Document document = documentWrapper.builder(rawStringFile);

        final NodeList nodeList = document.getElementsByTagName(ROOT);

        final FunctionalMap<Element> functionalMap = new ElementMap(nodeList);

        return functionalMap.map(this::build).collect(Collectors.toSet());

    }

    private PaymentAdapter build(final Element element) {

        return
                new PaymentAdapter(
                        Integer.valueOf(element.getElementsByTagName(CLIENT_ID).item(0).getTextContent()),
                        new BigDecimal(element.getElementsByTagName(PAYMENT).item(0).getTextContent()),
                        DateUtil.rawStringToLocalDate(element.getElementsByTagName(PAYMENT_DATE).item(0).getTextContent()),
                        DateUtil.rawStringToLocalTime(element.getElementsByTagName(PAYMENT_TIME).item(0).getTextContent()));

    }

    private static class ElementMap extends FunctionalMap<Element> {

        private final NodeList nodeList;

        private final int length;

        private int currentIndex;

        public ElementMap(final NodeList nodeList) {
            this.nodeList = nodeList;
            length = nodeList.getLength();
            currentIndex = 0;
        }

        @Override
        protected boolean hasNext() {
            return currentIndex < length;
        }

        @Override
        protected Element next() {
            return (Element) nodeList.item(currentIndex++);
        }

    }

}
