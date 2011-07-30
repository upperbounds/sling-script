package cfm.slingscript.test;

import cfm.slingscript.GenericEngineFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptException;

public class SlingScriptTest{

    ScriptEngineFactory engineFactory;
    @Before
    public void setUp(){
       engineFactory = new GenericEngineFactory();

    }
    @Test
    public void testScript(){
        ScriptEngine engine = engineFactory.getScriptEngine();
        String script = "<html><head></head><body></body></html>";
        try {
            Assert.assertEquals(script,engine.eval(script));
        } catch (ScriptException e) {
            e.printStackTrace();
        }

    }


}
