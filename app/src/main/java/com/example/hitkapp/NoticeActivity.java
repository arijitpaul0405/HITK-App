package com.example.hitkapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hitkapp.adapter_class.NoticeListAdapter;
import com.example.hitkapp.custom_class.NoticeClass;
import com.example.hitkapp.databinding.ActivityNoticeBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class NoticeActivity extends AppCompatActivity {

    ActivityNoticeBinding binding;

    FirebaseFirestore fs = FirebaseFirestore.getInstance();

    List<NoticeClass> noticeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNoticeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fetchDetails();

    }

    private void fetchDetails() {
        fs.collection("Notice")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if(!queryDocumentSnapshots.isEmpty()) {
                        List<DocumentSnapshot> dockList = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot ds : dockList) {
                            String notice = ds.getString("notice");
                            noticeList.add(new NoticeClass(notice));
                        }

                        NoticeListAdapter adapter = new NoticeListAdapter(this, noticeList);
                        binding.recyclerview.setAdapter(adapter);
                    }
                })
                .addOnFailureListener(e -> Toast.makeText(NoticeActivity.this, "Failure : " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show());
    }
}