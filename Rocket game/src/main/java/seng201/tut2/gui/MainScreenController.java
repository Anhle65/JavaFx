package seng201.tut2.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import models.Rocket;
import seng201.tut2.RocketManager;

import java.util.ArrayList;
import java.util.List;


public class MainScreenController {
    @FXML
    private Label playerName;
    @FXML
    private Label rocket1State;
    @FXML
    private Label rocket2State;
    @FXML
    private Label rocket3State;
    @FXML
    private Label rocket1Fuel;
    @FXML
    private Label rocket2Fuel;
    @FXML
    private Label rocket3Fuel;
    @FXML
    private Label rocket1Name;
    @FXML
    private Label rocket2Name;
    @FXML
    private Label rocket3Name;
    @FXML
    private Button rocket1;
    @FXML
    private Button rocket2;
    @FXML
    private Button rocket3;
    @FXML
    private Button cleaningButton;
    @FXML
    private Button refuelingButton;
    private int selectedRocketIndex = -1;
    private Rocket selectedRockets;
    private RocketManager rocketManager;
    private List<Label> rocketNames;
    private List<Label> rocketFuels;
    private List<Label> rocketStates;
    public MainScreenController(RocketManager rkManager) {
        this.rocketManager = rkManager;
    }
    @FXML
    public void initialize() {
        playerName.setText(rocketManager.getName());
        rocketNames = List.of(rocket1Name, rocket2Name, rocket3Name);
        rocketFuels = List.of(rocket1Fuel, rocket2Fuel, rocket3Fuel);
        rocketStates = List.of(rocket1State, rocket2State, rocket3State);
        List<Button> rocketButtons = List.of(rocket1, rocket2, rocket3);
        for (int i = 0; i < rocketManager.getRocketList().size(); i++) {
            int finalI = i; // variables used within lambdas must be final
            rocketButtons.get(i).setOnAction(event -> {
                updateStats(rocketManager.getRocketList().get(finalI), rocketNames.get(finalI), rocketFuels.get(finalI), rocketStates.get(finalI));
                selectedRocketIndex = finalI;
                rocketButtons.forEach(button -> {
                    rocketButtons.get(finalI).setText(rocketManager.getRocketList().get(finalI).getName());
                    if (button == rocketButtons.get(finalI)) {
                        selectedRockets = rocketManager.getRocketList().get(finalI);
                        button.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
                    } else {
                        button.setStyle("");
                    }
                });
            });
        }
    }
    private void updateStats(Rocket rocket, Label rocketName, Label rocketFuel, Label rocketState) {
        rocketName.setText(rocket.getName());
        rocketFuel.setText(rocket.getFuel());
        rocketState.setText(rocket.getCleanliness());
    }
    @FXML
    private void onQuitClicked(){
        rocketManager.closeMainScreen();
    }
    @FXML
    private void onCleanedClicked(){
        selectedRockets.setCleanliness("Sparkling");
        updateStats(selectedRockets, rocketNames.get(selectedRocketIndex), rocketFuels.get(selectedRocketIndex), rocketStates.get(selectedRocketIndex));
    }
    @FXML
    private void onRefueledClicked(){
        selectedRockets.setFuel("Full");
        updateStats(selectedRockets, rocketNames.get(selectedRocketIndex), rocketFuels.get(selectedRocketIndex), rocketStates.get(selectedRocketIndex));

    }
}
