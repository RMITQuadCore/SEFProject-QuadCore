import main.Main;
import model.Constraint;
import model.ProjectManager;
import model.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProjectManagerTestForRequiredPersonalityConstraint {

    @Before
    public void setUp() throws Exception {
        Main.updateArrays();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void requiredPersonalityConstraintApplicator() {
        ArrayList<Student> studentListForTest = new ArrayList<>();
        for(Student student : ProjectManager.studentsNotInATeam)
        {
            if(student.getFirstName().compareTo("Nana")==0)
            {
                studentListForTest.add(student);
            }
            if(student.getFirstName().compareTo("deepika")==0)
            {
                studentListForTest.add(student);
            }
            if(student.getFirstName().compareTo("Shahrukh")==0)
            {
                studentListForTest.add(student);
            }
            if(student.getFirstName().compareTo("Amitabh")==0)
            {
                studentListForTest.add(student);
            }
        }
        boolean requiredPersonalityConstraintMet;
        studentListForTest = ProjectManager.requiredPersonalityApplicator(studentListForTest);
        requiredPersonalityConstraintMet = Constraint.requiredPersonalityConstraintCheck(studentListForTest);
        assertTrue(requiredPersonalityConstraintMet);

    }
}