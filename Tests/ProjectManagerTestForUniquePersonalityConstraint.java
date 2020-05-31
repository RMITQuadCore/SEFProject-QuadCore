import main.Main;
import model.Constraint;
import model.ProjectManager;
import model.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProjectManagerTestForUniquePersonalityConstraint {

    @Before
    public void setUp() throws Exception {
        Main.updateArrays();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void uniquePersonalityConstraintApplicator() {
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
        boolean uniquePersonalityConstraintMet;
        studentListForTest = ProjectManager.uniquePersonalityConstraintApplicator(studentListForTest);
        uniquePersonalityConstraintMet = Constraint.uniquePersonalityConstraintCheck(studentListForTest);
        assertTrue(uniquePersonalityConstraintMet);

    }
}