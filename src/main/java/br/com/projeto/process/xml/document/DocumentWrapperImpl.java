package br.com.projeto.process.xml.document;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

/**
 * @author jefferson
 */
final class DocumentWrapperImpl implements DocumentWrapper {

    @Override
    public Document builder(String rawString) {

        final DocumentBuilderFactory instance = DocumentBuilderFactory.newInstance();

        try {
            return instance.newDocumentBuilder().parse(new InputSource(new StringReader(rawString)));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new IllegalArgumentException();
        }

    }
}
