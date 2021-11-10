package tn.esprit.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivityAdmin extends AppCompatActivity {

    FrameLayout fragHolder;
    BottomNavigationView navBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        fragHolder = findViewById(R.id.fragHolder);
        navBar= findViewById(R.id.navBar);

        navBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                switch (item.getItemId()){
                    case R.id.users:
                        selectedFragment = new AdminUsersFragment();
                        break;
                    case R.id.categories:
                        selectedFragment = new AdminCategoriesFragment();
                        break;
                    case R.id.cars:
                        selectedFragment = new AdminCarsFragment();
                        break;
                    case R.id.complaints:
                        selectedFragment = new AdminUsersFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragHolder,selectedFragment).commit();
                return true;
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.fragHolder,new AdminUsersFragment()).commit();

    }
}