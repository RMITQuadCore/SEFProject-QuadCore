package main;

import model.*;
import util.FileReadWrite;

import java.io.IOException;
import java.io.Serializable;

public class Main implements Serializable {

    public static String userFileName = "Users.txt";
    public static String studentsFileName = "Students.txt";
    public static String studentsNotInATeamFileName = "StudentsNotInATeam.txt";
    public static String projectsFileName = "Projects.txt";
    public static String projectsNotAssignedFileName = "ProjectsNotAssigned.txt";
    public static String teamsFileName = "Teams.txt";
    public static String softConstraintFileName = "SoftConstraint.txt";
    public static String allConstraintFileName = "Constraint.txt";

    /**
     * Reads from saved files to update corresponding array list, used when main method is executed.
     *
     * @throws IOException if an I/O error occurs
     * @throws ClassNotFoundException if the class of a serialized object could not be found
     */
    public static void updateArrays() throws IOException, ClassNotFoundException {
        User.allUserDetails = FileReadWrite.readUserDetails(userFileName); //Updating all User details
        Team.allTeams = FileReadWrite.readTeamDetails(teamsFileName); //Updating all teams
        Student.allStudents = FileReadWrite.readStudentDetails(studentsFileName); //Updating all Students details
        ProjectManager.studentsNotInATeam = FileReadWrite.readStudentDetails(studentsNotInATeamFileName); //Updating Students details who are not in a team yet
        Project.totalProjects = FileReadWrite.readProjectDetails(projectsFileName); //Updating all projects
        Project.projectsNotAssigned = FileReadWrite.readProjectDetails(projectsNotAssignedFileName); //Updating Projects not yet assigned to any team
        Constraint.allSoftConstraints = FileReadWrite.readConstraintDetails(softConstraintFileName); //Updating all soft constraints
        ProjectManager.constraints = FileReadWrite.readConstraintDetails(softConstraintFileName); //Updating all constraints
        Constraint.allConstraints = FileReadWrite.readConstraintDetails(allConstraintFileName);//Updating all constraints
    }

    /**
     * Main Method
     *
     * @param args
     * @throws IOException if an I/O error occurs
     * @throws ClassNotFoundException if the class of a serialized object could not be found
     * @throws NullPointerException if the specified array is null
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException, NullPointerException {
        updateArrays();
        User user = new User();
        user.mainMenu();
    }
}