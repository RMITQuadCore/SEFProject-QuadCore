package model;

import util.Global;

import java.io.Serializable;
import java.util.ArrayList;


public class Role implements Serializable {
    private static final long serialVersionUID = -8977434449646607064L;
    private String id; // ID can be both for student or Project
    private String roleName;
    private ArrayList < String > frameworks = new ArrayList < > ();


    public Role(String id, String roleName, ArrayList < String > frameworks) {
        this.id = id;
        this.roleName = roleName;
        this.frameworks = frameworks;
    }


    /**
     * Getter and setter methods.
     *
     * @return
     */
    public ArrayList < String > getFrameworks() {
        return frameworks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


    /**
     * Method to enter preferred frameworks by student
     *
     * @param role preferred role
     */
    public void enterFrameworks(Role role) {
        boolean frameWorkFound = false;

        System.out.println("Available frameworks are :");
        for (int i = 0; i < role.getFrameworks().size(); i++) {
            System.out.println(frameworks);
        }

        do { //TODO Display list of frameworks
            System.out.println("Enter the framework : ");
            String input = Global.scan.nextLine();

            for (int i = 0; i < role.getFrameworks().size(); i++) {
                if (input.equalsIgnoreCase(role.getFrameworks().get(i))) {
                    frameWorkFound = true;
                }
                if (frameWorkFound) {
                    frameworks.add(input);
                } else {
                    System.out.println(" There is no such framework required for this role.");
                }
            }
            frameworks.add(input);
        } while (!frameWorkFound);
    }
}