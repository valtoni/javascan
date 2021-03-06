package info.boaventura.scan.core;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipFile;

/**
 * Search for files with {@link #extensions} and, in startpoint file {@link MainSetting#current} do
 * a recursive search in {@link #mount(File)} to find matched file classes in each file.
 *
 * The method {@link #mount(File)} can be called indicating another startpoint.
 *
 * @author Valtoni Boaventura - valtoni@gmail.com
 */
public class ResultCached implements Result {

	int dirs = 0;
	int files = 0;

	/** Extensions to lead */
	String[] extensions = new String[] {"jar", "zip"};

	/** A jar file is a zipfile too, than, we can treat in one type only **/
	List<ZipFile> availableFiles;
	SortedSet<ResultEntry> cachedEntries;

	public ResultCached() {
		setup();
	}

	public void setup() {
		dirs = 0;
		availableFiles = new ArrayList<>();
		cachedEntries = new TreeSet<>();
	}

	private boolean matchExtension(File file) {
		for (String extension: extensions) {
			if (file.getName().toLowerCase().endsWith(extension)) {
				return true;
			}
		}
		return false;
	}

	private String addFile(File file) {
		if (matchExtension(file)) {
			try {
				final ZipFile zipFile = new ZipFile(file);
				files++;
				availableFiles.add(zipFile);
				cachedEntries.addAll(Collections.list(zipFile.entries()).stream()
						.map(z -> new ResultEntry(file.toPath(), zipFile, z))
						.collect(CollectorSorted.toSortedSet()));
				return "Added library " + zipFile.getName();
			} catch (IOException e) {
				return "Error accessing " + file.getName() + ": " + e.getMessage();
			}
		}
		return "";
	}

	@Override
	public StringBuffer mount(File fileItem) {
		StringBuffer result = new StringBuffer();
		if (fileItem.isDirectory()) {
			System.out.println("Mounting " + fileItem.getName() + "...");
			File[] fileList = fileItem.listFiles();
			if (fileList == null) return new StringBuffer("No libraries available at " + fileItem.getName());
			for (File file: fileItem.listFiles()) {
				if (file.isDirectory()) {
					dirs++;
					mount(file);
				}
				else {
					result.append(addFile(file));
				}
			}
		}
		else {
			result.append(addFile(fileItem));
		}
		return result;
	}

	@Override
	public Collection<ZipFile> getIndexedFiles() {
		return availableFiles;
	}

	/**
	 * Match a search for expression.
	 * The expression to be search will use {@link SortedSet#subSet(Object, Object)} and put a non
	 * inclusive string in end range, adding "x" 20 times at end of expression parameter.
	 * @param expression expression to be search
	 * @return a set containing matched expression
	 */
	@Override
	public Set<ResultEntry> match(String expression) {
		return cachedEntries.stream()
				.filter(re -> expression == null ? true : re.getEntry().toLowerCase().contains(expression.toLowerCase()))
				.collect(Collectors.toSet());
	}

}
