package com.example.hitkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(() -> {
            //This method will be executed once the timer is over
            // Start your app main activity
            Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
            startActivity(i);
            // close this activity
            finish();
        }, 2000);

    }
}