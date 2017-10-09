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
	 * Do the tricks to mount a structure to be searched.
	 * @return Any trick mounting result search
	 * @throws IOException
	 */
	StringBuffer mount();

	/**
	 * Do the tricks to mount a structure to be searched, based on fileItem parameter.
	 * @param fileItem entry to mount a structure
	 * @return Any trick mounting result search
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
