import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Student extends User {
	static Scanner scan = new Scanner(System.in);

	private String studentID;
	private String emailID;
	private String universityName;
	private double gPA;
	private double experience;
	private char gender;
	protected char studentPersonality;

	public char getStudentPersonality() {
		return studentPersonality;
	}

	public void setStudentPersonality(char studentPersonality) {
		this.studentPersonality = studentPersonality;
	}

	private ArrayList<String> Skills = new ArrayList<String>();
	private ArrayList<String> Frameworks = new ArrayList<String>();
	private String[] preferredProjects = new String[4];
	private String[] preferredRoles = new String[2];
	private String[] dislikedMembers = new String[3];
	public static ArrayList<Student> allStudents = new ArrayList<Student>();
	public static int studentsregistered;

	public Student() {
	}

	public Student(String studentID, String firstName, String lastName, String emailID, String userName,
			String password, String universityName, double gPA, double experience, char gender,
			ArrayList<String> Skills, ArrayList<String> Frameworks, String[] preferredProjects, String[] preferredRoles,
			String[] dislikedMembers, char studentPersonality)

	{
		super(studentID, firstName, lastName, emailID, userName, password, universityName);
		this.gender = gender;
		this.dislikedMembers = dislikedMembers;
		this.experience = experience;
		this.Frameworks = Frameworks;
		this.preferredProjects = preferredProjects;
		this.preferredRoles = preferredRoles;
		this.Skills = Skills;
		this.universityName = universityName;
		this.gPA = gPA;
		this.studentPersonality = 0;

	}

	public String getstudentID() {
		return studentID;
	}

	public void setstudentID(String studentID) {
		this.studentID = studentID;

	}

	public String getemailID() {
		return emailID;
	}

	public void setemailID(String emailID) {
		this.emailID = emailID;
	}

	public char getgender() {
		return gender;
	}

	public void setgender(char gender) {
		this.gender = gender;
	}

	public String getuniversityName() {
		return universityName;
	}

	public void setuniversityName(String universityName) {
		this.universityName = universityName;
	}

	public double getgPA() {
		return gPA;
	}

	public void setgPA(double gPA) {
		this.gPA = gPA;
	}

	public double getexperience() {
		return experience;
	}

	public void setexperience(double experience) {
		this.experience = experience;
	}

	public String[] getpreferredProjects() {
		return preferredProjects;
	}

	public void setpreferredProjects(String[] preferredProjects) {
		this.preferredProjects = preferredProjects;

	}

	public String[] getpreferredRoles() {
		return preferredRoles;
	}

	public void setpreferredRoles(String[] preferredRoles) {
		this.preferredRoles = preferredRoles;

	}

	public String[] getdislikedMembers() {
		return dislikedMembers;
	}

	public void setdislikedMembers(String[] dislikedMembers) {
		this.dislikedMembers = dislikedMembers;

	}

	public void start() {
		int ch = 0;
		do {
			try {
				System.out.println("\n1.Enter preferred projects\n2.Enter preferred roles\n3.Enter disliked members\n");
				ch = Integer.parseInt(s.next());
			} catch (NumberFormatException e) {
				System.err.println("enter an integer");
			}
		} while (ch < 1 || ch > 4);

		switch (ch) {
		case 1:
			enterpreferredProjects();
			// super.start();
			break;
		case 2:
			enterpreferredRoles();
			break;
		case 3:
			enterdislikedMembers();
			break;
		default:
			System.out.println("Invalid choice!");
			break;
		}
	}

	public void enterpreferredProjects() {
		String input;
		int j = 0;
		System.out.println(" You are allowed to enter 4 projects you would like to work for.");

		for (int i = 0; i < 4; i++) {

			System.out.print("\n Please enter your preferred project number " + (j + 1) + ":");
			input = scan.next();
			input += scan.nextLine();

			for (Project p : Project.pr) {

				if (p.getProjectId().compareTo(input) == 0) {

					p.setPopularityCounter(p.getPopularityCounter() + (4 - i));

				}
			}

			j++;

		}

	}

	public void enterpreferredRoles() {

		System.out.println(" You are allowed to enter 2 roles of your preference for this project.");
		for (int i = 0; i < 2; i++) {
			boolean checkpref = false;
			do {
				System.out.print("\n Please enter your preferred role number " + (i + 1) + ":");
				String input = scan.next();
				boolean existing = false;
				for (int k = 0; k < preferredRoles.length; k++) {
					if (input.equals(preferredRoles[k])) {
						existing = true;
					}
				}
				if (!existing) {
					preferredRoles[i] = input;
					checkpref = true;
				} else {
					checkpref = false;
					System.out.println("You have already registered this preference !!");
				}

			} while (!checkpref);
		}

	}

	public void enterdislikedMembers() {

		System.out.println(getstudentID() + "! You are allowed to enter 3 members you do not wish to team up with.");
		for (int i = 0; i < 3; i++) {
			boolean checkstudent = false;
			do {
				System.out.print(" \nPlease enter the student ID of member number " + (i + 1) + ":");
				String input = scan.next();
				// add for null
				boolean existing = false;
				for (int k = 0; k < dislikedMembers.length; k++) {
					if (input.equals(dislikedMembers[k])) {
						existing = true;
					}
				}
				if (!existing) {
					if (input.equals(getstudentID())) {
						System.out.println("\nYou cannot enter your own ID!!");
					} else {
						for (int j = 0; j < allStudents.size(); j++) {
							if (input.equals(allStudents.get(j).getstudentID())) {
								checkstudent = true;
								dislikedMembers[i] = input;
							}
						}
						if (!checkstudent) {
							System.out.println("\nNo student found. Please enter an existing student's ID");
						}
					}
				} else {
					checkstudent = false;
					System.out.println("You have already registered this student ID as disliked Member!!");
				}
			} while (!checkstudent);
		}

	}

	public static void addStudent(Student student) {
//		boolean studentpresent = false;
//		for (int j = 0; j < allStudents.size(); j++) {
//			if (student.getstudentID().equals(allStudents.get(j).getstudentID())) {
//				System.out.println("Student Already exists");
//				studentpresent = true;
//			}
//		}
//		if (!studentpresent) {
//			allStudents.add(student);
//		}
	}
}