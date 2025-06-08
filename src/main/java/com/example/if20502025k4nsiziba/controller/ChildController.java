package com.example.if20502025k4nsiziba.controller;

import com.example.if20502025k4nsiziba.model.Child;
import com.example.if20502025k4nsiziba.model.ChildDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.Node;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChildController {
    private final ChildDAO childDAO = new ChildDAO(); // DAO for DB operations
    private final List<Child> childList = new ArrayList<>(); // In-memory list
    private final List<ChildProfileCardController> cardControllers = new ArrayList<>();

    @FXML
    private HBox profileCardContainer;

    @FXML
    private Button addChildButton;

    @FXML
    public void initialize() {
        try {
            childList.clear();
            childList.addAll(childDAO.getAllChildren());

            for (Child child : childList) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/if20502025k4nsiziba/view/child-profile-card.fxml"));
                Node card = loader.load();
                ChildProfileCardController controller = loader.getController();
                controller.setNewChild(child);
                profileCardContainer.getChildren().add(card);
                cardControllers.add(controller);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddChildButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/if20502025k4nsiziba/view/add-child-form.fxml"));
            Node root = loader.load();

            AddChildFormController formController = loader.getController();
            formController.setOnChildCreated(newChild -> {
                try {
                    // Save to DB
                    childDAO.insertChild(newChild);

                    // Add to in-memory list
                    childList.add(newChild);

                    // Load and show new card
                    FXMLLoader cardLoader = new FXMLLoader(getClass().getResource("/com/example/if20502025k4nsiziba/view/child-profile-card.fxml"));
                    Node card = cardLoader.load();
                    ChildProfileCardController cardController = cardLoader.getController();
                    cardController.setNewChild(newChild);
                    profileCardContainer.getChildren().add(card);
                    cardControllers.add(cardController);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            Stage popup = new Stage();
            popup.setTitle("Add New Child");
            popup.setScene(new Scene((Parent) root));
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
