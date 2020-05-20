import java.util.Scanner;

public class Constraint {
    //Scanner scan = SingletonScanner.getInstance();


    // Method to set weightage for soft constraints
    public void setWeightage() {
        int personalityWeightage = 0;
        int experienceWeightage = 0;
        System.out.println("Enter weightage for Soft-Constraints (1-4):\n");
        do {
            try {
                System.out.println("Personality Soft-Constraint :");
                personalityWeightage = Integer.parseInt(Global.scan.next());

                if (personalityWeightage < 1 || personalityWeightage > 4) {
                    System.out.println("Please enter weighatge in range 1 to 4");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.err.println("Please enter an integer (1-4)");
            }
        } while (personalityWeightage < 1 || personalityWeightage > 4);

        do {
            try {
                System.out.println("Experience Soft-Constraint :");
                experienceWeightage = Integer.parseInt(Global.scan.next());
                if (experienceWeightage < 1 || experienceWeightage > 4) {
                    System.out.println("Please enter weighatge in range 1 to 4");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.err.println("Please enter an integer (1-4)");
            }
        } while (experienceWeightage < 1 || experienceWeightage > 4);
    }


    public void displayConstraints() {
        System.out.println("Currently Set Constraints are:\n");

        System.out.println("\n Hard Constraint: \n" +
                "1. Maximum number of female student per team: 1 \n" +
                "2. Minimum number of student with atleast 3 GPA per team: 2 \n" +
                "3. Maximum average GPA of a team: 3.5 GPA");

        System.out.println("\n Soft Constraints: \n" +
                "1. Minimum number of student with 5+ years of experience per team: 1 \n" +
                "2. Personality type must be in every team: A or B type \n" +
                "3. Every personality on a team should be unique");
    }


}
