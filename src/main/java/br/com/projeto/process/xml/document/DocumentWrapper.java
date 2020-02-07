package br.com.projeto.process.xml.document;

import org.w3c.dom.Document;

public interface DocumentWrapper {

    Document builder(String rawString);

}
