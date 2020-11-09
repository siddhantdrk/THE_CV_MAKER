package com.example.thecvmaker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView personalDetails;
    TextView educationalDetails;
    TextView workExperience;
    TextView projectDetailContributions;
    TextView OtherSkills;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        personalDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PersonalDetailsActivity.class);
                startActivity(intent);


            }
        });
    }

    public void initViews() {
        personalDetails = findViewById(R.id.personal_details_txt);
        educationalDetails = findViewById(R.id.educational_details_txt);
        workExperience = findViewById(R.id.work_experience_txt);
        projectDetailContributions = findViewById(R.id.project_con_txt);
        OtherSkills = findViewById(R.id.other_skills_txt);

    }

}