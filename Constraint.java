import java.util.Scanner;

public class Constraint
{
    Scanner scan = new Scanner(System.in);

    public int setMaxFemaleStudent()
    {
        int maxFemaleStudent = 0;
        do {
            System.out.println("Enter number of maximum female student per team you want to set : ");
            String input = scan.nextLine();

            try
            {
                maxFemaleStudent = Integer.parseInt(input);
            }
            catch (NumberFormatException exp)
            {
                System.err.println("Please enter an integer: ");
            }
        } while (!(maxFemaleStudent > 0 ));

        return maxFemaleStudent;
    }

    // THIS WILL HERE OR IN SYSTEM

    //method to check Every team should have at least two or more students with GPA 3.
    public void checkGpaThreePlus()
    {

        //need data from student
    }

    // THIS WILL HERE OR IN SYSTEM
    public void checkTeamAvg() //should be below 3.5
    {

    }


    // Method to set weighage for soft constraints
    public void setWeightage()
    {
        System.out.println("Please enter weightage for Soft constraints (1-4): ");

        int weightageExp = 0;
        String temp1 ="";

        do {
            try
            {

                System.out.println("Weightage for 'One member with at least 5 years of previous work experience' : ");
                temp1 = scan.nextLine();
                weightageExp = Integer.parseInt(temp1);
                if(!(weightageExp>0 && weightageExp<5))
                {
                    System.out.println("Please enter weightage between 1 to 4");
                }

            }
            catch(NumberFormatException e)
            {
                //If number is not integer,you will get exception and exception message will be printed
                System.out.println("Please enter Integer only (1-4)");
            }
        }while(!(weightageExp>0 && weightageExp<5));


        int weightagePersonality =0;
        String temp2 ="";
        do {
            try
            {

                System.out.println("Weightage for 'No two members of the same personality types' ");
                temp2 = scan.nextLine();
                weightagePersonality = Integer.parseInt(temp2);
                if(!(weightagePersonality>0 && weightagePersonality<5))
                {
                    System.out.println("Please enter weightage between 1 to 4");
                }

            }
            catch(NumberFormatException e)
            {
                //If number is not integer,you will get exception and exception message will be printed
                System.out.println("Please enter Integer only (1-4)");
            }
        }while(!(weightagePersonality>0 && weightagePersonality<5));
    }



}
