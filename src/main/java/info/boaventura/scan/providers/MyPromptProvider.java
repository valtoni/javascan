package info.boaventura.scan.providers;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.support.DefaultPromptProvider;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MyPromptProvider extends DefaultPromptProvider {
  
  @Override
  public String getPrompt() {
    return new AttributedString("SCAN> ", AttributedStyle.DEFAULT.foreground(AttributedStyle.BLUE)).toString();
  }

  @Override
  public String getProviderName() {
    return "The Commander prompt provider";
  }
  
}