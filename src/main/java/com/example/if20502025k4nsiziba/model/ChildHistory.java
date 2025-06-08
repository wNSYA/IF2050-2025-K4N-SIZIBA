package com.example.if20502025k4nsiziba.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ChildHistory{
    private LocalDateTime dateRecorded;
    private int childId;
    private float headCircumference;
    private float handCircumference;
    private float abdominalCircumference;
    private float height;
    private float weight;
    private LocalDate dateAdded;

//    public ChildHistory(){}

    public ChildHistory(Child child){
        this.dateRecorded = LocalDateTime.now();
        this.childId = child.getId();
        this.headCircumference = child.getHeadCircumference();
        this.abdominalCircumference = child.getAbdominalCircumference();
        this.handCircumference = child.getHandCircumference();
        this.height = child.getHeight();
        this.weight = child.getWeight();
        this.dateAdded = child.getDateAdded();
    }

    //    getter
    public int getChildId() {return childId;}
    public float getHeadCircumference() {return headCircumference;}
    public float getAbdominalCircumference() {return abdominalCircumference;}
    public float getHandCircumference() {return handCircumference;}
    public float getHeight() {return height;}
    public float getWeight() {return weight;}
    public LocalDate getDateAdded() {return dateAdded;}
    public LocalDateTime getDateRecorded() {return dateRecorded;}

//    immutable; no setter

    @Override
    public String toString(){
        return  "Date Recorded: " + dateRecorded + "\n" +
                "ChildId: " + childId + "\n" +
                "Lingkar kepala: " + getHeadCircumference() + "\n" +
                "Lingkar perut: " + getAbdominalCircumference() + "\n" +
                "Lingkar tangan: " + getHandCircumference() + "\n" +
                "Tinggi badan: " + height + "\n" +
                "Berat badan: " + weight + "\n" +
                "Date added: " + dateAdded
                ;
    }
}