package com.example.mycampus_assistant;

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

public class AssignmentsExamsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AssignmentsExamsAdapter adapter;
    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_assignments_exams);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerViewAssignments);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<AssignmentExamItem> items = new ArrayList<>();
        items.add(new AssignmentExamItem("Math Assignment 1", "Due: June 10", true));
        items.add(new AssignmentExamItem("Physics Midterm", "Date: June 12", true));
        items.add(new AssignmentExamItem("Essay on Shakespeare", "Due: June 15", false));
        items.add(new AssignmentExamItem("Computer Science Project", "Due: June 20", true));

        adapter = new AssignmentsExamsAdapter(items);
        recyclerView.setAdapter(adapter);

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setSelectedItemId(R.id.nav_assignments);

        bottomNavigation.setOnItemSelectedListener(item -> {
            Class<?> targetActivity = null;

            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) targetActivity = HomeActivity.class;
            else if (itemId == R.id.nav_schedule) targetActivity = ClassScheduleActivity.class;
            else if (itemId == R.id.nav_map) targetActivity = CampusMapActivity.class;
            else if (itemId == R.id.nav_profile) targetActivity = ProfileSettingsActivity.class;
            else if (itemId == R.id.nav_assignments) return true;

            if (targetActivity != null) {
                startActivity(new Intent(this, targetActivity));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                return true;
            }

            return false;
        });
    }
}
