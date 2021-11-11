package tn.esprit.myapplication.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import tn.esprit.myapplication.entity.Car;
import tn.esprit.myapplication.entity.CategoryWithCars;
import tn.esprit.myapplication.entity.User;

@Dao
public interface CarDAO {
    @Insert
    void insertCar(Car car);

    @Delete
    void deleteCar(Car car);

    @Query("SELECT * FROM car")
    List<Car> getAllCar();

    @Query("SELECT * FROM category WHERE id = :categoryId")
    List<CategoryWithCars> getCarsByCategory(int categoryId);

    @Query("UPDATE car SET userRent = :id WHERE id = :id")
    void rentCar(int id);

    @Query("SELECT * FROM car WHERE userRent = :id")
    List<Car> getRentCars(int id);

    @Query("SELECT * FROM car WHERE category = :cat")
    List<Car> getCarsByCategory(String cat);
}
