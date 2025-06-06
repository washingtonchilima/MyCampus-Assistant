package com.example.mycampusassistant;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    // UI Components
    private TextView mWelcomeText;
    private TextView mUserInfo;
    private Button mBtnClassSchedule;
    private Button mBtnAssignmentsExams;
    private Button mBtnCampusMap;
    private Button mBtnProfileSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initializeViews();
        setupUserInfo();
        setupButtonListeners();
    }

    private void initializeViews() {
        try {
            mWelcomeText = findViewById(R.id.welcomeText);
            mUserInfo = findViewById(R.id.userInfo);
            mBtnClassSchedule = findViewById(R.id.btnClassSchedule);
            mBtnAssignmentsExams = findViewById(R.id.btnAssignmentsExams);
            mBtnCampusMap = findViewById(R.id.btnCampusMap);
            mBtnProfileSettings = findViewById(R.id.btnProfileSettings);
        } catch (Exception e) {
            e.printStackTrace();
            showErrorAndFinish();
        }
    }

    private void setupUserInfo() {
        String userName = "Washington Chilima";
        String userId = "BScICT/22/";
        mUserInfo.setText(String.format("Name: %s\nID: %s", userName, userId));
    }

    private void setupButtonListeners() {
        mBtnClassSchedule.setOnClickListener(v -> {
            // TODO: Uncomment when ClassScheduleActivity is ready
            // navigateTo(ClassScheduleActivity.class);
            Toast.makeText(this, "Class Schedule feature coming soon!", Toast.LENGTH_SHORT).show();
        });

        mBtnAssignmentsExams.setOnClickListener(v -> {
            // TODO: Uncomment when AssignmentsExamsActivity is ready
            // navigateTo(AssignmentsExamsActivity.class);
            Toast.makeText(this, "Assignments & Exams feature coming soon!", Toast.LENGTH_SHORT).show();
        });

        mBtnCampusMap.setOnClickListener(v -> {
            // TODO: Uncomment when CampusMapActivity is ready
            // navigateTo(CampusMapActivity.class);
            Toast.makeText(this, "Campus Map feature coming soon!", Toast.LENGTH_SHORT).show();
        });

        mBtnProfileSettings.setOnClickListener(v -> {
            // TODO: Uncomment when ProfileSettingsActivity is ready
            // navigateTo(ProfileSettingsActivity.class);
            Toast.makeText(this, "Profile Settings feature coming soon!", Toast.LENGTH_SHORT).show();
        });
    }

    private void navigateTo(Class<?> destinationActivity) {
        try {
            Intent intent = new Intent(HomeActivity.this, destinationActivity);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        } catch (Exception e) {
            e.printStackTrace();
            showErrorToast();
        }
    }

    private void showErrorToast() {
        Toast.makeText(this, "An error occurred. Please try again.", Toast.LENGTH_SHORT).show();
    }

    private void showErrorAndFinish() {
        Toast.makeText(this, "A critical error occurred. The app will close.", Toast.LENGTH_LONG).show();
        finish();
    }
}