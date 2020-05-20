import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    public static ArrayList<User> allUserDetails = new ArrayList<User>();
    // private String confirmPassword;
    Scanner scan = Global.scan;
    private String id;
    private String firstName;
    private String lastName;
    private String organisation;
    private String emailID;
    private String userName;
    private String password;
    private String studentID = "ST000";
    private String clientID = "CL000";
    private String managerID = "PM000";


    public User() {
    }

    public User(String id, String firstName, String lastName, String emailID, String userName, String password,
                String organisation) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailID = emailID;
        this.userName = userName;
        this.password = password;
        this.organisation = organisation;

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

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void mainMenu() throws IOException, ClassNotFoundException {
        int choice = 0;
        int i = 0;
        do {
            try {
                System.out.println(i++);
                System.out.println("**** Main Menu ****\n" +
                        "1.Sign Up\n" +
                        "2.Login\n" +
                        "3.Exit");
                System.out.println("Enter your choice : ");
                choice = Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Enter an integer (1-3)");
            }
        } while (choice < 1 || choice > 4);

        switch (choice) {
            case 1:
                try {
                    signup();
                } catch (IncorrectInputException e) {
                    e.getMessage();
                }
                break;

            case 2:
                try {
                    login();
                } catch (IncorrectInputException e) {
                    e.getMessage();
                }
                break;

            case 3:
                System.out.println("System exited! Thanks for using Project Team Formation System");
                System.exit(0);
                break;
        }

    }

    public void signup() throws IncorrectInputException, IOException, ClassNotFoundException {

        ProjectManager pm = new ProjectManager();
        //If Project Manager disables signUp, no new sign ups are allowed
        if (pm.getSignUpStatus() == false) {
            System.out.println("Sign Up is disable by Project Manager." +
                    "Please Contact Project Manager.");
            return;
        }


        boolean foundFirstName = false, foundLastName = false, foundOrg = false;

        int choice = 0;

        System.out.print("***********Sign Up***********\n");

        // First/ Last name field should not contain special characters.
        do {
            try {
                System.out.println("First Name: ");
                firstName = scan.nextLine();

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
                System.out.println("Last Name: ");
                lastName = scan.nextLine();
                foundLastName = inputValidations(lastName);

                if (foundLastName) {
                    throw new IncorrectInputException("Last name cannot contain special characters! Try again.");
                }

            } catch (IncorrectInputException ex) {
                System.err.println(ex.getMessage());
            }
        } while (foundLastName == true || lastName.isEmpty());

        do {
            // try {
            System.out.println("Organisation: ");
            organisation = scan.nextLine();

            //foundOrg = inputValidations(organisation);

            //if (foundOrg) {
            // throw new IncorrectInputException("Organisation cannot contain special characters! Try again.");
            //}
            // } catch (IncorrectInputException ex) {
            //   System.err.println(ex.getMessage());
            //}
        } while (foundOrg == true || organisation.isEmpty());


        // regex expression to validate email ID
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        do {
            System.out.println("Email id: (example@xyz.com)");
            emailID = scan.nextLine();
        } while (!(emailID.matches(regex)));

        for (User u : allUserDetails) {
            if (u.getEmailID().compareTo(emailID) == 0) {
                System.err.println("Username already present. Use a different email id!");
            }
        }

        // Whether the given email id has been used earlier.
        do {
            System.out.println("\nUsername: ");
            userName = scan.nextLine();

            System.out.println("\nPassword: ");
            password = scan.nextLine();
        } while (userName.isEmpty() || password.isEmpty());

        // ID generation
        do {
            try {
                System.out.println("\nAre you a: \n1.Student\n2.Client Representative\n3.Project Manager\n");
                choice = Integer.parseInt(scan.next());
            } catch (NumberFormatException e) {
                System.err.println("Enter an integer (1-3)");
            }
        } while (choice < 1 || choice > 4);

        switch (choice) {
            case 1:

                char gender;
                float gpa = 0;
                float experience = 0;
                do {
                    System.out.println("\nEnter your gender: F/M");
                    gender = scan.next().toUpperCase().charAt(0);
                   /* if (gender != 'F' || gender != 'M') {
                        continue;
                    }*/
                    System.out.println("gender" + gender);
                } while ((gender != 'F' && gender != 'M'));

                float input = 0;
                do {
                    try {
                        System.out.println("\nEnter your GPA:");
                        gpa = Float.parseFloat(scan.next());
                        input = gpa;
                    } catch (NumberFormatException e) {
                        System.err.println("Please enter a number (0 - 4)");
                    }
                } while (input < 0 || input > 5);

                input = 0;
                do {
                    try {
                        System.out.println("\nEnter your experience:");
                        experience = Float.parseFloat(scan.next());
                        input = experience;
                    } catch (NumberFormatException e) {
                        System.err.println("Please enter a number");
                    }
                } while (input < 0 || input > 60);


                studentID = "ST" + String.format("%03d",
                        (Integer.parseInt(getStudentID().substring(2)) + 1));

                setStudentID(studentID);

                allUserDetails.add(new Student(studentID, firstName, lastName, emailID, userName, password, organisation, gpa, experience,
                        gender, '0'));

                Student.allStudents.add(new Student(studentID, firstName, lastName, emailID, userName, password, organisation, gpa,
                        experience, gender, '0'));
                FileReadWrite.saveUserDetails(Main.userFileName,allUserDetails);
                FileReadWrite.saveStudentDetails(Main.studentsFileName,Student.allStudents);

                System.out.println("You have successfully signed up with ID: " + studentID + "!\n");

                break;

            case 2:

                clientID = "CL"
                        + String.format("%03d", (Integer.parseInt(getClientID().substring(2)) + 1));

                setClientID(clientID);

                allUserDetails.add(
                        new ClientRepresentative(clientID, firstName, lastName, emailID, userName, password, organisation));
                FileReadWrite.saveUserDetails(Main.userFileName,allUserDetails);
                System.out.println("You have successfully signed up with ID: " + clientID + "!\n");
                break;

            case 3:

                managerID = "PM" + String.format("%03d",
                        (Integer.parseInt(getManagerID().substring(2)) + 1));

                setManagerID(managerID);
                allUserDetails.add(new ProjectManager(managerID, firstName, lastName, emailID, userName, password, organisation));
                FileReadWrite.saveUserDetails(Main.userFileName,allUserDetails);
                System.out.println("You have successfully signed up with ID: " + managerID + "!\n");
                break;

            default:
                System.err.println("Invalid choice!");
                break;

        }
        setUserName(userName);
        setPassword(password);
        System.out.println("11111");
        mainMenu();
        System.out.println("22322");

    }

    public void login() throws IncorrectInputException, IOException, ClassNotFoundException {

        // Verify the login for both, when the field is blank and the Submit button is
        // clicked.

        String loginName, pass;
        boolean foundUsername = false, foundPassword = false;
        do {

            System.out.println("\n***********Login***********\nEnter username: ");
            loginName = scan.nextLine();

            for (User user : allUserDetails) {
                if (user instanceof ClientRepresentative) {

                    if (loginName.compareTo(user.getUserName()) == 0) {
                        foundUsername = true;
                        setUserName(loginName);

                    }

                } else if (user instanceof Student) {

                    if (loginName.compareTo(user.getUserName()) == 0) {

                        foundUsername = true;
                        System.out.println("Username found!" + foundUsername);
                        setUserName(loginName);

                    }

                } else {

                    if (loginName.compareTo(user.getUserName()) == 0) {
                        foundUsername = true;
                        setUserName(loginName);
                    }

                }

                if (foundUsername) {
                    // System.out.println("Login name:" + user.getUserName());
                    System.out.println("\nEnter Password: ");
                    pass = scan.nextLine();

                    if (pass.compareTo(user.getPassword()) == 0) {
                        foundPassword = true;
                        setPassword(pass);
                        // Verify whether the user has entered corresponding username/email id and
                        // password.

                        System.out.println("You have successfully logged in!");

                        if (user instanceof ClientRepresentative) {
                            ((ClientRepresentative) user).clientMenu();
                            break;
                        } else if (user instanceof Student) {
                            ((Student) user).studentMenu();
                            break;
                        } else {
                            ((ProjectManager) user).projectManagerMenu();
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
        mainMenu();
    }

    public boolean inputValidations(String input) {
        boolean result;
        Pattern p = Pattern.compile("[^[a-zA-Z\\s]+$]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(input);
        result = m.find();
        return result;
    }


}
