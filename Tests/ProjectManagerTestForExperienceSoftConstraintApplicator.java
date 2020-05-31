import main.Main;
import model.Constraint;
import model.ProjectManager;
import model.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectManagerTestForExperienceSoftConstraintApplicator {

    @Before
    public void setUp() throws Exception {
        Main.updateArrays();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void experienceSoftConstraintApplicator() {
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
        boolean experienceConstraintMet =false;
        studentListForTest = ProjectManager.experienceSoftConstraintApplicator(studentListForTest);
        experienceConstraintMet = Constraint.memberWith5YearExperienceConstraintCheck(studentListForTest);
        assertEquals(experienceConstraintMet,true);

    }
}