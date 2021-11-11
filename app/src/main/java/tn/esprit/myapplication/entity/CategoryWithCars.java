package tn.esprit.myapplication.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class CategoryWithCars {
    @Embedded
    public Category category;
    @Relation(
            parentColumn = "id",
            entityColumn = "id"
    )
    public List<Car> cars;
}
