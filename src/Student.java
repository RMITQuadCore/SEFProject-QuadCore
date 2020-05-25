import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Student extends User implements Serializable {
    private static final long serialVersionUID = -1640510881420655396L;
    public static ArrayList < Student > allStudents = new ArrayList < > (); // TODO Maybe remove
    private double gPA;
    private double experience;
    private char gender;
    private char studentPersonality; // Changes made according to Class diagram
    private Role[] preferredRoles = new Role[2]; // TODO Change to arraylist
    private Project[] preferredProjects = new Project[4]; // TODO Change to arraylist
    private Student[] dislikedMembers = new Student[3]; // TODO Change to arraylist

    private Team assignedTeam;



    public Student(String id, String firstName, String lastName, String emailID, String userName,
                   String password, String org, double gPA, double experience, char gender, char studentPersonality) {
        super(id, firstName, lastName, emailID, userName, password, org);
        this.gender = gender;
        this.experience = experience;
        this.gPA = gPA;
        this.studentPersonality = studentPersonality;
    }

    public Student() {}


    /**
     * Getter and setter methods.
     * @return
     */

    public char getStudentPersonality() {
        return studentPersonality;
    }
    public void setStudentPersonality(char studentPersonality) {
        this.studentPersonality = studentPersonality;
    }
    public char getGender() {
        return gender;
    }
    public double getExperience() {
        return experience;
    }
    public Project[] getPreferredProjects() {
        return preferredProjects;
    }
    public void setPreferredProjects(Project[] preferredProjects) {
        this.preferredProjects = preferredProjects;
    }
    public Student[] getDislikedMembers() {
        return dislikedMembers;
    }
    public void setExperience(double experience) {
        this.experience = experience;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
    public void setDislikedMembers(Student[] dislikedMembers) {
        this.dislikedMembers = dislikedMembers;
    }
    public Team getAssignedTeam() { return assignedTeam; }

    public void setAssignedTeam(Team assignedTeam) { this.assignedTeam = assignedTeam; }

    public double getGpa() {
        return gPA;
    }

    public void setgPA(double gPA) {

        this.gPA = gPA;
    }
    public Role[] getPreferredRoles() {
        return preferredRoles;
    }

    public void setPreferredRoles(Role[] preferredRoles) {
        this.preferredRoles = preferredRoles;
    }

    /**
     * Method to display menu for Student and further navigate to required functionality.
     * @throws IOException
     */
    public void studentMenu() throws IOException, NullPointerException{
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        boolean quit = false;
        do {
        System.out.println("\n                    ****Hello! " + getFirstName() +"****\n" +
                "                    Your Details are given below :\n" +
                "                    Your System ID is " + getId() +"\n" +
                "                    Name \t\t\t: " + getFirstName() + " " + getLastName() +"\n" +
                "                    Organisation \t: " + getOrganisation() +"\n" +
                "                    Email\t\t\t: " + getEmailID() +"\n" +
                "                    Gender\t\t\t: " + getGender()  +"\n" +
                "                    GPA\t\t\t\t: " + numberFormat.format(getGpa())  +"\n" +
                "                    Experience \t\t: " + numberFormat.format(getExperience()) + " Years"  +"\n\n" );
        System.out.print("                    Project Preferences \t: ");

        ArrayList<Project>preference = new ArrayList<>();
        for(Project project : preferredProjects)
        {
            preference.add(project);
        }

            if (preference.isEmpty())
            {
                for (int i = 0; i < preferredProjects.length; i++)
                {
                    System.out.println("                    " +i + 1 + ". ID:\t " + preferredProjects[i].getProjectId() + ". Client:\t " + preferredProjects[i].getClient().getOrganisation() + "Title:\t " + preferredProjects[i].getProjectTitle() + "\n");
                }
            }else System.out.print("Not Entered");
        boolean empty = true;
        for (int i=0; i<dislikedMembers.length; i++) {
            if (dislikedMembers[i] != null) {
                empty = false;
                break;
            }
        }
        System.out.print("\n                    Disliked Students \t\t: ");
        if(!empty)
        {
            System.out.println();
            for(int i =0 ; i < dislikedMembers.length; i++)
            {
                System.out.println("                    " +(i+1) + ". ID : " + dislikedMembers[i].getId() + "\tName : " + dislikedMembers[i].getFirstName() + " " + dislikedMembers[i].getLastName());
            }
        }
        else System.out.println("Not Entered");
        System.out.println("\n\n\n");


        int choice = 0;
            System.out.println( "****Student Menu****\n" +
                    "1.Enter/Change Preferred Projects\n" +
                    "2.Enter/Change Preferred Roles\n" +
                    "3.Enter/Change Disliked Members\n" +
                    "4.Change GPA\n" +
                    "5.Get Team Assignment and Details\n" +
                    "6.Logout\n");
            choice = InputTools.intChecker(1, 6);

            switch (choice) {
                case 1:
                    if (Project.projectsNotAssigned.size() == 0) {
                        System.out.println(" Sorry no projects are available at the moment. " +
                                "Please come back later");
                    } else {
                        enterPreferredProjects();
                    }
                    break;

                case 2:
                    //enterPreferredRoles();
                    break;

                case 3:
                    if(Student.allStudents.size() == 0)
                    {
                    System.out.println(" Sorry no students to select at the moment. \n" +
                            " Please come back later");
                    } else
                        {
                            System.out.println("                    Students that currently signed up are: \n\n ");
                            int i = 1;
                            for (Student student : allStudents) {
                                if (!(student.getId() == getId())) {

                                    System.out.println("                    " + i + ". Student ID: " + student.getId() + "\t" +
                                            "Name \t\t\t: " + student.getFirstName() + " " + student.getLastName() );
                                    i++;
                                }
                            }
                            enterDislikedMembers();
                            break;
                        }

                case 4:
                    System.out.println("Current GPA: " +numberFormat.format(getGpa()) +"\n");

                    System.out.print("\nEnter your new GPA: ");
                    gPA = InputTools.floatChecker(0,4);
                    System.out.println("\n\n Your GPA is now : " +numberFormat.format(getGpa()) + "\n\n");
                    break;

                case 5:
                    if (assignedTeam == null)
                    {
                        System.out.println("\n\nSorry! No Team Assigned Yet, Please Come Back Later.\n\n");
                    }else
                    {
                        System.out.println( "\n\n Your Team ID:\t\t " +assignedTeam.getTeamID() +
                                "\n Project Assigned to your team:\t" + assignedTeam.getProjectAssigned().getClient() +":" + assignedTeam.getProjectAssigned().getProjectTitle() +
                                "\n Students in the team: " );
                        for(Student student : assignedTeam.getStudentsInTeam())
                        {
                            System.out.println("ID : " + student.getId() + "\tName : " + student.getFirstName() + " " + student.getLastName() +"\n");
                        }
                    }
                    break;

                case 6:
                    quit = true;
                    break;

                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        } while (!quit);
    }


    /**
     * Method for students to enter disliked students whom they don't want to team up with.
     */
    public void enterDislikedMembers() {
        System.out.println(getFirstName() + "! You are allowed to enter 3 members you do not wish to team up with.");
        for (int i = 0; i < 3; i++)
        {
            boolean studentExists = false;
            do {
                System.out.print(" \nPlease enter the student ID of member number " + (i+1) + ":");
                String input = Global.scan.nextLine();
                boolean dislikedStudExists = false;
                boolean empty = true;
                for (int j=0; j<dislikedMembers.length; j++)
                {
                    if (dislikedMembers[j] != null) {
                        empty = false;
                        break;
                    }
                }
                if(!empty)
                {
                    System.out.println(dislikedMembers.length);
                    for (int a =0 ; a < dislikedMembers.length; a++)
                    {
                        if(dislikedMembers[a] != null)
                        {
                            if (input.equalsIgnoreCase(dislikedMembers[a].getId())) {
                                dislikedStudExists = true;
                                break;
                            }
                        }

                    }
                }
                if (!dislikedStudExists) {
                    if (input.equalsIgnoreCase(getId())) {
                        System.out.println("\nYou cannot enter your own ID!!");
                    } else
                        {
                        for (Student student : allStudents)
                        {
                            if (input.equalsIgnoreCase(student.getId()))
                            {
                                studentExists = true;
                                dislikedMembers[i] = student;
                            }
                        }
                        if (!studentExists) {
                            System.out.println("\nNo student found. Please enter the  student's ID from the list above");
                        }
                    }
                } else {

                    System.out.println("You have already registered this student ID as disliked Member!!");
                }
            } while (!studentExists);
        }

    }

    /**
     * Method to enter preferred Projects by student
     * @throws IOException
     */
    public void enterPreferredProjects() throws IOException {
        System.out.println(" The List of available projects are ");
        for (Project a: Project.projectsNotAssigned) {
            System.out.println("ID:\t"+a.getProjectId() + "\t\tClient : " + a.getClient().getOrganisation()+ "\tTitle : " + a.getProjectTitle() + "\t" );
        }

        String input;
        System.out.println(" You are allowed to enter 4 projects you would like to work for:");
        for (int i = 0; i < 4; i++) {
            boolean projectExists = false;
            do {
                System.out.print("\n Please enter the ID of your preferred project number " + (i + 1) + ":");
                input = Global.scan.nextLine();
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
                            FileReadWrite.saveStudentDetails(Main.studentsFileName, Student.allStudents);
                            Project.totalProjects.get(j).setPopularityCounter(Project.totalProjects.get(j).getPopularityCounter() + (4 - i));
                        }
                    }
                    if (!projectExists) {
                        System.out.println("\n No Project found. Please enter an preferredProjExists Project's ID");
                    }
                } else {
                    System.out.println("You have already registered this Project as a Preference!!");
                }
            } while (!projectExists);
        }
    }


    /**
     * Method to enter preferred roles for a project by student.
     * @param project
     */
    public void enterPreferredRoles(Project project) {
        String input;
        //TODO Display list of roles
        System.out.println(" You are allowed to enter 2 Roles you would like to apply for.");
        for (int i = 0; i < 2; i++) {
            boolean roleExists = false;
            do {
                System.out.print("\n Please enter your preferred Roles number " + (i + 1) + ":");
                input = Global.scan.nextLine();
                boolean preferredRoleExists = false;
                for (int k = 0; k < preferredRoles.length; k++) {
                    if (input.equals(preferredRoles[k].getId())) {
                        preferredRoleExists = true;
                    }
                }
                if (!preferredRoleExists) {
                    for (int j = 0; j < project.getRolesInProject().size(); j++) {
                        if (input.equals(project.getRolesInProject().get(j).getId())) {
                            roleExists = true;
                            preferredRoles[i].setId(project.getRolesInProject().get(j).getId());
                            preferredRoles[i].setRoleName(project.getRolesInProject().get(j).getRoleName());

                            System.out.println("These are the list of Frameworks required for that role");
                            for (int z = 0; z < project.getRolesInProject().get(j).getFrameworks().size(); z++) {
                                System.out.println(project.getRolesInProject().get(j).getFrameworks().get(z));
                            }
                            int number = 0;
                            do {
                                try {
                                    System.out.println(" Please enter the number of frameworks you know from the list above :");
                                    number = Integer.parseInt(Global.scan.next());
                                } catch (NumberFormatException e) {
                                    System.err.println("Enter a valid Integer");
                                }
                            } while (number < 0 || number > project.getRolesInProject().get(j).getFrameworks().size());

                            for (int l = 0; l < number; l++) {
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
}