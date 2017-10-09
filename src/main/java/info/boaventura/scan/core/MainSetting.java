package info.boaventura.scan.core;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Main settings from current instance.
 */
public final class MainSetting {

	/** Current work dir */
	public static Path current = Paths.get(".").toAbsolutePath().normalize();


}
