package info.boaventura.scan.core;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Main settings from current instance.
 */
public final class MainSetting {

	/** Current work dir */
	private static Path current = Paths.get(".").toAbsolutePath().normalize();

	private static List<Path> indexedPaths = new ArrayList<>();

	public static Boolean mounted = false;

	private static final String START_VAR;
	private static final String END_VAR;
	private static final String REGEX_PATH_SEP;

	static {
		String os = System.getProperty("os.name").toLowerCase();
		System.out.println(os);
		if (os.indexOf("win") >= 0 && os.indexOf("cygwin") == -1) {
			START_VAR = "%";
			END_VAR = "%";
			REGEX_PATH_SEP = "[,|;]";
		}
		else {
			START_VAR = "${";
			END_VAR = "}";
			REGEX_PATH_SEP = "[:]";
		}
	}

	private static String expand(String startVar, String endVar, String item) {
		if (item == null  || item.length() < startVar.length() + endVar.length()) return item;
		int first = item.indexOf(startVar);
		if (first < 0) return item;
		int last = item.indexOf(endVar, startVar.length() + 1);
		if (last < 0) return item;
		String variable = item.substring(first + startVar.length(), last - endVar.length() + 1);
		String value = System.getenv(variable);
		System.out.println("Expanded variable <" + variable + "> to " + value);
		return value;
	}

	private static String expand(String item) {
		if (item.startsWith("$") && !item.startsWith("${")) {
			return expand(START_VAR, END_VAR, START_VAR + item.substring(1) + END_VAR);
		}
		return expand(START_VAR, END_VAR, item);
	}

	public static void main(String[] args) {
		addPath("$CLASSPATH");
		for (Path i: indexedPaths) {
			System.out.println(i.toString());
		}
	}

	public static synchronized List<Path> getPaths() {
		return indexedPaths;
	}

	private static boolean pathContains(String item) {
		for (byte b: REGEX_PATH_SEP.getBytes()) {
			if (item.contains("" + (char)b)) {
				return true;
			}
		}
		return false;
	}

	public static synchronized void addPath(String tumblr) {
		// Splits classpath, path env variables: "," or ";" or ":"
		String[] items = tumblr.split(REGEX_PATH_SEP);
		String finalValue;
		for (String item: items) {
			finalValue = expand(item);
			if (pathContains(finalValue)) addPath(finalValue);
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
