package cfm.slingscript;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class HtmlContentHandler implements ContentHandler {
    private static final Logger log = LoggerFactory.getLogger(HtmlContentHandler.class);
    private Locator locator;

    @Override
    public void setDocumentLocator(Locator locator) {
        log.debug("setDocumentLocator: {}", locator);
        this.locator = locator;
    }

    @Override
    public void startDocument() throws SAXException {
        log.debug("startDocument");
    }

    @Override
    public void endDocument() throws SAXException {
        log.debug("endDocument");
    }

    @Override
    public void startPrefixMapping(String prefix, String uri) throws SAXException {
        log.debug("startPrefixMapping: prefix = {}, uri={}", prefix, uri);
    }

    @Override
    public void endPrefixMapping(String prefix) throws SAXException {
        log.debug("endPrefixMapping: prefix = {}", prefix);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        log.debug("startElement: uri = {}, localName= " + localName + ", qName=" + qName + " attributes={}", uri, atts);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        log.debug("endElement: uri = {}, localName={}, qName=" + qName, uri, localName);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        log.debug("characters: chars = {}, start={}, length=" + length, ch, start);
    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
        log.debug("ignorableWhitespace: chars = {}, start={}, length=" + length, ch, start);
    }

    @Override
    public void processingInstruction(String target, String data) throws SAXException {
        log.debug("processingInstruction: target = {}, data={}", target, data);
    }

    @Override
    public void skippedEntity(String name) throws SAXException {
        log.debug("skippedEntity: name = {}", name);
    }
}
