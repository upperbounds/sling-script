package cfm.slingscript;

import nu.validator.htmlparser.common.XmlViolationPolicy;
import nu.validator.htmlparser.sax.HtmlParser;
import nu.validator.htmlparser.xom.HtmlBuilder;
import nu.xom.Document;
import nu.xom.ParsingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        log.info("evaling {} {}", script, context);
        HtmlBuilder parser = new HtmlBuilder();
        try {
            Document doc = parser.build(new ByteArrayInputStream(script.getBytes("UTF-8")));
            StringWriter writer = new StringWriter();
            Html5Writer w = new Html5Writer(writer);
            w.writeHtml(doc);
            log.info("result: {}", writer);
            return writer.toString();
        } catch (ParsingException e) {
            throw new ScriptException(e);
        } catch (IOException e) {
            throw new ScriptException(e);
        }

    }

    public Object eval(Reader reader, ScriptContext context) throws ScriptException {
        log.info("Initializing script engine {} {}", reader, context);
        throw new ScriptException("not implemented");
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
