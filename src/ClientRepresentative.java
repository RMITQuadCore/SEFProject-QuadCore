import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class ClientRepresentative extends User implements Serializable {
    private static final long serialVersionUID = -7506201449608946074L;

    //constructor
    public ClientRepresentative(String id, String firstName, String lastName, String emailID, String userName,
                                String password, String org) {
        super(id, firstName, lastName, emailID, userName, password, org);
        setClientID(id);
    }


    /**
     * Method displays Client representative it's menu and further navigate to required functionality.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void clientMenu() throws IOException, ClassNotFoundException {
        int choice = 0;
        do {
                System.out.println( Constraint.ANSI_YELLOW+"****Client Representative Menu****\n"+ Constraint.ANSI_RESET+

                        "1. Add new project\n" +
                        "2. Display Projects\n" +
                        "3. Logout\n"
                );
                choice = InputTools.intChecker(1,3);

            switch (choice) {
                case 1:
                    Project newProject = new Project();
                    newProject.createProject(this);
                    break;
                case 2:
                    for (Project project: Project.totalProjects) {
                        if (project.getClient().getClientID().equals(this.getClientID())) {
                            project.displayProject();
                        }
                    }
                    break;
                case 3:
                    mainMenu();
                    break;
            }
        } while (choice != 3);
    }
}