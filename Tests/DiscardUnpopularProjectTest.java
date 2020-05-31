import main.Main;
import model.Project;
import model.ProjectMismatchException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscardUnpopularProjectTest {

    @BeforeEach
    void setUp() throws Exception {
        Main.updateArrays();
    }

    @Test
    void discardUnpopularProjects() throws ProjectMismatchException {
        assertEquals(5, Project.totalProjects.size());
        Project.discardUnpopularProjects();
        assertEquals(4, Project.totalProjects.size());
    }
}