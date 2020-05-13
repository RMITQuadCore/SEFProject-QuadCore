




import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private String id;
    protected String firstName;
    protected String lastName;
    protected String org;
    protected String emailID;
    protected String userName;
    protected String password;
    private String studentID = "ST000";
    protected String clientID = "CL000";
    private String managerID = "PM000";
    // private String confirmPassword;
    Scanner s = new Scanner(System.in);
    Student student;
    ClientRepresentative cr;
    ProjectManager pm;
    public ArrayList<User> details = new ArrayList<User>();

    public User() {
    }

    public User(String id, String firstName, String lastName, String emailID, String userName, String password,
                String org) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailID = emailID;
        this.userName = userName;
        this.password = password;
        this.org = org;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getManagerID() {
        return managerID;
    }

    public void setManagerID(String managerID) {
        this.managerID = managerID;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void start() {
        int choice = 0;
        do {
            try {
                System.out.println(
                        "Project Team Formation System Menu!\n1.Signup\n2.Login\n3.Logout\n4.Exit");
                choice = Integer.parseInt(s.next());
            } catch (NumberFormatException e) {
                System.err.println("enter an integer");
            }
        } while (choice < 1 || choice > 5);

        switch (choice) {
            case 1:
                try {
                    signup();
                } catch (IncorrectInputException e) {
                    // TODO Auto-generated catch block
                    e.getMessage();
                }
                break;
            case 2:
                try {
                    login();
                } catch (IncorrectInputException e) {
                    // TODO Auto-generated catch block
                    e.getMessage();
                }
                break;
            case 3:
                start();
                break;
            case 4:
                System.out.println("System exited! Thanks for using Project Team Formation System");
                System.exit(0);
                break;
          /*  case 5:
                // System.out.println("Discard projects selected!");
                Project p = new Project();

                try {
                    p.discardUnpopularProjects();
                } catch (ProjectMismatchException e) {
                    // TODO Auto-generated catch block
                    e.getMessage();
                }
                break;*/
        }

    }

    public void signup() throws IncorrectInputException {
        boolean foundFirstName = false, foundLastName = false, foundOrg = false;

        int ch = 0;

        System.out.print("***********Signup***********\n");

        do {
            try {
                System.out.println("First Name: ");
                firstName = s.next();
                firstName += s.nextLine();

                foundFirstName = inputValidations(firstName);

                if (foundFirstName) {
                    throw new IncorrectInputException("First name cannot contain special characters! Try again.");
                }
            } catch (IncorrectInputException ex) {
                System.err.println(ex.getMessage());
            }
        } while (foundFirstName == true || firstName.isEmpty());

        do {
            try {
                System.out.println("\nLast Name: ");
                lastName = s.next();
                lastName += s.nextLine();
                foundLastName = inputValidations(lastName);

                if (foundLastName) {
                    throw new IncorrectInputException("Last name cannot contain special characters! Try again.");
                }

            } catch (IncorrectInputException ex) {
                System.err.println(ex.getMessage());
            }
        } while (foundLastName == true || lastName.isEmpty());

        do {
            try {
                System.out.println("\nOrganisation: ");
                org = s.next();
                org += s.nextLine();

                foundOrg = inputValidations(org);

                if (foundOrg) {
                    throw new IncorrectInputException("Organisation cannot contain special characters! Try again.");
                }
            } catch (IncorrectInputException ex) {
                System.err.println(ex.getMessage());
            }
        } while (foundOrg == true || org.isEmpty());

        // First/ Last name field should not contain special characters.

        System.out.println("\nEmail id: ");
        emailID = s.next();
        emailID += s.nextLine();

        for (User u : details) {
            if (u.getEmailID().compareTo(emailID) == 0) {
                System.err.println("Signup already performed by this email id. Use a different email id!");
            }
        }

        // Whether the given email id has been used earlier.
        do {
            System.out.println("\nUsername: ");
            userName = s.next();
            userName += s.nextLine();

            System.out.println("\nPassword: ");
            password = s.next();
            password += s.nextLine();
        } while (userName.isEmpty() || password.isEmpty());

        // ID generation
        do {
            try {
                System.out.println("\nAre you a: \n1.Student\n2.Client Representative\n3.Project Manager\n");
                ch = Integer.parseInt(s.next());
            } catch (NumberFormatException e) {
                System.err.println("enter an integer");
            }
        } while (ch < 1 || ch > 4);

        switch (ch) {
            case 1:

                char gender;
                Float gpa, experience;
                System.out.println("\nEnter your gender: F/M");
                gender = s.next().charAt(0);

                System.out.println("\nEnter your GPA:");
                gpa = s.nextFloat();

                System.out.println("\nEnter your experience:");
                experience = s.nextFloat();

                studentID = "ST" + String.format("%03d",
                        (Integer.parseInt(getStudentID().substring(2, getStudentID().length())) + 1));

                setStudentID(studentID);

                details.add(new Student(studentID, firstName, lastName, emailID, userName, password, org, gpa, experience,
                        gender, '0'));

                Student.allStudents.add(new Student(studentID, firstName, lastName, emailID, userName, password, org, gpa,
                        experience, gender, '0'));

                System.out.println("You have successfully signed up with ID: " + studentID + "!\n");

                break;

            case 2:

                clientID = "CL"
                        + String.format("%03d", (Integer.parseInt(getClientID().substring(2, getClientID().length())) + 1));

                setClientID(clientID);

                details.add(
                        new ClientRepresentative(clientID, firstName, lastName, emailID, userName, password, org));
                System.out.println("You have successfully signed up with ID: " + clientID + "!\n");

                break;

            case 3:

                managerID = "PM" + String.format("%03d",
                        (Integer.parseInt(getManagerID().substring(2, getManagerID().length())) + 1));

                setManagerID(managerID);
                details.add(new ProjectManager(managerID, firstName, lastName, emailID, userName, password, org,null));
                System.out.println("You have successfully signed up with ID: " + managerID + "!\n");
                break;

            default:
                System.err.println("Invalid choice!");
                break;

        }
        setUserName(userName);
        setPassword(password);
        start();

    }

    public void login() throws IncorrectInputException {

        // Verify the login for both, when the field is blank and the Submit button is
        // clicked.

        String loginName, pass;
        boolean foundUsername = false, foundPassword = false;
        do {

            System.out.println("\n***********Login***********\nEnter username: ");
            loginName = s.next();
            loginName += s.nextLine();

            for (User a : details) {

                if (a instanceof ClientRepresentative) {

                    if (loginName.compareTo(((ClientRepresentative) a).getUserName()) == 0) {
                        foundUsername = true;
                        setUserName(loginName);

                    }

                } else if (a instanceof Student) {

                    if (loginName.compareTo(((Student) a).getUserName()) == 0) {

                        foundUsername = true;
                        System.out.println("Username found!" + foundUsername);
                        setUserName(loginName);

                    }

                } else {

                    if (loginName.compareTo(((ProjectManager) a).getUserName()) == 0) {
                        foundUsername = true;
                        setUserName(loginName);
                    }

                }

                if (foundUsername) {
                    // System.out.println("Login name:" + a.getUserName());
                    System.out.println("\nEnter Password: ");
                    pass = s.next();
                    pass += s.nextLine();

                    if (pass.compareTo(a.getPassword()) == 0) {
                        foundPassword = true;
                        setPassword(pass);
                        // Verify whether the user has entered corresponding username/email id and
                        // password.

                        System.out.println("You have successfully logged in!");

                        if (a instanceof ClientRepresentative) {
                            ((ClientRepresentative) a).start();
                            break;
                        } else if (a instanceof Student) {
                            ((Student) a).start();
                            break;
                        } else {
                            ((ProjectManager) a).pmMenu();
                            break;
                        }

                        // What messages pop up when an invalid login is carried out?
                    } else {
                        foundPassword = false;
                        System.err.println("Incorrect password!");
                        break;
                    }
                }
            }
            if (foundUsername == false) {
                System.err.println("Username not signed up!");
            } else if (foundUsername == false && foundPassword == false) {
                throw new IncorrectInputException("Incorrect username or password!");
            }

        } while (foundUsername == false || foundPassword == false);
        start();
    }

    public boolean inputValidations(String input) {
        boolean result;
        Pattern p = Pattern.compile("[^[a-zA-Z]+$]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(input);
        result = m.find();
        return result;
    }
//	public static void main(String args[]) {
//		User u = new User();
//		u.start();
//	}

}
