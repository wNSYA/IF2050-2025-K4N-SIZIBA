package com.example.if20502025k4nsiziba.controller;

import com.example.if20502025k4nsiziba.model.User;
import com.example.if20502025k4nsiziba.model.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class UpdateUserDialogController {

    @FXML private TextField nameField;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField currentPasswordField;


    private User user;
    private Consumer<User> onUserUpdated;

    public void setUser(User user) {
        this.user = user;
        nameField.setText(user.getName());
        usernameField.setText(user.getUsername());
    }

    public void setOnUserUpdated(Consumer<User> callback) {
        this.onUserUpdated = callback;
    }

    @FXML
    private void handleCancel() {
        closeWindow();
    }

    @FXML
    private void handleUpdate() {
        String enteredCurrentPassword = currentPasswordField.getText();
        if (!enteredCurrentPassword.equals(user.getPassword())) {
            // Show warning or error — use alert or label if needed
            showAlert("Incorrect current password. Cannot update.");
            return;
        }

        user.setName(nameField.getText());
        user.setUsername(usernameField.getText());

        String newPassword = passwordField.getText();
        if (!newPassword.isEmpty()) {
            user.setPassword(newPassword);
        }

        if (UserDAO.updateUser(user)) {
            if (onUserUpdated != null) {
                onUserUpdated.accept(user);
            }
        }

        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User Notification");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
