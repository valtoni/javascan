package info.boaventura.scan.core.variables;

import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Primary
public class VariableStyleSpring implements VariableStyle {

    private final Environment environment;

    public VariableStyleSpring(Environment environment) {
        this.environment = environment;
    }

    @Override
    public String replace(String item) {
        return environment.resolvePlaceholders(item);
    }

}
