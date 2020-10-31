package com.example.thecvmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

public class SplashActivity extends AppCompatActivity {
     private final int splashTime=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this,WelcomeActivity.class);
                startActivity(intent);
                finish();
            }
        }, splashTime);
    }
}