import java.util.ArrayList;
import java.util.Scanner;

public class ClientRepresentative {
	public static ArrayList<ClientRepresentative> cr = new ArrayList<ClientRepresentative>();
	// public String[] projects = new String[20];
	public ArrayList<String> projects = new ArrayList<String>();
	Scanner sc = new Scanner(System.in);

	public ClientRepresentative() {
		// TODO Auto-generated constructor stub
	}

	public ClientRepresentative(String clientId, String firstName, String lastName, String userName, String password,
			String organisation, String emailId, ArrayList<String> projects) {
		this.clientId = clientId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.organisation = organisation;
		this.emailId = emailId;
		this.projects = null;
	}

	public void createProject() {
		System.out.println("Do you want to add your projects now: Y/N");
		String option = sc.nextLine();
		if (option.toUpperCase().compareTo("Y") == 0) {
			ArrayList<String> projects = new ArrayList<String>();
			String choice;
			do {
				Project pr = new Project();
				projects.add(pr.createProject());
				System.out.println("Do you want to add more projects? Y/N");
				choice = sc.nextLine();
			} while (choice.toUpperCase().compareTo("N") != 0);
		}
		cr.add(new ClientRepresentative(clientId, organisation, emailId, projects));
	}

//	public static void main(String[] args) {
//		ClientRepresentative cr1 = new ClientRepresentative();
//		cr1.createProject();
//	}

}