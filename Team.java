import java.util.HashSet;
import java.util.Set;

public class Team {
  

        private String teamID;
        private Set<Student> studentsInTeam = new HashSet<>();
        private int teamFitness;
        private Set<Constraint> constraintsMet = new HashSet<>();
        private Project projectAssigned;

        public Team(String teamID)
        {
            this.teamID = teamID;
        }

        public String getTeamID() {
            return teamID;
        }

        public void setTeamID(String teamID) {
            this.teamID = teamID;
        }

        public Set<Student> getStudentsInTeam() {
            return studentsInTeam;
        }

        public void setStudentsInTeam(Set<Student> studentsInTeam) {
            this.studentsInTeam = studentsInTeam;
        }

        public int getTeamFitness() {
            return teamFitness;
        }

        public void setTeamFitness(int teamFitness) {
            this.teamFitness = teamFitness;
        }

        public Set<Constraint> getConstraintsMet() {
            return constraintsMet;
        }

        public void setConstraintsMet(Set<Constraint> constraintsMet) {
            this.constraintsMet = constraintsMet;
        }

        public Project getProjectAssigned() {
            return projectAssigned;
        }

        public void setProjectAssigned(Project projectAssigned) {
            this.projectAssigned = projectAssigned;
        }
    

}
