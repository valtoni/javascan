package info.boaventura.scan.configurations;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.plugin.PromptProvider;
import org.springframework.shell.plugin.support.DefaultPromptProvider;

@Configuration
public class PromptProviderConfiguration {

	@Bean
	public PromptProvider provider() {
		return new PromptProvider() {

			@Override
			public String getPrompt() {
				return new AttributedString("SCAN>", AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW)).toAnsi();
			}

			@Override
			public String getProviderName() {
				return "scan provider";
			}

		};
	}

}
