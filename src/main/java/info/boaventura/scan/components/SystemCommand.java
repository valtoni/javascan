package info.boaventura.scan.components;

import info.boaventura.scan.core.MainSetting;
import info.boaventura.scan.core.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Paths;
import java.util.Collection;
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
			return entries(null, false);
		}
		return "Directory " + directory.toPath().toString() + " does not exists";
	}

	@CliCommand(value = "entries", help = "List entries currently indexed")
	public String entries(@CliOption(key = "") String title,
												@CliOption(key = "verbose") Boolean verbose) {
		StringBuffer sb = new StringBuffer();
		// Mount the result
		sb.append(result.mount());
		sb.append("Available Files at " + MainSetting.current.toAbsolutePath() + ": \n");
		Collection<ZipFile> files = result.getIndexedFiles();
		for (ZipFile entry: files) {
			sb.append("- " + MainSetting.current.relativize(Paths.get(entry.getName())).toString());
			if (entry.getComment() != null) {
				sb.append(" /* " + entry.getComment() + " */");
			}
			sb.append("\n");
		}
		sb.append("\n" + files.size() + " entries available.\n");
		sb.append("Directory changed to " + MainSetting.current.toString());
		return sb.toString();
	}

	@CliCommand(value = "pwd", help = "Current work dir")
	public String currentDirectory() {
		return "Current directory are " + MainSetting.current.toString();
	}

}
