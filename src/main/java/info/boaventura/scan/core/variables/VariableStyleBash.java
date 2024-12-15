package info.boaventura.scan.core.variables;

import org.springframework.stereotype.Component;

@Component
public class VariableStyleBash implements VariableStyle {

    private static final String START_VAR = "${";
    private static final String END_VAR = "}";

    private final VariableExpander variableExpander;

    public VariableStyleBash(VariableExpander variableExpander) {
        this.variableExpander = variableExpander;
    }

    public String replace(String item) {
        if (item == null  || item.length() < START_VAR.length() + END_VAR.length()) return item;
        int first = item.indexOf(START_VAR);
        if (first < 0) return item;
        int last = item.indexOf(END_VAR, START_VAR.length() + 1);
        if (last < 0) return item;
        String variable = item.substring(first + START_VAR.length(), last - END_VAR.length() + 1);
        return variableExpander.expand(variable);
    }

}
