package info.boaventura.scan.components;

import info.boaventura.scan.core.MainSetting;
import info.boaventura.scan.core.Result;
import info.boaventura.scan.core.ResultEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class SearchCommand implements CommandMarker {

	@Autowired
	Result result;

	@Autowired
	IndexerCommand indexerCommand;

	@CliCommand(value = "search", help = "Search for entries in jar or zip files")
	public String search(@CliOption(key = "") String pattern,
											 @CliOption(key = "all") String all) {
		if (!MainSetting.mounted) {
			System.out.println("Index has need to be mounted: mounting...");
			indexerCommand.mount(null);
		}
		boolean matchAll = all != null;
		if (pattern == null && !matchAll) {
			return "Oh, man... Please, show me some pattern, buddy. I sure that i'll show you matched results.";
		}
		StringBuffer sb = new StringBuffer("");
		Set<ResultEntry> foundEntries = result.match(pattern);
		for (ResultEntry resultEntry: foundEntries) {
			if ((!matchAll && resultEntry.isClassEntry()) || matchAll) {
				sb.append(resultEntry.toString() + "\n");
			}
		}
		sb.append("\nThe search search for '" + pattern + "' has found " + foundEntries.size() + " matches");
		return sb.toString();
	}

}
