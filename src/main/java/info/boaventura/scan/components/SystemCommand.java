package info.boaventura.scan.components;

import info.boaventura.scan.core.MainSetting;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class SystemCommand implements CommandMarker {

	@CliCommand(value = "cd", help = "Change dir to be search")
	public String changeDirectory(
			@CliOption(key = "")
			File directory) {
		File change = directory;
		if (directory == null) {
			return "Are you kidding with me?";
		}
		if (directory.toString().endsWith("..")) {
			if (MainSetting.current.getParent() == null) {
				return "Already at root";
			}
			change = MainSetting.current.getParent().toFile();
		}
		if (change.exists()) {
			MainSetting.current = change.toPath();
			return "Directory changed to " + MainSetting.current.toString();
		}
		return "Directory " + directory.toPath().toString() + " does not exists";
	}

	@CliCommand(value = "pwd", help = "Current work dir")
	public String currentDirectory() {
		return "Current directory are " + MainSetting.current.toString();
	}

}
