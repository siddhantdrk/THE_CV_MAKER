package com.example.thecvmaker;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.material.button.MaterialButton;

import java.io.FileNotFoundException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    TextView personalDetails;
    TextView educationalDetails;
    TextView workExperience;
    TextView projectDetailContributions;
    TextView OtherSkills;
    private static final int PICK_PHOTO = 1;
    CircleImageView image;
    MaterialButton GeneratePdfBtn;
    Uri selectedImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        personalDetails.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, PersonalDetailsActivity.class);
            intent.putExtra("FromActivity", "MainActivity");
            startActivity(intent);
        });

        educationalDetails.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, EducationActivity.class);
            intent.putExtra("FromActivity", "MainActivity");
            startActivity(intent);
        });
        workExperience.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, WorkExperienceActivity.class);
            intent.putExtra("FromActivity", "MainActivity");
            startActivity(intent);
        });
        projectDetailContributions.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ProjectContributionActivity.class);
            intent.putExtra("FromActivity", "MainActivity");
            startActivity(intent);
        });
        OtherSkills.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, OthersAndSkillsActivity.class);
            intent.putExtra("FromActivity", "MainActivity");
            startActivity(intent);
        });
    }

    public void initViews() {
        personalDetails = findViewById(R.id.personal_details_txt);
        educationalDetails = findViewById(R.id.educational_details_txt);
        workExperience = findViewById(R.id.work_experience_txt);
        projectDetailContributions = findViewById(R.id.project_con_txt);
        OtherSkills = findViewById(R.id.other_skills_txt);
        image = findViewById(R.id.upload_user_photo);
        GeneratePdfBtn = findViewById(R.id.generate_pdf_btn);
    }
}