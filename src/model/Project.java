package model;

import main.Main;
import util.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;


public class Project implements Serializable {
    private static final long serialVersionUID = 7351202416537420904L;
    public static ArrayList < Project > totalProjects = new ArrayList < Project > ();
    public static ArrayList < Project > projectsNotAssigned = totalProjects;
    public int popularityCounter;
    private String projectId = "PROJ000";
    private String projectTitle;
    private String projectDetails;
    private ClientRepresentative client;
    private ArrayList <Role> rolesInProject = new ArrayList < Role > ();

    public Project() {

    }

    public Project(ClientRepresentative client, String projectId, String projectTitle, String projectDetails) {
        this.client = client;
        this.projectId = projectId;
        this.projectTitle = projectTitle;
        this.projectDetails = projectDetails;
    }

    /**
     * Getter and setter methods.
     *
     * @return project class attributes
     */

    public ArrayList < Role > getRolesInProject() {
        return rolesInProject;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public ClientRepresentative getClient() {
        return client;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public String getProjectDetails() {
        return projectDetails;
    }

    public int getPopularityCounter() {
        return popularityCounter;
    }

    public void setPopularityCounter(int popularityCounter) {
        this.popularityCounter = popularityCounter;
    }

    /**
     * Discarding unpopular projects based on popularity vote by students.
     * Taking number of student and team size in consideration
     *
     * @return boolean: method success
     * @throws ProjectMismatchException when number of project is less than required
     */
    public static boolean discardUnpopularProjects() throws ProjectMismatchException {
        int numProjectReqd = 0;
        int numStudents = Student.allStudents.size();
        System.out.println("Number of students: " + numStudents);

        int numProjects = Project.totalProjects.size();
        System.out.println("Number of projects: " + numProjects);

        try {
            if(numStudents % 4 != 0) {
                numProjectReqd = (numStudents / 4 + 1);
            }else {
                numProjectReqd = (numStudents / 4 );
            }
            System.out.println("Number of projects required: " + numProjectReqd);

            if (numProjectReqd > numProjects) {
                throw new ProjectMismatchException("Number of projects not enough!");
            }
        } catch (ProjectMismatchException ex) {
            System.err.println(ex.getMessage());
            return false;
        }

        Project temp;
        if (Project.totalProjects.size() > 1) // check if the number of orders is larger than 1
        {
            for (int x = 0; x < Project.totalProjects.size(); x++) // bubble sort (descending order) outer loop
            {
                for (int i = 0; i < Project.totalProjects.size() - x - 1; i++) {
                    if (Project.totalProjects.get(i).getPopularityCounter() < (Project.totalProjects.get(i + 1).getPopularityCounter())) {
                        temp = Project.totalProjects.get(i);
                        Project.totalProjects.set(i, Project.totalProjects.get(i + 1));
                        Project.totalProjects.set(i + 1, temp);
                    }
                }
            }
        }

        //deleting unpopular projects
        for (int i = numProjects - 1; i >= numProjectReqd; i--) {
            Project.totalProjects.remove(i);
        }

        //display required projects
        System.out.println(Constraint.ANSI_BLUE + "\nRequired Popular Projects are:" + Constraint.ANSI_RESET);
        for (Project p: totalProjects) {
            p.displayProject();
            System.out.println("Popularity\t\t:   " + p.getPopularityCounter());

        }

        //after discarding, boolean for projects discarded is set to true
        ProjectManager.setProjectsDiscarded(true);
        return true;
    }

    /**
     * Method to create new projects
     *
     * <p>
     * Client representative can create new projects with specified roles and
     * frameworks which will be made available to students.
     * All projects will have an auto generated unique project ID.
     * All projects are being saved in a file.
     *
     * @param client
     * @throws IOException
     */
    public void createProject(ClientRepresentative client) throws IOException {
        //incrementing project Id for new project
        if (totalProjects.size() > 0) {
            Project lastProject = totalProjects.get(totalProjects.size() - 1);
            projectId = "PROJ" + String.format("%03d", (Integer.parseInt(lastProject.getProjectId().substring(4)) + 1));
        } else {
            projectId = "PROJ" + String.format("%03d", (Integer.parseInt(projectId.substring(4)) + 1));
        }

        this.client = client;
        System.out.println("Enter Project Title: ");
        projectTitle = Global.scan.next() + Global.scan.nextLine();
        System.out.println("Enter Project details: ");
        projectDetails = Global.scan.nextLine();

        String choice;
        do {
            System.out.println("Specify one required role: ");
            String roleName = Global.scan.nextLine();
            ArrayList < String > frameworks = new ArrayList < String > ();

            String input;
            do {
                System.out.println("Specify one framework '" + roleName + "' should be familiar with:");
                String framework = Global.scan.nextLine();
                frameworks.add(framework);
                System.out.println("Do you want to add more frameworks? (Y/N)");
                input = Global.scan.nextLine();
            } while (input.toUpperCase().compareTo("N") != 0);

            rolesInProject.add(new Role(projectId, roleName, frameworks));
            System.out.println("Do you want to add more roles? (Y/N)");
            choice = Global.scan.nextLine();
        } while (choice.toUpperCase().compareTo("N") != 0);

        totalProjects.add(this);
        projectsNotAssigned.add(this);
        FileReadWrite.saveProjectDetails(Main.projectsFileName, Project.totalProjects);
        FileReadWrite.saveProjectDetails(Main.projectsNotAssignedFileName, Project.projectsNotAssigned);
        System.out.println("Success! Project is created with Id : " + projectId);
    }

    /**
     * Method to display all projects for particular client
     *
     */
    public void displayProject() {
        System.out.println("\nClient Id\t\t:\t" + this.getClient().getId());
        System.out.println("Project Id\t\t:\t" + this.getProjectId());
        System.out.println("Project Title\t:\t" + this.getProjectTitle());
        System.out.println("Project Details\t:\t" + this.getProjectDetails());
        int roleNumber = 1;
        for (Role role: this.getRolesInProject()) {
            System.out.println("Role\t\t\t:\t" + roleNumber + ")" + role.getRoleName());
            roleNumber++;
            System.out.print("Frameworks\t\t:\t");
            int frameworkNumber = 1;
            for (String framework: role.getFrameworks()) {
                System.out.print(frameworkNumber + ")" + framework + "\t");
                frameworkNumber++;
            }
            System.out.println();
        }
    }
}