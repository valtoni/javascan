package info.boaventura.scan.core;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ResultEntry implements Comparable<ResultEntry> {

	private Path path;
	private ZipFile zipFile;
	private ZipEntry zipEntry;

	public ResultEntry(Path path, ZipFile zipFile, ZipEntry zipEntry) {
		this.path = path;
		this.zipFile = zipFile;
		this.zipEntry = zipEntry;
	}

	/**
	 * Fake entry to be used in {@link ResultCached#match(String)}
	 * @param name
	 */
	ResultEntry(String name) {
		zipEntry = new ZipEntry(name);
	}

	public ZipFile getZipFile() {
		return zipFile;
	}

	public ZipEntry getZipEntry() {
		return zipEntry;
	}

	@Override
	public int compareTo(ResultEntry o) {
		if (o == null) return -1;
		return zipEntry.getName().compareTo(o.getZipEntry().getName());
	}

	@Override
	public String toString() {
		if (path.toString().equals(zipFile.getName())) {
			return zipFile.getName() + ": " + zipEntry.getName().replace("/", ".");
		}
		else {
			return path.relativize(Paths.get(zipFile.getName())) + ": " + zipEntry.getName().replace("/", ".");
		}
	}

	public boolean isClassEntry() {
		return zipEntry.getName().toLowerCase().endsWith(".class");
	}

}
