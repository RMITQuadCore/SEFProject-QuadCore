package util;

import java.util.Scanner;

/**
 * util.Global class is aimed at using singular scanner globally in project. It helps in removing
 * redundant and int to trying scan conflict which usually occur otherwise.
 *
 */
public class Global {
    //Global Scanner
    public static Scanner scan = new Scanner(System.in);
}