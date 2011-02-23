package cfm.slingscript;

import org.apache.sling.scripting.api.AbstractSlingScriptEngine;

import javax.script.*;
import java.io.Reader;

public class SlingScriptEngine extends AbstractSlingScriptEngine {

    public SlingScriptEngine(SlingScriptFactory factory) {
        super(factory);
    }

    public Object eval(String script, ScriptContext context) throws ScriptException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Object eval(Reader reader, ScriptContext context) throws ScriptException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Bindings createBindings() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ScriptEngineFactory getFactory() {
        return getFactory();
    }
}
