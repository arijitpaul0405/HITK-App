package com.example.hitkapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hitkapp.databinding.ActivityAssignmentUploadBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class AssignmentUploadActivity extends AppCompatActivity {

    ActivityAssignmentUploadBinding binding;

    TextInputLayout name_layout;

    TextInputEditText name_input;

    Uri pdfUri;

    StorageReference storageReference;

    ProgressDialog progressDialog;

    String file_name = null;

    boolean pdfChosen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAssignmentUploadBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        name_input = binding.nameInput;
        name_layout = binding.nameLayout;

        binding.chooseFileBtn.setOnClickListener(view -> selectPDF());
        
        binding.uploadFileBtn.setOnClickListener(view -> {

            file_name = String.valueOf(name_input.getText());

            if(!pdfChosen) {
                Toast.makeText(AssignmentUploadActivity.this, "Please choose a file to upload!", Toast.LENGTH_SHORT).show();
            }
            else if(file_name.isEmpty() || file_name.trim().isEmpty()) {
                Toast.makeText(AssignmentUploadActivity.this, "Please enter file name!", Toast.LENGTH_SHORT).show();
            }
            else {
                uploadPDF();
            }
        });
    }

    private void uploadPDF() {
        progressDialog = new ProgressDialog(AssignmentUploadActivity.this);
        progressDialog.setTitle("Uploading assignment...");
        progressDialog.show();

        String file = file_name + "/";
        String location = "Assignment/" + file;

        storageReference = FirebaseStorage.getInstance().getReference(location);

        storageReference.putFile(pdfUri)
                .addOnSuccessListener(taskSnapshot -> {
                    Toast.makeText(AssignmentUploadActivity.this, "Assignment uploaded successfully!", Toast.LENGTH_SHORT).show();

                    if(progressDialog.isShowing())
                        progressDialog.dismiss();

                    pdfChosen = false;

                    AssignmentUploadActivity.this.finish();
                })
                .addOnFailureListener(e -> {
                    if(progressDialog.isShowing())
                        progressDialog.dismiss();

                    Toast.makeText(AssignmentUploadActivity.this, "Failure : " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    private void selectPDF() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100 && data != null && data.getData() != null) {
            pdfUri = data.getData();
            String pdf = pdfUri.toString();
            binding.fileName.setText(pdf);

            pdfChosen = true;
        }
    }
}