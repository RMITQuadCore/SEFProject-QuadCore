import model.Constraint;
import model.ProjectManager;
import model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProjectManagerTestForFemaleConstraint {
    ArrayList<Student> teamCreator = new ArrayList<>();
    @BeforeEach
    void setUp() {
//        model.Student s1= new model.Student("ST201","haffaz","mohammad","haffaz@gmail.com","haffaz","haffaz","rmit",3.4,6,'M');
//        model.ProjectManager.studentsNotInATeam.add(s1);
//        model.Student s2= new model.Student("ST202","aamir","khan","aamir@gmail.com","aamir","aamir","rmit",2.7,2,'M');
//        model.ProjectManager.studentsNotInATeam.add(s2);
//        model.Student s3= new model.Student("ST203","nana","patekar","nana@gmail.com","nana","nana","rmit",3.3,1,'M');
//        model.ProjectManager.studentsNotInATeam.add(s3);
////        model.Student s4= new model.Student("ST204","deepika","padukone","deepika@gmail.com","deepika","deepika","rmit",2.5,3,'F');
////        model.ProjectManager.studentsNotInATeam.add(s4);
//        model.Student s5= new model.Student("ST205","shahrukh","khan","shahrukh@gmail.com","shahrukh","shahrukh","rmit",3.2,4,'M');
//        model.ProjectManager.studentsNotInATeam.add(s5);
        Student s6= new Student("ST206","amitabh","bachchan","amitabh@gmail.com","amitabh","amitabh","rmit",3.2,3,'M');
        ProjectManager.studentsNotInATeam.add(s6);
        Student s7= new Student("ST207","salman","khan","salman@gmail.com","salman","salman","rmit",2.9,2,'M');
        ProjectManager.studentsNotInATeam.add(s7);
        Student s8= new Student("ST208","juilee","kulkarni","juilee@gmail.com","juilee","juilee","rmit",3.5,7,'F');
        ProjectManager.studentsNotInATeam.add(s8);
        Student s9= new Student("ST209","alia","bhatt","alia@gmail.com","alia","alia","rmit",2.5,4,'F');
        ProjectManager.studentsNotInATeam.add(s9);


    }

    @Test
    void femaleHardConstraintApplicatorTest() {
        teamCreator = ProjectManager.femaleHardConstraintApplicator(teamCreator,4);
        System.out.println("inside function");
        for(Student student: teamCreator) {
            System.out.println("Name: "+student.getFirstName()+" Gender: "+student.getGender());
        }
        assertEquals(Constraint.femaleHardConstraintCheck(teamCreator),true);
    }
}