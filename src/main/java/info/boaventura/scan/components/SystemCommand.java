package info.boaventura.scan.components;

import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.stereotype.Component;

@Component
public class SystemCommand implements CommandMarker {

	@CliCommand(value = "version", help = "Print a version of this scanner")
	public String version() {
		return "1.0.0-SNAPSHOT";
	}

}
