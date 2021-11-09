package tn.esprit.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import tn.esprit.myapplication.database.MyDatabase;
import tn.esprit.myapplication.entity.User;

public class MainActivity extends AppCompatActivity {

    MyDatabase mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = MyDatabase.getDatabase(this);

        //mydb.userDAO().insertAdmin();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragHolder,new LoginFragment()).commit();
    }
}