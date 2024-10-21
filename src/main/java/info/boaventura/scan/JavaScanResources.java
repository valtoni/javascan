package info.boaventura.scan;


import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.shell.command.annotation.CommandScan;
import org.springframework.shell.jline.PromptProvider;


@SpringBootApplication
@CommandScan
public class JavaScanResources {

	public static void main(String[] args) {
		try {
			// Initialize spring application with external configuration
			SpringApplication application = new SpringApplication(JavaScanResources.class);
			application.setBannerMode(Banner.Mode.OFF);
			application.addListeners(new ConfigFileApplicationListener());
			application.run(args);
		} catch (Exception e) {
			System.err.println("Impossible starts scan console: " + e.getMessage());
		}
	}

	@Bean
	public PromptProvider promptProvider() {
		return () -> new AttributedString("SCAN> ", AttributedStyle.DEFAULT.foreground(AttributedStyle.BLUE));
	}

}
