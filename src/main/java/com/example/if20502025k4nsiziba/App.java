package com.example.if20502025k4nsiziba;

import com.example.if20502025k4nsiziba.database.DatabaseHelper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view/hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("SIZIBA");
//        stage.setScene(scene);
//        stage.show();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/UserView.fxml"));
//        Parent root = loader.load();
//        Scene scene = new Scene(root);
//        stage.setTitle("User Manager");
//        stage.setScene(scene);
//        stage.show();

        DatabaseHelper.initializeDatabase();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/child-profile-view.fxml"));
        Scene scene = new Scene(loader.load());

        // Get screen size
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

        // Example: set stage to 80% of screen size
        primaryStage.setWidth(screenWidth * 0.9);
        primaryStage.setHeight(screenHeight * 0.9);

        // Optional: center the stage
        primaryStage.setX((screenWidth - primaryStage.getWidth()) / 2);
        primaryStage.setY((screenHeight - primaryStage.getHeight()) / 2);

        primaryStage.setTitle("SIZIBA");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}