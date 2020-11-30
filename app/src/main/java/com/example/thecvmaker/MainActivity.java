package com.example.thecvmaker;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.thecvmaker.Utils.DbBitmapUtility;
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
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    private static final int PICK_PHOTO = 1;
    TextView personalDetails;
    TextView educationalDetails;
    TextView workExperience;
    TextView projectDetailContributions;
    TextView OtherSkills;
    CircleImageView image;
    MaterialButton GeneratePdfBtn;
    Uri selectedImage;
    int skillCount = 0;
    int proCount = 0;
    int workCount = 0;
    int eduCount = 0;
    Bitmap scaleBitmap;
    Bitmap bitmap;
    private List<EducationItem> EductionList;
    private List<WorkExpItem> WorkExperienceList;
    private List<ProjectContributionItem> ProjectContributionList;
    private List<SkillsItem> OtherSkillList;
    private int noOfEducationList;
    private int noOfWorkExperienceList;
    private int noOfProjectContributionList;
    private int noOfOthersSkillsList;
    private boolean isImageSelected = false;
    private String m_Text, fileName;
    private MyDbHelper db;

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

        MyDbHelper dbImage = new MyDbHelper(MainActivity.this);
        Bitmap bitmap = DbBitmapUtility.getImage(dbImage.getImageByte());
        image.setImageBitmap(bitmap);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PICK_PHOTO);
                isImageSelected = true;
            }
        });

        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        GeneratePdfBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                db = new MyDbHelper(MainActivity.this);
                UserCv userCv = db.getCv();
                if (isImageSelected) {
                    byte[] imageByte;
                    try {
                        imageByte = DbBitmapUtility.getBytes(scaleBitmap);
                        db.addCv(userCv, db.getCount(), imageByte);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    scaleBitmap = bitmap;
                }
                enterPdfFileName(userCv);
            }
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

    private void enterPdfFileName(UserCv userCv) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter name for pdf");
        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                fileName = "/" + input.getText().toString() + ".pdf";
                createMyPDF(userCv);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void createMyPDF(UserCv userCv) {
        String extractEducationString = userCv.getEducationListString();
        Type EducationListType = new TypeToken<ArrayList<EducationItem>>() {
        }.getType();
        EductionList = new Gson().fromJson(extractEducationString, EducationListType);
        //Test.setText(EductionList.get(0).getEduSchoolInstitute());
        noOfEducationList = EductionList.size();

        // here we retrieving data of workExperience detail
        String extractWorkExperienceString = userCv.getWorkExpListString();
        Type WorkExperienceListType = new TypeToken<ArrayList<WorkExpItem>>() {
        }.getType();
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

        PdfDocument myPdfDocument = new PdfDocument();
        PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(595, 842, 1).create();
        PdfDocument.Page myPage = myPdfDocument.startPage(myPageInfo);
//        if(y>800){
//            x=290;y=300;
//        }
        Paint myPaint = new Paint();
        myPaint.setTextSize(16);
        int x = 40, y = 80;

        Canvas canvas = myPage.getCanvas();
        canvas.drawBitmap(scaleBitmap, 360, 70, myPaint);

        Paint namePaint = new Paint();
        namePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        namePaint.setTextSize(36);
        namePaint.setColor(Color.BLACK);
        canvas.drawText(userCv.getName(), x, y, namePaint);
        y += namePaint.descent() - namePaint.ascent();


        String myPersonalString = userCv.getEmailAddress() + "\n" + userCv.getPhoneNumber() + "\n" + userCv.getNationality()
                + "\n" + userCv.getDob() + "\n" + userCv.getGender() + "\n" + userCv.getLanguage() + "\n" + userCv.getAddress() + "\n" + " ";

        for (String line : myPersonalString.split("\n")) {
            myPage.getCanvas().drawText(line, x, y, myPaint);
            y += myPaint.descent() - myPaint.ascent();
//            if(y>800){
//                x=300;y=300;
//            }
        }
        int yWork = y;

        Paint headingPaint = new Paint();
        headingPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        headingPaint.setTextSize(26);
        headingPaint.setColor(Color.BLUE);
        canvas.drawText("Education", x, y, headingPaint);
        y += 5;

        Paint eduLinePaint = new Paint();
        eduLinePaint.setColor(Color.BLACK);
        eduLinePaint.setStrokeWidth(1f);
        canvas.drawLine(x, y, x + 240, y, eduLinePaint);
        y += namePaint.descent() - namePaint.ascent();
        if (y > 800) {
            x = 300;
            y = 300;
        }

        // Vertical Line and border
        Paint verticalLine = new Paint();
        verticalLine.setColor(Color.BLACK);
        verticalLine.setStrokeWidth(1f);
        canvas.drawLine(290, 230, 290, 790, eduLinePaint);
        //border
        canvas.drawLine(10, 10, 10, 832, eduLinePaint);
        canvas.drawLine(585, 10, 585, 832, eduLinePaint);
        canvas.drawLine(10, 10, 585, 10, eduLinePaint);
        canvas.drawLine(10, 832, 585, 832, eduLinePaint);

        canvas.drawLine(12, 12, 12, 830, eduLinePaint);
        canvas.drawLine(583, 12, 583, 830, eduLinePaint);
        canvas.drawLine(12, 12, 583, 12, eduLinePaint);
        canvas.drawLine(12, 830, 583, 830, eduLinePaint);

        int tName = 0;  // To detect name of company, college etc...
        eduCount = 0;
        for (int i = 0; i < noOfEducationList; i++) {
            String myEducationalString = EductionList.get(eduCount).getEduStartDate() + " - " + EductionList.get(eduCount).getEduEndDate()
                    + "\n" + EductionList.get(eduCount).getEduSchoolInstitute() + "\n" + EductionList.get(eduCount).getEduDegreeTitle()
                    + "\n" + EductionList.get(eduCount).getEduDescription() + "\n" + " ";
            tName = 1;
            for (String line : myEducationalString.split("\n")) {
                if (tName == 2) {
                    myPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
                    myPage.getCanvas().drawText(line, x, y, myPaint);
                    myPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
                } else myPage.getCanvas().drawText(line, x, y, myPaint);
                y += myPaint.descent() - myPaint.ascent();
                if (y > 800) {
                    x = 300;
                    y = 300;
                }
                tName++;
            }
            eduCount++;
        }

        canvas.drawText("Work Experience", x, y, headingPaint);
        y += 5;
        Paint workLinePaint = new Paint();
        workLinePaint.setColor(Color.BLACK);
        workLinePaint.setStrokeWidth(1f);
        canvas.drawLine(x, y, x + 240, y, workLinePaint);
        y += namePaint.descent() - namePaint.ascent();
        if (y > 800) {
            x = 300;
            y = 300;
        }


        workCount = 0;
        for (int i = 0; i < noOfWorkExperienceList; i++) {
            String myWorkExperienceString = WorkExperienceList.get(workCount).getStart_date() + " - " + WorkExperienceList.get(workCount).getEnd_date()
                    + "\n" + "Company: " + WorkExperienceList.get(workCount).getCompany() + "\n" + WorkExperienceList.get(workCount).getPosition()
                    + "\n" + WorkExperienceList.get(workCount).getDescription() + "\n" + " ";
            tName = 1;
            for (String line : myWorkExperienceString.split("\n")) {
                if (tName == 2) {
                    myPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
                    myPage.getCanvas().drawText(line, x, y, myPaint);
                    myPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
                } else myPage.getCanvas().drawText(line, x, y, myPaint);
                y += myPaint.descent() - myPaint.ascent();
                if (y > 800) {
                    x = 300;
                    y = 300;
                }
                tName++;
            }

            workCount++;
        }

        int ySkill = yWork;
        canvas.drawText("Project Contribution", x, y, headingPaint);
        y += 5;

        Paint ProLinePaint = new Paint();
        ProLinePaint.setColor(Color.BLACK);
        ProLinePaint.setStrokeWidth(1f);
        canvas.drawLine(x, y, x + 240, y, ProLinePaint);
        y += namePaint.descent() - namePaint.ascent();
        if (y > 800) {
            x = 300;
            y = 300;
        }
        proCount = 0;
        for (int i = 0; i < noOfProjectContributionList; i++) {
            //int x = 10, y = 25;
            String myProjectContributionString = ProjectContributionList.get(proCount).getProjectStartDate() + " - " + ProjectContributionList.get(proCount).getProjectEndDate()
                    + "\n" + ProjectContributionList.get(proCount).getProjectTitle() + "\n" + ProjectContributionList.get(proCount).getProjectCategory()
                    + "\n" + ProjectContributionList.get(proCount).getProjectDescription() + "\n" + " ";
            tName = 1;
            for (String line : myProjectContributionString.split("\n")) {
                if (tName == 2) {
                    myPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
                    myPage.getCanvas().drawText(line, x, y, myPaint);
                    myPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
                } else myPage.getCanvas().drawText(line, x, y, myPaint);
                y += myPaint.descent() - myPaint.ascent();
                if (y > 800) {
                    x = 300;
                    y = 300;
                }
                tName++;
            }
            proCount++;
        }

        canvas.drawText("Skills", x, y, headingPaint);
        y += 5;
        if (y > 800) {
            x = 300;
            y = 300;
        }

        Paint skillLinePaint = new Paint();
        skillLinePaint.setColor(Color.BLACK);
        skillLinePaint.setStrokeWidth(1f);
        canvas.drawLine(x, y, x + 240, y, skillLinePaint);
        y += namePaint.descent() - namePaint.ascent();
        if (y > 800) {
            x = 300;
            y = 300;
        }

        skillCount = 0;
        for (int i = 0; i < noOfOthersSkillsList; i++) {

            String myOthersSkillsString = OtherSkillList.get(skillCount).getHobby() + "\n" + OtherSkillList.get(skillCount).getSkill_description() + "\n" + " ";
            tName = 1;
            for (String line : myOthersSkillsString.split("\n")) {
                if (tName == 1) {
                    myPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
                    myPage.getCanvas().drawText(line, x, y, myPaint);
                    myPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
                } else myPage.getCanvas().drawText(line, x, y, myPaint);
                y += myPaint.descent() - myPaint.ascent();
                if (y > 800) {
                    x = 300;
                    y = 300;
                }
                tName++;
            }
            skillCount++;
        }

        myPdfDocument.finishPage(myPage);

        String myFilePath = Environment.getExternalStorageDirectory().getPath() + fileName;
        Toast.makeText(this, myFilePath, Toast.LENGTH_SHORT).show();
        File myFile = new File(myFilePath);
        try {
            myPdfDocument.writeTo(new FileOutputStream(myFile));
        } catch (Exception e) {
            e.printStackTrace();
            //myEditText.setText("Error");
        }

        myPdfDocument.close();
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
                scaleBitmap = Bitmap.createScaledBitmap(bitmap, 160, 160, false);
                isImageSelected = true;

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}