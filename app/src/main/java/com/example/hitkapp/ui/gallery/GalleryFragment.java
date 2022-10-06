package com.example.hitkapp.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.hitkapp.adapter_class.GalleryAdapter;
import com.example.hitkapp.custom_class.GalleryClass;
import com.example.hitkapp.databinding.FragmentGalleryBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;

    FirebaseFirestore fs = FirebaseFirestore.getInstance();

    List<GalleryClass> galleryList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        fetchDetails();

        return root;
    }

    private void fetchDetails() {
        fs.collection("Gallery")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if(!queryDocumentSnapshots.isEmpty()) {
                        List<DocumentSnapshot> dockList = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot ds : dockList) {
                            String image = ds.getString("image");
                            galleryList.add(new GalleryClass(image));
                        }

                        GalleryAdapter adapter = new GalleryAdapter(getContext(), galleryList);
                        binding.recyclerview.setAdapter(adapter);
                    }
                })
                .addOnFailureListener(e -> Toast.makeText(getContext(), "Failure : " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}