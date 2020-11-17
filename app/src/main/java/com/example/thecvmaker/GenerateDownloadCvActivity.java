package com.example.thecvmaker;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class GenerateDownloadCvActivity extends AppCompatActivity {

    private MaterialButton GeneratePreviewCvBtn, DownloadCvBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_download_cv);
        initViews();
    }

    private void initViews() {
        GeneratePreviewCvBtn = findViewById(R.id.generate_cv_btn);
        DownloadCvBtn = findViewById(R.id.download_cv_btn);
    }
}