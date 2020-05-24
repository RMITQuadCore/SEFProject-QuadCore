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
        int PMCount =0;
        int CLCount =0;
        int STCount =0;

        for (User user : User.allUserDetails)
        {
            if (user.getId().contains("ST"))
            {
                STCount++;
            }
            if (user.getId().contains("CL"))
            {
                CLCount++;
            }
            if (user.getId().contains("PM"))
            {
                PMCount++;
            }
        }
        System.out.println(" The Number of students Registered in allstudent array " + Student.allStudents.size());
        System.out.println(" The Number of students Registered in studentNotinTeam array " + ProjectManager.studentsNotInATeam.size());
        System.out.println(" The Number of students Registered in userdetails are " + STCount);
        System.out.println(" The Number of PM  Registered are " + PMCount);
        System.out.println(" The Number of clients Registered are " + CLCount);
        System.out.println(" The Number of projects Registered are " + Project.totalProjects.size());
        System.out.println(" The Number of projects not assigned are " + Project.projectsNotAssigned.size());
        System.out.println(" The Number of teams made are " + Team.allTeams.size());
         User user = new User();
         user.mainMenu();
        System.out.println("The Students Registered are : \n");
        for (Student student : Student.allStudents)
        {
            System.out.println(student.getId() + "  Name: " + student.getFirstName());
        }
//        User.allUserDetails.remove((User.allUserDetails.size())-1);
//        FileReadWrite.saveUserDetails(Main.userFileName,User.allUserDetails);
        System.out.println("The Users Registered are : \n");
        for (User user1 : User.allUserDetails)
        {
            System.out.println(user1.getId() + "  Name: " + user1.getFirstName());
        }
        System.out.println("The Projects Registered are : \n");
        for (Project project :  Project.totalProjects)
        {
            System.out.println(" Client: " +project.getClient().getOrganisation() + "  Project ID : " + project.getProjectId() +  "  Project Title : " + project.getProjectTitle());
        }
    }
}
