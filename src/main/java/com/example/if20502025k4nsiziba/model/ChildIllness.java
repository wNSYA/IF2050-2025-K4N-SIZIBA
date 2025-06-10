package com.example.if20502025k4nsiziba.model;

import java.time.LocalDate;

public class ChildIllness {
    private int id;
    private int childId;
    private String illnessName;
    private String description;
    private LocalDate dateOfIllness;

    // Constructor for creating a new illness record before saving to DB
    public ChildIllness(int childId, String illnessName, String description, LocalDate dateOfIllness) {
        this.childId = childId;
        this.illnessName = illnessName;
        this.description = description;
        this.dateOfIllness = dateOfIllness;
    }

    // Constructor for creating an object from DB data
    public ChildIllness(int id, int childId, String illnessName, String description, LocalDate dateOfIllness) {
        this.id = id;
        this.childId = childId;
        this.illnessName = illnessName;
        this.description = description;
        this.dateOfIllness = dateOfIllness;
    }

    // Getters
    public int getId() { return id; }
    public int getChildId() { return childId; }
    public String getIllnessName() { return illnessName; }
    public String getDescription() { return description; }
    public LocalDate getDateOfIllness() { return dateOfIllness; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setChildId(int childId) { this.childId = childId; }
    public void setIllnessName(String illnessName) { this.illnessName = illnessName; }
    public void setDescription(String description) { this.description = description; }
    public void setDateOfIllness(LocalDate dateOfIllness) { this.dateOfIllness = dateOfIllness; }

    @Override
    public String toString() {
        return "ChildIllness{" +
                "id=" + id +
                ", childId=" + childId +
                ", illnessName='" + illnessName + '\'' +
                ", description='" + description + '\'' +
                ", dateOfIllness=" + dateOfIllness +
                '}';
    }
}