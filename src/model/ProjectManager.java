package model;

import controller.SwapTeamMembersController;
import main.Main;
import main.SwapTeamGUI;
import util.FileReadWrite;
import util.Global;
import util.InputTools;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;


public class ProjectManager extends User implements Serializable {
    private static final long serialVersionUID = 2437934725196379683L;

    public static ArrayList < Student > studentsNotInATeam = new ArrayList < Student > ();
    public static ArrayList < Constraint > constraints = new ArrayList < > ();
    private static boolean projectsDiscarded = false;
    private boolean signUpStatus = true;

    public ProjectManager() {}

    public ProjectManager(String id, String firstName, String lastName, String emailID, String userName,
                          String password, String org) {
        super(id, firstName, lastName, emailID, userName, password, org);

    }

    public static ArrayList < Constraint > getConstraints() {
        return constraints;
    }

    public static void setProjectsDiscarded(boolean projectsDiscarded) {
        ProjectManager.projectsDiscarded = projectsDiscarded;
    }

    public static void setConstraints(ArrayList < Constraint > constraints) {
        ProjectManager.constraints = constraints;
    }

    public static boolean isProjectsDiscarded() {
        return projectsDiscarded;
    }

    public ArrayList < Student > getTempStudent() {
        return studentsNotInATeam;
    }

    public void setTempStudent(ArrayList < Student > tempStudent) {
        ProjectManager.studentsNotInATeam = tempStudent;
//        System.out.println();
    }

    public boolean getSignUpStatus() {
        return signUpStatus;
    }

    /**
     * Project Manger Menu
     *
     * Displays Project Manager, it's menu and further navigate to required functionality.
     * @throws IOException if an I/O error occurs
     * @throws ClassNotFoundException if the class of a serialized object
     */
    public void projectManagerMenu() throws IOException, ClassNotFoundException {
        int choice;
        do {
            System.out.println(Constraint.ANSI_BLUE + "\n*** Project Manager Menu ***\n" + Constraint.ANSI_RESET +
                    "1. Set all Constraints & weightage\n" +
                    "2. Display Current Constraint\n" +
                    "3. Enter Personality of students\n" +
                    "4. Change Sign up status\n" +
                    "5. Discard Unpopular projects\n" +
                    "6. Display all projects\n" +
                    "7. Run Project Team formation\n" +
                    "8. Display Teams and their fitness\n" +
                    "9. Swap team members\n" +
                    "10. Logout");
            choice = InputTools.intChecker(1, 10);

            Constraint constraint = new Constraint();

            switch (choice) {
                case 1:
                    constraint.setAllConstraints();
                    break;

                case 2:
                    constraint.displayConstraints();
                    break;

                case 3:
                    enterStudentPersonalities();
                    break;

                case 4:
                    setSignUpStatus();
                    break;

                case 5:
                    try {
                        Project.discardUnpopularProjects();
                    } catch (ProjectMismatchException e) {
                        e.getMessage();
                    }
                    break;

                case 6:
                    displayAllProjects();//TODO write function
                    break;

                case 7:
                    if (projectsDiscarded == true) {
                        if (Constraint.allSoftConstraints.isEmpty() == false) {
                            createTeams();
                        } else {
                            System.out.println("\n\n You need to set weightage of constraints first.");
                        }
                    }
                    else
                        System.out.println("\n\n You need to discard unpopular projects before creating teams.");
                    break;

                case 8:
                    displayTeams();
                    break;

                case 9:
                    swapTeamMembers();
                    break;

                case 10:
                    mainMenu();
                    break;
            }
        } while (choice != 10);
    }

