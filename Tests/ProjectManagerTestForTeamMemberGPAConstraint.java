import model.Constraint;
import model.ProjectManager;
import model.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProjectManagerTestForTeamMemberGPAConstraint {
    private static ArrayList<Student> teamCreator = new ArrayList<>();

    @BeforeAll
    static void beforeAll() {
        Student s1= new Student("ST201","haffaz","mohammad","haffaz@gmail.com","haffaz","haffaz","rmit",3.8,6,'M');
        teamCreator.add(s1);
        Student s2= new Student("ST202","aamir","khan","aamir@gmail.com","aamir","aamir","rmit",2.5,2,'M');
        teamCreator.add(s2);
        Student s3= new Student("ST203","nana","patekar","nana@gmail.com","nana","nana","rmit",2.5,1,'M');
        teamCreator.add(s3);
        Student s4= new Student("ST204","deepika","padukone","deepika@gmail.com","deepika","deepika","rmit",2,3,'F');
        teamCreator.add(s4);
        Student s5= new Student("ST205","shahrukh","khan","shahrukh@gmail.com","shahrukh","shahrukh","rmit",3.8,4,'M');
        ProjectManager.studentsNotInATeam.add(s5);
        Student s6= new Student("ST206","amitabh","bachchan","amitabh@gmail.com","amitabh","amitabh","rmit",2.5,3,'M');
        ProjectManager.studentsNotInATeam.add(s6);
        Student s7= new Student("ST207","salman","khan","salman@gmail.com","salman","salman","rmit",2.8,3.5,'M');
        ProjectManager.studentsNotInATeam.add(s7);
        Student s8= new Student("ST208","juilee","kulkarni","juilee@gmail.com","juilee","juilee","rmit",4,7,'F');
        ProjectManager.studentsNotInATeam.add(s8);
    }

    @Test
    void teamMemberGPAConstraintApplicatorTest(){

        teamCreator = ProjectManager.teamMemberGPAConstraintApplicator(teamCreator);

        for(Student student: teamCreator) {
            System.out.println("Name: "+student.getId()+" GPA: "+student.getGpa());
        }
        Assertions.assertTrue(Constraint.twoMembersWith3GPAHardConstraintCheck(teamCreator));
    }

    @Test
    void teamMemberGPAConstraintApplicatorTestRemaining(){
        teamCreator.clear();
        teamCreator = ProjectManager.teamMemberGPAConstraintApplicator(ProjectManager.studentsNotInATeam);

        for(Student student: teamCreator) {
            System.out.println("Name: "+student.getId()+" GPA: "+student.getGpa());
        }
        assertTrue(Constraint.twoMembersWith3GPAHardConstraintCheck(teamCreator));
    }
}