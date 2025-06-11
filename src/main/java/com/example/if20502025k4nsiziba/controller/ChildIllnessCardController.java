package com.example.if20502025k4nsiziba.controller;

import com.example.if20502025k4nsiziba.model.ChildIllness;
import com.example.if20502025k4nsiziba.model.ChildIllnessDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class ChildIllnessCardController {

    @FXML private Text illnessNameText;
    @FXML private Text illnessDateText;
    @FXML private Text descriptionText;

    private ChildIllness illness;
    private ChildIllnessDAO illnessDAO = new ChildIllnessDAO();
    private Runnable onDataChangedCallback; // To refresh the parent view

    public void setData(ChildIllness illness, Runnable onDataChangedCallback) {
        this.illness = illness;
        this.onDataChangedCallback = onDataChangedCallback;

        illnessNameText.setText(illness.getIllnessName());
        descriptionText.setText(illness.getDescription());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        illnessDateText.setText(illness.getDateOfIllness().format(formatter));
    }

    @FXML
    private void handleDelete() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Deletion");
        alert.setHeaderText("Delete Illness Record");
        alert.setContentText("Are you sure you want to delete the record for '" + illness.getIllnessName() + "'?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            illnessDAO.deleteIllness(illness.getId());
            if (onDataChangedCallback != null) {
                onDataChangedCallback.run(); // Execute the callback to refresh the list
            }
        }
    }
}