package com.example.if20502025k4nsiziba.controller;

import com.example.if20502025k4nsiziba.model.ChildIllness;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.function.Consumer;

public class AddIllnessFormController {

    @FXML private TextField txtIllnessName;
    @FXML private DatePicker datePickerIllness;
    @FXML private TextArea txtDescription;

    private int childId;
    private Consumer<ChildIllness> onIllnessCreated;

    public void setChildId(int childId) {
        this.childId = childId;
    }

    public void setOnIllnessCreated(Consumer<ChildIllness> callback) {
        this.onIllnessCreated = callback;
    }

    @FXML
    private void handleSave() {
        try {
            String illnessName = txtIllnessName.getText();
            LocalDate date = datePickerIllness.getValue();
            String description = txtDescription.getText();

            if (illnessName == null || illnessName.isBlank() || date == null) {
                throw new IllegalArgumentException("Required fields are missing.");
            }

            ChildIllness newIllness = new ChildIllness(childId, illnessName, description, date);

            if (onIllnessCreated != null) {
                onIllnessCreated.accept(newIllness);
            }

            // Close the form
            ((Stage) txtIllnessName.getScene().getWindow()).close();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Please fill all required fields correctly.").showAndWait();
            e.printStackTrace();
        }
    }
}