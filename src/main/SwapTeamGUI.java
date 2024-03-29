package main;
import model.*;

import controller.SwapTeamMembersController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class SwapTeamGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stageOne) throws InterruptedException {
        displayAllTeamsFitness();
    }

    /**
     * Method to display all teams fitness in GUI
     */
    public static void displayAllTeamsFitness() {

        Stage stage = new Stage();
        SwapTeamMembersController.calculateTeamConstraints();

        CategoryAxis yAxis = new CategoryAxis();
        ArrayList < String > projectIds = new ArrayList < String > ();
        for (Team team: Team.allTeams) {
            projectIds.add(team.getProjectAssigned().getProjectId());
        }

        yAxis.setCategories(FXCollections. < String > observableArrayList(projectIds));

        yAxis.setLabel("Project Teams");
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Fitness");

        StackedBarChart < Number, String > fitnessBarChart = new StackedBarChart < > (xAxis, yAxis);
        fitnessBarChart.setTitle("Team Fitness");

        XYChart.Series < Number, String > constraintOne = new XYChart.Series < > ();
        XYChart.Series < Number, String > constraintTwo = new XYChart.Series < > ();
        XYChart.Series < Number, String > constraintThree = new XYChart.Series < > ();

        //first constraint data for chart
        constraintOne.setName("Constraint 1");
        constraintSetData(constraintOne,"Constraint 1");

        //second constraint data for chart
        constraintTwo.setName("Constraint 2");
        constraintSetData(constraintTwo,"Constraint 2");

        //third constraint data for chart
        constraintThree.setName("Constraint 3");
        constraintSetData(constraintThree,"Constraint 3");

        //adding all constraints to bar chart
        fitnessBarChart.getData().addAll(constraintOne, constraintTwo, constraintThree);

        //for two projects set gap between columns for better view
        if (projectIds.size() == 2) {
            fitnessBarChart.setCategoryGap(70);
        }

        Group chart = new Group(fitnessBarChart);

        //adding SWAP button in GUI
        Button swapButton = new Button();
        swapButton.setText("SWAP");

        //action after clicking on SWAP button
        swapButton.setOnAction(new EventHandler < ActionEvent > () {

            public void handle(ActionEvent event) {
                stage.hide();
                try {
                    Stage stageTwo = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/view/SwapTeamMembers.fxml"));
                    Scene scene = new Scene(root);
                    stageTwo.setTitle("Enter Student IDs");
                    stageTwo.setScene(scene);
                    stageTwo.show();
                } catch (IOException e) {
                    System.out.println("Failed to load fxml file.");
                }
            }
        });


        Button closeButton = new Button();
        closeButton.setText("CLOSE");
        closeButton.setLayoutX(60);

        //action after clicking on CLOSE button
        closeButton.setOnAction(new EventHandler < ActionEvent > () {
            public void handle(ActionEvent event) {
                stage.close();
            }
        });

        chart.getChildren().add(swapButton);
        chart.getChildren().add(closeButton);
        Scene scene = new Scene(chart, 550, 400);
        stage.setTitle("Team Fitness");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method to set constraint data for chart according to constraint Id
     */
    public static void constraintSetData(XYChart.Series<Number, String> constraintNumber, String constraintId) {
        for(int i = 0; i < Team.allTeams.size(); i++) {
            for (Constraint constraint : Team.allTeams.get(i).getConstraintsMet()) {
                if (constraint.getConstraintId().compareTo(constraintId) == 0) {
                    constraintNumber.getData().add(new XYChart.Data<>(constraint.getWeightAge(), Team.allTeams.get(i).getProjectAssigned().getProjectId()));
                }
            }
        }
    }
}