package cfm.slingscript;

import org.apache.sling.scripting.api.AbstractSlingScriptEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.*;
import java.io.Reader;

public class SlingScriptEngine extends AbstractSlingScriptEngine {
    private static final Logger log = LoggerFactory.getLogger(SlingScriptEngine.class);

    public SlingScriptEngine(SlingScriptFactory factory) {
        super(factory);
        log.info("Initializing script engine");
    }

    public Object eval(String script, ScriptContext context) throws ScriptException {
        log.info("evaling {} {}", script, context);
        return new Object();
    }

    public Object eval(Reader reader, ScriptContext context) throws ScriptException {
        log.info("Initializing script engine {} {}", reader, context);
        return new Object();
    }

    public Bindings createBindings() {
        log.info("creating Bindings");
        return new SimpleBindings();
    }

    public ScriptEngineFactory getFactory() {
        log.info("getting Factory");
        return super.getFactory();
    }
}
