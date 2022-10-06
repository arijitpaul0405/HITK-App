package com.example.hitkapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.example.hitkapp.adapter_class.FacultyListAdapter;
import com.example.hitkapp.custom_class.FacultyClass;
import com.example.hitkapp.databinding.ActivityFacultyBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FacultyActivity extends AppCompatActivity {

    ActivityFacultyBinding binding;

    FirebaseFirestore fs = FirebaseFirestore.getInstance();

    List<FacultyClass> faculties = new ArrayList<>();

    FacultyListAdapter adapter;

    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFacultyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        searchView = binding.searchView;
        searchView.clearFocus();

        fetchDetails();
    }

    private void fetchDetails() {
        fs.collection("Faculties")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if(!queryDocumentSnapshots.isEmpty())
                    {
                        List<DocumentSnapshot> dockList = queryDocumentSnapshots.getDocuments();

                        for(DocumentSnapshot ds : dockList) {
                            String name = ds.getString("name");
                            String designation = ds.getString("designation");
                            String image = ds.getString("image");
                            faculties.add(new FacultyClass(name, designation, image));
                        }

                        adapter = new FacultyListAdapter(FacultyActivity.this, faculties);
                        binding.recyclerview.setAdapter(adapter);
                    }
                })
                .addOnFailureListener(e -> Toast.makeText(FacultyActivity.this, "Failure : " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show());

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
        List<FacultyClass> filteredList = new ArrayList<>();
        for (FacultyClass item : faculties) {
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