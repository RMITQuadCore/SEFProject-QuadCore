package model;

import java.io.Serializable;
import java.util.ArrayList;


public class Team implements Serializable {
    private static final long serialVersionUID = -1323766548602603946L;
    public static ArrayList < Team > allTeams = new ArrayList < > ();
    private String teamID;
    private ArrayList < Student > studentsInTeam = new ArrayList < > ();
    private int teamFitness;
    private ArrayList <Constraint> constraintsMet = new ArrayList < > ();
    private Project projectAssigned;


    /**
     * Getter and setter methods.
     *
     * @param teamID
     */
    public Team(String teamID) {
        this.teamID = teamID;
    }

    public Team(String teamID, ArrayList < Student > studentsInTeam, int teamFitness, ArrayList <Constraint> constraintsMet, Project projectAssigned) {
        this.teamID = teamID;
        this.studentsInTeam = studentsInTeam;
        this.teamFitness = teamFitness;
        this.constraintsMet = constraintsMet;
        this.projectAssigned = projectAssigned;
    }

    public String getTeamID() {
        return teamID;
    }

    public ArrayList < Student > getStudentsInTeam() {
        return studentsInTeam;
    }

    public int getTeamFitness() {
        return teamFitness;
    }

    public void setTeamFitness(int teamFitness) {
        this.teamFitness = teamFitness;
    }

    public ArrayList <Constraint> getConstraintsMet() {
        return constraintsMet;
    }

    public void setConstraintsMet(ArrayList <Constraint> constraintsMet) {
        this.constraintsMet = constraintsMet;
    }

    public Project getProjectAssigned() {
        return projectAssigned;
    }

    public void setProjectAssigned(Project projectAssigned) {
        this.projectAssigned = projectAssigned;
    }
}