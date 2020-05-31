import main.Main;
import model.ProjectManager;
import model.Student;
import model.Team;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectManagerTestForSetProjectForTeam {

    @Before
    public void setUp() throws Exception {
        Main.updateArrays();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void setProjectForTeam() {
        ArrayList<Student> studentListForTest = new ArrayList<>();
        for(Student student : ProjectManager.studentsNotInATeam)
        {
            if(student.getFirstName().compareTo("Haffaz")==0)
            {
                studentListForTest.add(student);
            }
            if(student.getFirstName().compareTo("Katrina")==0)
            {
                studentListForTest.add(student);
            }
            if(student.getFirstName().compareTo("Aamir")==0)
            {
                studentListForTest.add(student);
            }
            if(student.getFirstName().compareTo("Salman")==0)
            {
                studentListForTest.add(student);
            }
        }
        Team team = ProjectManager.setProjectForTeam(studentListForTest);
        assertEquals(team.getProjectAssigned().getProjectId(),"PROJ005");
    }
}