//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ProjectManagerTeamAssignmentTest {
//    ArrayList<model.Student> teamStudent = new ArrayList<model.Student>();
//    model.ProjectManager boss = new model.ProjectManager();
//
//    @BeforeEach
//    void setUp() {
//        System.out.println(" Before test");
//
//        model.Student s1 = new model.Student("S1", "Karan", "Thapar", "karan.234@gmail.com", "karan234", "12345", "RMIT", 3.4, 2.2, 'M', 'A');
//        model.Student s2 = new model.Student("S2", "Karan", "Thapar", "karan.234@gmail.com", "karan234", "12345", "RMIT", 3.4, 2.2, 'F', 'A');
//        model.Student s3 = new model.Student("S3", "Karan", "Thapar", "karan.234@gmail.com", "karan234", "12345", "RMIT", 3.4, 2.2, 'M', 'A');
//        model.Student s4 = new model.Student("S4", "Karan", "Thapar", "karan.234@gmail.com", "karan234", "12345", "RMIT", 3.4, 2.2, 'M', 'A');
//        model.Project proj1 = new model.Project();
//        model.Project proj2 = new model.Project();
//        model.Project proj3 = new model.Project();
//        model.Project proj4 = new model.Project();
//        proj1.setProjectId("PROJ001");
//        proj2.setProjectId("PROJ002");
//        proj3.setProjectId("PROJ003");
//        proj4.setProjectId("PROJ004");
//        model.Project.totalProjects.add(proj1);
//        model.Project.totalProjects.add(proj2);
//        model.Project.totalProjects.add(proj3);
//        model.Project.totalProjects.add(proj4);
//        model.Project[] preference1 = new model.Project[]{proj4, proj1, proj2, proj3};
//        model.Project[] preference2 = new model.Project[]{proj2, proj3, proj4, proj1};
//        model.Project[] preference3 = new model.Project[]{proj1, proj3, proj4, proj2};
//        model.Project[] preference4 = new model.Project[]{proj4, proj3, proj1, proj2};
//        //Preference for proj1 - 3 + 1 + 4 + 2 = 10
//        //Preference for proj2 - 2 + 4 + 1 + 1 = 8
//        //Preference for proj3 - 1 + 3 + 3 + 3 = 10
//        //Preference for proj3 - 4 + 2 + 2 + 4 = 12
//        // Highest Preference for this group is for proj4
//        s1.setPreferredProjects(preference1);
//        s2.setPreferredProjects(preference2);
//        s3.setPreferredProjects(preference3);
//        s4.setPreferredProjects(preference4);
//        teamStudent.add(s1);
//        teamStudent.add(s2);
//        teamStudent.add(s3);
//        teamStudent.add(s4);
//    }
//
//    @AfterEach
//    void tearDown() {
//        System.out.println(" After test");
//    }
//
//    @Test
//    void setProjectForTeam() {
//        model.Team t1 = boss.setProjectForTeam(teamStudent);
//        assertEquals("PROJ004",t1.getProjectAssigned().getProjectId());
//    }
//
//    @Test
//    void checkStudentsInTeam() {
//        model.Team t1 = boss.setProjectForTeam(teamStudent);
//        assertEquals(4, t1.getStudentsInTeam().size());
//    }
//}