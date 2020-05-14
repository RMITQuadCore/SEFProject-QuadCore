import java.util.*;


public class ProjectManager extends User {

	public ArrayList<String> studentPersonalities = new ArrayList<String>();
	public static ArrayList<Student> teamStudent = new ArrayList<Student>();
	public static ArrayList<Student> tempStudent = new ArrayList<>();

	public ArrayList<Student> getTempStudent() {
		return tempStudent;
	}

	public void setTempStudent(ArrayList<Student> tempStudent) {
		ProjectManager.tempStudent = tempStudent;
	}

	public static ArrayList<Student> getTeamStudent() {
		return teamStudent;
	}

	public static void setTeamStudent(ArrayList<Student> teamStudent) {
		ProjectManager.teamStudent = teamStudent;
	}

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
				//enterStudentPersonality();
				break;
			case 2:
				con.setWeightage();
				break;
			case 3:
				//setSignUpStatus();
				break;
			case 4:
				try {
					discardUnpopularProjects();
				} catch (ProjectMismatchException e) {
					// TODO Auto-generated catch block
					e.getMessage();
				}
				break;
			case 5:
				con.displayConstraints();
				break;
			case 6:
				createTeams();
				// check gpa creating teamStudent temp student
				// check female creating team Student
				// ;
				break;
			case 7:
				//displayTeams();
				break;

