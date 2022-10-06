package com.example.hitkapp.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.hitkapp.FacultyActivity;
import com.example.hitkapp.NoticeActivity;
import com.example.hitkapp.databinding.FragmentHomeBinding;
import com.google.firebase.firestore.FirebaseFirestore;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    FirebaseFirestore fs = FirebaseFirestore.getInstance();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.notice.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), NoticeActivity.class);
            startActivity(intent);
        });

        binding.holiday.setOnClickListener(view -> fs.collection("Holiday")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if(!queryDocumentSnapshots.isEmpty()) {
                        String holiday_pdf_link = queryDocumentSnapshots.getDocuments().get(0).getString("holiday_list");
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(holiday_pdf_link));
                        startActivity(intent);
                    }
                }));

        binding.faculty.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), FacultyActivity.class);
            startActivity(intent);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}