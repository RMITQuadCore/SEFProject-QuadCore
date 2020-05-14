import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Student extends User {
	static Scanner scan = new Scanner(System.in);
	private double gPA;
	private double experience;
	private char gender;
	protected char studentPersonality; // Changes made according to Class diagram
	private Role[] preferredRoles = new Role[2];// This should be deleted ?
	private Project[] preferredProjects = new Project[4]; // Storing preferred project as collection
	private Student[] dislikedMembers = new Student[3]; // Storing student as a collection
	public static ArrayList<Student> allStudents = new ArrayList<>();

	public Student(String id, String firstName, String lastName, String emailID, String userName,
			String password, String org, double gPA, double experience, char gender, char studentPersonality)
	{
		super(id, firstName,lastName,emailID,userName,password,org);
		this.gender = gender;
		this.experience = experience;
		this.gPA = gPA;
		this.studentPersonality = studentPersonality;
	}

	public Student(){}

	public char getStudentPersonality()
	{
		return studentPersonality;
	}

	public void setStudentPersonality(char studentPersonality)
	{
		this.studentPersonality = studentPersonality;
	}

	public char getGender()
	{
		return gender;
	}

	public void setGender(char gender)
	{
		this.gender = gender;
	}

	public double getGPA()
	{
		return gPA;
	}

	public void setGPA(double gPA)
	{
		this.gPA = gPA;
	}

	public double getExperience()
	{
		return experience;
	}

	public void setExperience(double experience)
	{
		this.experience = experience;
	}

	public void enterDislikedMembers()
	{
		System.out.println(getId() + "! You are allowed to enter 3 members you do not wish to team up with.");
		for (int i = 0; i < 3; i++) {
			boolean checkStudent = false;
			do {
				System.out.print(" \nPlease enter the student ID of member number " + (i + 1) + ":");
				String input = scan.next();
				boolean existing = false;
				for (Student dislikedMember : dislikedMembers) {
					if (input.equals(dislikedMember.getId())) {
						existing = true;
					}
				}
				if (!existing)
				{
					if (input.equals(getId()))
					{
						System.out.println("\nYou cannot enter your own ID!!");
					}
					else
					{
						for (int j = 0; j < allStudents.size(); j++)
						{
							if (input.equals(allStudents.get(j).getId()))
							{
								checkStudent = true;
								dislikedMembers[i] = allStudents.get(j);
							}
						}
						if (!checkStudent)
						{
							System.out.println("\nNo student found. Please enter an existing student's ID");
						}
					}
				} else {

					System.out.println("You have already registered this student ID as disliked Member!!");
				}
			} while (!checkStudent);
		}

	}

	public void enterPreferredProjects()
	{
		String input;
		System.out.println(" You are allowed to enter 4 projects you would like to work for.");
		for (int i = 0; i < 4; i++) {
			boolean checkProject = false;
				do {
					System.out.print("\n Please enter your preferred project number " + (i + 1) + ":");
					input = scan.next();
					input += scan.nextLine();
					boolean existing = false;
					for (int k = 0; k < preferredProjects.length; k++) {
						if (input.equals(preferredProjects[k].getProjectId())) {
							existing = true;
						}
					}
					if (!existing) {
						for (int j = 0; j < Project.pr.size(); j++) {
							if (input.equals(Project.pr.get(j).getProjectId())) {
								checkProject = true;
								preferredProjects[i] = Project.pr.get(j);
								Project.pr.get(j).setPopularityCounter(Project.pr.get(j).getPopularityCounter() + (4 - i));
							}
						}
						if (!checkProject) {
							System.out.println("\n No Project found. Please enter an existing Project's ID");
						}
					} else {
						System.out.println("You have already registered this Project as a Preference!!");
					}
				}while(!checkProject);
		}
	}

	public void enterPreferredRoles(Project project) {
		String input;
		System.out.println(" You are allowed to enter 2 Roles you would like to apply for.");
		for (int i = 0; i < 2; i++)
		{
			boolean checkRole = false;
			do {
				System.out.print("\n Please enter your preferred Roles number " + (i + 1) + ":");
				input = scan.next();
				input += scan.nextLine();
				boolean existing = false;
				for (int k = 0; k < preferredRoles.length; k++) {
					if (input.equals(preferredRoles[k].getId())) {
						existing = true;
					}
				}
				if (!existing)
				{
					for (int j = 0; j < project.allRoles.size(); j++)
					{
						if (input.equals(project.allRoles.get(j).getId()))
						{
							checkRole = true;
							preferredRoles[i].setId(project.allRoles.get(j).getId());
							preferredRoles[i].setRoleName(project.allRoles.get(j).getRoleName());

							System.out.println("These are the list of Frameworks required for that role");
							for(int z = 0 ; z< project.allRoles.get(j).getFrameworks().size();z++)
							{
								System.out.println(project.allRoles.get(j).getFrameworks().get(z));
							}
							int number =0;
							do {
								try {
									System.out.println(" Please enter the number of frameworks you know from the list above :");
									number = Integer.parseInt(scan.next());
								} catch (NumberFormatException e) {
									System.err.println("Enter a valid Integer");
								}
							}while(number < 0 || number > project.allRoles.get(j).getFrameworks().size());

							for (int l = 0; l < number ; l++)
							{
								preferredRoles[i].enterFrameworks(project.allRoles.get(j));
							}
						}
					}
					if (!checkRole) {
						System.out.println("\n No Role found. Please enter an existing Project's ID");
					}
				} else {
					System.out.println("You have already registered this Role as a Preference!!");
				}
			}while(!checkRole);
		}
	}


//	public static void addStudent(Student student) {
////		boolean studentpresent = false;
////		for (int j = 0; j < allStudents.size(); j++) {
////			if (student.getstudentID().equals(allStudents.get(j).getstudentID())) {
////				System.out.println("Student Already exists");
////				studentpresent = true;
////			}
////		}
////		if (!studentpresent) {
////			allStudents.add(student);
////		}
//	}
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
				enterPreferredProjects();
				// super.start();
				break;
			case 2:
				//enterPreferredRoles();
				break;
			case 3:
				enterDislikedMembers();
				break;
			default:
				System.out.println("Invalid choice!");
				break;
		}
	}

	public Project[] getPreferredProjects() {
		return preferredProjects;
	}

	public void setPreferredProjects(Project[] preferredProjects) {
		this.preferredProjects = preferredProjects;
	}

	public char getgender() {
		return gender;
	}
}