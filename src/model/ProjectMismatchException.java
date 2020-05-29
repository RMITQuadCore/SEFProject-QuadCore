package model;

/**
 * Exception is thrown with a message if number of
 * required project is more than all projects
 *
 */
public class ProjectMismatchException extends Exception {

    public ProjectMismatchException(String reason) {
        super(reason);
    }
}