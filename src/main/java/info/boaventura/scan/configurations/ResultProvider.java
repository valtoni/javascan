package info.boaventura.scan.configurations;

import info.boaventura.scan.core.DataSearchManager;
import info.boaventura.scan.core.DataSearchManagerCached;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResultProvider {

	@Bean
	public DataSearchManager result() {
		return new DataSearchManagerCached();
	}

}
