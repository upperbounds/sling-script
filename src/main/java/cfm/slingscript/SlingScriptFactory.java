package cfm.slingscript;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.scripting.api.AbstractScriptEngineFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.ScriptEngine;
import java.util.Arrays;
import java.util.List;


@Component(metatype = false)
@Service(value = javax.script.ScriptEngineFactory.class)
//@Properties({     })

public class SlingScriptFactory extends AbstractScriptEngineFactory {

    private final static String ENGINE_NAME = "Sling Scripting Engine";
    private final static String ENGINE_VERSION = "0.1";
    private final static List<String> MIME_TYPES = Arrays.asList("application/x-slingscript");
    private final static List<String> EXTENSIONS = Arrays.asList("cfm");

    private static final Logger log = LoggerFactory.getLogger(SlingScriptFactory.class);

    public String getEngineName() {
        log.info("getEngineName called");
        return ENGINE_NAME;
    }

    public String getEngineVersion() {
        log.info("getEngineVersion called");
        return ENGINE_VERSION;
    }

    public String getLanguageName() {
        log.info("getLanguageName called");
        return ENGINE_NAME;
    }

    public String getLanguageVersion() {
        log.info("getLanguageVersion called");
        return ENGINE_VERSION;
    }

    public List<String> getExtensions() {
        log.info("getExtensions called");
        return EXTENSIONS;
    }

    public List<String> getMimeTypes() {
        log.info("getMimeTypes called");
        return MIME_TYPES;
    }

    public ScriptEngine getScriptEngine() {
        log.info("getScriptEngine called");
        return new SlingScriptEngine(this);
    }
}
