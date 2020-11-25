package com.example.thecvmaker;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

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
    private int noOfEducationList;
    private int noOfWorkExperienceList;
    private int noOfProjectContributionList;
    private int noOfOthersSkillsList;
    int skillCount=0;
    int proCount =0;
    int workCount=0;
    int eduCount=0;
    private static final int PICK_PHOTO = 1;
    CircleImageView image;
    Bitmap scaleBitmap;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_download_cv);
        initViews();

        Intent intent = getIntent();
        if (intent.getStringExtra("FromActivity").equals("OthersAndSkillsActivity")) {
            userCv = intent.getParcelableExtra("SharedUserCv");
        }

        image = findViewById(R.id.upload_user_photo);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(GenerateDownloadCvActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PICK_PHOTO);
            }
        });

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
        noOfEducationList = EductionList.size();

        // here we retrieving data of workExperience detail
        String extractWorkExperienceString = userCv.getWorkExpListString();
        Type WorkExperienceListType = new TypeToken<ArrayList<WorkExpItem>>(){}.getType();
        WorkExperienceList = new Gson().fromJson(extractWorkExperienceString, WorkExperienceListType);
        noOfWorkExperienceList = WorkExperienceList.size();

        // here we retrieving data of projectContribution detail
        String extractProjectContributionString = userCv.getProjectContributionListString();
        Type ProjectContributionListType = new TypeToken<ArrayList<ProjectContributionItem>>() {
        }.getType();
        ProjectContributionList = new Gson().fromJson(extractProjectContributionString, ProjectContributionListType);
        noOfProjectContributionList = ProjectContributionList.size();

        // here we retrieving data of othersSkills detail
        String extractOtherSkillString = userCv.getSkillsOthersListString();
        Type OtherSkillListType = new TypeToken<ArrayList<SkillsItem>>() {
        }.getType();
        OtherSkillList = new Gson().fromJson(extractOtherSkillString, OtherSkillListType);
        noOfOthersSkillsList = OtherSkillList.size();

//        MyDbHelper db = new MyDbHelper(GenerateDownloadCvActivity.this);
//        db.addCv(userCv);


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void createMyPDF(View view) {

        PdfDocument myPdfDocument = new PdfDocument();
        PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder( 595,842,1).create();
        PdfDocument.Page myPage = myPdfDocument.startPage(myPageInfo);

        Paint myPaint = new Paint();
        myPaint.setTextSize(12);

        Canvas canvas = myPage.getCanvas();
        canvas.drawBitmap(scaleBitmap,200,10,myPaint);

        String myPersonalString =userCv.getName()+"\n"+""+userCv.getEmailAddress()+"\n"+userCv.getPhoneNumber()+"\n"+userCv.getNationality()
                +"\n"+userCv.getDob()+"\n"+userCv.getGender()+"\n"+userCv.getLanguage()+"\n"+userCv.getAddress()+"\n"+" ";

        int x=10,y = 25;
        for (String line : myPersonalString.split("\n")) {
            myPage.getCanvas().drawText(line, x, y, myPaint);
            y += myPaint.descent() - myPaint.ascent();
        }

        eduCount=0;
        for(int i=0; i<noOfEducationList; i++)
        {
            String myEducationalString =  EductionList.get(eduCount).getEduStartDate()+" - "+ EductionList.get(eduCount).getEduEndDate()
                    +"\n"+ EductionList.get(eduCount).getEduSchoolInstitute()+"\n"+ EductionList.get(eduCount).getEduDegreeTitle()
                    +"\n"+ EductionList.get(eduCount).getEduDescription()+"\n"+" ";

            for (String line : myEducationalString.split("\n")) {
                myPage.getCanvas().drawText(line, x, y, myPaint);
                y += myPaint.descent() - myPaint.ascent();
            }
            eduCount++;
        }

        workCount=0;
        for(int i=0; i<noOfWorkExperienceList; i++)
        {
            //int x = 10, y = 25;
            String myWorkExperienceString =  WorkExperienceList.get(workCount).getStart_date() +" - "+WorkExperienceList.get(workCount).getEnd_date()
                    +"\n"+WorkExperienceList.get(workCount).getCompany() +"\n"+WorkExperienceList.get(workCount).getPosition()
                    +"\n"+WorkExperienceList.get(workCount).getDescription()+"\n"+" ";

            for (String line : myWorkExperienceString.split("\n")) {
                myPage.getCanvas().drawText(line, x, y, myPaint);
                y += myPaint.descent() - myPaint.ascent();
            }
            workCount++;
        }

        proCount =0;
        for(int i=0; i<noOfProjectContributionList; i++)
        {
            //int x = 10, y = 25;
            String myProjectContributionString =  ProjectContributionList.get(proCount).getProjectStartDate()+" - "+ProjectContributionList.get(proCount).getProjectEndDate()
                    +"\n"+ProjectContributionList.get(proCount).getProjectTitle()+"\n"+ProjectContributionList.get(proCount).getProjectCategory()
                    +"\n"+ProjectContributionList.get(proCount).getProjectDescription()+"\n"+" ";

            for (String line : myProjectContributionString.split("\n")) {
                myPage.getCanvas().drawText(line, x, y, myPaint);
                y += myPaint.descent() - myPaint.ascent();
            }
            proCount++;
        }

        skillCount=0;
        for(int i=0; i<noOfOthersSkillsList; i++)
        {
            //int x = 10, y = 25;
            String myOthersSkillsString =  OtherSkillList.get(skillCount).getHobby()+"\n"+OtherSkillList.get(skillCount).getSkill_description()+"\n"+" ";

            for (String line : myOthersSkillsString.split("\n")) {
                myPage.getCanvas().drawText(line, x, y, myPaint);
                y += myPaint.descent() - myPaint.ascent();
            }
            skillCount++;
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


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PICK_PHOTO) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, PICK_PHOTO);
            } else {

                Toast.makeText(getApplicationContext(), "You don't have permission to access this media", Toast.LENGTH_SHORT);
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            // String[] filePathColumn = {MediaStore.Images.Media.DATA};
            try {
                InputStream inputStream = getContentResolver().openInputStream(selectedImage);

                bitmap = BitmapFactory.decodeStream(inputStream);
                image.setImageBitmap(bitmap);
                BitmapDrawable drawable = (BitmapDrawable) image.getDrawable();
                bitmap = drawable.getBitmap();
                scaleBitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, false);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}