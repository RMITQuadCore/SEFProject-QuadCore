import java.util.ArrayList;
import java.util.Scanner;

public class ClientRepresentative extends User {
    public static ArrayList<ClientRepresentative> cr = new ArrayList<ClientRepresentative>();
    Project pr = new Project();
    Scanner sc = new Scanner(System.in);

    public ClientRepresentative() {
        // TODO Auto-generated constructor stub
    }

    public ClientRepresentative(String id, String firstName, String lastName, String emailID, String userName,
                                String password, String org) {
        super(id, firstName, lastName, emailID, userName, password, org);
    }

    public void start() {
        System.out.println("Do you want to add your projects now: Y/N");
        String option = sc.nextLine();
        if (option.toUpperCase().compareTo("Y") == 0) {
            String choice;
            do {
                pr.createProject(this.clientID);
                System.out.println("Do you want to add more projects? Y/N");
                choice = sc.nextLine();
            } while (choice.toUpperCase().compareTo("N") != 0);
        }
    }
}
