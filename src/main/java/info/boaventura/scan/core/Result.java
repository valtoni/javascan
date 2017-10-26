package info.boaventura.scan.core;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipFile;

/**
 * Data search structure to entries of type {@link ResultEntry}.
 */
public interface Result {

	/**
	 * Prepare for a new search.
	 * Call this method before {@link #mount(File)}.
	 * @see #mount(File)
	 */
	void setup();

	/**
	 * Do the tricks to mount a structure to be searched, based on fileItem parameter.
	 * Note: the method {@link #setup()} must be called before this method.
	 * @param fileItem entry to mount a structure
	 * @return Any trick mounting result search
	 * @see #setup()
	 * @throws IOException
	 */
	StringBuffer mount(File fileItem);

	/**
	 * Files that was indexed.
	 * @return Indexed files
	 */
	Collection<ZipFile> getIndexedFiles();

	/**
	 * Match expression, returning the {@link Set} of matched {@link ResultEntry} instances.
	 *
	 * @param expression
	 * @return Matched entries
	 * @see ResultCached#match(String)
	 */
	Set<ResultEntry> match(String expression);

}
