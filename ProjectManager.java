import java.util.ArrayList;
import java.util.Scanner;

//It's working
public class ProjectManager extends User {

	public ArrayList<String> studentPersonalities = new ArrayList<String>();
	Scanner scan = new Scanner(System.in);

	public ProjectManager() {
	}

	public ProjectManager(String id, String firstName, String lastName, String emailID, String userName,
						  String password, String org) {

		super(id, firstName, lastName, emailID, userName, password, org);

	}

	public void pmMenu() {
		int choice = 0;
		do {
			try {
				System.out.println("Project Manager Menu!\n1.Enter Personality of students\n2. Change Sign up status");
				choice = Integer.parseInt(s.next());
			} catch (NumberFormatException e) {
				System.err.println("Please enter an integer");
			}
		} while (choice < 1 || choice > 3);

		switch (choice) {
			case 1:
				enterStudentPersonality();
				break;
			case 2:
				setSignUpStatus();
				break;

		}

	}

	public void enterStudentPersonality() {
		boolean foundStudent = false, validPersonality = false;
		String studID;
		char studPersonality = '0';

		do {
			System.out.println("\nEnter assigned student ID:");
			studID = scan.next();
			studID += scan.nextLine();
			for (Student stud : Student.allStudents) {

				if (((Student) stud).getId().compareTo(studID) == 0) {
					foundStudent = true;
					System.out.println("\nPlease enter personality of " + studID + " :");
					studPersonality = scan.nextLine().toUpperCase().charAt(0);

					char[] validPersonalities = { 'A', 'B', 'C', 'D', 'E', 'F' };
					for (int i = 0; i < 6; i++) {
						if (studPersonality == validPersonalities[i]) {
							validPersonality = true;
							stud.setStudentPersonality(studPersonality);
						}
					}
					if (!validPersonality) {
						System.out.println("Invalid personality type!");
					}

				}

			}
			if (!foundStudent) {
				System.out.println("Student not found!");
			}
		} while (foundStudent == false || studID.isEmpty() || studPersonality == '0');
	}

	public boolean setSignUpStatus() {
//		// true = open && false = closed
//		
//		int choice = 0;
//		do {
//			try {
//				System.out.println("Set Sign Up Status" + "\n1. Open" + "\n2.Closed");
//				System.out.println("Please enter your Choice :");
//				choice = Integer.parseInt(s.next());
//			} catch (NumberFormatException e) {
//				System.err.println("Please enter an integer");
//			}
//		} while (choice < 1 || choice > 3);
//
//		switch (choice) {
//		case 1:
//			return true;
//			break;
//		case 2:
//			return false;
//			break;
		// }
		return false;
	}

}
