package com.example.hitkapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hitkapp.adapter_class.SyllabusListAdapter;
import com.example.hitkapp.custom_class.SyllabusClass;
import com.example.hitkapp.databinding.ActivitySyllabusBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class SyllabusActivity extends AppCompatActivity {

    ActivitySyllabusBinding binding;

    FirebaseFirestore fs = FirebaseFirestore.getInstance();

    List<SyllabusClass> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySyllabusBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fetchDetails();
    }

    private void fetchDetails() {
        fs.collection("Syllabus")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if(!queryDocumentSnapshots.isEmpty()) {
                        List<DocumentSnapshot> dockList = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot ds : dockList) {
                            String title = ds.getString("title");
                            String link = ds.getString("syllabus_pdf");
                            list.add(new SyllabusClass(title, link));
                        }

                        SyllabusListAdapter adapter = new SyllabusListAdapter(SyllabusActivity.this, list);
                        binding.recyclerview.setAdapter(adapter);
                    }
                })
                .addOnFailureListener(e -> Toast.makeText(SyllabusActivity.this, "Failure : " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show());
    }
}