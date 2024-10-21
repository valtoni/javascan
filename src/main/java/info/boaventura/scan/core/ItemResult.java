package info.boaventura.scan.core;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ItemResult implements Comparable<ItemResult> {

	private final Path path;
	private final ZipFile zipFile;
	private final ZipEntry zipEntry;
	private final String entry;

	public ItemResult(Path path, ZipFile zipFile, ZipEntry zipEntry) {
		this.path = path;
		this.zipFile = zipFile;
		this.zipEntry = zipEntry;
		this.entry = zipEntry.getName().replace("/", ".").replace(".class", "");
	}

	public ZipFile getZipFile() {
		return zipFile;
	}

	public ZipEntry getZipEntry() {
		return zipEntry;
	}

	public String getEntry() {
		return entry;
	}

	@Override
	public int compareTo(ItemResult o) {
		if (o == null) return -1;
		return zipEntry.getName().compareTo(o.getZipEntry().getName());
	}

	@Override
	public String toString() {
		if (path.toString().equals(zipFile.getName())) {
			return zipFile.getName() + ": " + entry;
		}
		else {
			return path.relativize(Paths.get(zipFile.getName())) + ": " + entry;
		}
	}

	public boolean isClassEntry() {
		return zipEntry.getName().toLowerCase().endsWith(".class");
	}

}
