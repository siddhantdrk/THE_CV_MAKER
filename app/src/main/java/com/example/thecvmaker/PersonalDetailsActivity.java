package com.example.thecvmaker;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.util.Calendar;

public class PersonalDetailsActivity extends AppCompatActivity {

    EditText EditTextFullName;
    EditText editTextEmail;
    EditText editTextPhone;
    EditText editTextNationality;
    EditText editTextDOB;
    // EditText editTextGender;
    EditText editTextLanguage;
    EditText editTextAddress;
    RadioGroup GenderRadioGrp;
    RadioButton GenderRadioBtn;
    MaterialButton nextToEducation;

    private String Name;
    private String Address;
    private String PhoneNumber;
    private String EmailAddress;
    private String Dob;
    private String Nationality;
    private String Gender;
    private String Language;

    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        initViews();
        setPersonalDetails();


        editTextDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(PersonalDetailsActivity.this,
                        mDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                dialog.show();


            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                editTextDOB.setText(date);
            }
        };
        if (GenderRadioGrp.isActivated()) {
            int selectedId = GenderRadioGrp.getCheckedRadioButtonId();
            GenderRadioBtn = findViewById(selectedId);
            Gender = GenderRadioBtn.getText().toString();
        }
        CvUser obj = new CvUser();
        obj.setName(Name);
        obj.setAddress(Address);
        obj.setPhoneNumber(PhoneNumber);
        nextToEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonalDetailsActivity.this, EducationActivity.class);
                intent.putExtra("Example", obj);

                startActivity(intent);
            }
        });


    }

    private void initViews() {
        EditTextFullName = findViewById(R.id.full_name_edt);
        editTextDOB = findViewById(R.id.date_of_birth_edt);
        editTextLanguage = findViewById(R.id.languages_edt);
        editTextEmail = findViewById(R.id.email_edt);
        editTextAddress = findViewById(R.id.address_edt);
        editTextPhone = findViewById(R.id.phone_edt);
        editTextNationality = findViewById(R.id.nationality_edt);
        GenderRadioGrp = findViewById(R.id.genderGrp);
        nextToEducation = findViewById(R.id.next_Education);
    }

    private void setPersonalDetails() {
        Name = EditTextFullName.getText().toString();
        Address = editTextAddress.getText().toString();
        PhoneNumber = editTextPhone.getText().toString();
        Dob = editTextDOB.getText().toString();
        EmailAddress = editTextEmail.getText().toString();
        Nationality = editTextNationality.getText().toString();
        //Gender = editText7.getText().toString();
        Language = editTextLanguage.getText().toString();
    }

}