package com.example.hitkapp.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.hitkapp.StudentSignInActivity;
import com.example.hitkapp.TeacherSignInActivity;
import com.example.hitkapp.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.studentBtn.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), StudentSignInActivity.class);
            startActivity(intent);
        });

        binding.teacherBtn.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), TeacherSignInActivity.class);
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