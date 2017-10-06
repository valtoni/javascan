package info.boaventura.scan.components;

import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliAvailabilityIndicator;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.stereotype.Component;

@Component
public class SystemCommand implements CommandMarker {

	@CliAvailabilityIndicator({"version-scanner"})
	public boolean isConnectAvailable() {
		return true;
	}

	@CliCommand(value = "version-scanner", help = "Print a version of this scanner")
	public String version() {
		return "1.0.0-SNAPSHOT";
	}

}
