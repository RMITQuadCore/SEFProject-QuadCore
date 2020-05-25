import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class ClientRepresentative extends User implements Serializable {
    private static final long serialVersionUID = -7506201449608946074L;
    //    Project project = new Project(); //TODO Array of projects
    public ArrayList < Project > projects = Project.totalProjects;

    public ClientRepresentative() {
    }


    public ClientRepresentative(String id, String firstName, String lastName, String emailID, String userName,
                                String password, String org) {
        super(id, firstName, lastName, emailID, userName, password, org);
        setClientID(id);
    }


    /**
     * Method display Client representative it's menu and further navigate to required functionality.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void clientMenu() throws IOException, ClassNotFoundException {
        int choice = 0;
        do {
                System.out.println( "****Client Representative Menu****" );
                System.out.println(
                        "1. Add new project\n" +
                        "2. Display Projects\n" +
                        "3. Logout\n"
                );
                choice = InputTools.intChecker(1,3);

            switch (choice) {
                case 1:
                    Project project = new Project();
                    project.createProject(this);
                    break;
                case 2:
                    for (Project p: Project.totalProjects) {
                        if (p.getClient().getClientID().equals(this.getClientID())) {
                            p.displayProject();
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