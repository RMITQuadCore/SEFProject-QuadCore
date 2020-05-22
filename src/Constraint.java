import java.util.ArrayList;

public class Constraint {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    int maxNosOfFemaleStudent;
    float benchmarkStudentGpa;
    int minNosOfStudWithBenchmarkGpa;
    float maxAvgGpaOfTeam;
    float yearsOfExperience;
    int minNosOfStudWithExperience;
    ArrayList<Character> requiredPersonalities = new ArrayList<>();
    ArrayList<Character> validPersonalities = new ArrayList<>();
    boolean uniquePersonality;
    int teamSize;
    int uniquePersonalityWeightAge = 0;
    int requiredPersonalityWeightAge = 0;
    int experienceWeightAge = 0;

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

    public ArrayList<Character> getRequiredPersonalities() {
        return requiredPersonalities;
    }

    public void setRequiredPersonalities(ArrayList<Character> requiredPersonalities) {
        this.requiredPersonalities = requiredPersonalities;
    }

    public ArrayList<Character> getValidPersonalities() {
        return validPersonalities;
    }

    public void setValidPersonalities(ArrayList<Character> validPersonalities) {
        this.validPersonalities = validPersonalities;
    }

    public boolean isUniquePersonality() {
        return uniquePersonality;
    }

    public void setUniquePersonality(boolean uniquePersonality) {
        this.uniquePersonality = uniquePersonality;
    }

    //Method to set all constraints
    public void setAllConstraints() {
        System.out.println(" Enter number of students in a team: ");
        teamSize = InputTools.intChecker(1, 100);

        System.out.println(ANSI_RED + "Hard Constraints : " + ANSI_RESET);
        System.out.println("1. Maximum number of female student per team: ");
        maxNosOfFemaleStudent = InputTools.intChecker(0, teamSize);

        System.out.println("2. Benchmark GPA of individual student: ");
        benchmarkStudentGpa = InputTools.floatChecker(0, 4);

        System.out.println("3. Minimum number of student with at least benchmark GPA in a team: ");
        minNosOfStudWithBenchmarkGpa = InputTools.intChecker(0, teamSize);

        System.out.println("4. Maximum average GPA of a team: ");
        maxAvgGpaOfTeam = InputTools.floatChecker(0, 4);


        System.out.println("\n" + ANSI_GREEN + "Soft Constraints: " + ANSI_RESET);
        System.out.println("1. Number of years of experience benchmark: ");
        yearsOfExperience = InputTools.floatChecker(0, 80);

        System.out.println("2. Minimum number of student with " + yearsOfExperience +
                "+ year(s) of experience per team: ");
        minNosOfStudWithExperience = InputTools.intChecker(0, teamSize);

        System.out.println("3. Define valid Personality types of students (A-Z): ");
        int i = 1;
        char input = '0';
        do {
            System.out.println("Personality " + i++ + ":");
            input = Global.scan.nextLine().toUpperCase().charAt(0);
            validPersonalities.add(input);
            System.out.println("Do you want to add more personality: (Y/N)");
            input = Global.scan.nextLine().toUpperCase().charAt(0);
        } while (input == 'Y');

        System.out.println("4. Enter Personality(s) type should be present in every team : ");
        int j = 1;
        String choice = "";
        do {
            System.out.println("Personality " + j + ":");
            choice = Global.scan.nextLine().toUpperCase();
            boolean isExist = false;
            for (Character c : validPersonalities) {
                if (choice.equals(c.toString())) {
                    requiredPersonalities.add(choice.charAt(0));
                    j++;
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                System.err.println("Invalid personality ");
            }
            System.out.println("Do you want enter more personality(s): (Y/N)");
            choice = Global.scan.nextLine().toUpperCase();
        } while (choice.charAt(0) == 'Y');


        boolean isExist = false;
        do {
            System.out.println("5. Should every personality in a team should be unique? (Y/N): ");
            if (Global.scan.nextLine().toUpperCase().charAt(0) == 'Y') {
                setUniquePersonality(true);
                isExist = true;
            } else if (Global.scan.nextLine().toUpperCase().charAt(0) == 'N') {
                setUniquePersonality(false);
                isExist = true;
            }
        } while (isExist == false);

        displayConstraints();
    }


    // Method to set weight age for soft constraints
    public void setWeightAge() {

        System.out.println("\nEnter weight age for Soft-Constraints (1-4): ");

        System.out.println("Unique personality constraint: ");
        uniquePersonalityWeightAge = InputTools.intChecker(1, 4);

        System.out.println("Required personality in a team constraint: ");
        uniquePersonalityWeightAge = InputTools.intChecker(1, 4);

        System.out.println("Experience Soft-Constraint : ");
        experienceWeightAge = InputTools.intChecker(1, 4);
    }


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
        for (Character c : requiredPersonalities) {
            int k = 0;
            System.out.println(k++);
            System.out.print(" " + requiredPersonalities + ",");
        }

        System.out.println(
                "3. Every personality on a team should be unique: " + uniquePersonality);

        System.out.println("\n Weight age for Soft constraints : \n" +
                "1. Unique personality constraint: " + uniquePersonalityWeightAge + "\n" +
                "2. Required personality in a team constraint: " + requiredPersonalityWeightAge + "\n" +
                "3. Experience Soft-Constraint : " + experienceWeightAge + "\n");
    }
}
