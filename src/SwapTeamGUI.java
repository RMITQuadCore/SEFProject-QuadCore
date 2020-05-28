import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.ArrayList;

public class SwapTeamGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage1) throws InterruptedException {
        displayAllTeamsFitness();
    }

    public static void displayAllTeamsFitness(){

        Stage stage = new Stage();
        SwapTeamMembersController.calculateTeamConstraints();

        CategoryAxis yAxis = new CategoryAxis();
        ArrayList<String> projectIds = new ArrayList<String>();
        for(Team t :Team.allTeams) {
            projectIds.add(t.getProjectAssigned().getProjectId());
        }

        yAxis.setCategories(FXCollections .<String> observableArrayList(projectIds));

        yAxis.setLabel("Project Teams");
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Fitness");

        StackedBarChart<Number, String> fitnessBarChart = new StackedBarChart<>(xAxis, yAxis);
        fitnessBarChart.setTitle("Team Fitness");

        XYChart.Series<Number, String> constraint1 = new XYChart.Series<>();
        XYChart.Series<Number, String> constraint2 = new XYChart.Series<>();
        XYChart.Series<Number, String> constraint3 = new XYChart.Series<>();

        //first constraint data for chart
        constraint1.setName("Constraint 1");
        constarintOneSetData(constraint1);

        //second constraint data for chart
        constraint2.setName("Constraint 2");
        constarintTwoSetData(constraint2);

        //third constraint data for chart
        constraint3.setName("Constraint 3");
        constarintThreeSetData(constraint3);

        //adding all constraints to bar chart
        fitnessBarChart.getData().addAll(constraint1, constraint2, constraint3);

        //for two projects set gap between columns for better view
        if(projectIds.size()==2) {
            fitnessBarChart.setCategoryGap(70);
        }

        Group chart = new Group(fitnessBarChart);

        //adding SWAP buttun in GUI
        Button swapButton = new Button();
        swapButton.setText("SWAP");

        //action after clicking on SWAP button
        swapButton.setOnAction(new EventHandler<ActionEvent>() {

            public void handle (ActionEvent event){
                stage.hide();
                try {
                    Stage stage2 = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("SwapTeamMembers.fxml"));
                    Scene scene = new Scene(root);
                    stage2.setTitle("Enter Student IDs");
                    stage2.setScene(scene);
                    stage2.show();
                } catch (IOException e) {
                    System.out.println("Failed to load fxml file.");
                }
            }
        });


        Button closeButton = new Button();
        closeButton.setText("CLOSE");
        closeButton.setLayoutX(60);

        //action after clicking on CLOSE button
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
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

    protected static void constarintThreeSetData(XYChart.Series<Number, String> constraint3) {
        for(int i = 0; i<Team.allTeams.size();i++) {
            for (Constraint c : Team.allTeams.get(i).getConstraintsMet()) {
                if (c.getConstraintId().compareTo("Constraint 3") == 0) {
                    constraint3.getData().add(new XYChart.Data<>(c.getWeightAge(), Team.allTeams.get(i).getProjectAssigned().getProjectId()));
                }
            }
        }
    }

    protected static void constarintTwoSetData(XYChart.Series<Number, String> constraint2) {
        for(int i = 0; i<Team.allTeams.size();i++) {
            for (Constraint c : Team.allTeams.get(i).getConstraintsMet()) {
                if (c.getConstraintId().compareTo("Constraint 2") == 0) {
                    constraint2.getData().add(new XYChart.Data<>(c.getWeightAge(), Team.allTeams.get(i).getProjectAssigned().getProjectId()));
                }
            }
        }
    }

    protected static void constarintOneSetData(XYChart.Series<Number, String> constraint1) {
        for(int i = 0; i<Team.allTeams.size();i++) {
            for (Constraint c : Team.allTeams.get(i).getConstraintsMet()) {
                if (c.getConstraintId().compareTo("Constraint 1") == 0) {
                    constraint1.getData().add(new XYChart.Data<>(c.getWeightAge(), Team.allTeams.get(i).getProjectAssigned().getProjectId()));
                }
            }
        }
    }
}
