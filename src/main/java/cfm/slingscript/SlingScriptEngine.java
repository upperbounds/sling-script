package cfm.slingscript;

import nu.validator.htmlparser.common.XmlViolationPolicy;
import nu.validator.htmlparser.sax.HtmlParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.script.*;
import java.io.IOException;
import java.io.Reader;

public class SlingScriptEngine extends AbstractScriptEngine {
    private static final Logger log = LoggerFactory.getLogger(SlingScriptEngine.class);
    private final ScriptEngineFactory scriptEngineFactory;

    public SlingScriptEngine(ScriptEngineFactory scriptEngineFactory) {
        this.scriptEngineFactory = scriptEngineFactory;
    }

    public Object eval(String script, ScriptContext context) throws ScriptException {
        log.info("evaling {} {}", script, context);
        HtmlParser reader = new HtmlParser();
        reader.setXmlPolicy(XmlViolationPolicy.ALLOW);
        reader.setContentHandler(new HtmlContentHandler());
        try {
            reader.parse(script);
        } catch (IOException e) {
            throw new ScriptException(e);
        } catch (SAXException e) {
            throw new ScriptException(e);
        }
        //rootElem
        return script;
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
