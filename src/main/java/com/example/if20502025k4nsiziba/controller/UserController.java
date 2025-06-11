package com.example.if20502025k4nsiziba.controller;

import com.example.if20502025k4nsiziba.model.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class UserController {

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

        boolean success = UserDAO.login(username, password);
        if (success) {
            showAlert("Login successful!");
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
    private void handleRegister() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Please fill in both username and password.");
            return;
        }

        boolean registered = UserDAO.register(username, password);
        if (registered) {
            showAlert("Registration successful. You can now log in.");
        } else {
            showAlert("Registration failed. Username might already exist.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User Notification");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
