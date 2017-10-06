package info.boaventura.scan;


import org.springframework.shell.Bootstrap;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		try {
			Bootstrap.main(args);
		} catch (IOException e) {
			System.err.println("Impossible starts scan console: " + e.getMessage());
		}
	}

}
