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

    public static void discardUnpopularProjects() {
        int numStudents = Student.allStudents.size();
        System.out.println("no of studs" + numStudents);

        int numProjects = totalProjects.size();
        System.out.println("no of projs" + numProjects);

        int numProjectReqd = (numStudents / 4);
        System.out.println("no of projs reqd" + numProjectReqd);

        // bubble sort for arranging project ArrayList in descending order of
        // popularityCounter
        Project temp;
        if (totalProjects.size() > 1) // check if the number of orders is larger than 1
        {
            for (int x = 0; x < totalProjects.size(); x++) // bubble sort outer loop
            {
                for (int i = 0; i < totalProjects.size() - x - 1; i++) {
                    if (totalProjects.get(i).getPopularityCounter() < (totalProjects.get(i + 1).getPopularityCounter())) {
                        temp = totalProjects.get(i);
                        totalProjects.set(i, totalProjects.get(i + 1));
                        totalProjects.set(i + 1, temp);
                    }
                }
            }
        }

        for (Project p : totalProjects) {
            System.out.println(p.getPopularityCounter());
        }

        // removing unpopular projects from Project ArrayList
        for (int i = numProjects - 1; i >= numProjectReqd; i--) {
            System.out.println("i=" + i);
            totalProjects.remove(i);
        }

        // display required projects
        for (Project p : totalProjects) {
            System.out.println("Required Projects:\nProject ID: " + p.getProjectId() + "\nProject Details: "
                    + p.getProjectDetails() + "\nProject Popularity Counter:" + p.getPopularityCounter());
        }
    }

    public void createProject(ClientRepresentative client) {
        this.client = client;
        projectId = "PROJ"
                + String.format("%03d", (Integer.parseInt(getProjectId().substring(4, getProjectId().length())) + 1));

        System.out.println("Enter Project Title: ");
        projectTitle = scan.next();
        projectTitle += scan.nextLine();
        System.out.println("Enter Project details: ");
        projectDetails = scan.next();
        projectDetails += scan.nextLine();
        System.out.println("Specify required role: "); //TODO Add multiple Roles
        String role1 = scan.next(); //TODO change Role1 to roleName
        role1 += scan.nextLine();
        int length = 0;
        boolean lError = true;// TODO Rename
        do {
            try {
                System.out.println("How many frameworks do you want to add for role '" + role1 + "' ?");
                length = Integer.parseInt(scan.next());
                lError = false;
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer");
            }
        } while (lError || length < 0);
        ArrayList<String> frameworks = new ArrayList<String>(); // TODO Use the method in Role Class
        for (int i = 0; i < length; i++) {
            System.out.println("Specify one framework '" + role1 + "' should be familiar with:");
            String framework = scan.next();
            framework += scan.nextLine();
            frameworks.add(framework);
        }
        rolesInProject.add(new Role(projectId, role1));
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