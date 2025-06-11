package com.example.if20502025k4nsiziba.controller;

import com.example.if20502025k4nsiziba.SessionManager;
import com.example.if20502025k4nsiziba.model.User;
import com.example.if20502025k4nsiziba.model.UserDAO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;


    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Please fill in both username and password.");
            return;
        }

        User user = UserDAO.login(username, password);
        if (user!=null) {
            showAlert("Login successful!");
            SessionManager.getInstance().login(user);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/if20502025k4nsiziba/view/MainLayout.fxml"));
                Scene mainScene = new Scene(loader.load());

                Stage stage = (Stage) usernameField.getScene().getWindow();  // Get current window
                stage.setScene(mainScene);
                stage.setTitle("SIZIBA Dashboard");
                stage.setResizable(true);
                stage.centerOnScreen();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert("Invalid username or password.");
        }
    }

    @FXML
    private void toRegister(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/if20502025k4nsiziba/view/register.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);

        // Force layout calculation
        stage.sizeToScene();

        // Now center the stage
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
    }


    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User Notification");
        alert.setContentText(message);
        alert.showAndWait();
    }
}