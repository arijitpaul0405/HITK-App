package com.example.hitkapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hitkapp.databinding.ActivityTimeTableBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class TimeTableActivity extends AppCompatActivity {

    ActivityTimeTableBinding binding;

    FirebaseFirestore fs = FirebaseFirestore.getInstance();

    private String bTech1, bTech2, bTech3, bTech4, mca1, mca2, mTech1, mTech2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTimeTableBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fetchDetails();

        binding.btech1.setOnClickListener(view -> {
            Intent navIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(bTech1));
            startActivity(navIntent);
        });

        binding.btech2.setOnClickListener(view -> {
            Intent navIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(bTech2));
            startActivity(navIntent);
        });

        binding.btech3.setOnClickListener(view -> {
            Intent navIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(bTech3));
            startActivity(navIntent);
        });

        binding.btech4.setOnClickListener(view -> {
            Intent navIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(bTech4));
            startActivity(navIntent);
        });

        binding.mca1.setOnClickListener(view -> {
            Intent navIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mca1));
            startActivity(navIntent);
        });

        binding.mca2.setOnClickListener(view -> {
            Intent navIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mca2));
            startActivity(navIntent);
        });

        binding.mtech1.setOnClickListener(view -> {
            Intent navIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mTech1));
            startActivity(navIntent);
        });

        binding.mtech2.setOnClickListener(view -> {
            Intent navIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mTech2));
            startActivity(navIntent);
        });
    }

    private void fetchDetails() {
        fs.collection("Routine")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if(!queryDocumentSnapshots.isEmpty()) {
                        List<DocumentSnapshot> docList = queryDocumentSnapshots.getDocuments();

                        bTech1 = docList.get(0).getString("link");
                        bTech2 = docList.get(1).getString("link");
                        bTech3 = docList.get(2).getString("link");
                        bTech4 = docList.get(3).getString("link");
                        mTech1 = docList.get(4).getString("link");
                        mTech2 = docList.get(5).getString("link");
                        mca1 = docList.get(6).getString("link");
                        mca2 = docList.get(7).getString("link");
                    }
                })
                .addOnFailureListener(e -> Toast.makeText(TimeTableActivity.this, "Failure : " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show());
    }
}