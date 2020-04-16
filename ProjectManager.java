import java.util.ArrayList;
import java.util.Scanner;

public class ProjectManager //extends User
{

    //Actual constructor
  /*  public ProjectManager()
    {
        //super(id, firstName, lastName, emailId, userName, password, org)

    }
   */

    public ArrayList<String> studentPersonalities = new ArrayList<String>();
    Scanner scan = new Scanner(System.in);

    public ProjectManager(String projManagerId, String firstName, String lastName, String userName, String password,
                          String organisation, String emailId, ArrayList<String> projects) {
        //super(name, emailId, organisation, ID)
        this.clientId = clientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.organisation = organisation;
        this.emailId = emailId;
        this.projects = null;
    }

   
    public void pmMenu() {
        int choice = 0;
        do {
            try {
                System.out.println("Project Manager Menu!\n1.Enter Personality of students\n2. Change Sign up status" +
                        " \n3. Discard Unpopular Projects");
                choice = Integer.parseInt(s.next());
            } catch (NumberFormatException e) {
                System.err.println("Please enter an integer");
            }
        } while (choice < 1 || choice > 3);

        switch (choice)
        {
            case 1:
                enterStudentPersonality();
                break;
            case 2:
                setSignUpStatus();
                break;

        }

    }


    public void enterStudentPersonality() {
        // need to know name and ID of students here the only I can assign

        Student stud = new Student();
        ArrayList<String> studentPersonalities = new ArrayList<String>();
        char[] validPersonalities = {'a', 'A', 'b', 'B', 'c', 'C', 'd', 'D', 'e', 'E', 'f', 'F'};
        for (stud:
             Student)
            System.out.println("Please enter personality of + //stud.firstName + stud.ID");
        String input = scan.nextLine();
        char tempPersonality = input.charAt(0);
        for (int i = 0; i < 12; i++) {
            if (tempPersonality == validPersonalities[i]) {
                stud.add(new Student(personality))   ///////going wrong here...how do I add it in arrayList
            } else {
            }
        }
    }


    public boolean setSignUpStatus()
    {
        // true = open && false = closed

        int choice = 0;
        do {
            try
            {
                System.out.println("Set Sign Up Status" + "\n1. Open" + "\n2.Closed");
                System.out.println("Please enter your Choice :");
                choice = Integer.parseInt(s.next());
            } catch (NumberFormatException e) {
                System.err.println("Please enter an integer");
            }
        } while (choice < 1 || choice > 3);

        switch (choice)
        {
            case 1:
                return true;
            break;
            case 2:
                return false;
            break;
        }
    }

}
