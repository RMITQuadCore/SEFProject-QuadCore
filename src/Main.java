import java.io.IOException;
import java.io.Serializable;

public class Main implements Serializable {

    public static String userFileName = "Users.txt";
    public static String studentsFileName = "Students.txt";
    public static String studentsNotInATeamFileName = "StudentsNotInATeam.txt";
    public static String projectsFileName = "Projects.txt";
    public static String projectsNotAssignedFileName = "ProjectsNotAssigned.txt";
    public static String teamsFileName = "Teams.txt";
    public static String tempProjects1 = "TempProjects1.txt"; //totalProjects
    public static String tempProjects2 = "TempProjects2.txt"; //PRojectsnotassigned
    public static String softConstraintFileName = "SoftConstraint.txt";

   public static void updateArrays() throws IOException, ClassNotFoundException {
       User.allUserDetails = FileReadWrite.readUserDetails(userFileName);//Updating User Array List
       Team.allTeams = FileReadWrite.readTeamDetails(teamsFileName);//Updating teams Array List
       Student.allStudents = FileReadWrite.readStudentDetails(studentsFileName);//Updating Students Array List
//       Project.totalProjects = FileReadWrite.readProjectDetails(tempProjects1);
//       Project.projectsNotAssigned = FileReadWrite.readProjectDetails(tempProjects2);//Updating Projects Array List

       ProjectManager.studentsNotInATeam = FileReadWrite.readStudentDetails(studentsNotInATeamFileName);

       Project.totalProjects = FileReadWrite.readProjectDetails(projectsFileName);
       Project.projectsNotAssigned = FileReadWrite.readProjectDetails(projectsNotAssignedFileName);//Updating Projects Array List
       Constraint.allSoftConstraints = FileReadWrite.readConstraintDetails(softConstraintFileName);
       ProjectManager.constraints = FileReadWrite.readConstraintDetails(softConstraintFileName);
   }


    public static void main(String[] args) throws IOException, ClassNotFoundException ,NullPointerException{
        updateArrays();
//        FileReadWrite.saveStudentDetails(Main.studentsNotInATeamFileName,Student.allStudents);
        User user = new User();
        user.mainMenu();
//        for(Student s: ProjectManager.studentsNotInATeam){
//            for(Project p: s.getPreferredProjects())
//            {
//                System.out.println("Preferences: "+p.getProjectId());
//            }
//
//        }
//        Project[] projects=Student.allStudents.get(0).getPreferredProjects();
//        for(int i=0;i<4;i++){
//            System.out.println("Preferences1: "+projects[i].getProjectId());
//        }
//        for (User user :  User.allUserDetails)
//        {
//            System.out.println(" username : " + user.getUserName() + "\t\tpassword: " + user.getPassword());
//        }
    }
}
