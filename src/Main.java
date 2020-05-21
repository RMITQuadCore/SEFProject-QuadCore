import java.io.IOException;
import java.io.Serializable;

public class Main implements Serializable {

    public static String userFileName = "Users.txt";
    public static String studentsFileName = "Students.txt";
    public static String projectsFileName = "Projects.txt";
    public static String teamsFileName = "Teams.txt";

    public static void updateArrays() throws IOException, ClassNotFoundException {
        User.allUserDetails = FileReadWrite.readUserDetails(userFileName);//Updating User Array List
        Team.allTeams = FileReadWrite.readTeamDetails(teamsFileName);//Updating teams Array List
        Student.allStudents = FileReadWrite.readStudentDetails(studentsFileName);//Updating Students Array List
        Project.totalProjects = FileReadWrite.readProjectDetails(projectsFileName);//Updating Projects Array List
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {

        updateArrays();
        User user = new User();
        user.mainMenu();
    }
}
