package com.example.if20502025k4nsiziba.controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.File;
import java.util.List;

public class SidebarController {

//    @FXML
//    private ImageView logoImage;

    @FXML
    private Button btnChildProfile, btnChildHistory, btnMealPlan, btnFoodNutrition, btnChildIllness;

    private Button activeButton;

    @FXML
    public void initialize() {
//        // Load logo
//        File file = new File("img/logo.png");
//        Image image = new Image(file.toURI().toString());
//        logoImage.setImage(image);

        // Apply animation to buttons
        List<Button> buttons = List.of(btnChildProfile, btnChildHistory, btnMealPlan, btnFoodNutrition, btnChildIllness);
        buttons.forEach(this::applyButtonAnimation);

        // Set default active button
        setActiveButton(btnChildProfile);
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

        button.setOnAction(e -> {
            setActiveButton(button);
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

    @FXML
    private void handleChildProfile(ActionEvent event) {
        System.out.println("Home clicked");
    }

    @FXML
    private void handleChildHistory(ActionEvent event) {
        System.out.println("Settings clicked");
    }
}
