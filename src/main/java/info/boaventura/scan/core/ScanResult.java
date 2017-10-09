package info.boaventura.scan.core;

public class ScanResult {

	private String library;
	private String path;
	private String criteria;
	private String packageName;
	private String className;

	ScanResult(String criteria, String library, String path, String packageName, String className) {
		this.criteria = criteria;
		this.library = library;
		this.path = path;
		this.packageName = packageName;
		this.className = className;
	}

	public String getlibrary() {
		return this.library;
	}

	public String getpath() {
		return this.path;
	}

	public String getcriteria() {
		return this.criteria;
	}

	public String getPackageName() {
		return this.packageName;
	}

	public String getClassName() {
		return this.className;
	}

}
