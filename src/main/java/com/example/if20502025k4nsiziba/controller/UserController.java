package com.example.if20502025k4nsiziba.controller;

import com.example.if20502025k4nsiziba.model.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
            // TODO: Navigate to main dashboard or home screen
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
