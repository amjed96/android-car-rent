package tn.esprit.myapplication.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import tn.esprit.myapplication.entity.Car;
import tn.esprit.myapplication.entity.User;

@Dao
public interface CarDAO {
    @Insert
    void insertCar(Car car);

    @Delete
    void deleteCar(Car car);

    @Query("SELECT * FROM car")
    List<User> getAllCar();
}
