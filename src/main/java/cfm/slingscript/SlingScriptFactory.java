package cfm.slingscript;

import org.apache.sling.scripting.api.AbstractScriptEngineFactory;

import javax.script.ScriptEngine;
import java.util.Arrays;
import java.util.List;

public class SlingScriptFactory extends AbstractScriptEngineFactory {

    private final static String ENGINE_NAME = "Sling Scripting Engine";
    private final static String ENGINE_VERSION = "0.1";
    private final static List MIME_TYPES = Arrays.asList("application/x-sling");
    private final static List EXTENSIONS = Arrays.asList();

    public String getEngineName() {
        return ENGINE_NAME;
    }

    public String getEngineVersion() {
        return ENGINE_VERSION;
    }

    public String getLanguageName() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getLanguageVersion() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<String> getExtensions() {
        return EXTENSIONS;
    }

    public List<String> getMimeTypes() {
        return MIME_TYPES;
    }

    public ScriptEngine getScriptEngine() {
        return new SlingScriptEngine(this) ;
    }
}
