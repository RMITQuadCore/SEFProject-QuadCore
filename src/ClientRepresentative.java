import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

public class ClientRepresentative extends User implements Serializable {
    private static final long serialVersionUID = -7506201449608946074L;
    Project project = new Project();
    //Scanner scan = SingletonScanner.getInstance();

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
                        "2. Logout\n");

                choice = Integer.parseInt(Global.scan.next());
            } catch (NumberFormatException e) {
                System.err.println("Please enter an integer (1-2)");
            }

            switch (choice) {
                case 1:
                    project.createProject(this);
                    break;
                case 2:
                    mainMenu();
                    break;
            }
        } while (choice != 2);
    }
}


