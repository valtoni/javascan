package info.boaventura.scan.components;

import info.boaventura.scan.core.DataSearchManager;
import info.boaventura.scan.core.ItemResult;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.shell.command.annotation.Command;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class SearchCommand {

	DataSearchManager dataSearchManager;
	Indexer indexer;

	public SearchCommand(DataSearchManager dataSearchManager, Indexer indexer) {
		this.dataSearchManager = dataSearchManager;
		this.indexer = indexer;
	}

	@Command(command = "search", description = "Search for entries in jar or zip files")
	public String search(@NotEmpty(message = "You must inform a pattern to be search") String pattern) {
		StringBuilder sb = new StringBuilder();
		Set<ItemResult> foundEntries = dataSearchManager.match(pattern);
		for (ItemResult itemResult : foundEntries) {
			if (itemResult.isClassEntry()) {
				sb.append(itemResult).append("\n");
			}
		}
		sb.append("\nThe search search for '").append(pattern).append("' has found ").append(foundEntries.size()).append(" matches");
		return sb.toString();
	}

}
