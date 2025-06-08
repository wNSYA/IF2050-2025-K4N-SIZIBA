package com.example.if20502025k4nsiziba.controller;

import com.example.if20502025k4nsiziba.model.User;
import com.example.if20502025k4nsiziba.model.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class UserController {
    @FXML
    private TextField nameField;

    @FXML
    private ListView<User> userList;

    private final ObservableList<User> users = FXCollections.observableArrayList();
    private final UserDAO userDAO = new UserDAO();

    @FXML
    public void initialize() {
        loadUsers();
    }

    @FXML
    public void handleAddUser() {
        String name = nameField.getText().trim();
        if (!name.isEmpty()) {
            userDAO.addUser(name);
            nameField.clear();
            loadUsers();
        }
    }

    private void loadUsers() {
        users.setAll(userDAO.getAllUsers());
        userList.setItems(users);
    }
}