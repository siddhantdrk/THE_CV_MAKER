package com.example.thecvmaker;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.thecvmaker.models.EducationItem;
import com.example.thecvmaker.models.ProjectContributionItem;
import com.example.thecvmaker.models.SkillsItem;
import com.example.thecvmaker.models.WorkExpItem;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GenerateDownloadCvActivity extends AppCompatActivity {

    private MaterialButton GeneratePreviewCvBtn, DownloadCvBtn;
    private UserCv userCv;
    private TextView Test;
    private String StartDate;
    private String EndDate;
    private String SchoolInstitute;
    private String Degree;
    private String Description;
    private List<EducationItem> EductionList;
    private List<WorkExpItem> WorkExperienceList;
    private List<ProjectContributionItem> ProjectContributionList;
    private List<SkillsItem> OtherSkillList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_download_cv);
        initViews();

        Intent intent = getIntent();
        if (intent.getStringExtra("FromActivity").equals("OthersAndSkillsActivity")) {
            userCv = intent.getParcelableExtra("SharedUserCv");
        }

        ActivityCompat.requestPermissions(GenerateDownloadCvActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        DownloadCvBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                createMyPDF(view);
            }
        });

        // here we retrieving data of education detail
        String extractEducationString = userCv.getEducationListString();
        Type EducationListType = new TypeToken<ArrayList<EducationItem>>(){}.getType();
        EductionList = new Gson().fromJson(extractEducationString, EducationListType);
        //Test.setText(EductionList.get(0).getEduSchoolInstitute());

        // here we retrieving data of workExperience detail
        String extractWorkExperienceString = userCv.getWorkExpListString();
        Type WorkExperienceListType = new TypeToken<ArrayList<WorkExpItem>>(){}.getType();
        WorkExperienceList = new Gson().fromJson(extractWorkExperienceString, WorkExperienceListType);

        // here we retrieving data of projectContribution detail
        String extractProjectContributionString = userCv.getProjectContributionListString();
        Type ProjectContributionListType = new TypeToken<ArrayList<ProjectContributionItem>>(){}.getType();
        ProjectContributionList = new Gson().fromJson(extractProjectContributionString, ProjectContributionListType);

        // here we retrieving data of othersSkills detail
        String extractOtherSkillString = userCv.getSkillsOthersListString();
        Type OtherSkillListType = new TypeToken<ArrayList<SkillsItem>>(){}.getType();
        OtherSkillList = new Gson().fromJson(extractOtherSkillString, OtherSkillListType);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void createMyPDF(View view) {

        PdfDocument myPdfDocument = new PdfDocument();
        PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(300, 600, 1).create();
        PdfDocument.Page myPage = myPdfDocument.startPage(myPageInfo);

        Paint myPaint = new Paint();
        String myString ="Personal Detail"+"\n"+ userCv.getName()+"\n"+userCv.getEmailAddress()+"\n"+userCv.getPhoneNumber()+"\n"+userCv.getNationality()
                +"\n"+userCv.getDob()+"\n"+userCv.getGender()+"\n"+userCv.getLanguage()+"\n"+userCv.getAddress()+"\n\n"+
                "Education Detail"+"\n"+ EductionList.get(0).getEduStartDate()+"\n"+ EductionList.get(0).getEduEndDate()
                +"\n"+ EductionList.get(0).getEduSchoolInstitute()+"\n"+ EductionList.get(0).getEduDegreeTitle()
                +"\n"+ EductionList.get(0).getEduDescription()+"\n\n"+"WorkExperience Detail"+"\n"
                +WorkExperienceList.get(0).getStart_date() +"\n"+WorkExperienceList.get(0).getEnd_date()
                +"\n"+WorkExperienceList.get(0).getCompany() +"\n"+WorkExperienceList.get(0).getPosition()
                +"\n"+WorkExperienceList.get(0).getDescription()+"\n\n"+"ProjectContribution Detail"+"\n"
                +ProjectContributionList.get(0).getProjectStartDate()+"\n"+ProjectContributionList.get(0).getProjectStartDate()
                +"\n"+ProjectContributionList.get(0).getProjectStartDate()+"\n"+ProjectContributionList.get(0).getProjectStartDate()
                +"\n"+ProjectContributionList.get(0).getProjectStartDate()+"\n\n"+"OtherSkill detail"+"\n"
                +OtherSkillList.get(0).getHobby()+"\n"+OtherSkillList.get(0).getSkill_description();


        int x = 10, y = 25;


        for (String line : myString.split("\n")) {
            myPage.getCanvas().drawText(line, x, y, myPaint);
            y += myPaint.descent() - myPaint.ascent();
        }

        myPdfDocument.finishPage(myPage);

        String myFilePath = Environment.getExternalStorageDirectory().getPath() + "/myPDFFile.pdf";
        File myFile = new File(myFilePath);
        try {
            myPdfDocument.writeTo(new FileOutputStream(myFile));
        } catch (Exception e) {
            e.printStackTrace();
            //myEditText.setText("Error");
        }

        myPdfDocument.close();
    }

    private void initViews() {
        GeneratePreviewCvBtn = findViewById(R.id.generate_cv_btn);
        DownloadCvBtn = findViewById(R.id.download_cv_btn);
        Test = (TextView) findViewById(R.id.test);
    }
}