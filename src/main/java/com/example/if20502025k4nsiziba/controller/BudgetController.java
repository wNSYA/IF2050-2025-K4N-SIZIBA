package com.example.if20502025k4nsiziba.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.example.if20502025k4nsiziba.model.Food;

import java.util.List;
import java.util.stream.Collectors;

public class BudgetController {

    @FXML
    private ComboBox<String> mealTypeComboBox;

    @FXML
    private TextField budgetField;

    @FXML
    private VBox resultBox; // Keep for compatibility

    @FXML
    private VBox resultContainer; // New container for modern UI

    private final FoodController foodController = new FoodController();

    @FXML
    public void handleGetRecommendation() {
        // Clear both result containers
        if (resultBox != null) {
            resultBox.getChildren().clear();
        }
        if (resultContainer != null) {
            resultContainer.getChildren().clear();
        }

        try {
            String selectedMeal = mealTypeComboBox.getValue();
            if (selectedMeal == null || selectedMeal.isEmpty()) {
                showErrorMessage(" Pilih jenis makan terlebih dahulu!");
                return;
            }

            String budgetText = budgetField.getText().trim();
            if (budgetText.isEmpty()) {
                showErrorMessage(" Masukkan budget terlebih dahulu!");
                return;
            }

            double budget = Double.parseDouble(budgetText);
            if (budget <= 0) {
                showErrorMessage(" Budget harus lebih dari 0!");
                return;
            }

            List<Food> allFoods = foodController.getDummyFoods();
            List<Food> filtered = allFoods.stream()
                    .filter(f -> f.getMealTime().equalsIgnoreCase(selectedMeal) && f.getPrice() <= budget)
                    .sorted((f1, f2) -> Double.compare(f1.getPrice(), f2.getPrice()))
                    .collect(Collectors.toList());

            if (filtered.isEmpty()) {
                showNoResultsMessage(" Tidak ada makanan yang cocok dengan budget Anda.\nCoba tingkatkan budget atau pilih jenis makan lain.");
            } else {
                showResults(filtered, selectedMeal, budget);
            }

        } catch (NumberFormatException e) {
            showErrorMessage(" Budget harus berupa angka yang valid!");
        } catch (Exception e) {
            showErrorMessage(" Terjadi kesalahan: " + e.getMessage());
        }
    }

    private void showResults(List<Food> foods, String mealType, double budget) {
        VBox container = getResultContainer();

        // Results Header
        VBox headerBox = createResultHeader(foods.size(), mealType, budget);
        container.getChildren().add(headerBox);

        // Food Cards Container
        VBox foodCardsContainer = new VBox(15.0);
        foodCardsContainer.setStyle("-fx-background-color: transparent;");

        for (Food food : foods) {
            HBox foodCard = createFoodCard(food);
            foodCardsContainer.getChildren().add(foodCard);
        }

        container.getChildren().add(foodCardsContainer);
    }

    private VBox createResultHeader(int count, String mealType, double budget) {
        VBox headerBox = new VBox(10.0);
        headerBox.setAlignment(Pos.CENTER);
        headerBox.setStyle(
                "-fx-background-color: white; " +
                        "-fx-background-radius: 15; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 2);"
        );
        headerBox.setPadding(new Insets(20));

        Text titleText = new Text("Rekomendasi Makanan " + mealType);
        titleText.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-fill: #2C3E50;");

        Text countText = new Text(count + " makanan ditemukan untuk budget Rp " + String.format("%,.0f", budget));
        countText.setStyle("-fx-font-size: 14px; -fx-fill: #7F8C8D;");

        headerBox.getChildren().addAll(titleText, countText);
        return headerBox;
    }

