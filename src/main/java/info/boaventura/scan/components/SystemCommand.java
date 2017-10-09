package info.boaventura.scan.components;

import info.boaventura.scan.core.MainSetting;
import info.boaventura.scan.core.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.zip.ZipFile;

@Component
public class SystemCommand implements CommandMarker {

	@Autowired
	Result result;

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
			return mount();
		}
		return "Directory " + directory.toPath().toString() + " does not exists";
	}

	private String mount() {
		StringBuffer sb = new StringBuffer();
		// Mount the result
		sb.append(result.mount());
		sb.append("Available Files: \n");
		for (ZipFile entry: result.getIndexedFiles()) {
			sb.append("- " + entry.getName());
			if (entry.getComment() != null) {
				sb.append("/* " + entry.getComment() + " */");
			}
			sb.append("\n");
		}
		sb.append("\nDirectory changed to " + MainSetting.current.toString());
		return sb.toString();
	}

	@CliCommand(value = "pwd", help = "Current work dir")
	public String currentDirectory() {
		return "Current directory are " + MainSetting.current.toString();
	}

}
