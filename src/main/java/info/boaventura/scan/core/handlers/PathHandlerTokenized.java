package info.boaventura.scan.core;

import info.boaventura.scan.core.variables.VariableExpanderEnvironment;
import info.boaventura.scan.core.variables.VariableStyle;
import info.boaventura.scan.core.variables.VariableStyleBash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Path handler.
 */
@Component
public class PathHandlerTokenized implements PathHandler {

	private final Logger log = LoggerFactory.getLogger(PathHandlerTokenized.class);

	/** Current work dir */
	private static final Path CURRENT_WORK_DIR = Paths.get(".").toAbsolutePath().normalize();
	private final List<Path> indexedPaths = new ArrayList<>();

	private static final String REGEX_PATH_SEP = File.pathSeparator;

	private VariableStyle variableStyle = new VariableStyleBash(new VariableExpanderEnvironment());

	public List<Path> getPaths() {
		return indexedPaths;
	}

	private boolean pathContains(String item) {
		for (byte b: REGEX_PATH_SEP.getBytes()) {
			if (item.contains("" + (char)b)) {
				return true;
			}
		}
		return false;
	}

	public void addPath(String tumblr) {
		// Splits classpath
		String[] items = tumblr.split(REGEX_PATH_SEP);
		String finalValue;
		for (String item: items) {
			finalValue = variableStyle.replace(item);
			if (pathContains(finalValue)) addPath(finalValue);
			else {
				Path path = Paths.get(finalValue);
				File file = path.toFile();
				if (!file.canRead()) {
					log.error("Path {} cannot be read", path);
				} else if (!file.exists()) {
					log.error("Path {} was not found", path);
				} else if (file.exists()) {
					log.debug("Path {} added to index", path);
					indexedPaths.add(path);
				} else {
					log.error("Path {} cannot be added", path);
				}
			}
		}
	}

	public void removePath(String tumblr) {
		String[] items = tumblr.split(",");
		String finalValue;
		for (String item: items) {
			finalValue = variableStyle.replace(item);
			if (finalValue.contains(",")) addPath(finalValue);
			Path path = Paths.get(finalValue);
			if (indexedPaths.remove(path)) {
				log.debug("Path {} was removed from index", path);
			}
			else {
				log.warn("Path {} was not found on index", path);
			}
		}
	}

	public void cleanPaths() {
		indexedPaths.clear();
	}

	public boolean isReady() {
		return indexedPaths.isEmpty();
	}

}
