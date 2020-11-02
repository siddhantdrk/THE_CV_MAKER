package com.example.thecvmaker;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class PersonalDetails extends AppCompatActivity {

    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    EditText editText5;
    EditText editText6;
    EditText editText7;
    EditText editText8;
    ImageButton calender;
    private String name;
    private String address;
    private String phoneNumber;
    private String emailAddress;
    private String dob;
    private String nationality;
    private String gender;
    private String language;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        name = editText1.getText().toString();
        address = editText2.getText().toString();
        phoneNumber = editText3.getText().toString();
        dob = editText4.getText().toString();
        emailAddress = editText5.getText().toString();
        nationality = editText7.getText().toString();
        //gender = editText7.getText().toString();
        language = editText8.getText().toString();
        calender = findViewById(R.id.CalendearButton);
        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(PersonalDetails.this,
                        mDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                editText4.setText(date);


            }
        };


    }

    public void init() {
        EditText editText1 = findViewById(R.id.editText1Name);
        EditText editText2 = findViewById(R.id.editText2Address);
        EditText editText3 = findViewById(R.id.editText3Phone);
        EditText editText4 = findViewById(R.id.editText4DOB);
        EditText editText5 = findViewById(R.id.editText5Email);
        EditText editText7 = findViewById(R.id.editText6Nationality);

        EditText editText8 = findViewById(R.id.editText7Language);
    }

}