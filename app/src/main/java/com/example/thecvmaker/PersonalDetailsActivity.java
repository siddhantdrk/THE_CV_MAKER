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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.util.Calendar;
import java.util.List;

public class PersonalDetailsActivity extends AppCompatActivity {

    private EditText EditTextFullName;
    private EditText editTextEmail;
    private EditText editTextPhone;
    private EditText editTextNationality;
    private EditText editTextDOB;
    // EditText editTextGender;
    private EditText editTextLanguage;
    private EditText editTextAddress;
    private RadioGroup GenderRadioGrp;
    private RadioButton GenderRadioBtn;
    private MaterialButton nextToEducation;

    private String Name;
    private String Address;
    private String PhoneNumber;
    private String EmailAddress;
    private String Dob;
    private String Nationality;
    private String Gender;
    private String Language;

    private UserCv userCV;
    List<UserCv> CvList;

    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);


        Intent intent = getIntent();
        String msg = intent.getStringExtra("FromActivity");

        if (msg.equals("WelcomeActivity")) {
            initViews();

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

            nextToEducation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setPersonalDetails();
                    if (isAllDetailsFilled()) {
                        userCV = new UserCv();
                        setUserCVPersonalDetails();
                        Intent intent = new Intent(PersonalDetailsActivity.this, EducationActivity.class);
                        intent.putExtra("SharedUserCv", userCV);
                        intent.putExtra("FromActivity", "PersonalDetailsActivity");
                        startActivity(intent);
                    } else {
                        Toast.makeText(PersonalDetailsActivity.this, "Please check and fill all the Details", Toast.LENGTH_LONG).show();
                    }
                }
            });

        }

        if (msg.equals("MainActivity")) {
            initViews();
            MyDbHelper db = new MyDbHelper(PersonalDetailsActivity.this);
            CvList = db.getAllCv();
            UserCv demoitem = new UserCv();
            demoitem = CvList.get(0);
            EditTextFullName.setText(demoitem.getName());
            editTextDOB.setText(demoitem.getDob());
            editTextLanguage.setText(demoitem.getLanguage());
            editTextNationality.setText(demoitem.getNationality());
            editTextEmail.setText(demoitem.getEmailAddress());
            editTextAddress.setText(demoitem.getAddress());
            editTextPhone.setText(demoitem.getPhoneNumber());

        }


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
        Language = editTextLanguage.getText().toString();
        int selectedId = GenderRadioGrp.getCheckedRadioButtonId();
        if (selectedId == -1) {
            Gender = null;
        } else {
            GenderRadioBtn = findViewById(selectedId);
            Gender = GenderRadioBtn.getText().toString();
        }

    }

    private boolean isAllDetailsFilled() {
        return Name != null && Name.length() != 0 && Address != null && Address.length() != 0 && PhoneNumber != null && PhoneNumber.length() != 0 &&
                Dob != null && Dob.length() != 0 && EmailAddress != null && EmailAddress.length() != 0 && Nationality != null && Nationality.length() != 0
                && Language != null && Language.length() != 0 && Gender != null && Gender.length() != 0;
    }

    public void setUserCVPersonalDetails() {
        userCV.setName(Name);
        userCV.setAddress(Address);
        userCV.setDob(Dob);
        userCV.setEmailAddress(EmailAddress);
        userCV.setGender(Gender);
        userCV.setLanguage(Language);
        userCV.setNationality(Nationality);
        userCV.setPhoneNumber(PhoneNumber);
    }

}