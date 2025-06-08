module com.example.if20502025k4nsiziba {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.if20502025k4nsiziba to javafx.fxml;
    exports com.example.if20502025k4nsiziba;
    exports com.example.if20502025k4nsiziba.controller;
    opens com.example.if20502025k4nsiziba.controller to javafx.fxml;
}