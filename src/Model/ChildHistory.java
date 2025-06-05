package Model;

import java.io.Serializable;
import java.util.UUID;
import java.time.LocalDate;

public class ChildHistory implements Serializable {
    private String id;
    private String childId;
    private float height;
    private float weight;
    private LocalDate dateAdded;
    private LocalDate dateRecorded;

    public ChildHistory(){}

    public ChildHistory(Child child){
        this.id = UUID.randomUUID().toString();
        this.childId = child.getId();
        this.height = child.getHeight();
        this.weight = child.getWeight();
        this.dateAdded = child.getDateAdded();
        this.dateRecorded = LocalDate.now();
    }

//    getter
    public String getId() {return id;}
    public String getChildId() {return childId;}
    public float getHeight() {return height;}
    public float getWeight() {return weight;}
    public LocalDate getDateAdded() {return dateAdded;}
    public LocalDate getDateRecorded() {return dateRecorded;}

//    immutable; no setter

    @Override
    public String toString(){
        return  "Id: " + id + "\n" +
                "ChildId: " + childId + "\n" +
                "Tinggi badan: " + height + "\n" +
                "Berat badan: " + weight + "\n" +
                "Date added: " + dateAdded + "\n" +
                "Date Recorded: " + dateRecorded
                ;
    }
}