package info.boaventura.scan.configurations;

import info.boaventura.scan.core.DataSearchManager;
import info.boaventura.scan.core.DataSearchManagerCached;
import info.boaventura.scan.core.handlers.PathHandler;
import info.boaventura.scan.core.PathHandlerTokenized;
import info.boaventura.scan.exceptions.JavaScanCommandExceptionResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.command.CommandExceptionResolver;

@Configuration
public class SpringMainConfiguration {

	@Bean
	public DataSearchManager result() {
		return new DataSearchManagerCached();
	}

	@Bean
	public PathHandler producesPathHandler() {
		return new PathHandlerTokenized();
	}

}
