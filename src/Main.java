import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLOutput;

public class Main implements Serializable {

    public static String userFileName = "Users.txt";
    public static String studentsFileName = "Students.txt";
    public static String studentsNotInATeamFileName = "StudentsNotInATeam.txt";
    public static String projectsFileName = "Projects.txt";
    public static String projectsNotAssignedFileName = "ProjectsNotAssigned.txt";
    public static String teamsFileName = "Teams.txt";

   public static void updateArrays() throws IOException, ClassNotFoundException {
       User.allUserDetails = FileReadWrite.readUserDetails(userFileName);//Updating User Array List
       Team.allTeams = FileReadWrite.readTeamDetails(teamsFileName);//Updating teams Array List
       Student.allStudents = FileReadWrite.readStudentDetails(studentsFileName);//Updating Students Array List
       Project.totalProjects = FileReadWrite.readProjectDetails(projectsFileName);
       Project.projectsNotAssigned = FileReadWrite.readProjectDetails(projectsNotAssignedFileName);//Updating Projects Array List
       ProjectManager.studentsNotInATeam = FileReadWrite.readStudentDetails(studentsNotInATeamFileName);
   }


    public static void main(String[] args) throws IOException, ClassNotFoundException ,NullPointerException{
        updateArrays();
        User user = new User();
//        for (User user :  User.allUserDetails)
//        {
//            System.out.println(" username : " + user.getUserName() + "\t\tpassword: " + user.getPassword());
//        }
    }
}
