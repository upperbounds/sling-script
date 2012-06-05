package cfm.slingscript.test;

import cfm.slingscript.GenericEngineFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.script.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class SlingScriptTest {

    ScriptEngineFactory engineFactory;

    ScriptContext context;

    StringWriter sw;

    @Before
    public void setUp() {
        engineFactory = new GenericEngineFactory();
        context = new SimpleScriptContext();
        sw = new StringWriter();
        context.setWriter(sw);

    }

    @Test
    public void testEmptyScript() {
        ScriptEngine engine = engineFactory.getScriptEngine();
        String script = "<html><head></head><body></body></html>";
        try {

            engine.eval(new StringReader(script), context);

            Assert.assertEquals(script, sw.toString());
        } catch (ScriptException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testAttrScript() {
        ScriptEngine engine = engineFactory.getScriptEngine();
        String script = "<html lang=\"en\" id=\"facebook\" class=\"no_js\"><head></head><body></body></html>";
        try {
            engine.eval(new StringReader(script), context);
            Assert.assertEquals(script, sw.toString());
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testValueScript() {
        ScriptEngine engine = engineFactory.getScriptEngine();
        String script = "<html><head></head><body>Body text</body></html>";
        try {
            engine.eval(new StringReader(script), context);
            Assert.assertEquals(script, sw.toString());
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMixedContentScript() {
        ScriptEngine engine = engineFactory.getScriptEngine();
        String script = "<html><head></head><body>Body text with <b>bold</b></body></html>";
        try {
            engine.eval(new StringReader(script), context);
            Assert.assertEquals(script, sw.toString());
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testlinkScript() {
        ScriptEngine engine = engineFactory.getScriptEngine();
        String script = "<html><head></head><body>Body text with <b>bold</b><a href=\"thing.html\">thing</a></body></html>";
        try {
            engine.eval(new StringReader(script), context);
            Assert.assertEquals(script, sw.toString());
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQuotes() {
        ScriptEngine engine = engineFactory.getScriptEngine();
        String script = "<html><head></head><body class=\"&#quote;\">Body text with \"quotes\"<b>bold</b><a href=\"thing.html\">thing</a></body></html>";
        try {
            engine.eval(new StringReader(script), context);
            Assert.assertEquals(script, sw.toString());
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    //    @Test
    public void testNamespaces() {
        ScriptEngine engine = engineFactory.getScriptEngine();
        String script = "<html lang=\"en-us\" dir=\"ltr\" xmlns:fb=\"http://www.facebook.com/2008/fbml\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /></meta></head><body></body></html>";
        try {
            engine.eval(new StringReader(script), context);
            Assert.assertEquals(script, sw.toString());
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBinding() {
        ScriptEngine engine = engineFactory.getScriptEngine();
        String script = "<html><body><bind tag=\"longname\">binding text</bind><p>text1<longname/>text2<longname/>text3<longname/></body></html>";
        String result = "<html><head></head><body>binding text</bind><p>text1binding texttext2binding texttext3binding text</body></html>";
        try {
            engine.eval(new StringReader(script), context);
            Assert.assertEquals(script, result);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

}
