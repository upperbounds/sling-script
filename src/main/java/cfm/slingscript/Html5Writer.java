package cfm.slingscript;

import nu.xom.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Writer;

public class Html5Writer {
    private static final Logger log = LoggerFactory.getLogger(Html5Writer.class);
    private Writer writer;

    public Html5Writer(Writer writer) {
        this.writer = writer;
    }

    public void writeHtml(Document document) throws IOException {
        writeElement(document.getRootElement());

    }

    private void writeAttributes(Element element) throws IOException {
        for (int i = 0; i < element.getAttributeCount(); i++) {
            Attribute attribute = element.getAttribute(i);
            writer.write(" ");
            writer.write(attribute.getLocalName() + "=" + "\"" + attribute.getValue() + "\"");
        }
    }

    private void writeElement(Element element) throws IOException {

        writer.write("<" + element.getLocalName());
        writeAttributes(element);
        writer.write(">");
        if (element.getChildElements().size() > 0) {
            Node text = element.getChild(0);
            if (text instanceof Text) {
                writer.write(text.getValue());
            }
        }

        if (element.getChildElements().size() == 0) {
            writer.write(element.getValue());
        }
        Elements elements = element.getChildElements();
        for (int i = 0; i < elements.size(); i++) {
            writeElement(elements.get(i));
        }

        writer.write("</" + element.getLocalName() + ">");
    }
}
