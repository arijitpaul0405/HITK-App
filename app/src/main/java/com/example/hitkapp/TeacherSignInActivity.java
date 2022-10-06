package com.example.hitkapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hitkapp.databinding.ActivitySignInBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TeacherSignInActivity extends AppCompatActivity {

    ActivitySignInBinding binding;
    TextInputLayout email_layout, password_layout;
    TextInputEditText email_input, password_input;
    String email, password;
    String symbol = ".teacher@";

    FirebaseAuth _auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.circleImage.setImageResource(R.drawable.teacher_icon);
        binding.textHeading.setText(R.string.teacher_log_in);

        email_layout = binding.emailLayout;
        password_layout = binding.passwordLayout;
        email_input = binding.emailInput;
        password_input = binding.passwordInput;

        binding.loginBtn.setOnClickListener(view -> {
            email = String.valueOf(email_input.getText());
            password = String.valueOf(password_input.getText());

            email_layout.setError(null);
            password_layout.setError(null);

            if(validatedData(email, password)) {
                codeForAuth(email, password);
            }
        });
    }

    private void codeForAuth(String email, String password) {
        _auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()) {
                        FirebaseUser user = _auth.getCurrentUser();
                        if(user != null) {
                            Toast.makeText(TeacherSignInActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(TeacherSignInActivity.this, TeacherPageActivity.class);
                            startActivity(intent);
                            TeacherSignInActivity.this.finish();
                        }
                    }
                    else {
                        Toast.makeText(TeacherSignInActivity.this, "Please verify your email and password!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private boolean validatedData(String email, String password) {
        if(email.isEmpty())
            email_layout.setError("Email cannot be empty");
        else if(!email.contains(symbol))
            email_layout.setError("Please enter valid email id");
        else if(password.isEmpty())
            password_layout.setError("Password cannot be empty");
        else
            return true;

        return false;
    }
}