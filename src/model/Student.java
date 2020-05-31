package model;

import main.Main;
import util.*;

import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Student extends User implements Serializable {
    private static final long serialVersionUID = -1640510881420655396L;
    public static ArrayList<Student> allStudents = new ArrayList < > ();
    private double gPA;
    private double experience;
    private char gender;
    private char studentPersonality;
    private Role[] preferredRoles = new Role[2];
    private Project[] preferredProjects = new Project[4];
    private Student[] dislikedMembers = new Student[3];
    private Team assignedTeam;

    public Student() {}

    public Student(String id, String firstName, String lastName, String emailID, String userName,
                   String password, String org, double gPA, double experience, char gender) {
        super(id, firstName, lastName, emailID, userName, password, org);
        this.gender = gender;
        this.experience = experience;
        this.gPA = gPA;
    }

    /**
     * Getter and setter methods.
     *
     */

    public char getStudentPersonality() {
        return studentPersonality;
    }

    public void setStudentPersonality(char studPersonality) {
        studentPersonality = studPersonality;
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

    public void setAssignedTeam(Team assignedTeam) {
        this.assignedTeam = assignedTeam;
    }

    public double getGpa() {
        return gPA;
    }


    /**
     * Method to display menu for Student and further navigate to required functionality.
     *
     * @throws IOException if an I/O error occurs
     */
    public void studentMenu() throws IOException, NullPointerException {
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        boolean quit = false;
        do {


            System.out.println("\n                    ****Hello! " + getFirstName() + "****\n" +
                    "                    Your Details are given below :\n" +
                    "                    Your System ID is " + getId() + "\n" +
                    "                    Name \t\t\t: " + getFirstName() + " " + getLastName() + "\n" +
                    "                    Organisation \t: " + getOrganisation() + "\n" +
                    "                    Email\t\t\t: " + getEmailID() + "\n" +
                    "                    Gender\t\t\t: " + getGender() + "\n" +
                    "                    GPA\t\t\t\t: " + numberFormat.format(getGpa()) + "\n" +
                    "                    Experience \t\t: " + numberFormat.format(getExperience()) + " Years" + "\n\t\t\t\t\tPersonality Type: " + getStudentPersonality() + "\n\n");

            boolean preferencesIsEmpty = true;
            for (int i = 0; i < preferredProjects.length; i++) {
                if (preferredProjects[i] != null) {
                    preferencesIsEmpty = false;

                    break;
                }
            }
            System.out.print("\n                    Preferred Projects \t\t: ");
            if (!preferencesIsEmpty) {
                System.out.println();
                for (int i = 0; i < preferredProjects.length; i++) {
                    System.out.println("                    " + (i + 1) + ". ID : " + preferredProjects[i].getProjectId() + "\tClient: " + preferredProjects[i].getClient().getOrganisation() + "\t\t\t\t\tTitle : " + preferredProjects[i].getProjectTitle());
                }
            } else System.out.print("Not Entered");

            boolean preferenceRoleIsEmpty = true;
            for (int i = 0; i < preferredRoles.length; i++) {
                if (preferredRoles[i] != null) {
                    preferenceRoleIsEmpty = false;
                    break;
                }
            }
            System.out.print("\n                    Preferred Roles \t\t: ");
            if (!preferenceRoleIsEmpty) {
                System.out.println();
                for (int i = 0; i < preferredRoles.length; i++) {
                    System.out.println("                    " + (i + 1) + ". Project ID : " + preferredRoles[i].getId() + "\tRole Name: " + preferredRoles[i].getRoleName());
                }
            } else System.out.print("Not Entered");

            System.out.print("\n                    Frameworks Known: \t\t: ");
            if (!preferenceRoleIsEmpty) {
                System.out.println();
                int j = 0;
                for (int i = 0; i < preferredRoles.length; i++) {
                    for (String framework: preferredRoles[i].getFrameworks()) {
                        System.out.println("                    " + (j + 1) + ". Name : " + framework);
                        j++;
                    }

                }
            } else System.out.print("Not Entered");

            boolean empty = true;
            for (int i = 0; i < dislikedMembers.length; i++) {
                if (dislikedMembers[i] != null) {
                    empty = false;
                    break;
                }
            }
            System.out.print("\n                    Disliked Students \t\t: ");
            if (!empty) {
                System.out.println();
                for (int i = 0; i < dislikedMembers.length; i++) {
                    System.out.println("                    " + (i + 1) + ". ID : " + dislikedMembers[i].getId() + "\tName : " + dislikedMembers[i].getFirstName() + " " + dislikedMembers[i].getLastName());
                }
            } else System.out.println("Not Entered");
            System.out.println("\n\n\n");

            int choice = 0;
            System.out.println(Constraint.ANSI_BLUE + "\n****Student Menu****\n" + Constraint.ANSI_RESET +
                    "1.Enter/Change Preferred Projects and Roles\n" +
                    "2.Enter/Change Disliked Members\n" +
                    "3.Get Team Assignment and Details\n" +
                    "4.Logout\n");
            choice = InputTools.intChecker(1, 4);

            switch (choice) {
                case 1:
                    if (Project.projectsNotAssigned.size() < 4) {
                        System.out.println(" Sorry not enough projects are available at the moment. " +
                                "Please come back later");
                    } else {
                        enterPreferredProjects();
                    }
                    break;

                case 2:
                    if (Student.allStudents.size() < 4) {
                        System.out.println(" Sorry no students to select at the moment. \n" +
                                " Please come back later");
                    } else {
                        System.out.println("                    Students that currently signed up are: \n\n ");
                        int i = 1;
                        for (Student student: allStudents) {
                            if (!(student.getId() == getId())) {

                                System.out.println("                    " + i + ". Student ID: " + student.getId() + "\t" +
                                        "Name \t\t\t: " + student.getFirstName() + " " + student.getLastName());
                                i++;
                            }
                        }
                        enterDislikedMembers();
                        break;
                    }

                case 3:
                    if (assignedTeam == null) {
                        System.out.println("\n\nSorry! No Team Assigned Yet, Please Come Back Later.\n\n");
                    } else {
                        System.out.println("\n\n\n Your Team ID:\t\t " + assignedTeam.getTeamID() +
                                "\n Project Assigned to your team:\t" + assignedTeam.getProjectAssigned().getClient().getOrganisation() +
                                ": " + assignedTeam.getProjectAssigned().getProjectTitle() +
                                "\n Students in the team: ");
                        for (Student student: assignedTeam.getStudentsInTeam()) {
                            System.out.println("ID : " + student.getId() + "\tName : " + student.getFirstName() + " " + student.getLastName());
                        }
                    }

                    break;

                case 4:
                    quit = true;
                    break;

                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        } while (!quit);

        ProjectManager.studentsNotInATeam = (ArrayList<Student>)allStudents.clone();
        FileReadWrite.saveStudentDetails(Main.studentsNotInATeamFileName, ProjectManager.studentsNotInATeam);
    }


    /**
     * Method for students to enter disliked students whom they don't want to team up with.
     */
    public void enterDislikedMembers() throws IOException {
        System.out.println(getFirstName() + "! You are allowed to enter 3 members you do not wish to team up with.");
        for (int i = 0; i < 3; i++) {
            boolean studentExists = false;
            do {
                System.out.print(" \nPlease enter the student ID of member number " + (i + 1) + ":");
                String input = Global.scan.nextLine();
                boolean dislikedStudExists = false;
                boolean empty = true;
                for (int j = 0; j < dislikedMembers.length; j++) {
                    if (dislikedMembers[j] != null) {
                        empty = false;
                        break;
                    }
                }
                if (!empty) {
                    for (int a = 0; a < dislikedMembers.length; a++) {
                        if (dislikedMembers[a] != null) {
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
                    } else {
                        for (Student student: allStudents) {
                            if (input.equalsIgnoreCase(student.getId())) {
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
        FileReadWrite.saveStudentDetails(Main.studentsFileName, Student.allStudents);
    }

    /**
     * Method to enter preferred Projects by student
     *
     * @throws IOException if an I/O error occurs
     */
    public void enterPreferredProjects() throws IOException, NullPointerException {
        System.out.println(" The List of available projects are ");
        for (Project a: Project.projectsNotAssigned) {
            System.out.println("ID:\t" + a.getProjectId() + "\t\tClient : " + a.getClient().getOrganisation() + "\t\t\tTitle : " + a.getProjectTitle() + "\t");
        }

        String input, input2;
        System.out.println(" You are allowed to enter 4 projects you would like to work for:");
        for (int i = 0; i < 4; i++) {
            boolean projectExists = false;
            do {
                System.out.print("\n Please enter the ID of your preferred project number " + (i + 1) + ":");
                input = Global.scan.nextLine();
                boolean preferredProjExists = false;
                for (int k = 0; k < preferredProjects.length; k++) {
                    if (preferredProjects[k] != null)
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
                            System.out.println("Popularity set");
                        }
                    }
                    if (!projectExists) {
                        System.out.println("\n No Project found. Please enter a Project Exists Project's ID");
                    }
                } else {
                    System.out.println("You have already registered this Project as a Preference!!");
                }
            } while (!projectExists);
        }
//        System.out.println(preferredProjects);
        this.setPreferredProjects(preferredProjects);
        ArrayList < Role > allRolesInPreferredProjects = new ArrayList < > ();
        System.out.println("                    ****Your Preferred Projects and the Roles that are available!****\n\n" +
                "                    The Details are given below :\n");
        for (Project project: preferredProjects) {
            System.out.println(
                    "\n                    Project ID is " + project.getProjectId() + "\n" +
                            "                    Title \t\t\t: " + project.getProjectTitle() + "\n");

            for (Role role: project.getRolesInProject()) {
                System.out.print("                    Role name : " + role.getRoleName() + "\t\t\t Frameworks : ");
                for (String framework: role.getFrameworks()) {
                    System.out.print(framework + " ");
                }
                allRolesInPreferredProjects.add(role);
                System.out.print("\n");
            }
        }
        System.out.println(" You are allowed to enter 2 roles you would like to work as:");
        for (int i = 0; i < 2; i++) {
            boolean roleExists = false;
            do {
                System.out.print("\n Please enter the ID of Project that your preferred role " + (i + 1) + " belongs to : ");
                input2 = Global.scan.nextLine();
                boolean projectFound = false;
                for (Project project: preferredProjects) {
                    if (project.getProjectId().equalsIgnoreCase(input2))
                        projectFound = true;
                }
                if (projectFound) {
                    System.out.print("\n Please enter the name your preferred role number " + (i + 1) + ":");
                    input = Global.scan.nextLine();
                    boolean preferredRoleExists = false;
                    for (int k = 0; k < preferredRoles.length; k++) {
                        if (preferredRoles[k] != null)
                            if (input.equals(preferredRoles[k].getRoleName()) && input2.equalsIgnoreCase(preferredRoles[k].getId())) {
                                preferredRoleExists = true;
                            }
                    }
                    if (!preferredRoleExists) {
                        for (int j = 0; j < allRolesInPreferredProjects.size(); j++) {
                            if (input.equals(allRolesInPreferredProjects.get(j).getRoleName()) && input2.equalsIgnoreCase(allRolesInPreferredProjects.get(j).getId())) {
                                roleExists = true;
                                preferredRoles[i] = allRolesInPreferredProjects.get(j);
                            }
                        }
                        if (!roleExists) {
                            System.out.println("\n No Such Role found. Please enter a Role from the List");
                        }
                    } else {
                        System.out.println("You have already registered this Role as a Preference!!");
                    }
                } else System.out.println("\n\n You have not entered this project as a preference!!\n\n");
            } while (!roleExists);
        }
    }


    /**
     * Method to enter preferred roles for a project by student.
     *
     * @param project
     */
    public void enterPreferredRoles(Project project) {
        String input;
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