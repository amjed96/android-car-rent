package tn.esprit.myapplication.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import tn.esprit.myapplication.entity.User;

@Dao
public interface UserDAO {
    @Insert
    void insertUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("SELECT * FROM user")
    List<User> getAllUser();

    @Query("SELECT * FROM user WHERE id = :id")
    User getUserById(int id);

    @Query("UPDATE user SET username = :username, email = :email, password = :pwd WHERE id =:id")
    void editUser(int id, String username, String email, String pwd);

    @Query("INSERT INTO user (username,email,password,role) VALUES ('admin','admin@gmail.com','admin','admin')")
    void insertAdmin();
}
