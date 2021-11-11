package tn.esprit.myapplication.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UserWithCars {
    @Embedded
    public User user;
    @Relation(
            parentColumn = "id",
            entityColumn = "id"
    )
    public List<Car> cars;
}
