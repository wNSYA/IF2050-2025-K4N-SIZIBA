package com.example.if20502025k4nsiziba.model;

import java.time.LocalDate;
import java.time.Period;

public class Child {
    private int id;
    private String name;
    private boolean gender;
    private LocalDate birthDate;
    private float headCircumference;
    private float handCircumference;
    private float abdominalCircumference;
    private float height;
    private float weight;
    private LocalDate dateAdded;
//
//    public Child() {}

    public Child(String name,
                 boolean gender,
                 LocalDate birthDate,
                 float height,
                 float weight,
                 float headCircumference,
                 float abdominalCircumference,
                 float handCircumference) {
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.height = height;
        this.weight = weight;
        this.headCircumference = headCircumference;
        this.abdominalCircumference = abdominalCircumference;
        this.handCircumference = handCircumference;
        this.dateAdded = LocalDate.now(); // Autofill on creation
    }

    public Child (int id,
                  String name,
                  boolean gender,
                  LocalDate birthDate,
                  float height,
                  float weight,
                  float headCircumference,
                  float abdominalCircumference,
                  float handCircumference,
                  LocalDate dateAdded) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.height = height;
        this.weight = weight;
        this.headCircumference = headCircumference;
        this.abdominalCircumference = abdominalCircumference;
        this.handCircumference = handCircumference;
        this.dateAdded = dateAdded;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public boolean isGender() { return gender; }
    public LocalDate getBirthDate() { return birthDate; }
    public float getHeight() { return height; }
    public float getWeight() { return weight; }
    public float getHeadCircumference() { return headCircumference; }
    public float getHandCircumference() { return handCircumference; }
    public float getAbdominalCircumference() { return abdominalCircumference; }
    public LocalDate getDateAdded() { return dateAdded; }

    public int getAge() {
        if (birthDate == null) return 0;
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setGender(boolean gender) { this.gender = gender; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    public void setHeight(float height) { this.height = height; }
    public void setWeight(float weight) { this.weight = weight; }
    public void setHeadCircumference(float headCircumference) { this.headCircumference = headCircumference; }
    public void setHandCircumference(float handCircumference) { this.handCircumference = handCircumference; }
    public void setAbdominalCircumference(float abdominalCircumference) { this.abdominalCircumference = abdominalCircumference; }
    public void setDateAdded(LocalDate dateAdded) { this.dateAdded = dateAdded; }


    @Override
    public String toString() {
        String genderString = isGender() ? "Laki-Laki" : "Perempuan";
        return  "Id: " + getId() + "\n" +
                "Nama: " + getName() + "\n" +
                "Tanggal lahir: " + getBirthDate() + "\n" +
                "Usia: " + getAge() + "\n" +
                "Tinggi badan: " + getHeight() + "\n" +
                "Berat badan: " + getWeight() + "\n" +
                "Jenis kelamin: " + genderString + "\n" +
                "Lingkar kepala: " + getHeadCircumference() + "\n" +
                "Lingkar perut: " + getAbdominalCircumference() + "\n" +
                "Lingkar tangan: " + getHandCircumference() + "\n" +
                "Date added: " + getDateAdded();
    }
}
