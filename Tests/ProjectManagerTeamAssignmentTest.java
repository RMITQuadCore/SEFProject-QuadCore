import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProjectManagerTeamAssignmentTest {
    ArrayList<Student> teamStudent = new ArrayList<Student>();
    ProjectManager boss = new ProjectManager();

    @BeforeEach
    void setUp() {
        System.out.println(" Before test");

        Student s1 = new Student("S1", "Karan", "Thapar", "karan.234@gmail.com", "karan234", "12345", "RMIT", 3.4, 2.2, 'M', 'A');
        Student s2 = new Student("S2", "Karan", "Thapar", "karan.234@gmail.com", "karan234", "12345", "RMIT", 3.4, 2.2, 'F', 'A');
        Student s3 = new Student("S3", "Karan", "Thapar", "karan.234@gmail.com", "karan234", "12345", "RMIT", 3.4, 2.2, 'M', 'A');
        Student s4 = new Student("S4", "Karan", "Thapar", "karan.234@gmail.com", "karan234", "12345", "RMIT", 3.4, 2.2, 'M', 'A');
        Project proj1 = new Project();
        Project proj2 = new Project();
        Project proj3 = new Project();
        Project proj4 = new Project();
        proj1.setProjectId("PROJ001");
        proj2.setProjectId("PROJ002");
        proj3.setProjectId("PROJ003");
        proj4.setProjectId("PROJ004");
        Project.totalProjects.add(proj1);
        Project.totalProjects.add(proj2);
        Project.totalProjects.add(proj3);
        Project.totalProjects.add(proj4);
        Project[] preference1 = new Project[]{proj4, proj1, proj2, proj3};
        Project[] preference2 = new Project[]{proj2, proj3, proj4, proj1};
        Project[] preference3 = new Project[]{proj1, proj3, proj4, proj2};
        Project[] preference4 = new Project[]{proj4, proj3, proj1, proj2};
        //Preference for proj1 - 3 + 1 + 4 + 2 = 10
        //Preference for proj2 - 2 + 4 + 1 + 1 = 8
        //Preference for proj3 - 1 + 3 + 3 + 3 = 10
        //Preference for proj3 - 4 + 2 + 2 + 4 = 12
        // Highest Preference for this group is for proj4
        s1.setPreferredProjects(preference1);
        s2.setPreferredProjects(preference2);
        s3.setPreferredProjects(preference3);
        s4.setPreferredProjects(preference4);
        teamStudent.add(s1);
        teamStudent.add(s2);
        teamStudent.add(s3);
        teamStudent.add(s4);
    }

    @AfterEach
    void tearDown() {
        System.out.println(" After test");
    }

    @Test
    void setProjectForTeam() {
        Team t1 = boss.setProjectForTeam(teamStudent);
        assertEquals("PROJ004",t1.getProjectAssigned().getProjectId());
    }

    @Test
    void checkStudentsInTeam() {
        Team t1 = boss.setProjectForTeam(teamStudent);
        assertEquals(4, t1.getStudentsInTeam().size());
    }
}