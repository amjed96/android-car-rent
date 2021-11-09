package tn.esprit.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivityUser extends AppCompatActivity {

    BottomNavigationView navBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);

        navBar = findViewById(R.id.navBar);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragHolder,new UserCarsFragment()).commit();
    }
}