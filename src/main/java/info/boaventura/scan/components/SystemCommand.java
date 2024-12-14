package info.boaventura.scan.components;

import info.boaventura.scan.core.handlers.PathHandler;
import info.boaventura.scan.core.DataSearchManager;
import org.springframework.shell.command.annotation.Command;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.zip.ZipFile;

@Component
public class SystemCommand {

	DataSearchManager dataSearchManager;
	PathHandler pathHandler;

	public SystemCommand(DataSearchManager dataSearchManager, PathHandler pathHandler) {
		this.dataSearchManager = dataSearchManager;
		this.pathHandler = pathHandler;
	}

	@Command(command = "entries", description = "List entries currently indexed")
	public String entries() {
		StringBuilder sb = new StringBuilder();
		for (Path path: pathHandler.getPaths()) {
			sb.append("Available Files at ").append(path.toAbsolutePath()).append(": \n");
			Collection<ZipFile> files = dataSearchManager.getIndexedFiles();
			for (ZipFile entry: files) {
				sb.append("- ").append(path.relativize(Paths.get(entry.getName())).toString());
				if (entry.getComment() != null) {
					sb.append(" /* ").append(entry.getComment()).append(" */");
				}
				sb.append("\n");
			}
			sb.append("\n").append(files.size()).append(" entries available.\n");
		}
		return sb.toString();
	}

}
