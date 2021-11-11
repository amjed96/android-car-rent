package tn.esprit.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import tn.esprit.myapplication.entity.User;

public class MainActivityUser extends AppCompatActivity {

    BottomNavigationView navBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);

        User user = (User) getIntent().getSerializableExtra("user");

        navBar = findViewById(R.id.navBar);

        navBar.setSelectedItemId(R.id.cars);
        navBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                switch (item.getItemId()){
                    case R.id.profile:
                        selectedFragment = UserProfileFragment.newInstance(user);
                        break;
                    case R.id.cars:
                        selectedFragment = UserCarsFragment.newInstance(user);
                        break;
                    case R.id.favorites:
                        selectedFragment = UserFavoritesFragment.newInstance(user);/* HERE */
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragHolder,selectedFragment).commit();
                return true;
            }
        });
        //navBar.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragHolder,UserCarsFragment.newInstance(user)).commit();
    }
}