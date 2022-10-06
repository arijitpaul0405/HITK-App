package com.example.hitkapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.example.hitkapp.adapter_class.AssignmentAdapter;
import com.example.hitkapp.custom_class.AssignmentClass;

import com.example.hitkapp.databinding.ActivityAssignmentBinding;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AssignmentActivity extends AppCompatActivity {

    ActivityAssignmentBinding binding;

    StorageReference storageReference;

    List<AssignmentClass> assignmentList = new ArrayList<>();

    AssignmentAdapter adapter;

    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAssignmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        searchView = binding.searchView;

        fetchDetails();
    }

    private void fetchDetails() {
        storageReference = FirebaseStorage.getInstance().getReference().child("Assignment");

        storageReference.listAll().addOnSuccessListener(listResult -> {
            for (StorageReference fileRef : listResult.getItems()) {
                fileRef.getDownloadUrl().addOnSuccessListener(uri -> assignmentList.add(new AssignmentClass(fileRef.getName(), uri.toString()))).addOnSuccessListener(uri -> {adapter = new AssignmentAdapter(this, assignmentList);
                    binding.recyclerview.setAdapter(adapter);
                });
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
    }

    private void filterList(String text) {
        List<AssignmentClass> filteredList = new ArrayList<>();
        for (AssignmentClass item : assignmentList) {
            if(item.getName().toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT))) {
                filteredList.add(item);
            }
        }

        if(filteredList.isEmpty()) {
            binding.recyclerview.setAdapter(null);
            binding.emptyList.setText(R.string.empty_list);
        } else {
            binding.recyclerview.setAdapter(adapter);
            adapter.setFilteredList(filteredList);
            binding.emptyList.setText("");
        }
    }
}