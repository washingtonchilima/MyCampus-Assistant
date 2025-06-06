package com.example.mycampus_assistant;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ClassScheduleActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ScheduleAdapter adapter;
    private BottomNavigationView bottomNavigation;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_class_schedule);

        // Apply padding for edge-to-edge layout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set up RecyclerView
        recyclerView = findViewById(R.id.recyclerViewSchedule);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Sample data
        List<ClassScheduleEntry> scheduleEntries = new ArrayList<>();
        scheduleEntries.add(new ClassScheduleEntry("Monday", "09:00 - 10:00", "Mathematics"));
        scheduleEntries.add(new ClassScheduleEntry("Monday", "10:15 - 11:15", "Physics"));
        scheduleEntries.add(new ClassScheduleEntry("Tuesday", "08:00 - 09:00", "Chemistry"));
        scheduleEntries.add(new ClassScheduleEntry("Wednesday", "11:30 - 12:30", "Computer Science"));
        scheduleEntries.add(new ClassScheduleEntry("Thursday", "13:00 - 14:00", "English Literature"));
        scheduleEntries.add(new ClassScheduleEntry("Friday", "10:00 - 11:00", "History"));

        adapter = new ScheduleAdapter(scheduleEntries);
        recyclerView.setAdapter(adapter);

        // Set up BottomNavigationView
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setSelectedItemId(R.id.nav_schedule); // Highlight current tab

        bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_schedule) return true;

            Class<?> targetActivity = null;
            if (itemId == R.id.nav_home) {
                targetActivity = HomeActivity.class;

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
