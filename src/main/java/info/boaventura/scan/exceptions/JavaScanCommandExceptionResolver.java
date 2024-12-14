package info.boaventura.scan.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.ParameterValidationException;
import org.springframework.shell.command.CommandExceptionResolver;
import org.springframework.shell.command.CommandHandlingResult;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class JavaScanCommandExceptionResolver implements CommandExceptionResolver {

    @Override
    public CommandHandlingResult resolve(Exception ex) {
        if (ex instanceof ConstraintViolationException cve) {
            String message = cve.getConstraintViolations().stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("\n- "));
            AttributedString styledMessage = new AttributedString(
                    message + "\n",
                    AttributedStyle.DEFAULT.foreground(AttributedStyle.RED)
            );
            return CommandHandlingResult.of(styledMessage.toAnsi());
        } else if (ex instanceof ParameterValidationException pve) {
            String message = pve.getConstraintViolations().stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("\n- "));
            AttributedString styledMessage = new AttributedString(
                    message + "\n",
                    AttributedStyle.DEFAULT.foreground(AttributedStyle.MAGENTA)
            );
            return CommandHandlingResult.of(styledMessage.toAnsi());
        }
        return null;
    }

}
