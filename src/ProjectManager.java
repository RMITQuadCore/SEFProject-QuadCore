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

	Scanner scan = SingletonScanner.getInstance();

	private String id;
	private String firstName;
	private String lastName;
	private String org;
	private String emailID;
	private String userName;
	private String password;
	private boolean signUpStatus = true;

	public ProjectManager() {
	}

	public ProjectManager(String id, String firstName, String lastName, String emailID, String userName,
						  String password, String org) {
		super(id, firstName, lastName, emailID, userName, password, org);
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

	//Method to discard unpopular projects //TODO This will be in Project
	public static boolean discardUnpopularProjects() throws ProjectMismatchException {
		int numProjectReqd = 0;
		int numStudents = Student.allStudents.size();
		System.out.println("Number of students: " + numStudents);

		int numProjects = Project.totalProjects.size();
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
		if (Project.totalProjects.size() > 1) // check if the number of orders is larger than 1
		{
			for (int x = 0; x < Project.totalProjects.size(); x++) // bubble sort outer loop
			{
				for (int i = 0; i < Project.totalProjects.size() - x - 1; i++) {
					if (Project.totalProjects.get(i).getPopularityCounter() < (Project.totalProjects.get(i + 1).getPopularityCounter())) {
						temp = Project.totalProjects.get(i);
						Project.totalProjects.set(i, Project.totalProjects.get(i + 1));
						Project.totalProjects.set(i + 1, temp);
					}
				}
			}
		}


		for (int i = numProjects - 1; i >= numProjectReqd; i--) {

			Project.totalProjects.remove(i);
		}
		System.out.println("Required Projects:");
		for (Project p : Project.totalProjects) {
			System.out.println("\nsrc.Project ID: " + p.getProjectId() + "\nsrc.Project Details: " + p.getProjectDetails()
					+ "\nsrc.Project Popularity Counter:" + p.getPopularityCounter());
		}

		return true;
	}

	public String getEmailID() {
		return this.emailID;
	}

	public boolean getSignUpStatus() {
		return signUpStatus;
	}

	public String getOrganisation() {
		return this.org;
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
							stud.setStudentPersonality(studPersonality); // TODO setStudent Personality to Student Class Object
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
				choice = Integer.parseInt(scan.next());
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
						"7. Display Teams\n" +
						"8. Logout\n");
				choice = Integer.parseInt(scan.next());
			} catch (NumberFormatException e) {
				System.err.println("Please enter an integer (1-6)");
			}


			Constraint constraint = new Constraint();

			switch (choice) {
				case 1:
					enterStudentPersonality();
					break;
				case 2:
					constraint.setWeightage();
					break;
				case 3:
					setSignUpStatus();
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
					constraint.displayConstraints();
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
				case 8:
					mainMenu();
					break;

				//Stage3 methods
				//displayTeamFitness()
				//swapMembers()
				//changeStudentGpa()
			}
		} while (choice != 8);
	}


	//Method to form teams for projects
	private void createTeams() {
		overallTeamGPACheck();
		teamMemberGPACheck();
		ensureFemaleInATeamandExperience();
		personalityConstraint();

		Team team = setProjectForTeam(teamStudent);
		Team.allTeams.add(team);
	}


	// 1. Check overallGPA hard constraint
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
				sumOfGPA = sumOfGPA + s.getgPA();
			}

			if ((sumOfGPA / 4) > 3.5) {
				for (int i = 0; i < tempStudent.size(); i++) {
					if (tempStudent.get(i).getGender() == 'm' && tempStudent.get(i).getgPA() < 3.00) {
						for (Student tm : teamStudent) {
							if (tm.getGender() == 'm' && tm.getgPA() > 3.00) {
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
							sumOfNewGPA = sumOfNewGPA + s.getgPA();
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
				sumOfNewGPA = sumOfNewGPA + s.getgPA();
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

	// 2. Check GPA of individual team member
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
				if (teamStudent.get(i).getgPA() >= 3.00) {
					GPAGreaterThanThreeCounter++;
				}
			}

			while (GPAGreaterThanThreeCounter < 2) {
				int i;
				for (i = 0; i < tempStudent.size(); i++) {
					if (tempStudent.get(i).getGender() == 'm' && tempStudent.get(i).getgPA() >= 3.00) {
						for (Student tm : teamStudent) {
							if (tempStudent.get(i).getGender() == 'm' && tm.getgPA() < 3.00) {
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

	//3 & 4 Ensuring Female student and 5 years of experience constraint
	public static void ensureFemaleInATeamandExperience() {
		tempStudent.addAll(Student.allStudents);

		Integer numOfStudentsInATeam[];
		int femaleCount = 0, experienceCounter = 0, j = 0;
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
							if (tempStudent.get(a).getGender() == 'm' && tempStudent.get(a).getgPA() == tm.getgPA()
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
								if (tempStudent.get(a).getGender() == 'f' && tempStudent.get(a).getgPA() == tm.getgPA()
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

	//5 & 6 Function to make sure teams have unique personalities
	// and A or B type personality is present in a team
	//Satisfying Personality Soft Constraint
	public static void personalityConstraint() {

		ArrayList<Student> tempStudent = new ArrayList<Student>();
		ArrayList<Student> teamStudent = new ArrayList<Student>();
		ArrayList<Student> projectGroupStudent = new ArrayList<Student>();

		Student s = new Student();
		tempStudent = s.allStudents;

		System.out.println("tempStudent: " + tempStudent);

		while (!tempStudent.isEmpty()) {

			for (int i = 0; i < 4; i++) {
				teamStudent.add(i, tempStudent.get(0));
				tempStudent.remove(0);
			}

			for (Student s1 : teamStudent) {
				System.out.println("in teamStudent: " + s1);
			}


			//To make team of unique personalities
			char studPersonality = '0';
			char[] teamPersonality = new char[4];
			char duplicatePersonality[] = new char[4];
			int nosOfDuplicatePerso = 0;

			for (int i = 0; i < 4; i++) {
				teamPersonality[i] = teamStudent.get(i).getStudentPersonality();
			}

			//To check if duplicate personalities are present
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					k = +j;
					if (j == k) {
						continue;
					} else if (teamPersonality[j] == teamPersonality[k]) {
						duplicatePersonality[nosOfDuplicatePerso] = teamPersonality[j];
						nosOfDuplicatePerso++;
					}
				}

			}

			//Removing duplicate personalities
			while (nosOfDuplicatePerso != 0) {
				int i = 0;
				for (i = 0; i < tempStudent.size(); i++) {
					if (tempStudent.get(i).getGender() == 'm'
							&& tempStudent.get(i).getStudentPersonality() != duplicatePersonality[1]
							&& tempStudent.get(i).getStudentPersonality() != duplicatePersonality[2]
							&& tempStudent.get(i).getStudentPersonality() != duplicatePersonality[3]) {

						for (Student tm : teamStudent) {
							if (tempStudent.get(i).getGender() == 'm'
									&& (tm.getStudentPersonality() == duplicatePersonality[1]
									|| tm.getStudentPersonality() == duplicatePersonality[2]
									|| tm.getStudentPersonality() == duplicatePersonality[3])) {

								tempStudent.add(tm);
								teamStudent.remove(tm);
								teamStudent.add(tempStudent.get(i));
								tempStudent.remove(tempStudent.get(i));
								nosOfDuplicatePerso--;
								break;
							}
						}
					}
				}

				//If no student remains to satisfy condition
				if (i == tempStudent.size()) {
					break;
				}
			}

			//To ensure A or B personality is present
			char persoA = 'A';
			char persoB = 'B';
			boolean persoAOrBPresent = true;

			for (int j = 0; j < 4; j++) {
				if (!(teamPersonality[j] == persoA || teamPersonality[j] == persoB)) {
					//if A or B personality is not present in team
					persoAOrBPresent = false;

					//Adding A or B type personalty in team
					while (persoAOrBPresent == false) {
						int i = 0;
						for (i = 0; i < tempStudent.size(); i++) {
							if (tempStudent.get(i).getGender() == 'm'
									&& (tempStudent.get(i).getStudentPersonality() == persoA
									|| tempStudent.get(i).getStudentPersonality() == persoB)) {

								for (Student tm : teamStudent) {
									if (tempStudent.get(i).getGender() == 'm'
											&& (tempStudent.get(i).getStudentPersonality() != persoA
											|| tempStudent.get(i).getStudentPersonality() != persoB)) {

										tempStudent.add(tm);
										teamStudent.remove(tm);
										teamStudent.add(tempStudent.get(i));
										tempStudent.remove(tempStudent.get(i));
										persoAOrBPresent = true;
										break;
									}
								}
							}
						}

						//If no student remains to satisfy condition
						if (i == tempStudent.size()) {
							break;
						}
					}
				}
			}
		}
	}

	//Method to assign Project to a team
	public static Team setProjectForTeam(ArrayList<Student> teamStudent) {
		HashMap<Project, Integer> projectPopularity = new HashMap<>();
		for (int i = 0; i < Project.totalProjects.size(); i++) {
			projectPopularity.put(Project.totalProjects.get(i), 0);
		}

		for (int i = 0; i < teamStudent.size(); i++) {
			Project[] preference = new Project[4];
			preference = teamStudent.get(i).getPreferredProjects();
			for (int j = 0; j < 4; j++) {
				if (projectPopularity.containsKey(preference[j])) {
					projectPopularity.put(preference[j], projectPopularity.get(preference[j]) + (4 - j));
				}
			}
		}
		Team team01 = new Team("TEAM01");

		int maxValueInMap = (Collections.max(projectPopularity.values()));
		for (HashMap.Entry<Project, Integer> entry : projectPopularity.entrySet()) {
			if (entry.getValue() == maxValueInMap) {
				team01.setProjectAssigned(entry.getKey());
			}
		}
		team01.setStudentsInTeam(teamStudent);
		return team01;
	}
}
