package info.boaventura.scan.core.variables;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.PropertyResolver;
import org.springframework.stereotype.Component;

@Component
public class VariableExpanderEnvironment implements VariableExpander {

    private final Logger logger = LoggerFactory.getLogger(VariableExpanderEnvironment.class);

    @Override
    public String expand(String variable) {
        String value = System.getenv(variable);
        logger.debug("Environment: expanded variable <{}> to {}", variable, value);
        return value;
    }
}
