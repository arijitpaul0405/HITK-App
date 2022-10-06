package com.example.hitkapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hitkapp.databinding.ActivityStudentPageBinding;
import com.google.firebase.firestore.FirebaseFirestore;

public class StudentPageActivity extends AppCompatActivity {

    ActivityStudentPageBinding binding;

    FirebaseFirestore fs = FirebaseFirestore.getInstance();

    private String result_link = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fetchDetails();

        binding.assignmentBtn.setOnClickListener(view -> {
            Intent navIntent = new Intent(StudentPageActivity.this, AssignmentActivity.class);
            startActivity(navIntent);
        });

        binding.timeTableBtn.setOnClickListener(view -> {
            Intent navIntent = new Intent(StudentPageActivity.this, TimeTableActivity.class);
            startActivity(navIntent);
        });

        binding.syllabusBtn.setOnClickListener(view -> {
            Intent navIntent = new Intent(StudentPageActivity.this, SyllabusActivity.class);
            startActivity(navIntent);
        });

        binding.resultBtn.setOnClickListener(view -> {
            if(result_link != null) {
                Intent navIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(result_link));
                startActivity(navIntent);
            } else
                Toast.makeText(StudentPageActivity.this, "Error with result site\nPlease try again after sometime", Toast.LENGTH_SHORT).show();
        });
    }

    private void fetchDetails() {
        fs.collection("Result")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if(!queryDocumentSnapshots.isEmpty()) {
                        result_link = queryDocumentSnapshots.getDocuments().get(0).getString("result_link");
                    }
                })
                .addOnFailureListener(e -> Toast.makeText(StudentPageActivity.this, "Failure : " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show());
    }
}