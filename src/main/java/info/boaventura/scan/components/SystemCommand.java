package info.boaventura.scan.components;

import info.boaventura.scan.core.MainSetting;
import info.boaventura.scan.core.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.zip.ZipFile;

@Component
public class SystemCommand implements CommandMarker {

	@Autowired
	Result result;

	@CliCommand(value = "entries", help = "List entries currently indexed")
	public String entries(@CliOption(key = "") String title,
												@CliOption(key = "verbose") Boolean verbose) {
		if (!MainSetting.mounted) return "Index was not mounted (use mount first)";
		StringBuffer sb = new StringBuffer();

		for (Path path: MainSetting.getPaths()) {
			sb.append("Available Files at " + path.toAbsolutePath() + ": \n");
			Collection<ZipFile> files = result.getIndexedFiles();
			for (ZipFile entry: files) {
				sb.append("- " + path.relativize(Paths.get(entry.getName())).toString());
				if (entry.getComment() != null) {
					sb.append(" /* " + entry.getComment() + " */");
				}
				sb.append("\n");
			}
			sb.append("\n" + files.size() + " entries available.\n");
		}

		return sb.toString();
	}

}
