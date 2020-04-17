import java.util.ArrayList;
import java.util.Scanner;

public class ClientRepresentative extends User {
	public static ArrayList<ClientRepresentative> cr = new ArrayList<ClientRepresentative>();
	public ArrayList<String> projects = new ArrayList<String>();
	Project pr = new Project();
	Scanner sc = new Scanner(System.in);

	public ClientRepresentative() {
		// TODO Auto-generated constructor stub
	}

	public ClientRepresentative(String id, String firstName, String lastName, String emailID, String userName,
			String password, String org, ArrayList<String> projects) {
		super(id, firstName, lastName, emailID, userName, password, org);

		this.projects = null;
	}

	public void createProject() {
		System.out.println("Do you want to add your projects now: Y/N");
		String option = sc.nextLine();
		if (option.toUpperCase().compareTo("Y") == 0) {
			ArrayList<String> projects = new ArrayList<String>();
			String choice;
			do {

				projects.add(pr.createProject());
				System.out.println("Do you want to add more projects? Y/N");
				choice = sc.nextLine();
			} while (choice.toUpperCase().compareTo("N") != 0);
		}
		this.setProjects(projects);
	}

	public ArrayList<String> getProjects() {
		return projects;
	}

	public void setProjects(ArrayList<String> projects) {
		this.projects = projects;
	}

//	public static void main(String[] args) {
//		ClientRepresentative cr1 = new ClientRepresentative();
//		cr1.createProject();
//	}

}