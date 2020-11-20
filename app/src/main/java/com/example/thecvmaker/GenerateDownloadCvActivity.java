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

import com.google.android.material.button.MaterialButton;

import java.io.File;
import java.io.FileOutputStream;

public class GenerateDownloadCvActivity extends AppCompatActivity {

    private MaterialButton GeneratePreviewCvBtn, DownloadCvBtn;
    private UserCv userCv;
    private TextView Test;

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
        Test.setText(userCv.getName());

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void createMyPDF(View view) {

        PdfDocument myPdfDocument = new PdfDocument();
        PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(300, 600, 1).create();
        PdfDocument.Page myPage = myPdfDocument.startPage(myPageInfo);

        Paint myPaint = new Paint();
        String myString = userCv.getName()+"\n"+userCv.getEmailAddress()+"\n"+userCv.getPhoneNumber()+"\n"+userCv.getNationality()
                +"\n"+userCv.getDob()+"\n"+userCv.getGender()+"\n"+userCv.getLanguage()+"\n"+userCv.getAddress();
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
        Test = findViewById(R.id.test);
    }
}