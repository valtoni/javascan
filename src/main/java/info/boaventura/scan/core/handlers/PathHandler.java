package info.boaventura.scan.core.handlers;

import java.nio.file.Path;
import java.util.List;

public interface PathHandler {

    void addPath(String tumblr);

    void removePath(String tumblr);

    void cleanPaths();

    boolean isReady();

    List<Path> getPaths();

}