    /**
     * Method to display all projects
     */
    private void displayAllProjects() {
        for(Project projects: Project.totalProjects){
            projects.displayProject();
            System.out.println("Popularity: " + projects.getPopularityCounter());
        }
    }
    /**
     * Method to assign personality to all students
     *
     * <p>
     *     Project Manger assign each student with a personality based
     *     on his/staff's observations and class test.
     * </p>
     * @throws IOException if an I/O error occurs
     */
    private void enterStudentPersonalities() throws IOException {
        boolean foundStudent = false, validPersonality = false;
        String studID;
        char studPersonality = 0;

        do {
            System.out.println("\nEnter assigned student ID:");
            studID = Global.scan.nextLine();

            for (Student stud: Student.allStudents) {
                if (stud.getId().compareTo(studID) == 0) {
                    foundStudent = true;
                    System.out.println("\nPlease enter personality of " + studID + " :");
                    studPersonality = Global.scan.nextLine().toUpperCase().charAt(0);
                    char[] validPersonalities = {
                            'A',
                            'B',
                            'C',
                            'D',
                            'E',
                            'F'
                    };
                    for (int i = 0; i < 6; i++) {
                        if (studPersonality == validPersonalities[i]) {
                            validPersonality = true;
                            stud.setStudentPersonality(studPersonality);
                            System.out.println(stud.getStudentPersonality());
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
        FileReadWrite.saveStudentDetails(Main.studentsFileName, Student.allStudents);
    }

    /**
     * Project Manger can unable or disable Sign Up
     * Usually sign up is disabled before discarding unpopular projects
     *
     */
    public void setSignUpStatus() {
        // true = open && false = closed

        int choice = 0;
        signUpStatus = true;
        System.out.println("Set Sign Up Status" + "\n1. Open" + "\n2.Closed");
        choice = InputTools.intChecker(1, 2);

        switch (choice) {
            case 1:
                signUpStatus = true;
                break;
            case 2:
                signUpStatus = false;
                break;
        }
    }

    /**
     * Method to form student team
     *
     * <p>
     *     Keeping all constraints into account student team will be formed.
     *     First it gets the team size then applies Female hard constraint on the team,
     *     if constraint is not met then team members are swapped from students not
     *     assigned a team yet, until it meets or throws an error message if cannot meet.
     *     Similarly, in next disliked student is checked, then soft constraints are applied
     *     in ascending order of there weightage. Then both of the GPA hard constraint are applied
     *     in similar fashion. Finally team is created if all hard constraints are maintained.
     *     The team formed will be displayed and stored in team arraylist and file. A unique team ID
     *     will be assigned to team and project will be assigned too.
     *
     */
    public void createTeams() //TODO if consraints and weightage are not set methods shouldn't run.
    {
        ArrayList<Student> teamCreator = new ArrayList<Student>();
        Project.projectsNotAssigned=Project.totalProjects;
        Integer[] teamSize = getTeamSizeArray();// Calculate all the sizes
        for (int i =0 ; i < teamSize.length ; i ++)
        {
            teamCreator = femaleHardConstraintApplicator(teamCreator, teamSize[i]);//Initial 4/ 3/ 2 students added to the ArrayList
            if(!Constraint.femaleHardConstraintCheck(teamCreator)) // Hard Constraint Check
            {
                System.out.println("Sorry! A team cannot be formed currently as a hard constraint is not met.\n" +
                        "Hard Constraint : Maximum of one woman per team cannot be met currently");
                break;
            }
            teamCreator = dislikedMembersRemover(teamCreator);
            for (int x = 0; x < Constraint.allSoftConstraints.size(); x++) // bubble sort outer loop
            {
                for (int y = 0; y < Constraint.allSoftConstraints.size() - x - 1; y++) {
                    if (Constraint.allSoftConstraints.get(y).getWeightAge() > (Constraint.allSoftConstraints.get(y + 1).getWeightAge())) {
                        Constraint temp = Constraint.allSoftConstraints.get(y);
                        Constraint.allSoftConstraints.set(y, Constraint.allSoftConstraints.get(y + 1));
                        Constraint.allSoftConstraints.set(y + 1, temp);
                    }
                }
            }

            for(Constraint c: Constraint.allSoftConstraints){
                switch (Integer.parseInt(c.getConstraintId().substring(11,12))) {
                    case 1:
                        teamCreator = uniquePersonalityConstraintApplicator(teamCreator);
                        break;
                    case 2:
                        teamCreator = personalityAorBPresentApplicator(teamCreator);
                        break;
                    case 3:
                        teamCreator = experienceSoftConstraintApplicator(teamCreator);
                        break;
                    default:
                        break;
                }
            }

            teamCreator = teamAverageGPAConstraintApplicator(teamCreator);
            if(!Constraint.averageGPAHardConstraintCheck(teamCreator))
            {
                System.out.println("Sorry! A team cannot be formed currently as a hard constraint is not met.\n" +
                        "Hard Constraint : Average team GPA should be less than 3.5.");
                break;
            }
            teamCreator = teamMemberGPAConstraintApplicator(teamCreator);
            if(!Constraint.twoMembersWith3GPAHardConstraintCheck(teamCreator) && !Constraint.averageGPAHardConstraintCheck(teamCreator))
            {
                System.out.println("Sorry! A team cannot be formed currently as a hard constraint is not met.\n" +
                        "Hard Constraint : Two Members needed with GPA 3 or Above.");
                break;
            }
            Team team = setProjectForTeam(teamCreator);
            System.out.println("\nTeam Created");
            System.out.println("\nCongratulations! A team has been formed!!.\n" +
                    "The team ID is : \t\t\t" + team.getTeamID() +
                    "\nThe Project Assigned to this team is \t: " + team.getProjectAssigned().getProjectTitle() +
                    "\n");
            System.out.print("The Students IDs of students in this team are: \n\n");
            for (Student student : teamCreator)
            {
                System.out.print(student.getId() + "  Name: " + student.getFirstName() + "\t  Gender : " + student.getGender() + "\n");
            }
            Team.allTeams.add(team);
            for (Student student : teamCreator)
            {
                for(Student student1 : Student.allStudents){
                    if(student.getId().compareTo(student1.getId()) ==0){
                        student.setAssignedTeam(team);
                        student1.setAssignedTeam(team);
                    }
                }
            }
            teamCreator.clear();
            team = null;

            int choice = 0;
            do {
                try {
                    System.out.println("Do you want to attempt to create another team ?\n" +
                            "1.Yes\n" +
                            "2.No \n");
                    System.out.print("Enter your choice : ");
                    choice = Integer.parseInt(Global.scan.next() + Global.scan.nextLine());
                } catch (NumberFormatException e) {
                    System.err.println("Enter an integer (1-2)");
                }
            } while (choice < 1 || choice > 3);
            if (choice == 2)
            {
                break;
            }
        }
        System.out.println("Exited");
    }

    /**
     * Depending upon total number of students team size is decided
     * and if last few student are lesser than required size, smaller team size is decided for them.
     *
     * @return number of student in a team
     */
    public Integer[] getTeamSizeArray() {
        Integer numOfStudentsInATeam[];
        int numOfStudents = ProjectManager.studentsNotInATeam.size();
        int numOfTempTeams = numOfStudents / 4;
        int numOfExtraStudents = numOfStudents % 4; // extra students
        int i;
        if (numOfExtraStudents == 0) {
            numOfStudentsInATeam = new Integer[numOfTempTeams];
        } else {
            numOfStudentsInATeam = new Integer[numOfTempTeams + 1];
        }

        if (numOfExtraStudents == 3) {

            for (i = 0; i < numOfTempTeams; i++) {
                numOfStudentsInATeam[i] = 4;
            }
            numOfStudentsInATeam[i] = 3;
        } else if (numOfExtraStudents == 2) {

            for (i = 0; i < numOfTempTeams; i++) {
                numOfStudentsInATeam[i] = 4;
            }
            numOfStudentsInATeam[i] = 2;
        } else if (numOfExtraStudents == 1) {

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
        return numOfStudentsInATeam;
    }

    /**
     * Female Hard Constraint is applied
     *
     * <p>
     * Female hard constraint is applied on a group of student who can potentially form a team
     * student are passed from create team method and it is checked if team contains no more
     * than one female student in a team, if it satisfied, team is passed otherwise student
     * is swapped from student not in a team arraylist until it mets the constraint or if
     * it can't be applied.
     *
     * @param teamCreator arraylist containing details of students who can form a team.
     * @param teamSize size of team being formed
     * @return students satisfied female hard constraint and can form a team
     */
    public static ArrayList < Student > femaleHardConstraintApplicator(ArrayList < Student > teamCreator, Integer teamSize) {
        int femaleCount = 0;
        //Checking total number of females so that everytime a female is added in a team the counter is decremented
        for (Student t: studentsNotInATeam) {
            if (t.getGender() == 'f' || t.getGender() == 'F') {
                femaleCount++;
            }
        }
        int k = 0;
        boolean femaleFound = false;
        if (femaleCount != studentsNotInATeam.size()) {
            //for equating required number of members in a team with values in teamsize array
            while (k < teamSize) {
            //for adding the first member which should be a female in every team
                for (int z = 0; z < studentsNotInATeam.size(); z++) {
                    //if a female is found, she is added to teamCreator arraylist and removed from studentsNotInATeam arraylist
                    if (studentsNotInATeam.get(z).getGender() == 'f' || studentsNotInATeam.get(z).getGender() == 'F' && !femaleFound) {
                        teamCreator.add(studentsNotInATeam.get(z));
                        femaleFound = true;
                        femaleCount--;
                        studentsNotInATeam.remove(studentsNotInATeam.get(z));
                        k++;
                        break;
                    }
                    //to add remaining members in a team who are male, eventually removing them from studentsNotInATeam arraylist
                    if ((studentsNotInATeam.get(z).getGender() == 'm' || studentsNotInATeam.get(z).getGender() == 'M') && femaleFound == true) {
                        teamCreator.add(studentsNotInATeam.get(z));
                        studentsNotInATeam.remove(studentsNotInATeam.get(z));
                        k++;
                        break;

                    }
                    // when there are no females found/left
                    else {
                        if (femaleCount == 0) {
                            teamCreator.add(studentsNotInATeam.get(z));
                            studentsNotInATeam.remove(studentsNotInATeam.get(z));
                            k++; //executed when there are no female
                            break;
                        }
                    }

                }
                //in case hwen there are females still remaining in studentsNotInATeam
                if (femaleCount == studentsNotInATeam.size() && k != teamSize) {
                    for (Student s: studentsNotInATeam) {
                        teamCreator.add(s);
                    }
                    break;
                }
            }
        } else {
            for (Student s: studentsNotInATeam) {
                teamCreator.add(s);
            }
        }
        return teamCreator;
    }

    /**
     * Removing disliked member if it exists in the team for all student in the team
     *
     * @param teamCreator arraylist containing details of students who can form a team.
     * @return students whom can form a team
     */
    public static ArrayList < Student > dislikedMembersRemover(ArrayList < Student > teamCreator) {
        for (int i = 0; i < teamCreator.size(); i++) {
            Student[] dislikedMembers = teamCreator.get(i).getDislikedMembers();
            List < Student > dislikedMembersList = Arrays.asList(dislikedMembers);
            for (int j = 0; j < dislikedMembers.length; j++) {
                boolean partOfTeam = false;
                if (teamCreator.contains(dislikedMembers[j])) {
                    partOfTeam = true;
                }
                if (partOfTeam && (dislikedMembers[j].getGender() == 'f' || dislikedMembers[j].getGender() == 'F')) {
                    for (Student student: studentsNotInATeam) {
                        if ((!dislikedMembersList.contains(student)) && (student.getGender() == 'f' || student.getGender() == 'F')) {
                            teamCreator.remove(dislikedMembers[j]);
                            teamCreator.add(student);
                            studentsNotInATeam.remove(student);
                            studentsNotInATeam.add(dislikedMembers[j]);
                            break;
                        }
                    }
                }
                if (partOfTeam && (dislikedMembers[j].getGender() == 'm' || dislikedMembers[j].getGender() == 'M')) {
                    for (Student student: studentsNotInATeam) {
                        if ((!dislikedMembersList.contains(student)) && (student.getGender() == 'm' || student.getGender() == 'M')) {
                            teamCreator.remove(dislikedMembers[j]);
                            teamCreator.add(student);
                            studentsNotInATeam.remove(student);
                            studentsNotInATeam.add(dislikedMembers[j]);
                            break;
                        }
                    }
                }
            }
        }
        return teamCreator;
    }

    /**
     * Applying Unique personality in a team soft constraint
     *
     * <p>Checking if any personality is more than one time on the team. If yes,
     * member will get swapped with student who is not in a team yet. If it satisfied,
     * it's weightage will be considered otherwise not.
     *
     * @param teamCreator arraylist containing details of students who can form a team.
     * @return students whom can form a team
     */
    public static ArrayList < Student > uniquePersonalityConstraintApplicator(ArrayList < Student > teamCreator) {
        //To check if duplicate personalities are present and removing team. Creating a unique Personality Team
        for (int j = 0; j < teamCreator.size(); j++) {
            for (int k = j + 1; k < teamCreator.size(); k++) {
                if (teamCreator.get(j).getStudentPersonality() == teamCreator.get(k).getStudentPersonality()) {
                    if (teamCreator.get(k).getGender() == 'm' || teamCreator.get(k).getGender() == 'M') {
                        for (Student student: studentsNotInATeam) {
                            if ((student.getStudentPersonality() != teamCreator.get(j).getStudentPersonality()) && (student.getGender() == 'm' || student.getGender() == 'M')) {
                                teamCreator.add(student);
                                studentsNotInATeam.add(teamCreator.get(k));
                                teamCreator.remove(teamCreator.get(k));
                                studentsNotInATeam.remove(student);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return teamCreator;
    }

    /**
     * Applying Required personality in a team soft constraint
     *
     * <p>Checking if the required personality exists on the team or not. If No,
     * member will get swapped with student who is not in a team yet. If it satisfied,
     * it's weightage will be considered otherwise not.
     *
     * @param teamCreator arraylist containing details of students who can form a team.
     * @return students whom can form a team
     */
    public ArrayList < Student > personalityAorBPresentApplicator(ArrayList < Student > teamCreator) {
        boolean persoAOrBPresent = false;
        for (Student student: teamCreator) {
            if (student.getStudentPersonality() == 'A' || student.getStudentPersonality() == 'B' || student.getStudentPersonality() == 'a' || student.getStudentPersonality() == 'b') {
                //if A or B personality is present in team
                persoAOrBPresent = true;
            }
        }
        if (!persoAOrBPresent) {
            boolean replacementFound = false;
            Student replacement = new Student();
            for (Student student: studentsNotInATeam) {
                if (student.getStudentPersonality() == 'A' || student.getStudentPersonality() == 'B' || student.getStudentPersonality() == 'a' || student.getStudentPersonality() == 'b') {
                    if (student.getGender() == 'm' || student.getGender() == 'M') {
                        replacementFound = true;
                        replacement = student; //Replacement found
                        break;
                    }
                }
            }
            if (replacementFound) {
                for (Student student: teamCreator) {
                    if (student.getGender() == 'm' || student.getGender() == 'M') {
                        teamCreator.remove(student);
                        teamCreator.add(replacement);
                        studentsNotInATeam.remove(replacement);
                        studentsNotInATeam.add(replacement);
                        break;
                    }
                }
            }
        }
        return teamCreator;
    }

    /**
     * Applying Experienced student in a team soft constraint
     *
     *<p>
     * Checking if the experienced student exists on the team or not. If No,
     * member will get swapped with student who is not in a team yet. If it satisfied
     * it's weightage will be considered otherwise not.
     *
     * @param teamCreator arraylist containing details of students who can form a team.
     * @return students whom can form a team
     */
    public static ArrayList < Student > experienceSoftConstraintApplicator(ArrayList < Student > teamCreator) {
        boolean experienceCriteria = false;
        for (Student student: teamCreator) {
            // to check if any student in the team formed has experience >= 5
            if (student.getExperience() >= 5) {
                experienceCriteria = true;
                break;
            }
        }
        //if no student with experience >= 5 is not found then, swapping is carried out such that only male students are considered for swapping in order to maintain the female constraint
        if (!experienceCriteria) {
            boolean replacementFound = false;
            Student replacement = new Student();
            for (Student student: studentsNotInATeam) {
                if ((student.getExperience() >= 5) && (student.getGender() == 'm' || student.getGender() == 'M')) {
                    replacementFound = true;
                    replacement = student; //Replacement found
                    break;
                }
            }
            if (replacementFound) {
                for (Student student: teamCreator) {
                    if (student.getGender() == 'm' || student.getGender() == 'M') {
                        teamCreator.remove(student);
                        teamCreator.add(replacement);
                        studentsNotInATeam.remove(replacement);
                        studentsNotInATeam.add(replacement);
                        break;
                    }
                }
            }
        }
        return teamCreator;
    }

    /**
     * Applying average GPA hard constraint
     * <p>
     * Checking if average of team is more than the specified GPA, if yes, team members will
     * be swapped with students who have not being assigned any team yet. If constraint doesn't meet
     * an error message will be displayed and team cannot form.
     *
     * @param teamCreator arraylist containing details of students who can form a team.
     * @return students whom can form a team
     */
    public static ArrayList < Student > teamAverageGPAConstraintApplicator(ArrayList < Student > teamCreator) {

        double sumOfGPA = 0;
        for (Student student: teamCreator) {
            sumOfGPA = sumOfGPA + student.getGpa();
        }
        if ((sumOfGPA / 4) > 3.5) //if hard constraint is violeted then we will first try to meet it by swapping students between teamCreator and studentsNotInATeam
        {
            for (int i = 0; i < studentsNotInATeam.size(); i++) {
                if ((studentsNotInATeam.get(i).getGender() == 'm' || studentsNotInATeam.get(i).getGender() == 'M') && studentsNotInATeam.get(i).getGpa() < 3.00) {
                    for (Student student: teamCreator) {
                        if ((student.getGender() == 'm' || student.getGender() == 'M') && student.getGpa() > 3.00) {
                            //swapping students
                            studentsNotInATeam.add(student);
                            teamCreator.remove(student);
                            teamCreator.add(studentsNotInATeam.get(i));
                            studentsNotInATeam.remove(studentsNotInATeam.get(i));
                            i--; //removed student from studentsNotInATeam so taking 'for' loop back by one
                            break;
                        }
                    }
                }
                //checking if hard constraint is met immediate after swapping
                double sumOfNewGPA = 0;
                for (Student student: teamCreator) {
                    sumOfNewGPA = sumOfNewGPA + student.getGpa();
                }
                if ((sumOfNewGPA / 4) <= 3.5) {
                    break; //if hard constraint is met
                }
            }
        }
        return teamCreator;
    }

    /**
     * Applying team member GPA hard constraint
     * <p>
     * Checking if required number of student have required GPA, if no, team members will
     * be swapped with students who have not being assigned any team yet. If constraint doesn't meet
     * an error message will be displayed and team cannot form.
     *
     * @param teamCreator arraylist containing details of students who can form a team.
     * @return students whom can form a team
     */
    public static ArrayList < Student > teamMemberGPAConstraintApplicator(ArrayList < Student > teamCreator) {
        int GPAGreaterThanThreeCounter = 0;
        for (int i = 0; i < teamCreator.size(); i++) {
            if (teamCreator.get(i).getGpa() >= 3.00) {
                GPAGreaterThanThreeCounter++;
            }
        }

        //considering sumOfGPA here as well so that changes made here should not impact teamAverageGPAConstraint
        double sumOfGPA = 0;
        for (Student student: teamCreator) {
            sumOfGPA = sumOfGPA + student.getGpa();
        }
        double averageGpaOfTeam = sumOfGPA / 4;

        if (GPAGreaterThanThreeCounter < 2) { //if hard constraint is violeted then we will first try to meet it by swapping students between teamCreator and studentsNotInATeam
            for (int i = 0; i < studentsNotInATeam.size(); i++) {
                if ((studentsNotInATeam.get(i).getGender() == 'm' || studentsNotInATeam.get(i).getGender() == 'M')&& studentsNotInATeam.get(i).getGpa() >= 3.00) {
                    for (Student student: teamCreator) {
                        if (((studentsNotInATeam.get(i).getGender() == 'm' || studentsNotInATeam.get(i).getGender() == 'M') && student.getGpa() < 3.00) && (((sumOfGPA + studentsNotInATeam.get(i).getGpa() - student.getGpa()) / 4) < 3.50)) {
                            //swapping students
                            studentsNotInATeam.add(student);
                            teamCreator.remove(student);
                            teamCreator.add(studentsNotInATeam.get(i));
                            studentsNotInATeam.remove(studentsNotInATeam.get(i));
                            GPAGreaterThanThreeCounter++; //we got one more student with GPA >= 3
                            break;
                        }
                    }
                    if (GPAGreaterThanThreeCounter == 2) {
                        break; // if hard constraint is met
                    }
                }
            }
        }
        return teamCreator;
    }

    /**
     * Method to assign Project to a team
     *
     * <p> Team is passed to method and a project will be assigned
     * according to student preferences for the projects.
     * Also a team ID is assigned to team.
     * @param teamCreator arraylist containing details of students who can form a team.
     * @return a team
     */
    public static Team setProjectForTeam(ArrayList < Student > teamCreator) {
        HashMap < Project, Integer > projectPopularity = new HashMap < > ();
        for (int i = 0; i < Project.projectsNotAssigned.size(); i++) {
            projectPopularity.put(Project.projectsNotAssigned.get(i), 0);
        }

        for (int i = 0; i < teamCreator.size(); i++) {
            Project[] preference = new Project[4];
            preference = teamCreator.get(i).getPreferredProjects();

            for (int j = 0; j < 4; j++) {
                for (HashMap.Entry < Project, Integer > entry:projectPopularity.entrySet()) {
                    if (entry.getKey().getProjectId().compareTo(preference[j].getProjectId()) == 0) {
                        entry.setValue(entry.getValue() + (4 - j));
                    }
                }
            }
        }

        String search = "TEAM";
        String format = String.format("%03d", (Team.allTeams.size() + 1));
        Team team = new Team(search + format);
        int maxValueInMap = (Collections.max(projectPopularity.values()));
        for (HashMap.Entry < Project, Integer > entry: projectPopularity.entrySet()) {
            if (entry.getValue() == maxValueInMap) {
                team.setProjectAssigned(entry.getKey());
                Project.projectsNotAssigned.remove(entry.getKey());
            }
        }

        for (Student s: teamCreator) {
            team.getStudentsInTeam().add(s);
        }
        return team;
    }

    /**
     * Method to display all teams
     *
     */
    private void displayTeams() {
        SwapTeamMembersController.calculateTeamConstraints();
        System.out.println("\nTeams formed are:");
        for (Team team: Team.allTeams) {
            System.out.println("\nThe team ID is : \t\t\t" + team.getTeamID() +
                    "\nThe Project Assigned to this team is \t: " + team.getProjectAssigned().getProjectId() + ": " + team.getProjectAssigned().getProjectTitle() +
                    "\nThe team's fitness is \t\t\t: " + team.getTeamFitness());
            System.out.print("The Students IDs of students in this team are: \n");
            for (Student student: team.getStudentsInTeam()) {
                System.out.print(student.getId() + "  Name: " + student.getFirstName() + "\t  Gender : " + student.getGender() + "\t  Experience : " + student.getExperience() + " years\n");
            }
            int j = 1;
            System.out.println(" Constraints met are:");
            for (Constraint c: team.getConstraintsMet()) {
                System.out.println(j + ". " + c.getConstraintDescription() + ": " + c.getWeightAge());
                j++;
            }
        }
    }

    /**
     * Method to swap members and display team fitness using Graphical user Interface
     *
     * <p>
     *     The method first shows all team's fitness in GUI. Then project manager
     * can select "SWAP". Then it will ask project manager to enter two students
     * from different teams for swapping. The method does internal swapping of those
     * two students showing the predicted all team's fitness after swapping.
     * If he "CONFIRM" the swapping then it will continue else it will revert back
     * the swapping made in previous step and display previous original all team's fitness again.
     */
    private void swapTeamMembers() {
        // GUI call
        String[] argsArray = new String[0];
        SwapTeamGUI.main(argsArray);
    }
}