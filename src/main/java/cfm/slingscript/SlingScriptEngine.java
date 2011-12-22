package cfm.slingscript;

import nu.validator.htmlparser.common.XmlViolationPolicy;
import nu.validator.htmlparser.dom.HtmlDocumentBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.script.*;
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
        HtmlDocumentBuilder parser = new HtmlDocumentBuilder();
//        HtmlBuilder parser = new HtmlBuilder(XmlViolationPolicy.ALLOW);
//        parser.setCommentPolicy(XmlViolationPolicy.ALLOW);
//        parser.setContentNonXmlCharPolicy(XmlViolationPolicy.ALLOW);
        parser.setContentSpacePolicy(XmlViolationPolicy.FATAL);
//        parser.setNamePolicy(XmlViolationPolicy.ALLOW);

        try {

            try {

                Document doc = parser.parse(new InputSource(reader));
                StringWriter writer = new StringWriter();
                Html5Writer w = new Html5Writer(writer);
                w.writeHtml(doc);
                log.info("result: {}", writer);
                return writer.toString();
            } catch (SAXException e) {
                throw new ScriptException(e);
            }
        } catch (IOException e) {
            throw new ScriptException(e);
        }

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
