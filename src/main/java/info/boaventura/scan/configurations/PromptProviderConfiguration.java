package info.boaventura.scan.configurations;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.plugin.PromptProvider;

@Configuration
public class PromptProviderConfiguration {

	@Bean
	public PromptProvider provider() {
		return new PromptProvider() {

			public String getPrompt() {
				return new AttributedString("SCAN> ", AttributedStyle.DEFAULT.foreground(AttributedStyle.BLUE)).toString();
			}

			public String getProviderName() {
				return "scan provider";
			}

		};
	}

}
