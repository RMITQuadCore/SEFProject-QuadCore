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
//ToDO change file extension to .ser

   public static void updateArrays() throws IOException, ClassNotFoundException {
       User.allUserDetails = FileReadWrite.readUserDetails(userFileName);//Updating User Array List
       Team.allTeams = FileReadWrite.readTeamDetails(teamsFileName);//Updating teams Array List
       Student.allStudents = FileReadWrite.readStudentDetails(studentsFileName);//Updating Students Array List
       Project.totalProjects = FileReadWrite.readProjectDetails(projectsFileName);
       Project.projectsNotAssigned = FileReadWrite.readProjectDetails(projectsNotAssignedFileName);//Updating Projects Array List
       ProjectManager.studentsNotInATeam = FileReadWrite.readStudentDetails(studentsNotInATeamFileName);
   }


    /**
     * Main Method
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException ,NullPointerException{
        updateArrays();


        User user = new User();
      user.mainMenu();
//      Student.allStudents.remove((Student.allStudents.size())-1);
//      Student.allStudents.remove((Student.allStudents.size())-1);
//       User.allUserDetails.remove((User.allUserDetails.size())-1);
//        User.allUserDetails.remove((User.allUserDetails.size())-1);
//       FileReadWrite.saveUserDetails(Main.userFileName, User.allUserDetails);
//        Student.allStudents.add(new Student("ST012","Ranbir","Kapoor","ranbir@gmail.com","ranbir","ranbir","RMIT",3.1,1,'M'));
//        Student.allStudents.add(new Student("ST013","Rani","Mukherjee","rani@gmail.com","rani","rani","RMIT",3.5,2,'F'));
//        Student.allStudents.add(new Student("ST014","Kajol","Devgn","kajol@gmail.com","kajol","kajol","RMIT",2.5,3,'F'));
//        FileReadWrite.saveStudentDetails(Main.studentsFileName,Student.allStudents);
//        for(User u1:User.allUserDetails){
//            System.out.println(u1.getId()+" "+u1.getUserName());
//        }
//        System.out.println();
//        for(Student s1:Student.allStudents){
//            System.out.println(s1.getId()+" "+s1.getUserName());
//        }

    }
}
