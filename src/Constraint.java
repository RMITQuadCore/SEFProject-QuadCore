import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Constraint implements Serializable {

    String constraintId = "Constraint 0";
    String constraintDescription;
    private int weightAge;
    public static ArrayList  < Constraint > allSoftConstraints = new ArrayList < > ();

    int maxNosOfFemaleStudent;
    float benchmarkStudentGpa;
    int minNosOfStudWithBenchmarkGpa;
    float maxAvgGpaOfTeam;
    float yearsOfExperience;
    int minNosOfStudWithExperience;
    ArrayList <Character> requiredPersonalities = new ArrayList<>();
    ArrayList <Character> validPersonalities = new ArrayList<>();;
    //char[] validPersonalities = {'A', 'B', 'C', 'D', 'E', 'F'};
    boolean uniquePersonality;
    int teamSize;
    int uniquePersonalityWeightAge = 0;
    int requiredPersonalityWeightAge = 0;
    int experienceWeightAge = 0;

    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    public Constraint() {

    }

    public Constraint(String constraintId, String constraintDescription, int weightAge) {
        this.constraintId = constraintId;
        this.constraintDescription = constraintDescription;
        this.weightAge = weightAge;
    }
    public int getWeightAge() {
        return weightAge;
    }

    public void setWeightAge(int weightAge) {
        this.weightAge = weightAge;
    }
    public Constraint(String constraintId, String constraintDescription, int maxNosOfFemaleStudent,
                      float benchmarkStudentGpa, int minNosOfStudWithBenchmarkGpa, float maxAvgGpaOfTeam,
                      float yearsOfExperience, int minNosOfStudWithExperience,
                      ArrayList < Character > requiredPersonalities, ArrayList < Character > validPersonalities,
                      boolean uniquePersonality, int teamSize, int uniquePersonalityWeightAge,
                      int requiredPersonalityWeightAge, int experienceWeightAge) {
        this.constraintId = constraintId;
        this.constraintDescription = constraintDescription;
        this.maxNosOfFemaleStudent = maxNosOfFemaleStudent;
        this.benchmarkStudentGpa = benchmarkStudentGpa;
        this.minNosOfStudWithBenchmarkGpa = minNosOfStudWithBenchmarkGpa;
        this.maxAvgGpaOfTeam = maxAvgGpaOfTeam;
        this.yearsOfExperience = yearsOfExperience;
        this.minNosOfStudWithExperience = minNosOfStudWithExperience;
        this.requiredPersonalities = requiredPersonalities;
        this.validPersonalities = validPersonalities;
        this.uniquePersonality = uniquePersonality;
        this.teamSize = teamSize;
        this.uniquePersonalityWeightAge = uniquePersonalityWeightAge;
        this.requiredPersonalityWeightAge = requiredPersonalityWeightAge;
        this.experienceWeightAge = experienceWeightAge;
    }


    /**
     * Getter and Setter Methods
     * @return
     */
    public String getConstraintId() {
        return constraintId;
    }

    public void setConstraintId(String constraintId) {
        this.constraintId = constraintId;
    }

    public String getConstraintDescription() {
        return constraintDescription;
    }

    public void setConstraintDescription(String constraintDescription) {
        this.constraintDescription = constraintDescription;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }

    public int getUniquePersonalityWeightAge() {
        return uniquePersonalityWeightAge;
    }

    public void setUniquePersonalityWeightAge(int uniquePersonalityWeightAge) {
        this.uniquePersonalityWeightAge = uniquePersonalityWeightAge;
    }

    public int getRequiredPersonalityWeightAge() {
        return requiredPersonalityWeightAge;
    }

    public void setRequiredPersonalityWeightAge(int requiredPersonalityWeightAge) {
        this.requiredPersonalityWeightAge = requiredPersonalityWeightAge;
    }

    public int getExperienceWeightAge() {
        return experienceWeightAge;
    }

    public void setExperienceWeightAge(int experienceWeightAge) {
        this.experienceWeightAge = experienceWeightAge;
    }

    public int getMaxNosOfFemaleStudent() {
        return maxNosOfFemaleStudent;
    }

    public void setMaxNosOfFemaleStudent(int maxNosOfFemaleStudent) {
        this.maxNosOfFemaleStudent = maxNosOfFemaleStudent;
    }

    public float getBenchmarkStudentGpa() {
        return benchmarkStudentGpa;
    }

    public void setBenchmarkStudentGpa(float benchmarkStudentGpa) {
        this.benchmarkStudentGpa = benchmarkStudentGpa;
    }

    public int getMinNosOfStudWithBenchmarkGpa() {
        return minNosOfStudWithBenchmarkGpa;
    }

    public void setMinNosOfStudWithBenchmarkGpa(int minNosOfStudWithBenchmarkGpa) {
        this.minNosOfStudWithBenchmarkGpa = minNosOfStudWithBenchmarkGpa;
    }

    public float getMaxAvgGpaOfTeam() {
        return maxAvgGpaOfTeam;
    }

    public void setMaxAvgGpaOfTeam(float maxAvgGpaOfTeam) {
        this.maxAvgGpaOfTeam = maxAvgGpaOfTeam;
    }

    public float getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(float yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public int getMinNosOfStudWithExperience() {
        return minNosOfStudWithExperience;
    }

    public void setMinNosOfStudWithExperience(int minNosOfStudWithExperience) {
        this.minNosOfStudWithExperience = minNosOfStudWithExperience;
    }

    public ArrayList < Character > getRequiredPersonalities() {
        return requiredPersonalities;
    }

    public void setRequiredPersonalities(ArrayList < Character > requiredPersonalities) {
        this.requiredPersonalities = requiredPersonalities;
    }

    public ArrayList < Character > getValidPersonalities() {
        return validPersonalities;
    }

    public void setValidPersonalities(ArrayList < Character > validPersonalities) {
        this.validPersonalities = validPersonalities;
    }

    public boolean isUniquePersonality() {
        return uniquePersonality;
    }

    public void setUniquePersonality(boolean uniquePersonality) {
        this.uniquePersonality = uniquePersonality;
    }


    /**
     * Method to generate constraint ID
     */
    public void createConstraint() throws IOException {
//        constraintId = "Constraint " +
//                String.format("%03d", (Integer.parseInt(getConstraintId().substring(11)) + 1));

     //   if (allSoftConstraints.size() > 0) {
            //Constraint lastConstraint = allSoftConstraints.get(allSoftConstraints.size() - 1);
//       projectId = "PROJ" + String.format("%03d", (Integer.parseInt(lastProject.getProjectId().substring(4)) + 1));
            constraintId = "Constraint " + (allSoftConstraints.size()+1);
            setConstraintId(constraintId);
                    //"Constraint " +
                //String.format("%03d", (Integer.parseInt(getConstraintId().substring(11)) + 1));
//        } else {
//            constraintId = "Constraint " +
//                    String.format("%03d", (Integer.parseInt(getConstraintId().substring(11)) + 1));
//        }

        System.out.println("Enter Description: ");
        constraintDescription = Global.scan.nextLine();

        System.out.println("Enter weight age for soft constraint: ");
        weightAge = InputTools.intChecker(1,4);

        allSoftConstraints.add(new Constraint(constraintId, constraintDescription, weightAge));
        FileReadWrite.saveConstraintDetails(Main.softConstraintFileName, allSoftConstraints);
    }


    /**
     * Method to define and set value for all constraints by Project Manager.
     */
    public void setAllConstraints() throws IOException {
//        System.out.println(" Enter number of students in a team: ");
//        teamSize = InputTools.intChecker(1, 100);
//
//        System.out.println(ANSI_YELLOW + "Hard Constraints : " + ANSI_RESET);
//        System.out.println("1. Maximum number of female student per team: ");
//        maxNosOfFemaleStudent = InputTools.intChecker(0, teamSize);
//
//        System.out.println("2. Benchmark GPA of individual student: ");
//        benchmarkStudentGpa = InputTools.floatChecker(0, 4);
//
//        System.out.println("3. Minimum number of student with at least benchmark GPA in a team: ");
//        minNosOfStudWithBenchmarkGpa = InputTools.intChecker(0, teamSize);
//
//        System.out.println("4. Maximum average GPA of a team: ");
//        maxAvgGpaOfTeam = InputTools.floatChecker(0, 4);
//
//
//        System.out.println("\n" + ANSI_GREEN + "Soft Constraints: " + ANSI_RESET);
//        System.out.println("1. Number of years of experience benchmark: ");
//        yearsOfExperience = InputTools.floatChecker(0, 80);
//
//        System.out.println("2. Minimum number of student with " + yearsOfExperience +
//                "+ year(s) of experience per team: ");
//        minNosOfStudWithExperience = InputTools.intChecker(0, teamSize);
//
//        System.out.println("3. Define valid Personality types of students (A-Z): ");
//        int i = 1;
//        char input;
//        do {
//            System.out.println("Personality " + i++ + ":");
//            input = Global.scan.nextLine().toUpperCase().charAt(0);
//            validPersonalities.add(input);
//            System.out.println("Do you want to add more personality: (Y/N)");
//            input = Global.scan.nextLine().toUpperCase().charAt(0);
//        } while (input == 'Y');
//
//        System.out.println("4. Enter Personality(s) type should be present in every team : ");
//        int j = 1;
//        String choice;
//        do {
//            System.out.println("Personality " + j + ":");
//            choice = Global.scan.nextLine().toUpperCase();
//            boolean isExist = false;
//            for (Character c: validPersonalities) {
//                if (choice.equals(c.toString())) {
//                    requiredPersonalities.add(choice.charAt(0));
//                    j++;
//                    isExist = true;
//                    break;
//                }
//            }
//            if (!isExist) {
//                System.err.println("Invalid personality ");
//            }
//            System.out.println("Do you want enter more personality(s): (Y/N)");
//            choice = Global.scan.nextLine().toUpperCase();
//        } while (choice.charAt(0) == 'Y');
//
//
//        boolean isExist = false;
//        do {
//            System.out.println("5. Should every personality in a team should be unique? (Y/N): ");
//            if (Global.scan.nextLine().toUpperCase().charAt(0) == 'Y') {
//                setUniquePersonality(true);
//                isExist = true;
//            } else if (Global.scan.nextLine().toUpperCase().charAt(0) == 'N') {
//                setUniquePersonality(false);
//                isExist = true;
//            }
//        } while (!isExist);

        System.out.println("\nEnter weight age for Soft-Constraints (1-4): ");

        System.out.println("Unique Personality Soft-Constraint : ");
        createConstraint();

        System.out.println("Required Personality Soft-Constraint : ");
        createConstraint();

        System.out.println("Experience Soft-Constraint : ");
        createConstraint();

        displayConstraints();
    }


    /**
     * Method to display all set constraints.
     */
    public void displayConstraints() {
        System.out.println("Number of students in a team: " + teamSize);
        System.out.println("Currently Set Constraints are: ");

        System.out.println("\n Hard Constraint: \n" +
                "1. Maximum number of female student per team: " + maxNosOfFemaleStudent + "\n" +

                "2. Minimum number of student with at least " + benchmarkStudentGpa +
                " GPA per team: " + minNosOfStudWithBenchmarkGpa + "\n" +

                "3. Maximum average GPA of a team: " + maxAvgGpaOfTeam + " GPA");

        System.out.println("\n Soft Constraints: \n" +
                "1. Minimum number of student with " + yearsOfExperience +
                "+ year(s) of experience per team: " + minNosOfStudWithExperience + "\n" +

                "2. Any of these Personality type should be in every team: ");
        for (Character c : requiredPersonalities ) {
            int k =0;
            System.out.println(k++);
            System.out.print(" " + c.toString() + ",");
        }

        System.out.println(
                "3. Every personality on a team should be unique: " + uniquePersonality);

        int j = 1;
        System.out.println("Weight age for Soft constraints : ");
        for (Constraint c : allSoftConstraints){
            System.out.println(j + ". "+ c.getConstraintDescription() + ": "+ c.getWeightAge());
            j++;
        }

//        System.out.println("\n Weight age for Soft constraints : \n" +
//                "1. Unique personality constraint: " + uniquePersonalityWeightAge + "\n" +
//                "2. Required personality in a team constraint: " + requiredPersonalityWeightAge + "\n" +
//                "3. Experience Soft-Constraint : " + experienceWeightAge + "\n");
    }


    /**
     * Method to check unique personality constraint is being applied or not.
     * @param teamCreator
     * @return
     */
    public static boolean uniquePersonalityConstraintCheck(ArrayList<Student> teamCreator)
    {
        boolean noDuplicate = true;
        for (int j = 0; j < teamCreator.size()-1; j++)
        {
            for (int k = j + 1; k < teamCreator.size(); k++) {
                if (teamCreator.get(j).getStudentPersonality() == teamCreator.get(k).getStudentPersonality())
                {
                    noDuplicate = false;
                }
            }
        }
        return noDuplicate;
    }


    /**
     * Method to check required personality constraint exists on the team or not
     * @param teamCreator
     * @return
     */
    public static boolean requiredPersonalityConstraintCheck(ArrayList<Student> teamCreator)
    {
        boolean persoAOrBPresent = false;
        for (Student student : teamCreator) {
            if (student.getStudentPersonality() == 'A' || student.getStudentPersonality() == 'B' || student.getStudentPersonality() == 'a' || student.getStudentPersonality() == 'b') {
                //if A or B personality is present in team
                persoAOrBPresent = true;
                break;
            }
        }
        return persoAOrBPresent;
    }


    /**
     * Method to check average GPA of team, hard constraint met or not.
     * @param teamCreator
     * @return
     */
    public static boolean averageGPAHardConstraintCheck(ArrayList<Student> teamCreator)
    {
        double sumOfGPA = 0;
        for (Student student : teamCreator) {
            sumOfGPA = sumOfGPA + student.getGpa();
        }
        if ((sumOfGPA / 4) > 3.5)
            return false;
        else return true;
    }


    /**
     * Method to check GPA hard constraint is met or not.
     * @param teamCreator
     * @return
     */
    public static boolean twoMembersWith3GPAHardConstraintCheck(ArrayList<Student> teamCreator) //TODO better name
    {
        int GPAGreaterThanThreeCounter = 0;
        for (int i = 0; i < teamCreator.size(); i++)
        {
            if (teamCreator.get(i).getGpa() >= 3.00)
            {
                GPAGreaterThanThreeCounter++;
            }
        }
        if(GPAGreaterThanThreeCounter > 2)
        {
            return true;
        }
        else return false;

    }


    /**
     * Method to check experience soft constraint
     * @param teamCreator
     * @return
     */
    public static boolean memberWith5YearExperienceConstraintCheck(ArrayList<Student> teamCreator)
    {
        boolean experienceCriteria = false;
        for (Student student : teamCreator)
        {
            if (student.getExperience() >= 5)
            {
                experienceCriteria = true;
                break;
            }
        }

        return experienceCriteria;
    }


    /**
     * Method to check female student hard constraint is met for not.
     * @param teamCreator
     * @return
     */
    public static boolean femaleHardConstraintCheck(ArrayList<Student> teamCreator)
    {
        int femaleCounter =0 ;
       for(Student student : teamCreator)
       {
           if (student.getGender() == 'f' || student.getGender() == 'F' ) {
               femaleCounter++;
               System.out.println("Counter: "+femaleCounter);
           }
       }

        if (femaleCounter>1)
            return false;
        else
            return true;
    }
}
