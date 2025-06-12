package com.example.if20502025k4nsiziba.controller;

import com.example.if20502025k4nsiziba.SessionManager;
import com.example.if20502025k4nsiziba.model.Child;
import com.example.if20502025k4nsiziba.model.ChildDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.List;
import java.util.function.Consumer;

public class DeleteChildDialogController {

    @FXML
    private ListView<Child> childListView;

    @FXML
    private Button deleteButton;

    @FXML
    private Button cancelButton;

    private final ChildDAO childDAO = new ChildDAO();

    private Consumer<Child> onChildDeleted;

    public void initialize() {
        List<Child> children = childDAO.getAllChildrenByUser(SessionManager.getInstance().getCurrentUser().getId());
        childListView.setItems(FXCollections.observableArrayList(children));
    }

    public void setOnChildDeleted(Consumer<Child> callback) {
        this.onChildDeleted = callback;
    }

    @FXML
    private void handleDeleteChild() {
        Child selected = childListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            childDAO.deleteChild(selected.getId());
            if (onChildDeleted != null) {
                onChildDeleted.accept(selected);
            }
            closeWindow();
        }
    }

    @FXML
    private void handleCancel() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) deleteButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleDeleteButtonHover() {
        deleteButton.setStyle(deleteButton.getStyle() + "; -fx-background-color: #c82333;");
    }

    @FXML
    private void handleDeleteButtonExit() {
        deleteButton.setStyle(deleteButton.getStyle().replace("; -fx-background-color: #c82333;", ""));
    }

    @FXML
    private void handleCancelButtonHover() {
        // Similar implementation for cancel button
        cancelButton.setStyle(cancelButton.getStyle() + "; -fx-background-color: #000000;");
    }

    @FXML
    private void handleCancelButtonExit() {
        // Similar implementation for cancel button
        cancelButton.setStyle(cancelButton.getStyle().replace("; -fx-background-color: #000000;", ""));
    }





}
