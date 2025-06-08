package com.example.if20502025k4nsiziba.controller;

import com.example.if20502025k4nsiziba.model.Child;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.function.Consumer;

public class AddChildFormController {

    @FXML private TextField txtChildName;
    @FXML private DatePicker datePickerDOB;
    @FXML private ChoiceBox<String> choiceGender;
    @FXML private TextField txtHeight;
    @FXML private TextField txtWeight;
    @FXML private TextField txtHeadCirc;
    @FXML private TextField txtAbdCirc;
    @FXML private TextField txtHandCirc;

    private Consumer<Child> onChildCreated;

    public void setOnChildCreated(Consumer<Child> callback) {
        this.onChildCreated = callback;
    }

    @FXML
    private void initialize() {
        // Initialize gender choice options
        choiceGender.getItems().addAll("Male", "Female");
        choiceGender.setValue("Male");
    }

    @FXML
    private void handleSubmit() {
        try {
            String name = txtChildName.getText();
            if (name == null || name.isBlank()) throw new IllegalArgumentException();

            LocalDate birthDate = datePickerDOB.getValue();
            if (birthDate == null) throw new IllegalArgumentException();

            String genderStr = choiceGender.getValue();
            if (genderStr == null) throw new IllegalArgumentException();
            boolean isMale = genderStr.equalsIgnoreCase("male");

            float height = Float.parseFloat(txtHeight.getText());
            float weight = Float.parseFloat(txtWeight.getText());
            float headCirc = Float.parseFloat(txtHeadCirc.getText());
            float abdCirc = Float.parseFloat(txtAbdCirc.getText());
            float handCirc = Float.parseFloat(txtHandCirc.getText());

            Child child = new Child(name, isMale, birthDate, height, weight, headCirc, abdCirc, handCirc);

            if (onChildCreated != null) {
                onChildCreated.accept(child); // Let parent controller handle DB and UI
            }

            ((Stage) txtChildName.getScene().getWindow()).close();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Please fill all fields correctly.").showAndWait();
            e.printStackTrace();
        }
    }
}
