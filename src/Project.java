import java.util.ArrayList;
import java.util.Scanner;

public class Project {
    public static ArrayList<Project> totalProjects = new ArrayList<Project>();
    private String projectId = "PROJ100";
    private ClientRepresentative client; //TODO Check if needed to change to ClientRep Type
    private String projectTitle;
    private String projectDetails;
    private ArrayList<Role> rolesInProject = new ArrayList<Role>();
    public static int projectCounter = 0;
    public int popularityCounter;
    Scanner scan = SingletonScanner.getInstance(); // TODO Remove Scanner

    public Project() {

    }

    public Project(ClientRepresentative client, String projectId, String projectTitle, String projectDetails) {
        this.client = client;
        this.projectId = projectId;
        this.projectTitle = projectTitle;
        this.projectDetails = projectDetails;
    }

    public ArrayList<Role> getRolesInProject() {
        return rolesInProject;
    }

    public void setRolesInProject(ArrayList<Role> rolesInProject) {
        this.rolesInProject = rolesInProject;
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

    public void setClient(ClientRepresentative client) {
        this.client = client;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getProjectDetails() {
        return projectDetails;
    }

    public void setProjectDetails(String projectDetails) {
        this.projectDetails = projectDetails;
    }

    public static int getProjectCounter() {
        return projectCounter;
    }

    public static void setProjectCounter(int projectCounter) {
        Project.projectCounter = projectCounter;
    }

    public int getPopularityCounter() {
        return popularityCounter;
    }

    public void setPopularityCounter(int popularityCounter) {
        this.popularityCounter = popularityCounter;
    }

    //Method to discard unpopular projects //TODO This will be in Project
    public static boolean discardUnpopularProjects() throws ProjectMismatchException {
        int numProjectReqd = 0;
        int numStudents = Student.allStudents.size();
        System.out.println("Number of students: " + numStudents);

        int numProjects = Project.totalProjects.size();
        System.out.println("Number of projects: " + numProjects);

        try {
            numProjectReqd = (numStudents / 4);
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
            for (int x = 0; x < Project.totalProjects.size(); x++) // bubble sort outer loop
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


        for (int i = numProjects - 1; i >= numProjectReqd; i--) {

            Project.totalProjects.remove(i);
        }
        System.out.println("Required Projects:");
        for (Project p : Project.totalProjects) {
            System.out.println("\nsrc.Project ID: " + p.getProjectId() + "\nsrc.Project Details: " + p.getProjectDetails()
                    + "\nsrc.Project Popularity Counter:" + p.getPopularityCounter());
        }

        return true;
    }

    public void createProject(ClientRepresentative client) {
        this.client = client;
        projectId = "PROJ"
                + String.format("%03d", (Integer.parseInt(getProjectId().substring(4, getProjectId().length())) + 1));

        System.out.println("Enter Project Title: ");
        projectTitle = scan.nextLine();

        System.out.println("Enter Project details: ");
        projectDetails = scan.nextLine();

        System.out.println("Specify required role: "); //TODO Add multiple Roles
        String roleName = scan.nextLine();

        int length = 0;
        boolean lError = true;// TODO Rename
        do {
            try {
                System.out.println("How many frameworks do you want to add for role '" + roleName + "' ?");
                length = Integer.parseInt(scan.next());
                lError = false;
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer");
            }
        } while (lError || length < 0);
        ArrayList<String> frameworks = new ArrayList<String>(); // TODO Use the method in Role Class
        for (int i = 0; i < length; i++) {
            System.out.println("Specify one framework '" + roleName + "' should be familiar with:");
            String framework = scan.nextLine();
            frameworks.add(framework);
        }
        rolesInProject.add(new Role(projectId, roleName));
        totalProjects.add(new Project(client, projectId, projectTitle, projectDetails));
        System.out.println("Success! Project is created with Id : " + projectId);
    }

    public void displayProject() {
        for (Project p : totalProjects) {
            System.out.println("\nClient Id: " + p.getClient().getId());
            System.out.println("project Id: " + p.getProjectId());
            System.out.println("project Title: " + p.getProjectTitle());
            System.out.println("projectDetails: " + p.getProjectDetails());

            for (Role r : p.getRolesInProject()) {
                System.out.println("Role: " + r.getRoleName());
                System.out.println("Frameworks are: ");
                for (String f : r.getFrameworks()) {
                    System.out.println(f);
                }
            }
        }
    }

}