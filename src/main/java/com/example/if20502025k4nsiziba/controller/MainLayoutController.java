package com.example.if20502025k4nsiziba.controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.util.List;

public class MainLayoutController {

    @FXML private AnchorPane contentArea;
    @FXML private Button btnChildProfile, btnProfile, btnMealPlan, btnChildIllness;

    private Button activeButton;

    @FXML
    public void initialize() {
        setView("/com/example/if20502025k4nsiziba/view/child-profile-view.fxml");
        // Apply animation to buttons
        List<Button> buttons = List.of(btnChildProfile, btnProfile, btnMealPlan, btnChildIllness);
        buttons.forEach(this::applyButtonAnimation);

        // Set default active button
        setActiveButton(btnChildProfile);
    }

    public void setView(String fxmlPath) {
        try {
            Node node = FXMLLoader.load(getClass().getResource(fxmlPath));
            contentArea.getChildren().setAll(node);
            AnchorPane.setTopAnchor(node, 0.0);
            AnchorPane.setBottomAnchor(node, 0.0);
            AnchorPane.setLeftAnchor(node, 0.0);
            AnchorPane.setRightAnchor(node, 0.0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void applyButtonAnimation(Button button) {
        TranslateTransition enterTransition = new TranslateTransition(Duration.millis(200), button);
        enterTransition.setToX(10);

        TranslateTransition exitTransition = new TranslateTransition(Duration.millis(200), button);
        exitTransition.setToX(0);

        button.setOnMouseEntered(e -> {
            if (button != activeButton) {
                button.getStyleClass().add("hovered");
                enterTransition.playFromStart();
            }
        });

        button.setOnMouseExited(e -> {
            if (button != activeButton) {
                button.getStyleClass().remove("hovered");
                exitTransition.playFromStart();
            }
        });
    }

    private void setActiveButton(Button button) {
        if (activeButton != null) {
            activeButton.getStyleClass().removeAll("selected", "hovered");
            activeButton.setTranslateX(0);
        }
        activeButton = button;
        if (!activeButton.getStyleClass().contains("selected")) {
            activeButton.getStyleClass().add("selected");
        }
        activeButton.setTranslateX(10); // Keep it shifted
    }




    @FXML private void handleChildProfile(ActionEvent event) {
        setActiveButton(btnChildProfile);
        setView("/com/example/if20502025k4nsiziba/view/child-profile-view.fxml");
    }

    @FXML private void handleBudget(ActionEvent event) {
        setActiveButton(btnMealPlan);
        setView("/com/example/if20502025k4nsiziba/view/Budgetview.fxml");
    }

    @FXML private void handleChildIllness(ActionEvent event) {
        setActiveButton(btnChildIllness);
        setView("/com/example/if20502025k4nsiziba/view/child-illness-view.fxml");
    }

    @FXML private void handleProfile(ActionEvent event){
        setActiveButton(btnProfile);
        System.out.println("profile clicked");
    }


}
