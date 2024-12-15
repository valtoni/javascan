package info.boaventura.scan.components;

import info.boaventura.scan.core.handlers.PathHandler;
import info.boaventura.scan.core.DataSearchManager;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.context.annotation.Bean;
import org.springframework.shell.Availability;
import org.springframework.shell.AvailabilityProvider;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;
import org.springframework.shell.command.annotation.CommandAvailability;
import org.springframework.shell.command.annotation.Option;
import org.springframework.shell.standard.ShellOption;
import org.springframework.validation.annotation.Validated;

import java.nio.file.Path;
import java.util.Collection;
import java.util.zip.ZipFile;

import static org.springframework.shell.command.CommandRegistration.OptionArity.EXACTLY_ONE;

@Validated
@Command(command = "index")
public class IndexerCommand {

	DataSearchManager result;
	PathHandler pathHandler;

	public IndexerCommand(DataSearchManager dataSearchManager, PathHandler pathHandler) {
		this.result = dataSearchManager;
		this.pathHandler = pathHandler;
	}

	private String entries() {
		StringBuilder sb = new StringBuilder();
		sb.append("Available entries: \n");
		Collection<ZipFile> files = result.getIndexedFiles();
		for (ZipFile entry: files) {
			sb.append("- ").append(entry.getName());
			if (entry.getComment() != null) {
				sb.append(" /* ").append(entry.getComment()).append(" */");
			}
			sb.append("\n");
		}
		sb.append("\n").append(files.size()).append(" entries available.\n");

		return sb.toString();
	}

	@Bean
	public AvailabilityProvider indexesWasSupplied() {
		return () -> pathHandler.isReady()
				? Availability.available()
				: Availability.unavailable("You must supply at least one path to create index");
	}

	@Command(command = "mount", description = "Mount system index classes")
	@CommandAvailability(provider = "indexesWasSupplied")
	public String mount() {
		StringBuilder sb = new StringBuilder();
		for (Path item: pathHandler.getPaths()) {
			sb.append(result.mount(item.toFile()));
		}
		return sb.toString();
	}

	@Command(command = "add", description = "Add item to be indexed")
	public String add(
			@NotEmpty(message = "You must inform a pattern to be included (can be environment variables)")
			@Option(longNames = "pattern", description = "Path value (can be environment variables)")
			String item
		) {
		if (item.equalsIgnoreCase("maven")) {
			pathHandler.addPath("${user.home}/.m2/repository");
			return "Added maven repository to search";
		}
		else {
			pathHandler.addPath(item);
			return "Added directories to search";
		}
	}

	@Command(command = "clean", description = "Clean index")
	public String clean() {
		result.setup();
		pathHandler.cleanPaths();
		return "Index was cleaned";
	}

	@Command(command = "list", description = "List index content")
	public String list() {
		return entries();
	}

}
