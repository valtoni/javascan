package info.boaventura.scan.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractResult {

	File workDir;

	int dirs = 0;
	int files = 0;
	String extension;
	List<File> foundFiles;

	public AbstractResult(String extension) {
		this.workDir = MainSetting.current.toFile();
		this.extension = extension;
		setup();
	}

	private void setup() {
		dirs = 0;
		foundFiles = new ArrayList<>();
	}

	private boolean matchExtension(File file) {
		if (file.getName().endsWith(extension)) {
			return true;
		}
		return false;
	}

	public void mount() {
		mount(this.workDir);
	}

	public void mount(File fileItem) {
		for (File file: fileItem.listFiles()) {
			if (file.isDirectory()) {
				dirs++;
				mount(file);
			}
			else if (matchExtension(file)) {
				files++;
				foundFiles.add(file);
			}
		}
	}

}
