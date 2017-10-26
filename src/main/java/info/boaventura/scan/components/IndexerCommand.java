package info.boaventura.scan.components;

import info.boaventura.scan.core.MainSetting;
import info.boaventura.scan.core.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.zip.ZipFile;

@Component
public class IndexerCommand implements CommandMarker {

	@Autowired
	Result result;

	@CliCommand(value = "mount", help = "Mount system index classes")
	public String mount(@CliOption(key = "", help = "Mount ") String title) {
		if (MainSetting.mounted) return "Index was already mounted";
		else if (MainSetting.getPaths().size() == 0) return "Add path to index first (index --add <file|enviroment_path>)";
		StringBuffer sb = new StringBuffer();
		for (Path item: MainSetting.getPaths()) {
			sb.append(result.mount(item.toFile()));
		}
		MainSetting.mounted = true;
		return sb.toString();
	}


	@CliCommand(value = "index", help = "Add or clean index classes")
	public String index(
			@CliOption(key = "add", mandatory = false, help = "add item (index --add <file|enviroment_path>) to index (can be comma separated)") String add,
			@CliOption(key = "clean", mandatory = false, specifiedDefaultValue = "c", help = "clean index") String clean) {

		if (add == null && clean == null) {
			return entries();
		}
		else if (add != null) {
			MainSetting.addPath(add);
			return "Added directories to search";
		}
		else {
			result.setup();
			MainSetting.cleanPaths();
			return "Index was cleaned";
		}
	}


	private String entries() {
		if (MainSetting.getPaths().size() == 0) return "Add path to index first (index --add <file|enviroment_path>)";
		StringBuffer sb = new StringBuffer();

		if (!MainSetting.mounted) {
			sb.append("Warning: to search over entries you must mount indice (mount)\n");
			sb.append("Directories: \n");
			for (Path path: MainSetting.getPaths()) {
				sb.append("- " + path.toAbsolutePath() + "\n");
			}
		}
		else {
			sb.append("Available entries: \n");
			Collection<ZipFile> files = result.getIndexedFiles();
			for (ZipFile entry: files) {
				sb.append("- " + entry.getName());
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
