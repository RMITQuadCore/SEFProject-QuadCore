import java.util.ArrayList;
import java.util.Scanner;

public class Project {
	public static ArrayList<Project> pr = new ArrayList<Project>();
	public String projectId = "PROJ100";
	public String clientId;
	public String projectTitle;
	public String projectDetails;
	public String[] studentId;
	public String role;
	public String[] framework = new String[20];
	public char status;
	public static int projectCounter = 0;
	public int popularityCounter;
	Scanner sc = new Scanner(System.in);

	public Project() {
		// TODO Auto-generated constructor stub
	}

	public Project(String clientId, String projectId, String projectTitle, String projectDetails, String role,
			String[] framework) {
		this.clientId = clientId;
		this.projectId = projectId;
		this.projectTitle = projectTitle;
		this.projectDetails = projectDetails;
		this.studentId = null;
		this.role = role;
		this.framework = framework;
		this.popularityCounter = 0;
	}

	public String getRole() {
		return role;
	}

	public String[] getFramework() {
		return framework;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setFramework(String[] framework) {
		this.framework = framework;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public String getProjectDetails() {
		return projectDetails;
	}

	public void setProjectDetails(String projectDetails) {
		this.projectDetails = projectDetails;
	}

	public String[] getStudentId() {
		return studentId;
	}

	public void setStudentId(String[] studentId) {
		this.studentId = studentId;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public static int getProjectCounter() {
		return projectCounter;
	}

	public static void setProjectCounter(int projectCounter) {
		Project.projectCounter = projectCounter;
	}

	public int getPopularityCounter() {
		return popularityCounter;
	}

	public void setPopularityCounter(int popularityCounter) {
		this.popularityCounter = popularityCounter;
	}

	public String createProject() {
		projectId = "PROJ"
				+ String.format("%03d", (Integer.parseInt(getProjectId().substring(4, getProjectId().length())) + 1));

		System.out.println("Enter Project Title: ");
		projectTitle = sc.next();
		projectTitle += sc.nextLine();
		System.out.println("Enter Project details: ");
		projectDetails = sc.next();
		projectDetails += sc.nextLine();
		enterRole();
		return projectId;
	}

	public void enterRole() {
		System.out.println("Specify required role: ");
		role = sc.next();
		role += sc.nextLine();
		enterFramework();
	}

	public void enterFramework() {
		String choice;
		int i = 0;
		String[] framework1 = new String[20];
		do {
			System.out.println("Specify one framework '" + role + "' should be familiar with:");
			framework1[i] = sc.next();
			framework1[i] += sc.nextLine();
			i++;
			System.out.println("Do you want to add more frameworks to '" + role + "' role? Y/N");
			choice = sc.nextLine();
		} while (choice.toUpperCase().compareTo("N") != 0);
		this.setFramework(framework1);
		pr.add(new Project(clientId, projectId, projectTitle, projectDetails, role, framework));
	}

	public static void main(String[] args) {
		Project pr1 = new Project();
		pr1.createProject();
	}

}