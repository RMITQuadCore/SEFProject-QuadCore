

import model.Project;
import model.ProjectManager;
import model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscardUnpopularProjectTest {

    model.Project p=new model.Project();
    model.Student s = new model.Student();

    @BeforeEach
    void setUp() throws Exception {
        model.ClientRepresentative twitter = new model.ClientRepresentative("CL201","Jack","Dorsey","jack@twitter.com","twitter","twitter","Twitter");



        model.ClientRepresentative telstra = new model.ClientRepresentative("CL202","Andy","Penn","andy@telstra.com","telstra","telstra","Telstra");


        model.Project rezolous = new model.Project(twitter, "PROJ201", "rezolous","Systems performance telemetry");
        Project.totalProjects.add(rezolous);

        model.Project finatra = new model.Project(twitter, "PROJ202",  "finatra", "Fast, testable, Scala services built on TwitterServer and Finagle");
        Project.totalProjects.add(finatra);

        model.Project fiveG = new model.Project(telstra, "PROJ203", "5G", "5G technology");
        Project.totalProjects.add(fiveG);

        model.Project telstraLocator = new model.Project(telstra, "PROJ204",  "Telstra Locator", "Telstra Locator");
        Project.totalProjects.add(telstraLocator);

        model.Student s1= new model.Student("ST201","haffaz","mohammad","haffaz@gmail.com","haffaz","haffaz","rmit",3.4,6,'M');
        model.ProjectManager.studentsNotInATeam.add(s1);
        Student.allStudents.add(s1);

        model.Student s2= new model.Student("ST202","aamir","khan","aamir@gmail.com","aamir","aamir","rmit",2.7,2,'M');
        model.ProjectManager.studentsNotInATeam.add(s2);
        Student.allStudents.add(s2);

        model.Student s3= new model.Student("ST203","nana","patekar","nana@gmail.com","nana","nana","rmit",3.3,1,'M');
        model.ProjectManager.studentsNotInATeam.add(s3);
        Student.allStudents.add(s3);

        model.Student s4= new model.Student("ST204","deepika","padukone","deepika@gmail.com","deepika","deepika","rmit",2.5,3,'F');
        model.ProjectManager.studentsNotInATeam.add(s4);
        Student.allStudents.add(s4);

        model.Student s5= new model.Student("ST205","shahrukh","khan","shahrukh@gmail.com","shahrukh","shahrukh","rmit",3.2,4,'M');
        model.ProjectManager.studentsNotInATeam.add(s5);
        Student.allStudents.add(s5);

        Student s6= new Student("ST206","amitabh","bachchan","amitabh@gmail.com","amitabh","amitabh","rmit",3.2,3,'M');
        ProjectManager.studentsNotInATeam.add(s6);
        Student.allStudents.add(s6);

        Student s7= new Student("ST207","salman","khan","salman@gmail.com","salman","salman","rmit",2.9,2,'M');
        ProjectManager.studentsNotInATeam.add(s7);
        Student.allStudents.add(s7);

        Student s8= new Student("ST208","juilee","kulkarni","juilee@gmail.com","juilee","juilee","rmit",3.5,7,'F');
        ProjectManager.studentsNotInATeam.add(s8);
        Student.allStudents.add(s8);

        Project.totalProjects.get(0).setPopularityCounter(12);
//        Project.totalProjects.get(1).setPopularityCounter(5);
//        Project.totalProjects.get(2).setPopularityCounter(16);
//        Project.totalProjects.get(3).setPopularityCounter(10);

    }

    @Test
    void discardUnpopularProjects() {
        int check = 0;

        try {
            if (p.discardUnpopularProjects())
                check++;
        } catch (model.ProjectMismatchException e) {
            // Auto-generated catch block
            e.printStackTrace();
        }

        assertEquals(1, check);
    }
}