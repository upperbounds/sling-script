package cfm.slingscript;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.*;

import java.io.IOException;
import java.io.Writer;

public class Html5Writer {
    private static final Logger log = LoggerFactory.getLogger(Html5Writer.class);
    private Writer writer;

    public Html5Writer(Writer writer) {
        this.writer = writer;
    }

    public void writeHtml(Document document) throws IOException {

        writeElement(document.getDocumentElement());

    }

    private void writeAttributes(Node element) throws IOException {
        NamedNodeMap nodes = element.getAttributes();
        if (element instanceof Element) {
            Element e = (Element) element;
           e.getPrefix();
            //log.info("im ab eliment {}", e.getAttributeNS(null, e.getTagName()));
        }
        if (null != nodes) {
            for (int i = nodes.getLength() - 1; i > -1; i--) { // fragile assumption about attribute ordering that isn't guaranteed
                Node attribute = nodes.item(i);

                writer.write(" ");
                writer.write(attribute.getLocalName() + "=" + "\"" + attribute.getNodeValue() + "\"");
            }
        }
        //log.info("namespace {}", element.lookupNamespaceURI(null));

    }

    private void writeElement(Node element) throws IOException {

        if (null != element.getLocalName()) {
            writer.write("<" + element.getLocalName());
            writeAttributes(element);
            writer.write(">");
        } else {
            writer.write(element.getTextContent());
        }



        NodeList children = element.getChildNodes();

        for (int i = 0; i < children.getLength(); i++) {
            writeElement(children.item(i));
        }
        if (null != element.getLocalName()) {
            writer.write("</" + element.getLocalName() + ">");
        }
    }
}
