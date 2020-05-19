import java.util.ArrayList;
import java.util.Scanner;

public class Student extends User {
	static Scanner scan = new Scanner(System.in); // TODO remove scanner
	private double gPA;
	private double experience;
	private char gender;
	private char studentPersonality; // Changes made according to Class diagram
	private Role[] preferredRoles = new Role[2];// TODO Change to arraylist
	private Project[] preferredProjects = new Project[4]; // TODO Change to arraylist
	private Student[] dislikedMembers = new Student[3]; // TODO Change to arraylist
	public static ArrayList<Student> allStudents = new ArrayList<>(); // TODO Maybe remove

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

	public double getExperience()
	{
		return experience;
	}

	public void setExperience(double experience)
	{
		this.experience = experience;
	}
	public Project[] getPreferredProjects() {
		return preferredProjects;
	}

	public double getgPA() {
		return gPA;
	}

	public Role[] getPreferredRoles() {
		return preferredRoles;
	}

	public void setPreferredRoles(Role[] preferredRoles) {
		this.preferredRoles = preferredRoles;
	}

	public Student[] getDislikedMembers() {
		return dislikedMembers;
	}

	public void setDislikedMembers(Student[] dislikedMembers) {
		this.dislikedMembers = dislikedMembers;
	}

	public void setgPA(double gPA) {
		this.gPA = gPA;
	}

	public void setPreferredProjects(Project[] preferredProjects)
	{
		this.preferredProjects = preferredProjects;
	}

	public void enterDislikedMembers()
	{
		System.out.println(getId() + "! You are allowed to enter 3 members you do not wish to team up with.");
		for (int i = 0; i < 3; i++) {
			boolean studentExists = false;
			do {
				System.out.print(" \nPlease enter the student ID of member number " + (i + 1) + ":");
				String input = scan.next();
				boolean dislikedStudExists = false;
				for (Student dislikedMember : dislikedMembers) {
					if (input.equals(dislikedMember.getId())) {
						dislikedStudExists = true;
					}
				}

				if (!dislikedStudExists){
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
								studentExists = true;
								dislikedMembers[i] = allStudents.get(j);
							}
						}
						if (!studentExists)
						{
							System.out.println("\nNo student found. Please enter an dislikedStudExists student's ID");
						}
					}
				} else {

					System.out.println("You have already registered this student ID as disliked Member!!");
				}
			} while (!studentExists);
		}

	}

	public void enterPreferredProjects()
	{
		String input;
		System.out.println(" You are allowed to enter 4 projects you would like to work for.");
		for (int i = 0; i < 4; i++) {
			boolean projectExists = false;
				do {
					System.out.print("\n Please enter your preferred project number " + (i + 1) + ":");
					input = scan.next();
					input += scan.nextLine();
					boolean preferredProjExists = false;
					for (int k = 0; k < preferredProjects.length; k++) {
						if (input.equals(preferredProjects[k].getProjectId())) {
							preferredProjExists = true;
						}
					}
					if (!preferredProjExists) {
						for (int j = 0; j < Project.totalProjects.size(); j++) {
							if (input.equals(Project.totalProjects.get(j).getProjectId())) {
								projectExists = true;
								preferredProjects[i] = Project.totalProjects.get(j);
								Project.totalProjects.get(j).setPopularityCounter(Project.totalProjects.get(j).getPopularityCounter() + (4 - i));
							}
						}
						if (!projectExists) {
							System.out.println("\n No Project found. Please enter an preferredProjExists Project's ID");
						}
					} else {
						System.out.println("You have already registered this Project as a Preference!!");
					}
				}while(!projectExists);
		}
	}

	public void enterPreferredRoles(Project project)
	{
		String input;
		System.out.println(" You are allowed to enter 2 Roles you would like to apply for.");
		for (int i = 0; i < 2; i++)
		{
			boolean roleExists = false;
			do {
				System.out.print("\n Please enter your preferred Roles number " + (i + 1) + ":");
				input = scan.next();
				input += scan.nextLine();
				boolean preferredRoleExists = false;
				for (int k = 0; k < preferredRoles.length; k++) {
					if (input.equals(preferredRoles[k].getId())) {
						preferredRoleExists = true;
					}
				}
				if (!preferredRoleExists)
				{
					for (int j = 0; j < project.getRolesInProject().size(); j++)
					{
						if (input.equals(project.getRolesInProject().get(j).getId()))
						{
							roleExists = true;
							preferredRoles[i].setId(project.getRolesInProject().get(j).getId());
							preferredRoles[i].setRoleName(project.getRolesInProject().get(j).getRoleName());

							System.out.println("These are the list of Frameworks required for that role");
							for(int z = 0; z< project.getRolesInProject().get(j).getFrameworks().size(); z++)
							{
								System.out.println(project.getRolesInProject().get(j).getFrameworks().get(z));
							}
							int number =0;
							do {
								try {
									System.out.println(" Please enter the number of frameworks you know from the list above :");
									number = Integer.parseInt(scan.next());
								} catch (NumberFormatException e) {
									System.err.println("Enter a valid Integer");
								}
							}while(number < 0 || number > project.getRolesInProject().get(j).getFrameworks().size());

							for (int l = 0; l < number ; l++)
							{
								preferredRoles[i].enterFrameworks(project.getRolesInProject().get(j));
							}
						}
					}
					if (!roleExists) {
						System.out.println("\n No Role found. Please enter an preferredRoleExists Project's ID");
					}
				} else {
					System.out.println("You have already registered this Role as a Preference!!");
				}
			} while (!roleExists);
		}
	}

	public void studentMenu() {
		int ch = 0;
		do {
			try {
				System.out.println("\n1.Enter preferred projects\n2.Enter preferred roles\n3.Enter disliked members\n");
				ch = Integer.parseInt(s.next());
			} catch (NumberFormatException e) {
				System.err.println("Enter an integer");
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


}