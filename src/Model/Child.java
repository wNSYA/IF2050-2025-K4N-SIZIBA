package Model;

import java.io.Serializable;
import java.util.UUID;
import java.time.LocalDate;
import java.time.Period;

public class Child implements Serializable {
    private String id;
    private String name;
    // Male : 1, Female : 0
    private boolean gender;
    private LocalDate birthDate;
    private float height;
    private float weight;
    private LocalDate dateAdded;

    public Child(){}

    public Child(String name, boolean gender, LocalDate birthDate, float height, float weight){
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.height = height;
        this.weight = weight;
        this.dateAdded = LocalDate.now();
    }

    // getter
    public String getId() {return id;}
    public String getName() {return name;}
    public boolean getGender() {return  gender;}
    public LocalDate getBirthDate() {return birthDate;}
    public int getAge() {return Period.between(birthDate, LocalDate.now()).getYears();}
    public float getHeight() {return height;}
    public float getWeight() {return weight;}
    public LocalDate getDateAdded() {return dateAdded;}

    // setter
    public void setName(String name){this.name = name;}
    public void setGender(boolean gender) {this.gender = gender;}
    public void setBirthDate(LocalDate birthDate) {this.birthDate = birthDate;}
    public void setHeight(float height) {this.height = height;}
    public void setWeight(float weight) {this.weight = weight;}

    @Override
    public String toString(){
        String genderString = this.gender? "Laki-Laki" : "Perempuan";
        return  "Id: " + id + "\n" +
                "Nama: " + name + "\n" +
                "Tanggal lahir: " + birthDate + "\n" +
                "Usia: " + getAge() + "\n" +
                "Jenis kelamin: " + genderString + "\n" +
                "Tinggi badan: " + height + "\n" +
                "Berat badan: " + weight + "\n" +
                "Date added: " + dateAdded
                ;
    }
}