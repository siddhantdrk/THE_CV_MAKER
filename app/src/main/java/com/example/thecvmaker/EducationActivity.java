package com.example.thecvmaker;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thecvmaker.adapter.EducationRvAdapter;
import com.example.thecvmaker.models.EducationItem;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EducationActivity extends AppCompatActivity {

    private EditText eduStartDateEdt, eduEndDateEdt, eduSchoolInstituteEdt, eduDescriptionEdt;
    private CheckBox eduCurrentCheckBox;
    private Spinner eduDegreeSpinner;
    private TextView addEducationBtn;
    private MaterialButton nextToWorkExperience;
    private MaterialButton updateEducation;
    boolean checkIntent = true;
    private RecyclerView recyclerView;
    private EducationRvAdapter recyclerViewAdapter;
    private EducationItem educationItem;
    private UserCv userCv;
    private DatePickerDialog.OnDateSetListener mDateSetListener1;
    private DatePickerDialog.OnDateSetListener mDateSetListener2;
    private UserCv educationToupdate;
    private List<EducationItem> EducationList;
    private List<EducationItem> EducationListDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);
        initViews();
        Intent intent = getIntent();

        if (intent.getStringExtra("FromActivity").equals("PersonalDetailsActivity")) {
            userCv = intent.getParcelableExtra("SharedUserCv");
            //Dummy WorkExperience List
            EducationList = new ArrayList<>();
            EducationList.add(new EducationItem());
            EducationList.add(new EducationItem());
            EducationList.add(new EducationItem());
            setEducationRecyclerview(EducationList);


        } else {
            checkIntent = false;
            nextToWorkExperience.setVisibility(View.GONE);
            updateEducation.setVisibility(View.VISIBLE);
            MyDbHelper db = new MyDbHelper(EducationActivity.this);
            educationToupdate = db.getCv();
            String extractEducationString = educationToupdate.getEducationListString();
            Type EducationListType = new TypeToken<ArrayList<EducationItem>>() {
            }.getType();
            EducationListDb = new Gson().fromJson(extractEducationString, EducationListType);
            Toast.makeText(this, "" + EducationListDb.size(), Toast.LENGTH_SHORT).show();
            setEducationRecyclerview(EducationListDb);

            updateEducation.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    if (EducationListDb.size() != 0) {
                        String EducationListString = new Gson().toJson(EducationListDb);
                        // userCv.setEducationListString(EducationListString);
                        db.updateEducationDetails(EducationListString);
                        Toast.makeText(EducationActivity.this, "Education Details Updated" + EducationListDb.size(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(EducationActivity.this, "Please add your education details !", Toast.LENGTH_LONG).show();
                    }

                }
            });
        }


            eduStartDateEdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Calendar calendar = Calendar.getInstance();
                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH);
                    int day = calendar.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog dialog = new DatePickerDialog(EducationActivity.this,
                            mDateSetListener1, year, month, day);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                    dialog.show();
                }
            });

            mDateSetListener1 = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    month = month + 1;
                    String date = day + "/" + month + "/" + year;
                    eduStartDateEdt.setText(date);
                }
            };

            eduEndDateEdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Calendar calendar = Calendar.getInstance();
                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH);
                    int day = calendar.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog dialog = new DatePickerDialog(EducationActivity.this,
                            mDateSetListener2, year, month, day);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                    if (!eduCurrentCheckBox.isChecked()) {
                        dialog.show();

                    }
                }
            });

            mDateSetListener2 = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    month = month + 1;
                    String date = day + "/" + month + "/" + year;
                    eduEndDateEdt.setText(date);
                }
            };

            eduCurrentCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (eduCurrentCheckBox.isChecked()) {
                        eduEndDateEdt.setText("currently enrolled");
                        eduEndDateEdt.setHint("currently enrolled");
                    } else {
                        eduEndDateEdt.setHint("Select");
                        eduEndDateEdt.setText("");
                    }
                }
            });

            addEducationBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isAllDetailsFilled()) {
                        if (checkIntent) {
                            setEducationalArrayAdapterDetails(EducationList);
                            setEducationRecyclerview(EducationList);
                        } else {
                            setEducationalArrayAdapterDetails(EducationListDb);
                            setEducationRecyclerview(EducationListDb);
                        }
                        ResetEducationalDetails();
                    } else {
                        Toast.makeText(EducationActivity.this, "Please check and fill all the Details", Toast.LENGTH_LONG).show();
                    }
                }
            });

            nextToWorkExperience.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(EducationActivity.this, WorkExperienceActivity.class);
                    if (EducationList.size() != 0) {
                        String EducationListString = new Gson().toJson(EducationList);
                        userCv.setEducationListString(EducationListString);
                        intent.putExtra("FromActivity", "EducationActivity");
                        intent.putExtra("SharedUserCv", userCv);
                        startActivity(intent);
                    } else {
                        Toast.makeText(EducationActivity.this, "Please add your education details !", Toast.LENGTH_LONG).show();
                    }
                }
            });


    }


    private void setEducationRecyclerview(List<EducationItem> MyList) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerViewAdapter = new EducationRvAdapter(EducationActivity.this, MyList);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private boolean isAllDetailsFilled() {
        return eduStartDateEdt.getText() != null && eduStartDateEdt.getText().length() != 0 && eduEndDateEdt.getText() != null && eduEndDateEdt.getText().length() != 0 &&
                eduSchoolInstituteEdt.getText() != null && eduSchoolInstituteEdt.getText().length() != 0
                && eduDescriptionEdt.getText() != null && eduDescriptionEdt.getText().length() != 0 && eduDegreeSpinner.getSelectedItemPosition() != 0;
    }

    private void setEducationalArrayAdapterDetails(List<EducationItem> EducationList) {
        educationItem = new EducationItem();
        educationItem.setEduStartDate(eduStartDateEdt.getText().toString());
        if (eduCurrentCheckBox.isChecked()) {
            educationItem.setEduEndDate(eduCurrentCheckBox.getText().toString());
        } else {
            educationItem.setEduEndDate(eduEndDateEdt.getText().toString());
        }

        educationItem.setEduSchoolInstitute(eduSchoolInstituteEdt.getText().toString());
        educationItem.setEduDegreeTitle(String.valueOf(eduDegreeSpinner.getSelectedItem()));
        educationItem.setEduDescription(eduDescriptionEdt.getText().toString());
        EducationList.add(educationItem);
    }

    private void initViews() {
        eduStartDateEdt = findViewById(R.id.edu_start_date_edt);
        eduEndDateEdt = findViewById(R.id.edu_end_date_edt);
        eduDegreeSpinner = findViewById(R.id.degree_spinner);
        eduCurrentCheckBox = findViewById(R.id.edu_current_check_box);
        eduSchoolInstituteEdt = findViewById(R.id.school_name_edt);
        eduDescriptionEdt = findViewById(R.id.edu_description_edt);
        addEducationBtn = findViewById(R.id.add_education_btn);
        nextToWorkExperience = findViewById(R.id.next_work_experience);
        recyclerView = findViewById(R.id.education_rv_container);
        updateEducation = findViewById(R.id.update_education);
    }


    public void ResetEducationalDetails() {
        eduStartDateEdt.setText("");
        eduEndDateEdt.setText("");
        eduSchoolInstituteEdt.setText("");
        eduDegreeSpinner.setSelection(0);
        eduDescriptionEdt.setText("");
        eduCurrentCheckBox.setChecked(false);
    }
}