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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thecvmaker.adapter.WorkExperienceRvAdapter;
import com.example.thecvmaker.models.EducationItem;
import com.example.thecvmaker.models.WorkExpItem;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class WorkExperienceActivity extends AppCompatActivity {

    private List<WorkExpItem> WorkExperienceList;
    private List<EducationItem> EducationList;
    private EditText StartDateWorkExp;
    private EditText EndDateWorkExp;
    private EditText CompNameWorkExp;
    private EditText PositionWorkExp;
    private EditText DescriptionWorkExp;
    private CheckBox current_working;
    private TextView AnotherExp;
    private WorkExpItem expItem;
    private DatePickerDialog.OnDateSetListener mDateSetListener1;
    private DatePickerDialog.OnDateSetListener mDateSetListener2;
    private RecyclerView workExpRecyclerView;
    private WorkExperienceRvAdapter workExperienceRvAdapter;
    private UserCv userCv;
    private MaterialButton nextToProjectContribution;
    private MaterialButton updateWorkExperience;
    boolean checkIntent = false;
    private UserCv workExpToUpdate;
    private List<WorkExpItem> workExpListDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_experience);
        initViews();
        Intent intent = getIntent();
        if (intent.getStringExtra("FromActivity").equals("EducationActivity")) {
            userCv = intent.getParcelableExtra("SharedUserCv");
            //Dummy WorkExperience List
            WorkExperienceList = new ArrayList<>();
            WorkExperienceList.add(new WorkExpItem());
            WorkExperienceList.add(new WorkExpItem());
            setWorkExpRecyclerview(WorkExperienceList);
        } else {
            checkIntent = true;
            nextToProjectContribution.setVisibility(View.GONE);
            updateWorkExperience.setVisibility(View.VISIBLE);
            MyDbHelper db = new MyDbHelper(WorkExperienceActivity.this);
            workExpToUpdate = db.getCv();
            String extractWorkExpString = workExpToUpdate.getWorkExpListString();
            Type workExpListType = new TypeToken<ArrayList<WorkExpItem>>() {
            }.getType();
            workExpListDb = new Gson().fromJson(extractWorkExpString, workExpListType);
            Toast.makeText(this, "" + workExpListDb.size(), Toast.LENGTH_SHORT).show();
            setWorkExpRecyclerview(workExpListDb);

            updateWorkExperience.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    if (workExpListDb.size() != 0) {
                        String workExpListString = new Gson().toJson(workExpListDb);
                        // userCv.setEducationListString(EducationListString);
                        db.updateWorkExperienceDetails(workExpListString);
                        Toast.makeText(WorkExperienceActivity.this, "Education Details Updated" + workExpListDb.size(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(WorkExperienceActivity.this, "Please add your education details !", Toast.LENGTH_LONG).show();
                    }

                }
            });
        }

        StartDateWorkExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(WorkExperienceActivity.this,
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
                StartDateWorkExp.setText(date);
            }
        };


        EndDateWorkExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(WorkExperienceActivity.this,
                        mDateSetListener2, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                if(!current_working.isChecked()) {
                    dialog.show();
                }
            }
        });
        mDateSetListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                EndDateWorkExp.setText(date);
            }
        };


        current_working.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(current_working.isChecked()) {
                    EndDateWorkExp.setText("Currently working");
                    EndDateWorkExp.setHint("Currently working");
                }
                else{
                    EndDateWorkExp.setHint("Select");
                    EndDateWorkExp.setText("");
                }
            }
        });

        AnotherExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isAllDetailsFilled()) {
                    if (checkIntent) {
                        setWorkExperienceArrayAdapterDetails(workExpListDb);
                        setWorkExpRecyclerview(workExpListDb);
                    } else {
                        setWorkExperienceArrayAdapterDetails(WorkExperienceList);
                        setWorkExpRecyclerview(WorkExperienceList);
                    }
                    ResetWorkExpDetails();
                } else {
                    Toast.makeText(WorkExperienceActivity.this, "Please check and fill all the Details", Toast.LENGTH_LONG).show();
                }
            }
        });
        nextToProjectContribution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WorkExperienceActivity.this, ProjectContributionActivity.class);
                if (WorkExperienceList.size() != 0) {
                    String WorkExpListString = new Gson().toJson(WorkExperienceList);
                    userCv.setWorkExpListString(WorkExpListString);
                    intent.putExtra("FromActivity", "WorkExperienceActivity");
                    intent.putExtra("SharedUserCv", userCv);
                    startActivity(intent);
                } else {
                    Toast.makeText(WorkExperienceActivity.this, "please add your experience details !", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void setWorkExpRecyclerview(List<WorkExpItem> WorkExperienceList) {
        workExpRecyclerView.setHasFixedSize(true);
        workExpRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        workExperienceRvAdapter = new WorkExperienceRvAdapter(WorkExperienceActivity.this, WorkExperienceList);
        workExpRecyclerView.setAdapter(workExperienceRvAdapter);
    }

    private boolean isAllDetailsFilled() {
        return StartDateWorkExp.getText() != null && StartDateWorkExp.getText().length() != 0 && EndDateWorkExp.getText() != null
                && EndDateWorkExp.getText().length() != 0 && CompNameWorkExp.getText() != null
                && CompNameWorkExp.getText().length() != 0 && PositionWorkExp.getText() != null
                && PositionWorkExp.getText().length() != 0 && DescriptionWorkExp.getText() != null && DescriptionWorkExp.getText().length() != 0;
    }

    private void initViews() {

        StartDateWorkExp = findViewById(R.id.exp_start_date_edt);
        EndDateWorkExp = findViewById(R.id.exp_end_date_edt);
        CompNameWorkExp = findViewById(R.id.company_name_edt);
        PositionWorkExp = findViewById(R.id.position_edt);
        DescriptionWorkExp = findViewById(R.id.job_description_edt);
        current_working = findViewById(R.id.exp_current_checkbox_btn);
        AnotherExp = findViewById(R.id.add_experience_btn);
        workExpRecyclerView = findViewById(R.id.experience_rv_container);
        nextToProjectContribution = findViewById(R.id.next_project_contribution);
        updateWorkExperience = findViewById(R.id.update_work_experience);
    }

    private void setWorkExperienceArrayAdapterDetails(List<WorkExpItem> WorkExperienceList) {
        expItem = new WorkExpItem();
        expItem.setStart_date(StartDateWorkExp.getText().toString());
        if (current_working.isChecked()) {
            expItem.setEnd_date(current_working.getText().toString());

        } else {
            expItem.setEnd_date(EndDateWorkExp.getText().toString());
        }

        expItem.setCompany(CompNameWorkExp.getText().toString());
        expItem.setPosition(PositionWorkExp.getText().toString());
        expItem.setDescription(DescriptionWorkExp.getText().toString());
        WorkExperienceList.add(expItem);
    }

    public void ResetWorkExpDetails() {
        StartDateWorkExp.setText("");
        EndDateWorkExp.setText("");
        CompNameWorkExp.setText("");
        PositionWorkExp.setText("");
        DescriptionWorkExp.setText("");
        current_working.setChecked(false);
    }
}