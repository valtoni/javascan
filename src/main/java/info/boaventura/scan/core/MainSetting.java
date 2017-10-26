package info.boaventura.scan.core;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Main settings from current instance.
 */
public final class MainSetting {

	/** Current work dir */
	private static Path current = Paths.get(".").toAbsolutePath().normalize();

	private static List<Path> indexedPaths = new ArrayList<>();

	public static Boolean mounted = false;

	private static String expand(String variable) {
		if (variable == null) return null;
		else if (variable.startsWith("$")) {
			return System.getenv(variable.substring(1));
		}
		return variable;
	}

	public static void main(String[] args) {
		addPath("$PATH");
		for (Path i: indexedPaths) {
			System.out.println(i.toString());
		}
	}

	public static synchronized List<Path> getPaths() {
		return indexedPaths;
	}

	public static synchronized void addPath(String tumblr) {
		// Splits classpath, path env variables: "," or ";" or ":"
		String[] items = tumblr.split("[,|;|:]");
		String finalValue;
		for (String item: items) {
			finalValue = expand(item);
			if (finalValue.contains(",") || finalValue.contains(";")) addPath(finalValue);
			else {
				Path path = Paths.get(finalValue);
				File file = path.toFile();
				if (!file.canRead()) {
					System.out.println("ERROR: Path " + path.toString() + " cannot be readed");
				} else if (!file.exists()) {
					System.out.println("ERROR: Path " + path.toString() + " was not found");
				} else if (file.exists()) {
					System.out.println("Path " + path.toString() + " added to index");
					indexedPaths.add(path);
				} else {
					System.out.println("ERROR: Path " + path.toString() + " cannot be added");
				}
			}
		}
		mounted = false;
	}

	public static synchronized void removePath(String tumblr) {
		String[] items = tumblr.split(",");
		String finalValue;
		for (String item: items) {
			finalValue = expand(item);
			if (finalValue.contains(",")) addPath(finalValue);
			Path path = Paths.get(finalValue);
			if (indexedPaths.remove(path)) {
				System.out.println("Item " + item + " was removed from index");
			}
			else {
				System.out.println("Item " + item + " was not found on index");
			}
		}
		mounted = false;
	}

	public static void cleanPaths() {
		mounted = false;
		indexedPaths.clear();
	}

}
