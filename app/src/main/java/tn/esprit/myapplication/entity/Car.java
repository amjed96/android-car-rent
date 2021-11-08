package tn.esprit.myapplication.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Car {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    private String brand;
    @ColumnInfo
    private String edition;
    @ColumnInfo
    private String category/*Category*/;
    @ColumnInfo
    private int price;
    @ColumnInfo
    private String engine;
    @ColumnInfo
    private int topSpeed;

    public Car(int id, String brand, String edition, String category, int price, String engine, int topSpeed) {
        this.id = id;
        this.brand = brand;
        this.edition = edition;
        this.category = category;
        this.price = price;
        this.engine = engine;
        this.topSpeed = topSpeed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public int getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(int topSpeed) {
        this.topSpeed = topSpeed;
    }
}
