package info.boaventura.scan.core.variables;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.PropertyResolver;
import org.springframework.stereotype.Component;

@Component
@Primary
public class VariableExpanderSpring implements VariableExpander {

    private final Logger logger = LoggerFactory.getLogger(VariableExpanderSpring.class);

    private final PropertyResolver propertyResolver;

    public VariableExpanderSpring(PropertyResolver propertyResolver) {
        this.propertyResolver = propertyResolver;
    }

    @Override
    public String expand(String variable) {
        String value = propertyResolver.getProperty(variable);
        logger.debug("Environment: expanded variable <{}> to {}", variable, value);
        return value;
    }
}
