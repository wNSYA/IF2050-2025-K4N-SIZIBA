package com.example.if20502025k4nsiziba.controller;

import com.example.if20502025k4nsiziba.model.Child;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ChildProfileCardController {

    private int id;

    @FXML
    private Text profile1; // "Profile (Name)"

    @FXML
    private Text profile2; // "Child Profile (Name)"

    @FXML
    private Text profileAge;

    @FXML
    private Label gendere;

    @FXML
    private ImageView profilePictureV;

    @FXML
    private TextField weightV;

    @FXML
    private TextField heightV;

    @FXML
    private TextField headCircumferenceV;

    @FXML
    private TextField abdominalCircumferenceV;

    @FXML
    private TextField handCircumferenceV;

    public void setNewChild(Child child){
        this.id = child.getId();
        setProfileName(child.getName());
        setAge(""+ child.getAge());
        String genderString = child.isGender() ? "Laki-Laki" : "Perempuan";
        setGender(genderString);
        setHeight(child.getHeight()+" cm");
        setWeight(child.getWeight()+ " Kg");
        setHeadCircumference(child.getHeadCircumference()+" cm");
        setAbdominalCircumference(child.getAbdominalCircumference()+" cm");
        setHandCircumference(child.getHandCircumference()+" cm");

    }

//    @FXML
//    public void handleUpdateAction(){
//        Child updatedChild = new Child(
//                id,
//
//        )
//    }

    public void setProfileName(String name) {
        profile1.setText("Profile (" + name + ")");
        profile2.setText("Child Profile (" + name + ")");
    }

    public void setAge(String age) {
        profileAge.setText(age+" tahun");
    }

    public void setGender(String genderText) {
        gendere.setText(genderText);
        // Example: change background color depending on gender
        if ("Laki-laki".equalsIgnoreCase(genderText)) {
            gendere.setStyle("-fx-background-color: #3B68FF; -fx-background-radius: 10;");
        } else if ("Perempuan".equalsIgnoreCase(genderText)) {
            gendere.setStyle("-fx-background-color: #FF69B4; -fx-background-radius: 10;");
        } else {
            gendere.setStyle("-fx-background-color: #AAAAAA; -fx-background-radius: 10;");
        }
    }

    public void setWeight(String weight) {
        weightV.setText(weight);
    }

    public void setHeight(String height) {
        heightV.setText(height);
    }

    public void setHeadCircumference(String headCircumference) {
        headCircumferenceV.setText(headCircumference);
    }

    public void setAbdominalCircumference(String abdominalCircumference) {
        abdominalCircumferenceV.setText(abdominalCircumference);
    }

    public void setHandCircumference(String handCircumference) {
        handCircumferenceV.setText(handCircumference);
    }

    public void setProfileImage(Image image) {
        profilePictureV.setImage(image);
    }
}
