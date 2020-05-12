import java.util.*;


public class ProjectManager extends User {

	public ArrayList<String> studentPersonalities = new ArrayList<String>();
	Scanner scan = new Scanner(System.in);

	private String id;
	private String firstName;
	private String lastName;
	private String org;
	private String emailID;
	private String userName;
	private String password;
	private boolean signUpStatus;

	public ProjectManager() {
	}

	public ProjectManager(String id, String firstName, String lastName, String emailID, String userName,
						  String password, String org, ArrayList<String> studentPersonalities) {
		super(id, firstName, lastName, emailID, userName, password, org);
		this.studentPersonalities = studentPersonalities;
		signUpStatus = true;
	}

	public ArrayList<String> getStudentPersonalities() {
		return this.studentPersonalities;
	}

	public String getId() {
		return this.id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getOrg() {
		return this.org;
	}

	public String getEmailID() {
		return this.emailID;
	}

	public boolean getSignUpStatus() {
		return signUpStatus;
	}


	// Project Manager Menu
	public void pmMenu() {
		int choice = 0;
		do {
			try {
				System.out.println("\n*** Project Manager Menu ***\n" +
						"1. Enter Personality of students\n" +
						"2. Enter Weightage for Soft-Constraints\n" +
						"3. Change Sign up status\n" +
						"4. Discard Unpopular projects\n" +
						"5. Display Current Constraint\n" +
						"6. Run Project Team formation\n" +
						"7. Display Teams\n");
				choice = Integer.parseInt(scan.next());
			} catch (NumberFormatException e) {
				System.err.println("Please enter an integer (1-6)");
			}
		} while (choice < 1 || choice > 7);

		Constraint con = new Constraint();

		switch (choice) {
			case 1:
				enterStudentPersonality();
				break;
			case 2:
				con.setWeightage();
				break;
			case 3:
				setSignUpStatus();
				break;
			case 4:
				Project pro = new Project();
				pro.discardUnpopularProjects();
				break;
			case 5:
				con.displayConstraints();
				break;
			case 6:
				//createTeams();
				break;
			case 7:
				//displayTeams
				break;

			//Stage3 methods
			//displayTeamFitness()
			//swapMembers()
			//changeStudentGpa()
		}
	}

	//Method to assign personality to all students
	private void enterStudentPersonality() {
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

					char[] validPersonalities = {'A', 'B', 'C', 'D', 'E', 'F'};
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


	//Method to unable or disable Sign Up
	public void setSignUpStatus() {
		// true = open && false = closed

		int choice = 0;
		signUpStatus = true;
		do {
			try {
				System.out.println("Set Sign Up Status" + "\n1. Open" + "\n2.Closed");
				System.out.println("Please enter your Choice :");
				choice = Integer.parseInt(s.next());
			} catch (NumberFormatException e) {
				System.err.println("Please enter an integer");
			}
		} while (choice < 1 || choice > 3);

		switch (choice) {
			case 1:
				signUpStatus = true;
			case 2:
				signUpStatus = false;
		}
	}
}
