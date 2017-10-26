package info.boaventura.scan.configurations;

import info.boaventura.scan.Ansi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.plugin.PromptProvider;

@Configuration
public class PromptProviderConfiguration {

	@Bean
	public PromptProvider provider() {
		return new PromptProvider() {

			public String getPrompt() {
				//return new AttributedString("SCAN> ", AttributedStyle.DEFAULT.foreground(AttributedStyle.BLUE)).toString();
				return new Ansi.Builder().cyan("SCAN> ").string();
			}

			public String getProviderName() {
				return "scan provider";
			}

		};
	}

}
