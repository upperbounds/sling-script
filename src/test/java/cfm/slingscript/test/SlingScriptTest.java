package cfm.slingscript.test;

import cfm.slingscript.GenericEngineFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptException;

public class SlingScriptTest {

    ScriptEngineFactory engineFactory;

    @Before
    public void setUp() {
        engineFactory = new GenericEngineFactory();

    }

    @Test
    public void testEmptyScript() {
        ScriptEngine engine = engineFactory.getScriptEngine();
        String script = "<html><head></head><body></body></html>";
        try {
            Assert.assertEquals(script, engine.eval(script));
        } catch (ScriptException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testAttrScript() {
        ScriptEngine engine = engineFactory.getScriptEngine();
        String script = "<html lang=\"en\" id=\"facebook\" class=\"no_js\"><head></head><body></body></html>";
        try {
            Assert.assertEquals(script, engine.eval(script));
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testValueScript() {
        ScriptEngine engine = engineFactory.getScriptEngine();
        String script = "<html><head></head><body>Body text</body></html>";
        try {
            Assert.assertEquals(script, engine.eval(script));
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMixedContentScript() {
        ScriptEngine engine = engineFactory.getScriptEngine();
        String script = "<html><head></head><body>Body text with <b>bold</b></body></html>";
        try {
            Assert.assertEquals(script, engine.eval(script));
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testlinkScript() {
        ScriptEngine engine = engineFactory.getScriptEngine();
        String script = "<html><head></head><body>Body text with <b>bold</b><a href=\"thing.html\">thing</a></body></html>";
        try {
            Assert.assertEquals(script, engine.eval(script));
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQuotes() {
        ScriptEngine engine = engineFactory.getScriptEngine();
        String script = "<html><head></head><body class=\"&#quote;\">Body text with \"quotes\"<b>bold</b><a href=\"thing.html\">thing</a></body></html>";
        try {
            Assert.assertEquals(script, engine.eval(script));
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

//    @Test
    public void testNamespaces() {
        ScriptEngine engine = engineFactory.getScriptEngine();
        String script = "<html lang=\"en-us\" dir=\"ltr\" xmlns=\"http://www.w3.org/1999/xhtml\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /></meta></head><body></body></html>";
        try {
            Assert.assertEquals(script, engine.eval(script));
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }


}
