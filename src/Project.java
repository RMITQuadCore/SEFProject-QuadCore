import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Project implements Serializable {
    private static final long serialVersionUID = 7351202416537420904L;
    public static ArrayList < Project > totalProjects = new ArrayList < Project > ();
    public static int projectCounter = 0;
    public int popularityCounter;
    private String projectId = "PROJ000";
    private ClientRepresentative client;
    private String projectTitle;
    private String projectDetails;
    private ArrayList < Role > rolesInProject = new ArrayList < Role > ();

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
     * @return
     */
    public static int getProjectCounter() {
        return projectCounter;
    }

    public static void setProjectCounter(int projectCounter) {
        Project.projectCounter = projectCounter;
    }

    public ArrayList < Role > getRolesInProject() {
        return rolesInProject;
    }

    public void setRolesInProject(ArrayList < Role > rolesInProject) {
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

    public int getPopularityCounter() {
        return popularityCounter;
    }

    public void setPopularityCounter(int popularityCounter) {
        this.popularityCounter = popularityCounter;
    }


    /**
     * Method to create new projects.
     *
     * Client representative can create new projects with specified roles and
     * frameworks which will be made available to students.
     * All projects will have an auto generated unique project ID.
     * @param client
     * @throws IOException
     */
    public void createProject(ClientRepresentative client) throws IOException {
        if (totalProjects.size() > 0) {
            Project lastProject = totalProjects.get(totalProjects.size() - 1);
            projectId = "PROJ" + String.format("%03d", (Integer.parseInt(lastProject.getProjectId().substring(4)) + 1));
        } else {
            projectId = "PROJ" + String.format("%03d", (Integer.parseInt(projectId.substring(4)) + 1));
        }

        this.client = client;
        //        this.setProjectId(projectId);
        System.out.println("Enter Project Title: ");
        projectTitle = Global.scan.next() + Global.scan.nextLine();
        //        this.projectTitle = projectTitle;
        System.out.println("Enter Project details: ");
        projectDetails = Global.scan.nextLine();
        //        this.projectDetails = projectDetails;

        String choice;
        do {
            System.out.println("Specify a required role: ");
            String roleName = Global.scan.nextLine();
            ArrayList < String > frameworks = new ArrayList < String > ();

            String input;
            do {
                System.out.println("Specify a framework '" + roleName + "' should be familiar with:");
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
        FileReadWrite.saveProjectDetails(Main.projectsFileName, Project.totalProjects);
        System.out.println("Success! Project is created with Id : " + projectId);
    }

    public void displayProject() {
        System.out.println("\nClient Id: " + this.getClient().getId());
        System.out.println("Project Id: " + this.getProjectId());
        System.out.println("Project Title: " + this.getProjectTitle());
        System.out.println("Project Details: " + this.getProjectDetails());

        for (Role r: this.getRolesInProject()) {
            System.out.println("\nRole: " + r.getRoleName());
            System.out.println("Frameworks: ");
            for (String f: r.getFrameworks()) {
                System.out.println("\t" + f);
            }
        }
    }


    /**
     * Method to discard unpopular projects based on popularity vote by students.
     * @return
     * @throws ProjectMismatchException
     */
    public static boolean discardUnpopularProjects() throws ProjectMismatchException {
        int numProjectReqd = 0;
        int numStudents = Student.allStudents.size();
        System.out.println("Number of students: " + numStudents);

        int numProjects = Project.totalProjects.size();
        System.out.println("Number of projects: " + numProjects);

        try {
            numProjectReqd = (numStudents / 4); //TODO constraint.teamSize instead of 4
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
        for (Project p: Project.totalProjects) {
            System.out.println("\nsrc.Project ID: " + p.getProjectId() + "\nsrc.Project Details: " + p.getProjectDetails() +
                    "\nsrc.Project Popularity Counter:" + p.getPopularityCounter());
        }
        return true;
    }
}