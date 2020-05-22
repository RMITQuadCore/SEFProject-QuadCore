public class InputTools {

    // Method to make sure correct option is entered by user
    public static int intChecker(int min, int max) {
        int response = 0;

        do {
            System.out.println("Enter your choice: ");
            String input = Global.scan.next() + Global.scan.nextLine();
            try {
                response = Integer.parseInt(input);

            } catch (NumberFormatException exp) {
                System.err.println("Please enter a valid number within "+ min+ " to "+ max+ ": ");
            }
        } while (!(response >= min && response <= max));

        return response;
    }

    // Method to make sure correct float value is entered by user
    public static float floatChecker(float min, float max) {
        float response = 0;

        do {
            System.out.println("Enter your choice: ");
            String input = Global.scan.next() + Global.scan.nextLine();

            try {
                response = Float.parseFloat(input);
            } catch (NumberFormatException exp) {
                System.err.println("Please enter a valid number within "+ min+ " to "+ max+ ": ");
            }
        } while (!(response >= min && response <= max));

        return response;
    }
}
