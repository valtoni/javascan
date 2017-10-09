package info.boaventura.scan.core;

import java.util.List;

public class ResultJar extends AbstractResult implements Result {

	public ResultJar(String extension) {
		super(extension);
	}

	@Override
	public List<String> matchedClasses() {
		return null;
	}

}
