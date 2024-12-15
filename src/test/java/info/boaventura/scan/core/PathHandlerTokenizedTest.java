package info.boaventura.scan.core;

import info.boaventura.scan.core.variables.VariableExpander;
import info.boaventura.scan.core.variables.VariableExpanderEnvironment;
import info.boaventura.scan.core.variables.VariableStyle;
import info.boaventura.scan.core.variables.VariableStyleBash;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import info.boaventura.scan.core.PathHandlerTokenized;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;

@ExtendWith( MockitoExtension.class )
class PathHandlerTokenizedTest {

    @Mock
    VariableStyle variableStyle;

    @Test
    void addOnePath() {
        String tmpdir = System.getProperty("java.io.tmpdir");
        lenient().when(variableStyle.replace("${PATH}")).thenReturn(tmpdir);
        PathHandlerTokenized pathHandler = new PathHandlerTokenized(variableStyle);
        pathHandler.addPath("${PATH}");
        int pathsFound = pathHandler.getPaths().size();
        assertInstanceOf(Integer.class, pathsFound, "A value is expected");
        assertEquals(1, pathsFound, "1 path was expected");
        assertEquals(Path.of(tmpdir), pathHandler.getPaths().getFirst());
    }

    @Test
    void addTwoPaths() {
        String tmpdir = System.getProperty("java.io.tmpdir");
        lenient().when(variableStyle.replace("${PATH}")).thenReturn(tmpdir);
        PathHandlerTokenized pathHandler = new PathHandlerTokenized(variableStyle);
        pathHandler.addPath("${PATH}");
        pathHandler.addPath("${PATH}");
        int pathsFound = pathHandler.getPaths().size();
        assertInstanceOf(Integer.class, pathsFound, "A value is expected");
        assertEquals(2, pathsFound, "2 paths was expected");
        assertEquals(Path.of(tmpdir), pathHandler.getPaths().getFirst());
        assertEquals(Path.of(tmpdir), pathHandler.getPaths().getLast());
    }


}