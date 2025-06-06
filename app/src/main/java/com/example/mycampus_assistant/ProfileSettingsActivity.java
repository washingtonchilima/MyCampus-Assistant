package com.example.mycampus_assistant;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileSettingsActivity extends AppCompatActivity {

    private EditText nameEditText, idEditText, courseEditText;
    private Button editButton, saveButton;
    private BottomNavigationView bottomNavigation;

    private SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "student_profile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile_settings);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.profile_root), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        nameEditText = findViewById(R.id.editTextName);
        idEditText = findViewById(R.id.editTextId);
        courseEditText = findViewById(R.id.editTextCourse);
        editButton = findViewById(R.id.btnEdit);
        saveButton = findViewById(R.id.btnSave);
        bottomNavigation = findViewById(R.id.bottom_navigation);

        // Preferences setup
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        loadProfile();
        setEditable(false);

        // Button logic
        editButton.setOnClickListener(v -> setEditable(true));
        saveButton.setOnClickListener(v -> {
            saveProfile();
            setEditable(false);
        });

        setupBottomNavListener();
        bottomNavigation.setSelectedItemId(R.id.nav_profile); // Highlight current item
    }

    private void setEditable(boolean editable) {
        nameEditText.setEnabled(editable);
        idEditText.setEnabled(editable);
        courseEditText.setEnabled(editable);
    }

    private void loadProfile() {
        nameEditText.setText(sharedPreferences.getString("name", ""));
        idEditText.setText(sharedPreferences.getString("id", ""));
        courseEditText.setText(sharedPreferences.getString("course", ""));
    }

    private void saveProfile() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", nameEditText.getText().toString());
        editor.putString("id", idEditText.getText().toString());
        editor.putString("course", courseEditText.getText().toString());
        editor.apply();
    }

    private void setupBottomNavListener() {
        bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_profile) return true;

            Class<?> targetActivity = null;
            if (itemId == R.id.nav_home) {
                targetActivity = HomeActivity.class;
            } else if (itemId == R.id.nav_schedule) {
                targetActivity = ClassScheduleActivity.class;

            } else if (itemId == R.id.nav_map) {
                targetActivity = CampusMapActivity.class;
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
