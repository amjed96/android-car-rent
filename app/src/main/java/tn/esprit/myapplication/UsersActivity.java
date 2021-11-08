package tn.esprit.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import tn.esprit.myapplication.database.MyDatabase;
import tn.esprit.myapplication.entity.User;

public class UsersActivity extends AppCompatActivity {

    RecyclerView usersRv;
    UsersAdapter usersAdapter;
    //ImageButton deleteUser;
    List<User> users = new ArrayList<>();

    MyDatabase mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mydb = MyDatabase.getDatabase(this);
        users = mydb.userDAO().getAllUser();
        setContentView(R.layout.activity_users);

        usersRv = findViewById(R.id.usersRv);
        //deleteUser = findViewById(R.id.deleteBtn);

        usersAdapter = new UsersAdapter(users,this);
        usersRv.setAdapter(usersAdapter);
    }
}