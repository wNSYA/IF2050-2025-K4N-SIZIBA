package com.example.if20502025k4nsiziba.controller;

import com.example.if20502025k4nsiziba.SessionManager;
import com.example.if20502025k4nsiziba.model.ChildDAO;
import com.example.if20502025k4nsiziba.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfileController {
    @FXML private Label nameLabel;
    @FXML private Label usernameLabel;
    @FXML private Label passwordLabel;


    ChildDAO childDAO = new ChildDAO();

    @FXML
    public void initialize() {
        User currentUser = SessionManager.getInstance().getCurrentUser();

        if (currentUser != null) {
            nameLabel.setText(currentUser.getName());
            usernameLabel.setText(currentUser.getUsername());
            String masked = "*".repeat(currentUser.getPassword().length());
            passwordLabel.setText(masked); // or mask if preferred
        }
    }

    @FXML
    private void handleEditProfile() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/if20502025k4nsiziba/view/update-user-dialog.fxml"));
            Parent root = loader.load();

            // Get dialog controller and pass the current user
            UpdateUserDialogController dialogController = loader.getController();
            dialogController.setUser(SessionManager.getInstance().getCurrentUser()); // from your static session manager

            // Provide callback after user is updated
            dialogController.setOnUserUpdated(updatedUser -> {
                // Update session
                SessionManager.getInstance().setCurrentUser(updatedUser);
                // Refresh profile UI
                loadUserProfile();
            });

            // Show modal dialog
            Stage dialog = new Stage();
            dialog.setTitle("Update Profile");
            dialog.setScene(new Scene(root));
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadUserProfile() {
        User currentUser = SessionManager.getInstance().getCurrentUser();

        if (currentUser != null) {
            nameLabel.setText(currentUser.getName());
            usernameLabel.setText(currentUser.getUsername());
            String masked = "*".repeat(currentUser.getPassword().length());
            passwordLabel.setText(masked); // or mask if preferred
        }
    }


}
