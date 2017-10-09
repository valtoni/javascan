package info.boaventura.scan.configurations;

import info.boaventura.scan.core.Result;
import info.boaventura.scan.core.ResultCached;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResultProvider {

	@Bean
	public Result result() {
		return new ResultCached();
	}

}