    private HBox createFoodCard(Food food) {
        HBox card = new HBox(15.0);
        card.setAlignment(Pos.CENTER_LEFT);
        card.setStyle(
                "-fx-background-color: white; " +
                        "-fx-background-radius: 12; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 8, 0, 0, 2); " +
                        "-fx-border-color: #F1F3F4; " +
                        "-fx-border-radius: 12; " +
                        "-fx-border-width: 1;"
        );
        card.setPadding(new Insets(15, 20, 15, 20));

        // Food emoji/icon
        Label iconLabel = new Label(getFoodEmoji(food.getName()));
        iconLabel.setStyle(
                "-fx-font-size: 28px; " +
                        "-fx-background-color: #F8F9FA; " +
                        "-fx-background-radius: 20; " +
                        "-fx-alignment: center;"
        );
        iconLabel.setPrefSize(50, 50);
        iconLabel.setAlignment(Pos.CENTER);

        // Food details
        VBox detailsBox = new VBox(5.0);
        detailsBox.setAlignment(Pos.CENTER_LEFT);

        Label nameLabel = new Label(food.getName());
        nameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2C3E50;");

        Label mealTypeLabel = new Label("• " + food.getMealTime());
        mealTypeLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #95A5A6;");

        detailsBox.getChildren().addAll(nameLabel, mealTypeLabel);

        // Price
        Label priceLabel = new Label("Rp " + String.format("%,.0f", food.getPrice()));
        priceLabel.setStyle(
                "-fx-font-size: 16px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-text-fill: #27AE60; " +
                        "-fx-background-color: #E8F8F5; " +
                        "-fx-background-radius: 8; " +
                        "-fx-padding: 8 12 8 12;"
        );

        // Spacer
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        card.getChildren().add(iconLabel);
        card.getChildren().add(detailsBox);
        card.getChildren().add(spacer);
        card.getChildren().add(priceLabel);
        return card;
    }

    private String getFoodEmoji(String foodName) {
        String name = foodName.toLowerCase();
//        if (name.contains("nasi") || name.contains("rice")) return "🍚";
//        if (name.contains("ayam") || name.contains("chicken")) return "🍗";
//        if (name.contains("soto") || name.contains("soup")) return "🍲";
//        if (name.contains("bakso") || name.contains("meatball")) return "🍜";
//        if (name.contains("mie") || name.contains("noodle")) return "🍝";
//        if (name.contains("bubur") || name.contains("porridge")) return "🥣";
//        if (name.contains("sate") || name.contains("satay")) return "🍢";
//        if (name.contains("gado")) return "🥗";
//        if (name.contains("pecel")) return "🥙";
        return "🍽";
    }

    private void showErrorMessage(String message) {
        VBox container = getResultContainer();
        VBox errorBox = createMessageBox(message, "#E74C3C", "#FADBD8");
        container.getChildren().add(errorBox);
    }

    private void showNoResultsMessage(String message) {
        VBox container = getResultContainer();
        VBox noResultBox = createMessageBox(message, "#F39C12", "#FEF9E7");
        container.getChildren().add(noResultBox);
    }

    private VBox createMessageBox(String message, String textColor, String bgColor) {
        VBox messageBox = new VBox(10.0);
        messageBox.setAlignment(Pos.CENTER);
        messageBox.setStyle(
                "-fx-background-color: " + bgColor + "; " +
                        "-fx-background-radius: 12; " +
                        "-fx-border-color: " + textColor + "; " +
                        "-fx-border-radius: 12; " +
                        "-fx-border-width: 1;"
        );
        messageBox.setPadding(new Insets(20));

        Text messageText = new Text(message);
        messageText.setStyle(
                "-fx-font-size: 14px; " +
                        "-fx-fill: " + textColor + "; " +
                        "-fx-text-alignment: center;"
        );

        messageBox.getChildren().add(messageText);
        return messageBox;
    }

    private VBox getResultContainer() {
        // Use new result container if available, fallback to old one
        if (resultContainer != null) {
            return resultContainer;
        } else if (resultBox != null) {
            return resultBox;
        } else {
            // Create temporary container if neither exists
            VBox tempContainer = new VBox(10.0);
            return tempContainer;
        }
    }
}
