package info.boaventura.scan.components;

import info.boaventura.scan.core.MainSetting;
import info.boaventura.scan.core.Result;
import info.boaventura.scan.core.ResultEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

@Component
public class SearchCommand implements CommandMarker {

	@Autowired
	Result result;

	@CliCommand(value = "search", help = "Search for entries in jar or zip files")
	public String search(@CliOption(key = "") String pattern,
											 @CliOption(key = "all") String all) {
		StringBuffer sb = new StringBuffer("");
		boolean matchAll = all != null;
		for (ResultEntry resultEntry: result.match(pattern)) {
			if ((!matchAll && resultEntry.isClassEntry()) || matchAll) {
				sb.append(resultEntry.toString(MainSetting.current) + "\n");
			}
		}
		return sb.toString();
	}

}
