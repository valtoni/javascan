package info.boaventura.scan.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PathHandlerTokenizedTest {

    PathHandlerTokenized pathHandler;

    @BeforeEach
    public void setup() {
        pathHandler = new PathHandlerTokenized();
    }

    @Test
    void addPath() {
        pathHandler.addPath("${PATH}");
        int pathsFound = pathHandler.getPaths().size();
        assertInstanceOf(Integer.class, pathsFound, "A value is expected");
    }

}