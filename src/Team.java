import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Team implements Serializable {
    private static final long serialVersionUID = -1323766548602603946L;
    private String teamID;
    private ArrayList<Student> studentsInTeam = new ArrayList<>();
    private int teamFitness;
    private ArrayList<Constraint> constraintsMet = new ArrayList<>();
    private Project projectAssigned;
    public static ArrayList<Team> allTeams = new ArrayList<>();
    // TODO displayTeam Method should be added

    public Team(String teamID) {
        this.teamID = teamID;
    }

    public String getTeamID() {
        return teamID;
    }

    public void setTeamID(String teamID) {
        this.teamID = teamID;
    }

    public ArrayList<Student> getStudentsInTeam() {
        return studentsInTeam;
    }

    public void setStudentsInTeam(ArrayList<Student> studentsInTeam) {
        this.studentsInTeam = studentsInTeam;
    }

    public int getTeamFitness() {
        return teamFitness;
    }

    public void setTeamFitness(int teamFitness) {
        this.teamFitness = teamFitness;
    }

    public ArrayList<Constraint> getConstraintsMet() {
        return constraintsMet;
    }

    public void setConstraintsMet(ArrayList<Constraint> constraintsMet) {
        this.constraintsMet = constraintsMet;
    }

    public Project getProjectAssigned() {
        return projectAssigned;
    }

    public void setProjectAssigned(Project projectAssigned) {
        this.projectAssigned = projectAssigned;
    }

}
