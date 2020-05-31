

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class test {


        public static void main(String args[])
        {
            try
            {
                int x = getIntInput("Enter an integer");
                System.out.println(x);
            }
            catch (InputMismatchException in)
            {
                System.out.println("Invalid Input");
            }
        }

        public static int getIntInput(String s)
        {
            System.out.println("before");
            Scanner scan = new Scanner(System.in);
            System.out.println(s);
            int n = scan.nextInt();
            System.out.println(n);
            return n;
        }
    }



