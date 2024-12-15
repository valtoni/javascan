package info.boaventura.scan.components;

import info.boaventura.scan.core.DataSearchManager;
import info.boaventura.scan.core.ItemResult;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.validation.annotation.Validated;

import java.util.Set;

@Validated
@ShellComponent
public class SearchCommand {

	DataSearchManager dataSearchManager;

	public SearchCommand(DataSearchManager dataSearchManager) {
		this.dataSearchManager = dataSearchManager;
	}

	@ShellMethod(key = "search", value = "Search for entries in jar or zip files")
	public String search(
			@NotEmpty(message = "You must inform a pattern to be searched")
			@ShellOption(arity = 1, help = "Search pattern", value = "pattern")
			String pattern
	) {
		StringBuilder sb = new StringBuilder();
		Set<ItemResult> foundEntries = dataSearchManager.match(pattern);
		for (ItemResult itemResult : foundEntries) {
			if (itemResult.isClassEntry()) {
				sb.append(itemResult.toAnsiString()).append("\n");
			}
		}
		sb.append("\nThe search for '").append(pattern)
				.append("' has found ").append(foundEntries.size()).append(" matches\n");
		return sb.toString();
	}

}
