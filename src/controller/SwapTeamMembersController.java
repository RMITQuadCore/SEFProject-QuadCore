package controller;
import model.*;
import main.*;
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
    @FXML
    private ListView projectListView;
    @FXML
    private TextField studentOne;
    @FXML
    private TextField studentTwo;
    @FXML
    private Label messageLabel;
    @FXML
    private Button tryButton;
    @FXML
    private Button cancelButton;

    //Method to calculate all team constraint
    @FXML
    public static void calculateTeamConstraints() {
        for (Team team: Team.allTeams) {
            ArrayList <Constraint> constraints = new ArrayList < > ();

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
            for (Constraint constraint: constraints) {
                sumOfWeightage = sumOfWeightage + constraint.getWeightAge();
            }
            team.setTeamFitness(sumOfWeightage);
        }
    }

    @FXML
    public void initialize() {
        messageLabel.setText("");
        ArrayList < String > projectList = new ArrayList < String > ();

        //creating project list to be given to ListView for display
        projectList.add("Available Project Teams: ");
        for (Team team: Team.allTeams) {
            String students = "";
            for (Student student: team.getStudentsInTeam()) {
                students = students + student.getId() + ",";
            }
            students = students.substring(0, students.length() - 1); // removing ',' after last student
            projectList.add(team.getProjectAssigned().getProjectId() + " : " + students);
        }
        ObservableList < String > projectObservableList = FXCollections. < String > observableArrayList(projectList);

        //adding project list in ListView for display
        projectListView.getItems().addAll(projectObservableList);
    }

    @FXML
    private void tryButtonHandler(ActionEvent actionEvent) {
        // hiding previous window
        tryButton.getScene().getWindow().hide();

        String projectOne = "";
        String projectTwo = "";

        //checking if student one is present and has project assigned
        for (Team team: Team.allTeams) {
            for (Student student: team.getStudentsInTeam()) {
                if (student.getId().compareToIgnoreCase(studentOne.getText()) == 0) {
                    projectOne = team.getProjectAssigned().getProjectId();
                }
            }
        }

        if (projectOne == "") {
            messageLabel.setText("Student id : " + studentOne.getText() + " not found!");
            SwapTeamGUI.displayAllTeamsFitness();
        } else {
            //checking if student two is present and has project assigned
            for (Team team: Team.allTeams) {
                for (Student student: team.getStudentsInTeam()) {
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
                ArrayList < String > projectIdsList = new ArrayList < String > ();
                for (Team team: Team.allTeams) {
                    projectIdsList.add(team.getProjectAssigned().getProjectId());
                }
                yAxis.setCategories(FXCollections. < String > observableArrayList(projectIdsList));

                yAxis.setLabel("Project Teams");
                NumberAxis xAxis = new NumberAxis();
                xAxis.setLabel("Fitness");

                StackedBarChart < Number, String > fitnessBarChart = new StackedBarChart < > (xAxis, yAxis);
                fitnessBarChart.setTitle("Predicted Team Fitness");

                XYChart.Series < Number, String > constraintOne = new XYChart.Series < > ();
                XYChart.Series < Number, String > constraintTwo = new XYChart.Series < > ();
                XYChart.Series < Number, String > constraintThree = new XYChart.Series < > ();

                //first constraint data for chart
                constraintOne.setName("Constraint 1");
                SwapTeamGUI.constraintOneSetData(constraintOne);// Access changed to public

                //Second constraint data for chart
                constraintTwo.setName("Constraint 2");
                SwapTeamGUI.constraintTwoSetData(constraintTwo);

                //Second constraint data for chart
                constraintThree.setName("Constraint 3");
                SwapTeamGUI.constraintThreeSetData(constraintThree);

                //adding all constraints to bar chart
                fitnessBarChart.getData().addAll(constraintOne, constraintTwo, constraintThree);
                if (projectIdsList.size() == 2) {
                    fitnessBarChart.setCategoryGap(80);
                }

                Group chart = new Group(fitnessBarChart);
                Button confirmButton = new Button();
                confirmButton.setText("CONFIRM");
                confirmButton.setOnAction(new EventHandler < ActionEvent > () {
                    public void handle(ActionEvent event) {
                        //if Project Manager confirms swapping, swapping will retain
                        // and he will be directed to main GUI
                        confirmButton.getScene().getWindow().hide();
                        SwapTeamGUI.displayAllTeamsFitness();
                    }
                });

                Button cancelButton = new Button();
                cancelButton.setText("CANCEL");
                cancelButton.setLayoutX(80);

                cancelButton.setOnAction(new EventHandler < ActionEvent > () {
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

    //Method to swap team members
    @FXML
    private void membersSwap() {
        loop: for (Team teamOne: Team.allTeams) {
            for (Student firstStudent: teamOne.getStudentsInTeam()) {
                if (firstStudent.getId().compareToIgnoreCase(studentOne.getText()) == 0) {
                    for (Team teamTwo: Team.allTeams) {
                        for (Student secondStudent: teamTwo.getStudentsInTeam()) {
                            if (secondStudent.getId().compareToIgnoreCase(studentTwo.getText()) == 0) {
                                //swapping two students
                                teamTwo.getStudentsInTeam().add(firstStudent);
                                teamOne.getStudentsInTeam().add(secondStudent);
                                //changing assigned teams for swapped students
                                for (Student student: Student.allStudents) {
                                    if (student.getId().compareTo(firstStudent.getId()) == 0) {
                                        student.setAssignedTeam(teamTwo);
                                    }
                                    if (student.getId().compareTo(secondStudent.getId()) == 0) {
                                        student.setAssignedTeam(teamOne);
                                    }
                                }
                                teamOne.getStudentsInTeam().remove(firstStudent);
                                teamTwo.getStudentsInTeam().remove(secondStudent);
                                //break main loop once swapped
                                break loop;
                            }
                        }
                    }
                }
            }
        }
    }

    //Method to return back to Main GUI if CANCEL is pressed
    @FXML
    private void cancelButtonHandler(ActionEvent actionEvent) {
        cancelButton.getScene().getWindow().hide();
        SwapTeamGUI.displayAllTeamsFitness();
    }
}