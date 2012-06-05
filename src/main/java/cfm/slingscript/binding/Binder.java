package cfm.slingscript.binding;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import java.util.Set;

public class Binder extends AbstractProcessor {

    public Binder(){}

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

}
