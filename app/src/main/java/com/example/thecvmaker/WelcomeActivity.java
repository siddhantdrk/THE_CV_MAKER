package com.example.thecvmaker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
                Intent intent = new Intent(WelcomeActivity.this, PersonalDetailsActivity.class);
                intent.putExtra("FromActivity", "WelcomeActivity");
                startActivity(intent);
            }
        });
        MyDbHelper db = new MyDbHelper(WelcomeActivity.this);


        updateCvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (db.getCount() == 0) {
                    Toast.makeText(WelcomeActivity.this, "First Make Your Cv", Toast.LENGTH_SHORT);
                } else {
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    startActivity(intent);
                }

            }
        });
    }

    private void initViews() {
        createCvButton=findViewById(R.id.create_cv_btn);
        updateCvButton=findViewById(R.id.update_cv_btn);

    }

}