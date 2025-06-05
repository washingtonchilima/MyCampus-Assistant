package com.example.mycampus_assistant;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileSettingsActivity extends AppCompatActivity {

    private EditText nameEditText, idEditText, courseEditText;
    private Button editButton, saveButton;

    private SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "student_profile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);

        nameEditText = findViewById(R.id.editTextName);
        idEditText = findViewById(R.id.editTextId);
        courseEditText = findViewById(R.id.editTextCourse);
        editButton = findViewById(R.id.btnEdit);
        saveButton = findViewById(R.id.btnSave);

        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        // Load saved values
        loadProfile();

        // Make fields read-only initially
        setEditable(false);

        editButton.setOnClickListener(v -> setEditable(true));

        saveButton.setOnClickListener(v -> {
            saveProfile();
            setEditable(false);
        });
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
}
