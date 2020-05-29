package model;

/**
 * Exception is thrown with a message if undesired character
 * are enter in required string.
 *
 */
public class IncorrectInputException extends Exception {

    public IncorrectInputException(String reason) {
        super(reason);
    }
}