package seng201.tut2.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import models.Rocket;
import seng201.tut2.RocketManager;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SetupScreenController {
    @FXML
    private Label rocketName;
    @FXML
    private Label rocketFuel;
    @FXML
    private Label rocketState;
    @FXML
    private TextField nameTextField;
    @FXML
    private Slider rocketSlider;
    @FXML
    private Button selectRocket1;
    @FXML
    private Button selectRocket2;
    @FXML
    private Button selectRocket3;
    @FXML
    private Button rocketStat1;
    @FXML
    private Button rocketStat2;
    @FXML
    private Button rocketStat3;
    @FXML
    private Button rocketStat4;
    @FXML
    private Button rocketStat5;
    @FXML
    private Button rocketStat6;
    private RocketManager rocketManager;
    private int selectedRocketIndex = -1;
    private final Rocket[] selectedRockets = new Rocket[3];
    public SetupScreenController(RocketManager rocket){
        this.rocketManager = rocket;
    }
    @FXML
    private void onAcceptClicked() {
        rocketManager.setName(nameTextField.getText());
        rocketManager.setRocketList(Arrays.stream(selectedRockets).filter((Objects::nonNull)).toList());
        rocketManager.closeSetupScreen();
    }
    public void initialize(){
        List<Button> selectedRocketButtons = List.of(selectRocket1, selectRocket2, selectRocket3);
        List<Button> rocketButtons = List.of(rocketStat1, rocketStat2, rocketStat3, rocketStat4, rocketStat5, rocketStat6);
        for (int i = 0; i < rocketButtons.size(); i++) {
            int finalI = i; // variables used within lambdas must be final
            rocketButtons.get(i).setOnAction(event -> {
                updateStats(rocketManager.getDefaultRockets().get(finalI));
                selectedRocketIndex = finalI;
                rocketButtons.forEach(button -> {
                    if (button == rocketButtons.get(finalI)) {
                        button.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
                    } else {
                        button.setStyle("");
                    }
                });
            });
        }
        for (int i = 0; i < selectedRocketButtons.size(); i++) {
            int finalI = i; // variables used within lambdas must be final
            selectedRocketButtons.get(i).setOnAction(event -> {
                if (selectedRocketIndex != -1) {
                    selectedRocketButtons.get(finalI).setText(rocketManager.getDefaultRockets().get(selectedRocketIndex).getName());
                    selectedRockets[finalI] = rocketManager.getDefaultRockets().get(selectedRocketIndex);
                }
            });
        }
        rocketSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            for (int i = 0; i < selectedRocketButtons.size(); i++) {
                selectedRocketButtons.get(i).setDisable(i >= newValue.intValue());
            }
        });
    }
    private void updateStats(Rocket rocket) {
        rocketName.setText(rocket.getName());
        rocketFuel.setText(rocket.getFuel());
        rocketState.setText(rocket.getCleanliness());
    }

}
