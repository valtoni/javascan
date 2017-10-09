package info.boaventura.scan.core;

enum ScanType {

	JAR("jar", new ResultJar()), ZIP("zip", new ResultZip());

	Result result;
	String extension;

	ScanType(String extension, Result result) {
		this.extension = extension;
		this.result = result;
	}

}
