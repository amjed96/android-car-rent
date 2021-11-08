package tn.esprit.myapplication.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import tn.esprit.myapplication.dao.CarDAO;
import tn.esprit.myapplication.dao.CategoryDAO;
import tn.esprit.myapplication.dao.UserDAO;
import tn.esprit.myapplication.entity.Car;
import tn.esprit.myapplication.entity.Category;
import tn.esprit.myapplication.entity.User;

@Database(entities = {User.class, Car.class, Category.class}, version = 2, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {
    private static MyDatabase instance;

    public abstract UserDAO userDAO();
    public abstract CarDAO carDAO();
    public abstract CategoryDAO categoryDAO();

    public static MyDatabase getDatabase(Context context){
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),MyDatabase.class,"my_db")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
