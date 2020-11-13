package com.example.thecvmaker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EducationActivity extends AppCompatActivity {

    private EditText startDateEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);
        initViews();
        Intent intent = getIntent();
        if (intent.getStringExtra("FromActivity").equals("PersonalDetailsActivity")) {
            CvUser obj = intent.getParcelableExtra("Example");
            String name = obj.getName();
            Log.d("CheckParcelable", name);
        }
    }

    private void initViews() {
        startDateEdt = findViewById(R.id.edu_start_date_edt);
    }
}