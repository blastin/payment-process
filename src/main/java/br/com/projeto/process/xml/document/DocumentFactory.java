package br.com.projeto.process.xml.document;

/**
 * @author jefferson
 */
public final class DocumentFactory {

    public static DocumentWrapper get() {
        return new DocumentWrapperImpl();
    }

    private DocumentFactory() {
    }

}