			//Stage3 methods
			//displayTeamFitness()
			//swapMembers()
			//changeStudentGpa()
		}
	}

	private void createTeams() {
		overallTeamGPACheck();
		teamMemberGPACheck();
		ensureFemaleInATeamandExperience();
		Team team = setProjectForTeam(teamStudent);
		Team.allTeams.add(team);
	}

	// check overallGPA hard constraint
	public static void overallTeamGPACheck() {
		tempStudent.addAll(Student.allStudents);
		teamStudent.clear();
		for (Student tp : tempStudent) {
			System.out.println("tempStudent is : " + tp.getId());
		}
		System.out.println();

		while (!tempStudent.isEmpty()) {

			for (int i = 0; i < 4; i++) {
				teamStudent.add(i, tempStudent.get(0));
				tempStudent.remove(0);
			}
			double sumOfGPA = 0;
			for (Student s : teamStudent) {
				sumOfGPA = sumOfGPA + s.getGPA();
			}

			if ((sumOfGPA / 4) > 3.5) {
				for (int i = 0; i < tempStudent.size(); i++) {
					if (tempStudent.get(i).getGender() == 'm' && tempStudent.get(i).getGPA() < 3.00) {
						for (Student tm : teamStudent) {
							if (tm.getGender() == 'm' && tm.getGPA() > 3.00) {
								tempStudent.add(tm);
								teamStudent.remove(tm);
								teamStudent.add(tempStudent.get(i));
								tempStudent.remove(tempStudent.get(i));
								i--;
								break;
							}
						}
						double sumOfNewGPA = 0;
						for (Student s : teamStudent) {
							sumOfNewGPA = sumOfNewGPA + s.getGPA();
						}
						if ((sumOfNewGPA / 4) <= 3.5) {
							break;
						}
					}

				}
			}
			for (Student s : teamStudent) {
				System.out.println("teamStudent is: " + s.getId());
			}
			double sumOfNewGPA = 0;
			for (Student s : teamStudent) {
				sumOfNewGPA = sumOfNewGPA + s.getGPA();
			}
//			if ((sumOfNewGPA / 4) > 3.5) {
//				System.out.print("Project team: ");
//				for (Student s : teamStudent) {
//					System.out.print(s.getId() + ",");
//				}
//				System.out.println(
//						" could not meet hard constraint of average GPA less than 3.5. So, Hard constraint turns into Soft constraint.");
//			}
			setTeamStudent(teamStudent);
			// teamStudent.clear();
		}
		// setTeamStudent(teamStudent);
	}

	// check GPA of individual team member
	private static void teamMemberGPACheck() {
		tempStudent.addAll(Student.allStudents);
		for (Student tp : tempStudent) {
			System.out.println("tempStudent is : " + tp.getId());
		}
		System.out.println();

		while (!tempStudent.isEmpty()) {

			for (int i = 0; i < 4; i++) {
				teamStudent.add(i, tempStudent.get(0));
				tempStudent.remove(0);
			}

			int GPAGreaterThanThreeCounter = 0;
			for (int i = 0; i < 4; i++) {
				if (teamStudent.get(i).getGPA() >= 3.00) {
					GPAGreaterThanThreeCounter++;
				}
			}

			while (GPAGreaterThanThreeCounter < 2) {
				int i;
				for (i = 0; i < tempStudent.size(); i++) {
					if (tempStudent.get(i).getGender() == 'm' && tempStudent.get(i).getGPA() >= 3.00) {
						for (Student tm : teamStudent) {
							if (tempStudent.get(i).getGender() == 'm' && tm.getGPA() < 3.00) {
								tempStudent.add(tm);
								teamStudent.remove(tm);
								teamStudent.add(tempStudent.get(i));
								tempStudent.remove(tempStudent.get(i));
								GPAGreaterThanThreeCounter++;
								break;
							}
						}
						System.out.println("GPAGreaterThanThreeCounter2: " + GPAGreaterThanThreeCounter);
						break;
					}
				}
				if (i == tempStudent.size()) {
					break;
				}
			}
			for (Student s : teamStudent) {
				System.out.println("teamStudent is: " + s.getId());
			}
			teamStudent.clear();
		}

	}

	public static boolean discardUnpopularProjects() throws ProjectMismatchException {
		int numProjectReqd = 0;
		int numStudents = Student.allStudents.size();
		System.out.println("Number of students: " + numStudents);

		int numProjects = Project.proj.size();
		System.out.println("Number of projects: " + numProjects);

		try {
			numProjectReqd = (numStudents / 4);
			System.out.println("Number of projects required: " + numProjectReqd);

			if (numProjectReqd > numProjects) {
				throw new ProjectMismatchException("Number of projects not enough!");

			}
		} catch (ProjectMismatchException ex) {
			System.err.println(ex.getMessage());
			return false;
		}

		Project temp;
		if (Project.proj.size() > 1) // check if the number of orders is larger than 1
		{
			for (int x = 0; x < Project.proj.size(); x++) // bubble sort outer loop
			{
				for (int i = 0; i < Project.proj.size() - x - 1; i++) {
					if (Project.proj.get(i).getPopularityCounter() < (Project.proj.get(i + 1).getPopularityCounter())) {
						temp = Project.proj.get(i);
						Project.proj.set(i, Project.proj.get(i + 1));
						Project.proj.set(i + 1, temp);
					}
				}
			}
		}


		for (int i = numProjects - 1; i >= numProjectReqd; i--) {

			Project.proj.remove(i);
		}
		System.out.println("Required Projects:");
		for (Project p : Project.proj) {
			System.out.println("\nsrc.Project ID: " + p.getProjectId() + "\nsrc.Project Details: " + p.getProjectDetails()
					+ "\nsrc.Project Popularity Counter:" + p.getPopularityCounter());
		}

		return true;
	}

	public static void ensureFemaleInATeamandExperience(){
		tempStudent.addAll(Student.allStudents);

		Integer numOfStudentsInATeam[];
		int femaleCount = 0, experienceCounter = 0,j=0;
		int numOfStudents = Student.allStudents.size();
		int numOfTempTeams = numOfStudents / 4;
		int numOfextraStudents = numOfStudents % 4; // extra students
		int i;
		if (numOfextraStudents == 0) {
			numOfStudentsInATeam = new Integer[numOfTempTeams];
		} else {
			numOfStudentsInATeam = new Integer[numOfTempTeams + 1];
		}

		if (numOfextraStudents == 3) {

			for (i = 0; i < numOfTempTeams; i++) {
				numOfStudentsInATeam[i] = 4;
			}
			numOfStudentsInATeam[i] = 3;
		} else if (numOfextraStudents == 2) {

			for (i = 0; i < numOfTempTeams; i++) {
				numOfStudentsInATeam[i] = 4;
			}
			numOfStudentsInATeam[i] = 2;
		} else if (numOfextraStudents == 1) {

			for (i = 0; i < numOfTempTeams - 1; i++) {
				numOfStudentsInATeam[i] = 4;
			}
			numOfStudentsInATeam[i] = 3;
			numOfStudentsInATeam[i + 1] = 2;
		} else {
			for (i = 0; i < numOfTempTeams; i++) {
				numOfStudentsInATeam[i] = 4;
			}
		}
		for (int y = 0; y < numOfStudentsInATeam.length; y++) {
			System.out.println(" " + numOfStudentsInATeam[y]);
		}

		for (Student t : tempStudent) {
			if (t.getGender() == 'f') {
				femaleCount++;
			}
		}
		while (j < numOfStudentsInATeam.length) {
			int k = 0;
			boolean femaleFound = false;
			while (k < numOfStudentsInATeam[j]) {
				for (int z = 0; z < tempStudent.size(); z++) {
					if (tempStudent.get(z).getGender() == 'f') {
						System.out.println("j = " + j + "k = " + k + "z = " + z + "\tFemale found, assigning to team");
						teamStudent.add(tempStudent.get(z));
						femaleFound = true;
						femaleCount--;
						System.out.println("added to teamStudent, length of team student: " + teamStudent.size());
						tempStudent.remove(tempStudent.get(z));
						System.out.println("removed from tempStudent, length of team student: " + tempStudent.size());
						k++;
						break;
					}

					// for (int z = 0; z < tempStudent.size(); z++) {
					if (tempStudent.get(z).getGender() == 'm' && femaleFound == true) {
						System.out.println("j = " + j + "k = " + k + "z = " + z + "\tmale found, assigning to team");
						teamStudent.add(tempStudent.get(z));
						System.out.println("added to teamStudent, length of team student: " + teamStudent.size());
						tempStudent.remove(tempStudent.get(z));
						System.out.println("removed from tempStudent, length of team student: " + tempStudent.size());
						k++;
						break;

					} else {
						if (femaleCount == 0) {
							teamStudent.add(tempStudent.get(z));
							System.out.println("added to teamStudent, length of team student: " + teamStudent.size());
							tempStudent.remove(tempStudent.get(z));
							System.out
									.println("removed from tempStudent, length of team student: " + tempStudent.size());
							k++;
							break;
						}
					}

				}

			}

			for (Student t : tempStudent) {
				System.out.println(t.getId());
			}
			experienceCounter = 0;
			System.out.println("*************************\nTeam " + j);
			for (Student tm : teamStudent) {
				// System.out.println(tm.getId());
				if (tm.getExperience() >= 5) {
					experienceCounter++;
				}
				// System.out.println("experience counter: "+experienceCounter);
			}
			for (Student tm : teamStudent) {
				if (experienceCounter == 0) {
					if (tm.getGender() == 'm') {
						for (int a = 0; a < tempStudent.size(); a++) {
							if (tempStudent.get(a).getGender() == 'm' && tempStudent.get(a).getGPA() == tm.getGPA()
									&& tempStudent.get(a).getExperience() >= 5) {

								tempStudent.add(tm);
								teamStudent.remove(tm);

								teamStudent.add(tempStudent.get(a));
								tempStudent.remove(tempStudent.get(a));
								System.out.println(tm.getId() + " swapped with " + tempStudent.get(a).getId());
								break;
							}
						}

					} else {
						if (tm.getGender() == 'f') {
							for (int a = 0; a < tempStudent.size(); a++) {
								if (tempStudent.get(a).getGender() == 'f' && tempStudent.get(a).getGPA() == tm.getGPA()
										&& tempStudent.get(a).getExperience() >= 5) {

									tempStudent.add(tm);
									teamStudent.remove(tm);

									teamStudent.add(tempStudent.get(a));
									tempStudent.remove(tempStudent.get(a));
									System.out.println(tm.getId() + " swapped");
									break;
								}
							}
						}

					}
				}
				break;
			}

			for (Student newTeam : teamStudent) {

				System.out.println(newTeam.getId());
			}
			j++;

			teamStudent.clear();
		}

	}
	public static Team setProjectForTeam(ArrayList<Student> teamStudent)
	{
		HashMap<Project , Integer> projectPopularity = new HashMap<>();
		for (int i = 0; i < Project.totalProjects.size() ; i++)
		{
			projectPopularity.put(Project.totalProjects.get(i),0);
		}

		for (int i = 0; i <teamStudent.size(); i++)
		{
			Project [] preference = new Project[4];
			preference = teamStudent.get(i).getPreferredProjects();
			for (int j = 0; j < 4; j++)
			{
				if(projectPopularity.containsKey(preference[j]))
				{
					projectPopularity.put(preference[j], projectPopularity.get(preference[j]) + (4-j));
				}
			}
		}
		Team team01 = new Team("TEAM01");

		int maxValueInMap=(Collections.max(projectPopularity.values()));
		for (HashMap.Entry<Project, Integer> entry : projectPopularity.entrySet())
		{
			if (entry.getValue()==maxValueInMap) {
				team01.setProjectAssigned(entry.getKey());
			}
		}
		team01.setStudentsInTeam(teamStudent);
		return team01;
	}
}
