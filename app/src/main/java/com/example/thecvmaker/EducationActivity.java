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
        CvUser obj = (CvUser) intent.getParcelableExtra("Example");
        String name = obj.getName();

        if (name == "Mandar") {
            Intent intent1 = new Intent(EducationActivity.this, ProjectContributionActivity.class);
            startActivity(intent1);
        }
        Log.d("qwerty", name);
    }

    private void initViews() {
        startDateEdt = findViewById(R.id.start_date_edt);
    }
}