package com.example.mycampus_assistant;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CampusMapActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_campus_map);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bottomNavigation = findViewById(R.id.bottom_navigation);
        setupBottomNavListener();
        bottomNavigation.setSelectedItemId(R.id.nav_map);
    }

    private void setupBottomNavListener() {
        bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_map) return true;

            Class<?> targetActivity = null;
            if (itemId == R.id.nav_home) {
                targetActivity = HomeActivity.class;
            } else if (itemId == R.id.nav_schedule) {
                targetActivity = ClassScheduleActivity.class;

            } else if (itemId == R.id.nav_profile) {
                targetActivity = ProfileSettingsActivity.class;
            }

            if (targetActivity != null) {
                try {
                    startActivity(new Intent(this, targetActivity));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Error navigating", Toast.LENGTH_SHORT).show();
                }
                return true;
            }

            return false;
        });
    }
}
