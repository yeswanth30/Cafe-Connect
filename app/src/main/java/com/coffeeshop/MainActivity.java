package com.coffeeshop;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.coffeeshop.DBHandler.DBHandler;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.coffeeshop.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    DBHandler databaseHelper;
    SQLiteDatabase database;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        databaseHelper = new DBHandler(MainActivity.this);

        databaseHelper.addNewUser("sai",
                "Yeswanth",
                "yeswanth",
                "mailto:yeswanth@gmail.com",
                "96547896554",
                "1",
                "photo/profile.jpg");
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }



}