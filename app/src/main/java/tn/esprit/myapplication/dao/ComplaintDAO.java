package tn.esprit.myapplication.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import tn.esprit.myapplication.entity.Complaint;

@Dao
public interface ComplaintDAO {
    @Insert
    void insertComplaint(Complaint complaint);

    @Delete
    void deleteUser(Complaint complaint);

    @Query("SELECT * FROM user")
    List<Complaint> getAllUser();

    @Query("SELECT * FROM user WHERE id = :id")
    Complaint getUserById(int id);
}
