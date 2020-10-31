package com.example.thecvmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;

public class WelcomeActivity extends AppCompatActivity {

    private MaterialButton createCvButton,updateCvButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initViews();
        createCvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        createCvButton=findViewById(R.id.create_cv_btn);
        updateCvButton=findViewById(R.id.update_cv_btn);
    }

}