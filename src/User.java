import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User  implements Serializable {
    private static final long serialVersionUID =1L;
    public static ArrayList<User> allUserDetails = new ArrayList<User>();

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
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001B[0m";

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

    /**
     * Getter and setter methods.
     * @return
     */
    public String getEmailID() {
        return emailID;
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

    public String getId() {
        return id;
    }


    /**
     * The Main Menu
     *
     * First thing users see when they execute the program.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void mainMenu() throws IOException, ClassNotFoundException {
        int choice = 0;
        do {
            System.out.println(
                    ANSI_YELLOW + "\n**** Main Menu ****" + ANSI_RESET + "\n" +
                            "1.Sign Up\n" +
                            "2.Login\n" +
                            "3.Exit");
            choice = InputTools.intChecker(1, 3);

            switch (choice) {
                case 1:
                    try {
                        signUp();
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

                default:
                    System.err.println("Invalid choice!");
                    break;
            }
        } while (choice != 3);
    }


    /**
     * Method to Sign Up
     *
     * For new user, where they enter their required personal details and sign up into the program.
     * Method validates the details and save them for future use and assigns them an Unique ID.
     * @throws IncorrectInputException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void signUp() throws IncorrectInputException, IOException, ClassNotFoundException {

        ProjectManager pm = new ProjectManager();
        //If Project Manager disables signUp, no new sign ups are allowed
        if (!pm.getSignUpStatus()) {
            System.out.println("Sign Up is disable by Project Manager." +
                    "Please Contact Project Manager.");
            return;
        }

        boolean foundFirstName = false;
        boolean foundLastName = false;
        boolean foundOrg = false;
        int choice = 0;

        System.out.print("****Sign Up****\n");
        do {
            try {
                // First & Last name field should not contain special characters.
                System.out.println("First Name: ");
                firstName = Global.scan.nextLine();

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
                lastName = Global.scan.nextLine();
                foundLastName = inputValidations(lastName);

                if (foundLastName) {
                    throw new IncorrectInputException("Last name cannot contain special characters! Try again.");
                }

            } catch (IncorrectInputException ex) {
                System.err.println(ex.getMessage());
            }
        } while (foundLastName == true || lastName.isEmpty());

        do {
            System.out.println("Organisation: ");
            organisation = Global.scan.nextLine();
        } while (foundOrg == true || organisation.isEmpty());


        // regex expression to validate email ID
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        do {
            System.out.println("Email id: (example@xyz.com)");
            emailID = Global.scan.nextLine();
        } while (!(emailID.matches(regex)));

        // Whether the given email id has been used earlier.
        for (User u: allUserDetails) {
            if ((u.getEmailID() != null) && u.getEmailID().compareTo(emailID) == 0) {
                System.out.println("Email id already present. Use a different email id!");
                return;
            }
        }

        do {
            System.out.println("\nUsername: ");
            userName = Global.scan.nextLine();

            System.out.println("\nPassword: ");
            password = Global.scan.nextLine();
        } while (userName.isEmpty() || password.isEmpty());

        // ID generation
        System.out.println("\nAre you a: \n1.Student\n2.Client Representative\n3.Project Manager\n");
        choice = InputTools.intChecker(1, 3);

        switch (choice) {
            case 1: //For Students

                char gender;
                do {
                    System.out.println("Enter your gender: F/M");
                    gender = Global.scan.next().toUpperCase().charAt(0);
                } while ((gender != 'F' && gender != 'M'));

                System.out.println("GPA ");
                float gpa = InputTools.floatChecker(0, 4);

                System.out.println("Your Experience ");
                float experience = InputTools.floatChecker(0, 80);

                String newStudentID = studentID;
                if(allUserDetails.size() > 0) {
                    User lastStudent = null;
                    for(User u: allUserDetails) {
                        if(u instanceof Student) {
                            lastStudent = u;
                        }
                    }
                    newStudentID = lastStudent != null ? lastStudent.getStudentID() : newStudentID;
                    System.out.println(newStudentID);
                }
                newStudentID = "ST" + String.format("%03d",
                        (Integer.parseInt(newStudentID.substring(2)) + 1));

                allUserDetails.add(new Student(newStudentID, firstName, lastName, emailID, userName, password, organisation, gpa, experience,
                        gender, '0'));

                Student.allStudents.add(new Student(newStudentID, firstName, lastName, emailID, userName, password, organisation, gpa,
                        experience, gender, '0'));
                FileReadWrite.saveUserDetails(Main.userFileName, allUserDetails);
                FileReadWrite.saveStudentDetails(Main.studentsFileName, Student.allStudents);

                System.out.println("You have successfully signed up with ID: " + newStudentID + "!\n");
                break;

            case 2: //For Client Representative
                String newClientID = clientID;
                if (allUserDetails.size() > 0) {
                    User lastClient = null;
                    for (User u: allUserDetails) {
                        if (u instanceof ClientRepresentative) {
                            lastClient = u;
                        }
                    }
                    newClientID = lastClient != null ? lastClient.getClientID() : newClientID;
                }
                newClientID = "CL" + String.format("%03d", (Integer.parseInt(newClientID.substring(2)) + 1));

                allUserDetails.add(
                        new ClientRepresentative(newClientID, firstName, lastName, emailID, userName, password, organisation));
                FileReadWrite.saveUserDetails(Main.userFileName, allUserDetails);
                System.out.println("You have successfully signed up with ID: " + newClientID + "!\n");
                break;

            case 3: //For Project Manager
                String newManagerID = managerID;
                if(allUserDetails.size() > 0){
                    User lastManager = null;
                    for(User u: allUserDetails) {
                        if(u instanceof ProjectManager){
                            lastManager = u;
                        }
                    }
                    newManagerID = lastManager != null ? lastManager.getManagerID() : newManagerID;
                }
                newManagerID = "PM" + String.format("%03d", (Integer.parseInt(newManagerID.substring(2)) + 1));

                allUserDetails.add(new ProjectManager(newManagerID, firstName, lastName, emailID, userName, password, organisation));
                FileReadWrite.saveUserDetails(Main.userFileName, allUserDetails);
                System.out.println("You have successfully signed up with ID: " + newManagerID + "!\n");
                break;

            default:
                System.err.println("Invalid choice!");
                break;
        }
        setUserName(userName);
        setPassword(password);
        mainMenu();
    }


    /**
     * Method to Login for already signed up users.
     *
     * User can sign up using their username and password where they are further directed to their respective Menu.
     * @throws IncorrectInputException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void login() throws IncorrectInputException, IOException, ClassNotFoundException {

        // Verify the login for both, when the field is blank and the Submit button is clicked.

        String loginName;
        String pass;
        boolean foundUsername = false;
        boolean foundPassword = false;

        do {
            System.out.println("\n" + ANSI_YELLOW + "****Login****" + ANSI_RESET + "\n" +
                    "Enter username: ");
            loginName = Global.scan.nextLine();

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
                    System.out.println("\nEnter Password: ");
                    pass = Global.scan.nextLine();

                    if (pass.compareTo(user.getPassword()) == 0) {
                        foundPassword = true;
                        setPassword(pass);
                        // Verify whether the user has entered corresponding username/email id and password.

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

                        // TODO What messages pop up when an invalid login is carried out?
                    } else {
                        foundPassword = false;
                        System.err.println("Incorrect password!");
                        break;
                    }
                }
            }
            if (!foundUsername) {
                System.err.println("Username not signed up!");
            } else if (!foundUsername && !foundPassword) {
                throw new IncorrectInputException("Incorrect username or password!");
            }

        } while (!foundUsername || !foundPassword);
        mainMenu();
    }


    /**
     * Method to validate names to make sure it doesn't have any special characters.
     * @param input
     * @return results
     */
    public boolean inputValidations(String input) {
        boolean result;
        Pattern p = Pattern.compile("[^[a-zA-Z\\s]+$]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(input);
        result = m.find();
        return result;
    }
}
