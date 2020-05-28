import java.io.IOException;
import java.io.Serializable;
import java.util.*;


public class ProjectManager extends User implements Serializable {
    private static final long serialVersionUID = 2437934725196379683L;

    public ArrayList<String> studentPersonalities = new ArrayList<String>();
    public static ArrayList<Student> studentsNotInATeam = new ArrayList<Student>();
    private boolean signUpStatus = true;
    private static boolean projectsDiscarded = false;
    public static ArrayList < Constraint > constraints = new ArrayList < > ();

    public static ArrayList<Constraint> getConstraints() {
        return constraints;
    }

    public static void setConstraints(ArrayList<Constraint> constraints) {
        ProjectManager.constraints = constraints;
    }

    public ProjectManager()
    {
    }

    public ProjectManager(String id, String firstName, String lastName, String emailID, String userName,
                          String password, String org)  {
        super(id, firstName, lastName, emailID, userName, password, org);

    }

    public static boolean isProjectsDiscarded() {
        return projectsDiscarded;
    }

    public static void setProjectsDiscarded(boolean projectsDiscarded) {
        ProjectManager.projectsDiscarded = projectsDiscarded;
    }

    public ArrayList<Student> getTempStudent() {
        return studentsNotInATeam;
    }
    public void setTempStudent(ArrayList<Student> tempStudent) {
        ProjectManager.studentsNotInATeam = tempStudent;
    }
    //Scanner scan = SingletonScanner.getInstance();
    public ArrayList<String> getStudentPersonalities() {
        return this.studentPersonalities;
    }
    public boolean getSignUpStatus()
    {
        return signUpStatus;
    }


