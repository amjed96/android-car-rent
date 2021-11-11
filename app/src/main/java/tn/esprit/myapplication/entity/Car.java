package tn.esprit.myapplication.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Car implements Serializable {
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
    @ColumnInfo.SQLiteTypeAffinity()
    private byte[] carPic;
    @ColumnInfo
    private int userRent;



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

    public byte[] getCarPic() {
        return carPic;
    }

    public void setCarPic(byte[] carPic) {
        this.carPic = carPic;
    }

    public int getUserRent() {
        return userRent;
    }

    public void setUserRent(int userRent) {
        this.userRent = userRent;
    }
}
