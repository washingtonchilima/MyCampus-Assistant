package com.example.mycampus_assistant;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private TextView mUserInfo;
    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.home_root), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mUserInfo = findViewById(R.id.userInfo);
        bottomNavigation = findViewById(R.id.bottom_navigation);

        setupUserInfo();
        setupBottomNavListener(); // Renamed for clarity
        bottomNavigation.setSelectedItemId(R.id.nav_home);
    }

    private void setupUserInfo() {
        String userName = "Washington Chilima";
        String userId = "BScICT/22/";
        mUserInfo.setText(String.format("Name: %s\nID: %s", userName, userId));
    }

    private void setupBottomNavListener() {
        bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) return true;

            Class<?> targetActivity = null;
            if (itemId == R.id.nav_map) {
                targetActivity = CampusMapActivity.class;
            } else if (itemId == R.id.nav_profile) {
                targetActivity = ProfileSettingsActivity.class;
            }

            if (targetActivity != null) {
                navigateTo(targetActivity);
                return true;
            }

            return false;
        });
    }

    private void navigateTo(Class<?> destinationActivity) {
        try {
            Intent intent = new Intent(this, destinationActivity);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "An error occurred. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }
}
