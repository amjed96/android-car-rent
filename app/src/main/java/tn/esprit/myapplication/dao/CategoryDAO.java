package tn.esprit.myapplication.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import tn.esprit.myapplication.entity.Category;
import tn.esprit.myapplication.entity.User;

@Dao
public interface CategoryDAO {
    @Insert
    void insertCategory(Category category);

    @Delete
    void deleteCategory(Category category);

    @Query("SELECT * FROM category")
    List<User> getAllCategory();
}
