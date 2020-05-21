import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

public class ClientRepresentative extends User implements Serializable {
    private static final long serialVersionUID = -7506201449608946074L;
    Project project = new Project();

    public ClientRepresentative() {

    }


    public ClientRepresentative(String id, String firstName, String lastName, String emailID, String userName,
                                String password, String org) {
        super(id, firstName, lastName, emailID, userName, password, org);
    }


    public void clientMenu() throws IOException, ClassNotFoundException {
        int choice = 0;
        do {
            try {
                System.out.println("Client Representative Menu");
                System.out.println("1. Add new project\n" +
                        "2. Display Projects\n" +
                        "3. Logout\n");

                choice = Integer.parseInt(Global.scan.next());
            } catch (NumberFormatException e) {
                System.err.println("Please enter an integer (1-3)");
            }

            switch (choice) {
                case 1:
                    project.createProject(this);
                    break;
                case 2:
                    Project project = new Project();
                    project.displayProject();
                    break;
                case 3:
                    mainMenu();
                    break;
            }
        } while (choice != 3);
    }
}


