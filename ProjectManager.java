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
				enterStudentPersonality();
				break;
			case 2:
				con.setWeightage();
				break;
			case 3:
				setSignUpStatus();
				break;
			case 4:
				//Project pro = new Project();
				//pro.discardUnpopularProjects();
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
}
