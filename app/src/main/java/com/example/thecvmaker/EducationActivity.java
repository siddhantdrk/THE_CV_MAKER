package com.example.thecvmaker;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EducationActivity extends AppCompatActivity {

    private EditText startDateEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);
        initViews();
    }

    private void initViews() {
        startDateEdt = findViewById(R.id.start_date_edt);
    }
}