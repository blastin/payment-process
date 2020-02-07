package br.com.projeto.utils;

import br.com.projeto.functions.FunctionalMap;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * @author jefferson
 */
public final class ElementMap extends FunctionalMap<Element> {

    private final NodeList nodeList;

    private final int length;

    private int currentIndex;

    public ElementMap(NodeList nodeList) {
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
