package util;

/**
 * Class is aimed to validating use input via global methods to make sure
 * only desired and validated input is entered by user.
 */
public class InputTools {

    /**
     * Method to make sure correct integer value is entered by the user in the desired range.
     *
     * @param min minimum value of the desired integer range
     * @param max maximum value of the desired integer range
     * @return validated input entered by the user
     */
    public static int intChecker(int min, int max) {
        int response = 0;
        do {
            System.out.println("Enter your choice: ");
            String input = Global.scan.next() + Global.scan.nextLine();

            try {
                response = Integer.parseInt(input);
            }
            catch (NumberFormatException exp) {
                System.err.println("Please enter a valid number within " + min + " to " + max + ": ");
            }
        } while (!(response >= min && response <= max));
        return response;
    }

    /**
     * Method to make sure correct float value is entered by the user in the desired range.
     *
     * @param min minimum value of the desired float range
     * @param max minimum value of the desired float range
     * @return validated input entered by the user
     */
    public static float floatChecker(float min, float max) {
        float response = 0;
        do {
            System.out.println("Enter your choice: ");
            String input = Global.scan.next() + Global.scan.nextLine();

            try {
                response = Float.parseFloat(input);
            }
            catch (NumberFormatException exp) {
                System.err.println("Please enter a valid number within " + min + " to " + max + ": ");
            }
        } while (!(response >= min && response <= max));
        return response;
    }
}