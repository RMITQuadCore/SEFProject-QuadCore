import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SwapTeamMembersController {
    public ListView projectListView;
    public TextField studentOne;
    public TextField studentTwo;
    public Label messageLabel;
    public Button tryButton;
    public Button cancelButton;

    @FXML public void initialize() {
        messageLabel.setText("");
        ArrayList<String> projectList = new ArrayList<String>();

        //creating project list to be given to ListView for display
        projectList.add("Available Project Teams: ");
        for (Team team : Team.allTeams) {
            String students = "";
            for (Student student : team.getStudentsInTeam()) {
                students = students + student.getId() + ",";
            }
            students = students.substring(0, students.length() - 1);// removing ',' after last student
            projectList.add(team.getProjectAssigned().getProjectId() + " : " + students);
        }
        ObservableList<String> projectObservableList = FXCollections.<String>observableArrayList(projectList);

        //adding project list in ListView for display
        projectListView.getItems().addAll(projectObservableList);
    }


    public void tryButtonHandler(ActionEvent actionEvent) {
        // hiding previous window
        tryButton.getScene().getWindow().hide();

        String projectOne = "";
        String projectTwo = "";
        for (Team team : Team.allTeams) {
            for (Student student : team.getStudentsInTeam()) {
                if (student.getId().compareToIgnoreCase(studentOne.getText()) == 0) {
                    projectOne = team.getProjectAssigned().getProjectId();
                }
            }
        }

        if (projectOne == "") {
            messageLabel.setText("Student id : " + studentOne.getText() + " not found!");
            SwapTeamGUI.displayAllTeamsFitness();
        } else {
            for (Team team : Team.allTeams) {
                for (Student student : team.getStudentsInTeam()) {
                    if (student.getId().compareToIgnoreCase(studentTwo.getText()) == 0) {
                        projectTwo = team.getProjectAssigned().getProjectId();
                    }
                }
            }
            if (projectTwo == "") {
                messageLabel.setText("Student id : " + studentTwo.getText() + " not found!");
                SwapTeamGUI.displayAllTeamsFitness();
            } else if (projectOne.compareToIgnoreCase(projectTwo) == 0) {
                messageLabel.setText("Students from same team cannot be swapped!");
                SwapTeamGUI.displayAllTeamsFitness();
            } else {
                //swap members for predicted fitness chart
                //if Project Manager confirms swapping then keep it
                //if Project Manager cancels swapping then in cancel button action,
                // we will swap students again to take them back into their original teams
                membersSwap();

                //calculate predicted team constraints after swapping
                calculateTeamConstraints();

                //GUI for Predicted Team Fitness
                CategoryAxis yAxis = new CategoryAxis();
                ArrayList<String> projectIdsList = new ArrayList<String>();
                for (Team team : Team.allTeams) {
                    projectIdsList.add(team.getProjectAssigned().getProjectId());
                }
                yAxis.setCategories(FXCollections.<String>observableArrayList(projectIdsList));

                yAxis.setLabel("Project Teams");
                NumberAxis xAxis = new NumberAxis();
                xAxis.setLabel("Fitness");

                StackedBarChart<Number, String> fitnessBarChart = new StackedBarChart<>(xAxis, yAxis);
                fitnessBarChart.setTitle("Predicted Team Fitness");

                XYChart.Series<Number, String> constraint1 = new XYChart.Series<>();
                XYChart.Series<Number, String> constraint2 = new XYChart.Series<>();
                XYChart.Series<Number, String> constraint3 = new XYChart.Series<>();

                //first constraint data for chart
                constraint1.setName("Constraint 1");
                SwapTeamGUI.constarintOneSetData(constraint1);

                //Second constraint data for chart
                constraint2.setName("Constraint 2");
                SwapTeamGUI.constarintTwoSetData(constraint2);

                //Second constraint data for chart
                constraint3.setName("Constraint 3");
                SwapTeamGUI.constarintThreeSetData(constraint3);

                //adding all constraints to bar chart
                fitnessBarChart.getData().addAll(constraint1, constraint2, constraint3);
                if (projectIdsList.size() == 2) {
                    fitnessBarChart.setCategoryGap(100);
                }

                Group chart = new Group(fitnessBarChart);
                Button confirmButton = new Button();
                confirmButton.setText("CONFIRM");
                confirmButton.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        confirmButton.getScene().getWindow().hide();
                        SwapTeamGUI.displayAllTeamsFitness();
                    }
                });

                Button cancelButton = new Button();
                cancelButton.setText("CANCEL");
                cancelButton.setLayoutX(80);

                cancelButton.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        confirmButton.getScene().getWindow().hide();
                        //again swap members to original teams
                        membersSwap();
                        //calculate original team fitness
                        calculateTeamConstraints();
                        SwapTeamGUI.displayAllTeamsFitness();
                    }
                });

                chart.getChildren().add(confirmButton);
                chart.getChildren().add(cancelButton);
                Scene scene = new Scene(chart, 550, 400);
                Stage stage = new Stage();
                stage.setTitle("Team Fitness");
                stage.setScene(scene);
                stage.show();
            }
        }
    }

    //Method to calculate all team constraint
    protected static void calculateTeamConstraints() {
        for (Team team : Team.allTeams) {
            ArrayList<Constraint> constraints = new ArrayList<>();

            //checking whether first constraint id met
            if (Constraint.uniquePersonalityConstraintCheck(team.getStudentsInTeam())) {
                constraints.add(ProjectManager.getConstraints().get(0));
            }

            //checking whether second constraint id met
            if (Constraint.requiredPersonalityConstraintCheck(team.getStudentsInTeam())) {
                constraints.add(ProjectManager.getConstraints().get(1));
            }

            //checking whether third constraint id met
            if (Constraint.memberWith5YearExperienceConstraintCheck(team.getStudentsInTeam())) {
                constraints.add(ProjectManager.getConstraints().get(2));
            }

            //add constraint arraylist to team
            team.setConstraintsMet(constraints);

            //calculate team weightage and add in team
            int sumOfWeightage = 0;
            for(Constraint constraint : constraints){
                sumOfWeightage = sumOfWeightage + constraint.getWeightAge();
            }
            team.setTeamFitness(sumOfWeightage);
        }
    }

    //Method to swap team members
    private void membersSwap() {
        loop:
        for (Team teamOne : Team.allTeams) {
            for (Student student1 : teamOne.getStudentsInTeam()) {
                if (student1.getId().compareToIgnoreCase(studentOne.getText()) == 0) {
                    for (Team teamTwo : Team.allTeams) {
                        for (Student student2 : teamTwo.getStudentsInTeam()) {
                            if (student2.getId().compareToIgnoreCase(studentTwo.getText()) == 0) {
                                //swapping two students
                                teamTwo.getStudentsInTeam().add(student1);
                                teamOne.getStudentsInTeam().add(student2);
                                for(Student student: Student.allStudents){
                                    if(student.getId().compareTo(student1.getId()) == 0){
                                        student.setAssignedTeam(teamTwo);
                                    }
                                    if(student.getId().compareTo(student2.getId()) == 0){
                                        student.setAssignedTeam(teamOne);
                                    }
                                }
                                teamOne.getStudentsInTeam().remove(student1);
                                teamTwo.getStudentsInTeam().remove(student2);
                                //break main loop once swapped
                                break loop;
                            }
                        }
                    }
                }
            }
        }
    }

    public void cancelButtonHandler(ActionEvent actionEvent) {
        cancelButton.getScene().getWindow().hide();
        SwapTeamGUI.displayAllTeamsFitness();
    }
}
