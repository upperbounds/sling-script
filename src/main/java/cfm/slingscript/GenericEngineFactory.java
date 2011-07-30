package cfm.slingscript;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import java.util.Arrays;
import java.util.List;

public class GenericEngineFactory implements ScriptEngineFactory {

    private String engineName;
    private String engineVersion;
    private List<String> extensions;
    private List<String> mimeTypes;
    private List<String> names;

    public GenericEngineFactory() {
        engineName = "Sling Scripting Engine";
        engineVersion = "0.1";
        mimeTypes = Arrays.asList("application/x-slingscript");
        extensions = Arrays.asList("cfm");
        names = Arrays.asList("cfm");
    }

    @Override
    public String getEngineName() {
        return engineName;
    }

    @Override
    public String getEngineVersion() {
        return engineVersion;
    }

    @Override
    public List<String> getExtensions() {
        return extensions;
    }

    @Override
    public List<String> getMimeTypes() {
        return mimeTypes;
    }

    @Override
    public List<String> getNames() {
        return names;
    }

    @Override
    public String getLanguageName() {
        return engineName;
    }

    @Override
    public String getLanguageVersion() {
        return engineVersion;
    }

    @Override
    public Object getParameter(String name) {
        if (ScriptEngine.ENGINE.equals(name)) {
            return getEngineName();
        } else if (ScriptEngine.ENGINE_VERSION.equals(name)) {
            return getEngineVersion();
        } else if (ScriptEngine.NAME.equals(name)) {
            return getNames();
        } else if (ScriptEngine.LANGUAGE.equals(name)) {
            return getLanguageName();
        } else if (ScriptEngine.LANGUAGE_VERSION.equals(name)) {
            return getLanguageVersion();
        }
        return null;
    }

    @Override
    public String getMethodCallSyntax(String obj, String m, String... args) {
        StringBuilder callSyntax = new StringBuilder();
        callSyntax.append(obj).append('.').append(m).append('(');
        for (int i = 0; args != null && i < args.length; i++) {
            if (i > 0) callSyntax.append(',');
            callSyntax.append(args[i]);
        }
        callSyntax.append(')');
        return callSyntax.toString();
    }

    @Override
    public String getOutputStatement(String toDisplay) {
        return "println(" + toDisplay + ")";
    }

    @Override
    public String getProgram(String... statements) {
        return null;
    }

    @Override
    public ScriptEngine getScriptEngine() {
        return new SlingScriptEngine(this);
    }
}
