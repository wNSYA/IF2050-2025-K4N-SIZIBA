package com.example.if20502025k4nsiziba.controller;

import com.example.if20502025k4nsiziba.model.Child;
import com.example.if20502025k4nsiziba.model.ChildDAO;
import com.example.if20502025k4nsiziba.model.ChildIllness;
import com.example.if20502025k4nsiziba.model.ChildIllnessDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ChildIllnessController {

    @FXML private Text pageHeader;
    @FXML private Button addIllnessButton;
    @FXML private VBox illnessCardContainer;
    @FXML private Label noDataLabel;
    @FXML private ListView<Child> childListView; // ListView to display children

    private Child selectedChild;
    private final ChildDAO childDAO = new ChildDAO();
    private final ChildIllnessDAO illnessDAO = new ChildIllnessDAO();

    @FXML
    public void initialize() {
        // Initially disable button and set placeholder text
        addIllnessButton.setDisable(true);
        pageHeader.setText("Select a Child to View History");

        // Load children and populate the ListView
        populateChildList();

        // Add a listener to handle selection changes
        childListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectedChild = newValue;
                pageHeader.setText("Illness History for " + selectedChild.getName());
                addIllnessButton.setDisable(false); // Enable the button
                loadIllnessDataForSelectedChild();
            }
        });
    }

    private void populateChildList() {
        // Set a custom cell factory to display child names nicely
        childListView.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Child item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });
        // Get all children from DAO and add to the list
        childListView.getItems().setAll(childDAO.getAllChildren());
    }

    private void loadIllnessDataForSelectedChild() {
        illnessCardContainer.getChildren().clear();
        if (selectedChild == null) return;

        List<ChildIllness> illnesses = illnessDAO.getIllnessesByChildId(selectedChild.getId());

        if (illnesses.isEmpty()) {
            noDataLabel.setText("No illness history recorded for " + selectedChild.getName());
            noDataLabel.setVisible(true);
        } else {
            noDataLabel.setVisible(false);
            for (ChildIllness illness : illnesses) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/if20502025k4nsiziba/view/child-illness-card.fxml"));
                    Node card = loader.load();
                    ChildIllnessCardController controller = loader.getController();
                    // Pass a callback to refresh data when an item is deleted
                    controller.setData(illness, this::loadIllnessDataForSelectedChild);
                    illnessCardContainer.getChildren().add(card);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    private void handleAddIllnessButton() {
        if (selectedChild == null) return; // Should not happen if button is disabled

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/if20502025k4nsiziba/view/add-illness-form.fxml"));
            Parent root = loader.load();

            AddIllnessFormController formController = loader.getController();
            formController.setChildId(selectedChild.getId());

            // Set callback to handle new illness creation
            formController.setOnIllnessCreated(newIllness -> {
                illnessDAO.insertIllness(newIllness);
                loadIllnessDataForSelectedChild(); // Refresh the view for the current child
            });

            Stage popup = new Stage();
            popup.setTitle("Add New Illness Record for " + selectedChild.getName());
            popup.setScene(new Scene(root));
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}