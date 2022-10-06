package com.example.hitkapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hitkapp.databinding.ActivityTeacherPageBinding;

public class TeacherPageActivity extends AppCompatActivity {

    ActivityTeacherPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTeacherPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.assignmentBtn.setOnClickListener(view -> {
            Intent navIntent = new Intent(TeacherPageActivity.this, AssignmentActivity.class);
            startActivity(navIntent);
        });

        binding.assignmentUploadBtn.setOnClickListener(view -> {
            Intent navIntent = new Intent(TeacherPageActivity.this, AssignmentUploadActivity.class);
            startActivity(navIntent);
        });

        binding.timeTableBtn.setOnClickListener(view -> {
            Intent navIntent = new Intent(TeacherPageActivity.this, TimeTableActivity.class);
            startActivity(navIntent);
        });

        binding.syllabusBtn.setOnClickListener(view -> {
            Intent navIntent = new Intent(TeacherPageActivity.this, SyllabusActivity.class);
            startActivity(navIntent);
        });
    }
}