package cfm.slingscript;

import nu.validator.htmlparser.common.XmlViolationPolicy;
import nu.validator.htmlparser.dom.HtmlDocumentBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.script.*;
import javax.xml.stream.events.NotationDeclaration;
import java.io.*;

public class SlingScriptEngine extends AbstractScriptEngine {
    private static final Logger log = LoggerFactory.getLogger(SlingScriptEngine.class);
    private final ScriptEngineFactory scriptEngineFactory;

    public SlingScriptEngine(ScriptEngineFactory scriptEngineFactory) {
        this.scriptEngineFactory = scriptEngineFactory;
    }

    public Object eval(String script, ScriptContext context) throws ScriptException {
        log.info("eval'ing {} {}", script, context);
        return eval(new StringReader(script), context);
    }

    public Object eval(Reader reader, ScriptContext context) throws ScriptException {
        log.info("Initializing script engine {} {}", reader, context);

        // instantiate and configure the parser with default policies
        HtmlDocumentBuilder parser = new HtmlDocumentBuilder(XmlViolationPolicy.ALLOW);

        parser.setCommentPolicy(XmlViolationPolicy.ALLOW);
        parser.setContentNonXmlCharPolicy(XmlViolationPolicy.ALLOW);
        parser.setContentSpacePolicy(XmlViolationPolicy.FATAL);
        parser.setNamePolicy(XmlViolationPolicy.ALLOW);

        try {

            try {

                Document doc = parser.parse(new InputSource(reader));

                NodeList nodes = doc.getElementsByTagName("bind");
                for (int i = 0; i < nodes.getLength(); i++) {
                    Node node = nodes.item(i);
                    Node tag = node.getAttributes().getNamedItem("tag");


                    String t = tag.getNodeValue();
                    NodeList replaces = doc.getElementsByTagName(tag.getNodeValue());
                    log.info("about to replace {} with {}", tag.getNodeName(), tag.getNodeValue());
                    log.info("replaces is size of {}", replaces.getLength());

                    Node n = node.getParentNode().removeChild(node);
                    for (int a = 0; a < replaces.getLength(); a++) {

                        log.info("replacing content with {}", node.getTextContent());
                        Node replace = replaces.item(a);

                        Node par = replace.getParentNode();
                        Node n2 = replace.getParentNode().removeChild(replace);

                        for (int b = 0; b < n.getChildNodes().getLength(); b++) {
                            log.info("doing an add of {}", n.getChildNodes().item(b));
                            par.appendChild(n.getChildNodes().item(b));

                        }
                        for (int z = 0; z < n2.getChildNodes().getLength(); z++) {
                            par.appendChild(n2.getChildNodes().item(z));
                        }
                    }

                }

                Writer writer = context.getWriter();
                Html5Writer w = new Html5Writer(writer);
                w.writeHtml(doc);
                log.info("result: {}", writer);

            } catch (SAXException e) {
                throw new ScriptException(e);
            }
        } catch (IOException e) {
            throw new ScriptException(e);
        }
        return null;
    }

    public Bindings createBindings() {
        log.info("creating Bindings");
        return new SimpleBindings();
    }

    public ScriptEngineFactory getFactory() {
        log.info("getting Factory");
        return scriptEngineFactory;
    }
}