    /**
     * Project Manger Menu
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void projectManagerMenu() throws IOException, ClassNotFoundException {
        int choice;
        do {
            System.out.println(Constraint.ANSI_YELLOW + "\n*** Project Manager Menu ***\n" + Constraint.ANSI_RESET+
                    "1. Set all Constraints\n" +
                    "2. Enter Personality of students\n" +
                    "3. Enter Weight age for Soft-Constraints\n" +
                    "4. Change Sign up status\n" +
                    "5. Discard Unpopular projects\n" +
                    "6. Display Current Constraint\n" +
                    "7. Run Project Team formation\n" +
                    "8. Display Teams\n" +
                    "9. Swap team members\n" +
                    "10. Display team fitness\n" +
                    "11. Logout");
                choice = InputTools.intChecker(1,11);

            Constraint constraint = new Constraint();

            switch (choice) {
                case 1:
                    constraint.setAllConstraints();
                    break;

                case 2:
                    enterStudentPersonalities();
                    break;

                case 3:
                    constraint.setWeightAge();
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
                    constraint.displayConstraints();
                    break;

                case 7:
                    if(projectsDiscarded == true)
                    createTeams();
                    else System.out.println("\n\n You need to discard unpopular projects before creating teams .");
                    break;

                case 8:
                    //displayTeams();
                    System.out.println("Display Teams");
                    break;

                case 9:
                    swapTeamMembers();
                    break;

                case 10:
                    System.out.println("Display Team Fitness");
                    break;

                case 11:
                    mainMenu();
                    break;
            }
        } while (choice != 11);
    }


    //Method to assign personality to all students
    private void  enterStudentPersonalities() throws IOException {
        boolean foundStudent = false, validPersonality = false;
        String studID;
        char studPersonality = 0;

        do {
            System.out.println("\nEnter assigned student ID:");
            studID = Global.scan.nextLine();
            for (Student stud : Student.allStudents) {

                if (stud.getId().compareTo(studID) == 0) {
                    foundStudent = true;
                    System.out.println("\nPlease enter personality of " + studID + " :");
                    studPersonality = Global.scan.nextLine().toUpperCase().charAt(0);
                    //System.out.println("Student personality enterred is: " +studPersonality);
                    char[] validPersonalities = {'A', 'B', 'C', 'D', 'E', 'F'};
                    for (int i = 0; i < 6; i++) {
                        if (studPersonality == validPersonalities[i]) {
                            validPersonality = true;
                            stud.setStudentPersonality(studPersonality); // TODO setStudent Personality to Student Class Object
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
//        for(Student st: Student.allStudents){
//            System.out.println(st.getId()+" "+st.getStudentPersonality());
//        }
        FileReadWrite.saveStudentDetails(Main.studentsFileName, Student.allStudents);
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
                choice = Integer.parseInt(Global.scan.next());
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

   public static ArrayList<Student> dislikedMembersRemover(ArrayList<Student>teamCreator)
   {
       for(int i =0 ; i < teamCreator.size() ; i ++)
       {
           Student[] dislikedMembers = teamCreator.get(i).getDislikedMembers();
           List<Student>dislikedMembersList = Arrays.asList(dislikedMembers);
           for(int j = 0 ; j < dislikedMembers.length ; j ++ )
           {
               boolean partOfTeam = false;
               if(teamCreator.contains(dislikedMembers[j]))
               {
                   partOfTeam = true;
               }
               if(partOfTeam && (dislikedMembers[j].getGender() == 'f' || dislikedMembers[j].getGender() == 'F'))
               {
                   for(Student student : studentsNotInATeam)
                   {
                       if ((!dislikedMembersList.contains(student))&& (student.getGender() == 'f' || student.getGender() == 'F'))
                       {
                           teamCreator.remove(dislikedMembers[j]);
                           teamCreator.add(student);
                           studentsNotInATeam.remove(student);
                           studentsNotInATeam.add(dislikedMembers[j]);
                           break;
                       }
                   }
               }
               if(partOfTeam && (dislikedMembers[j].getGender() == 'm' || dislikedMembers[j].getGender() == 'M'))
               {
                   for(Student student : studentsNotInATeam)
                   {
                       if ((!dislikedMembersList.contains(student))&& (student.getGender() == 'm' || student.getGender() == 'M'))
                       {
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

    //Method to form teams for projects
    public void createTeams() //TODO if consraints and weightage are not set methods shouldn't run.
    { //TODO Create a method for calling Soft Contrainsts Order
        ArrayList<Student> teamCreator = new ArrayList<Student>();
        Integer[] teamSize = getTeamSizeArray();// Calculate all the sizes
        for (int i =0 ; i < teamSize.length ; i ++)
        {   System.out.println("Team size = "+teamSize[i]);
            teamCreator = femaleHardConstraintApplicator(teamCreator, teamSize[i]);//Initial 4/ 3/ 2 students added to the ArrayList
            if(!Constraint.femaleHardConstraintCheck(teamCreator)) // Hard Constraint Check
            {
                System.out.println("Sorry! A team cannot be formed currently as a hard constraint is not met.\n" +
                        "Hard Constraint : Maximum of one woman per team cannot be met currently");
                break;
            }
            else System.out.println("Female Hard Constraint Maintained.");
            teamCreator = dislikedMembersRemover(teamCreator);
            teamCreator = uniquePersonalityConstraintApplicator(teamCreator);
            teamCreator = personalityAorBPresentApplicator(teamCreator);
            teamCreator = experienceSoftConstraintApplicator(teamCreator);
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
            else System.out.println(" GPA Hard Constraints Maintained.\n\n");
            Team team = setProjectForTeam(teamCreator);
            System.out.println("\n\nTeam Created");
            Team.allTeams.add(team);
            System.out.println("\nCongratulations! A team has been formed!!.\n" +
                    "The team ID is : \t\t\t" + team.getTeamID() +
                    "\nThe Project Assigned to this team is \t: " + team.getProjectAssigned().getProjectTitle() +
                    "\nThe team's fitness is \t\t\t: " + team.getTeamFitness()+"\n");
            System.out.print("The Students IDs of students in this team are: \n\n");
            for (Student student : teamCreator)
            {
                System.out.print(student.getId() + "  Name: " + student.getFirstName() + "\t  Gender : " + student.getGender() + "\n");
            }
            teamCreator.clear();

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


    // 1. Check overallGPA hard constraint


    public static ArrayList<Student> teamAverageGPAConstraintApplicator(ArrayList<Student>teamCreator) {

        double sumOfGPA = 0;
        for (Student student : teamCreator) {
            sumOfGPA = sumOfGPA + student.getGpa();
        }
        if ((sumOfGPA / 4) > 3.5) //if hard constraint is violeted then we will first try to meet it by swapping students between teamCreator and studentsNotInATeam
        {
            for (int i = 0; i < studentsNotInATeam.size(); i++) {
                if ((studentsNotInATeam.get(i).getGender() == 'm' || studentsNotInATeam.get(i).getGender() == 'M')&& studentsNotInATeam.get(i).getGpa() < 3.00) {
                    for (Student student : teamCreator) {
                        if ((student.getGender() == 'm' || student.getGender() == 'M')  && student.getGpa() > 3.00) {
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
                for (Student student : teamCreator) {
                    sumOfNewGPA = sumOfNewGPA + student.getGpa();
                }
                if ((sumOfNewGPA / 4) <= 3.5) {
                    break; //if hard constraint is met
                }
            }
        }
        return teamCreator;
    }

    // 2. Check GPA of individual team member hard constraint
    private static ArrayList<Student> teamMemberGPAConstraintApplicator(ArrayList<Student> teamCreator)
    {
        int GPAGreaterThanThreeCounter = 0;
        for (int i = 0; i < teamCreator.size(); i++) {
            if (teamCreator.get(i).getGpa() >= 3.00) {
                GPAGreaterThanThreeCounter++;
            }
        }

        //considering sumOfGPA here as well so that changes made here should not impact teamAverageGPAConstraint
        double sumOfGPA = 0;
        for (Student student : teamCreator) {
            sumOfGPA = sumOfGPA + student.getGpa();
        }
        double averageGpaOfTeam = sumOfGPA / 4;

        if (GPAGreaterThanThreeCounter < 2) { //if hard constraint is violeted then we will first try to meet it by swapping students between teamCreator and studentsNotInATeam
            for (int i = 0; i < studentsNotInATeam.size(); i++) {
                if (studentsNotInATeam.get(i).getGender() == 'm' && studentsNotInATeam.get(i).getGpa() >= 3.00) {
                    for (Student student : teamCreator) {
                        if ((studentsNotInATeam.get(i).getGender() == 'm' && student.getGpa() < 3.00) && (((sumOfGPA + studentsNotInATeam.get(i).getGpa() - student.getGpa()) / 4) < 3.50)) {
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

    public Integer[] getTeamSizeArray()
    {
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
    //3 & 4 Ensuring Female Hard Constraint is met

    public static ArrayList<Student> femaleHardConstraintApplicator(ArrayList<Student>teamCreator ,Integer teamSize)
    {
        int femaleCount = 0;
        for (Student t : studentsNotInATeam) {
            if (t.getGender() == 'f' || t.getGender() == 'F') {
                femaleCount++;
            }
        }
            int k = 0;
            boolean femaleFound = false;
            while (k < teamSize) {
                for (int z = 0; z < studentsNotInATeam.size(); z++) {
                    if (studentsNotInATeam.get(z).getGender() == 'f' || studentsNotInATeam.get(z).getGender() == 'F' && !femaleFound) {
                        teamCreator.add(studentsNotInATeam.get(z));
                        femaleFound = true;
                        femaleCount--;
                        studentsNotInATeam.remove(studentsNotInATeam.get(z));
                        k++;
                        break;
                    }
                    if ((studentsNotInATeam.get(z).getGender() == 'm' || studentsNotInATeam.get(z).getGender() == 'M' ) && femaleFound == true) {
                        teamCreator.add(studentsNotInATeam.get(z));
                        studentsNotInATeam.remove(studentsNotInATeam.get(z));
                        k++;
                        break;

                    } else {
                        if (femaleCount == 0) {
                            teamCreator.add(studentsNotInATeam.get(z));
                            studentsNotInATeam.remove(studentsNotInATeam.get(z));
                            k++;
                            break;
                        }
                    }
                }
            }
        return teamCreator;
    }
    public static ArrayList<Student> experienceSoftConstraintApplicator(ArrayList<Student> teamCreator)
    {
            boolean experienceCriteria = false;
            for (Student student : teamCreator)
            {
                if (student.getExperience() >= 5) {
                    experienceCriteria = true;
                    break;
                }
            }
        if (!experienceCriteria)
        {
            boolean replacementFound = false;
            Student replacement = new Student();
            for (Student student : studentsNotInATeam) {
                if ((student.getExperience() >= 5) && (student.getGender() == 'm' || student.getGender() == 'M'))
                {
                        replacementFound = true;
                        replacement = student;//Replacement found
                        break;
                }
            }
            if (replacementFound) {
                for (Student student : teamCreator) {
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

    //5 & 6 Function to make sure teams have unique personalities
    // and A or B type personality is present in a team
    //Satisfying Personality Soft Constraint
    public static ArrayList<Student> uniquePersonalityConstraintApplicator(ArrayList<Student> teamCreator) {
        //To check if duplicate personalities are present and removing team. Creating a unique Personality Team
        for (int j = 0; j < teamCreator.size(); j++) {
            for (int k = j+1; k < teamCreator.size(); k++) {
                 if (teamCreator.get(j).getStudentPersonality() == teamCreator.get(k).getStudentPersonality()) {
                    if (teamCreator.get(k).getGender() == 'm' || teamCreator.get(k).getGender() == 'M') {
                        for (Student student : studentsNotInATeam) {
                            if ((student.getStudentPersonality() != teamCreator.get(j).getStudentPersonality()) && (student.getGender() == 'm' || student.getGender() == 'M')) {
                                teamCreator.remove(teamCreator.get(k));
                                teamCreator.add(student);
                                studentsNotInATeam.remove(student);
                                studentsNotInATeam.add(teamCreator.get(k));
                                break;
                            }
                        }
                    }
                }
            }
        }
      return teamCreator;
    }     //To ensure A or B personality is present
public ArrayList<Student> personalityAorBPresentApplicator(ArrayList<Student> teamCreator)
        {
        boolean persoAOrBPresent = false;
        for (Student student : teamCreator) {
            if (student.getStudentPersonality() == 'A' || student.getStudentPersonality() == 'B' || student.getStudentPersonality() == 'a' || student.getStudentPersonality() == 'b') {
                //if A or B personality is present in team
                persoAOrBPresent = true;
            }
        }
        if (!persoAOrBPresent) {
            boolean replacementFound = false;
            Student replacement = new Student();
            for (Student student : studentsNotInATeam) {
                if (student.getStudentPersonality() == 'A' || student.getStudentPersonality() == 'B' || student.getStudentPersonality() == 'a' || student.getStudentPersonality() == 'b') {
                    if (student.getGender() == 'm' || student.getGender() == 'M') {
                        replacementFound = true;
                        replacement = student;//Replacement found
                        break;
                    }
                }
            }
            if (replacementFound) {
                for (Student student : teamCreator) {
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

    //Method to assign Project to a team
    public static Team setProjectForTeam(ArrayList<Student> teamCreator) {
        HashMap<Project, Integer> projectPopularity = new HashMap<>();
        for (int i = 0; i < Project.projectsNotAssigned.size(); i++) {
            projectPopularity.put(Project.projectsNotAssigned.get(i), 0);
        }

        for (int i = 0; i < teamCreator.size(); i++) {
            Project[] preference = new Project[4];
            preference = teamCreator.get(i).getPreferredProjects();
            for (int j = 0; j < 4; j++) {
                if (projectPopularity.containsKey(preference[j])) {
                    projectPopularity.put(preference[j], projectPopularity.get(preference[j]) + (4 - j));
                }
            }
        }
        String search = "TEAM";
        String format =String.format("%03d", (Team.allTeams.size()+1));
        Team team = new Team(search + format);
        int maxValueInMap = (Collections.max(projectPopularity.values()));
        for (HashMap.Entry<Project, Integer> entry : projectPopularity.entrySet()) {
            if (entry.getValue() == maxValueInMap) {
                team.setProjectAssigned(entry.getKey());
                Project.projectsNotAssigned.remove(entry.getKey());
            }
        }
        for (Student student : team.getStudentsInTeam())
        {
            student.setAssignedTeam(team);
        }
        team.setStudentsInTeam(teamCreator);
        return team;
    }

    private void swapTeamMembers() {
        // GUI call
        String[] argsArray = new String[0];
        SwapTeamGUI.main(argsArray);
    }
}
