import java.util.ArrayList;
import java.util.Scanner;

public class ClientRepresentative extends User {
    public static ArrayList<ClientRepresentative> cr = new ArrayList<ClientRepresentative>(); // TODO Rename
    Project pr = new Project(); // TODO Rename - Ask Sunil
    Scanner sc = new Scanner(System.in); // TODO Remove Scanner

    public ClientRepresentative()
    {

    }


    public ClientRepresentative(String id, String firstName, String lastName, String emailID, String userName,
                                String password, String org)
    {
        super(id, firstName, lastName, emailID, userName, password, org);
    }


    public void start()  { // TODO Rename Method to Menu
        // TODO Menu Options to be shown
        // TODO Menu This needs to be changed , void method, not taking any argument
        // Menu Option
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
