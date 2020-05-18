import java.util.ArrayList;
import java.util.Scanner;

public class Project {
    public static ArrayList<Project> proj = new ArrayList<Project>(); // TODO use total projects as the name
    private String projectId = "PROJ100";
    private String clientId; //TODO Check if needed to change to ClientRep Type
    private String projectTitle;
    private String projectDetails;
    private String[] studentId; // TODO Sunil Maybe replace ?
    private ArrayList<Role> rolesInProject = new ArrayList<Role>();
    public static int projectCounter = 0;
    public int popularityCounter;
    public static ArrayList<Project> totalProjects = new ArrayList<>();
    Scanner sc = new Scanner(System.in); // TODO Remove Scanner

    public Project() {
        // TODO Auto-generated constructor stub
    }

    public Project(String clientId, String projectId, String projectTitle, String projectDetails) {
        this.clientId = clientId;
        this.projectId = projectId;
        this.projectTitle = projectTitle;
        this.projectDetails = projectDetails;
//        this.studentId = null; //TODO Confirm with Sunil
//        this.rolesInProject = rolesInProject;
//        this.popularityCounter = popularityCounter;
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

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
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

    public String[] getStudentId() {
        return studentId;
    }

    public void setStudentId(String[] studentId) {
        this.studentId = studentId;
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

    public void createProject(String clientId) {
        this.clientId = clientId;
        projectId = "PROJ"
                + String.format("%03d", (Integer.parseInt(getProjectId().substring(4, getProjectId().length())) + 1));

        System.out.println("Enter Project Title: ");
        projectTitle = sc.next();
        projectTitle += sc.nextLine();
        System.out.println("Enter Project details: ");
        projectDetails = sc.next();
        projectDetails += sc.nextLine();
        System.out.println("Specify required role: "); //TODO Add multiple Roles
        String role1 = sc.next(); //TODO change Role1 to roleName
        role1 += sc.nextLine();
        int length = 0;
        boolean lError = true;// TODO Rename
        do {
            try {
                System.out.println("How many frameworks do you want to add for role '" + role1 + "' ?");
                length = Integer.parseInt(sc.next());
                lError = false;
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer");
            }
        } while (lError || length < 0);
        ArrayList<String> frameworks = new ArrayList<String>(); // TODO Use the method in Role Class
        for (int i = 0; i < length; i++) {
            System.out.println("Specify one framework '" + role1 + "' should be familiar with:");
            String framework = sc.next();
            framework += sc.nextLine();
            frameworks.add(framework);
        }
        rolesInProject.add(new Role(projectId, role1));
        proj.add(new Project(clientId, projectId, projectTitle, projectDetails)); // TODO use totalProjects as the name
        System.out.println("Success! Project is created with Id : " + projectId);
    }

    public void displayProject() {
        for (Project p : proj) {
            System.out.println("\nClient Id: " + p.getClientId());
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

    public static void discardUnpopularProjects() {
        int numStudents = Student.allStudents.size();
        System.out.println("no of studs" + numStudents);

        int numProjects = proj.size();
        System.out.println("no of projs" + numProjects);

        int numProjectReqd = (numStudents / 4);
        System.out.println("no of projs reqd" + numProjectReqd);

        // bubble sort for arranging project ArrayList in descending order of
        // popularityCounter
        Project temp;
        if (proj.size() > 1) // check if the number of orders is larger than 1
        {
            for (int x = 0; x < proj.size(); x++) // bubble sort outer loop
            {
                for (int i = 0; i < proj.size() - x - 1; i++) {
                    if (proj.get(i).getPopularityCounter() < (proj.get(i + 1).getPopularityCounter())) {
                        temp = proj.get(i);
                        proj.set(i, proj.get(i + 1));
                        proj.set(i + 1, temp);
                    }
                }
            }
        }

        for (Project p : proj) {
            System.out.println(p.getPopularityCounter());
        }

        // removing unpopular projects from Project ArrayList
        for (int i = numProjects - 1; i >= numProjectReqd; i--) {
            System.out.println("i=" + i);
            proj.remove(i);
        }

        // display required projects
        for (Project p : proj) {
            System.out.println("Required Projects:\nProject ID: " + p.getProjectId() + "\nProject Details: "
                    + p.getProjectDetails() + "\nProject Popularity Counter:" + p.getPopularityCounter());
        }
    }

}