package com.example.thecvmaker;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class WorkExperienceActivity extends AppCompatActivity {

    private List<WorkExpItem> WorkExperienceList;
    private ArrayList<EducationItem> EducationList;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_experience);
        initViews();
        WorkExperienceList = new ArrayList<>();

        Intent intent = getIntent();
        if (intent.getStringExtra("FromActivity").equals("EducationActivity")) {
            userCv = intent.getParcelableExtra("SharedUserCv");
            EducationList = intent.getParcelableArrayListExtra("EducationList");
            Log.i("WorkExperienceActivity", "" + EducationList.size());
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
                dialog.show();
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
        AnotherExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isAllDetailsFilled()) {
                    setWorkExperienceArrayAdapterDetails();
                    ResetWorkExpDetails();
                }
                else{
                    Toast.makeText(WorkExperienceActivity.this, "Please check and fill all the Details", Toast.LENGTH_LONG).show();
                }
            }
        });

//        //Dummy WorkExperience List
//        ExperienceList.add(new WorkExpItem());
//        ExperienceList.add(new WorkExpItem());
        setWorkExpRecyclerview();
    }

    private void setWorkExpRecyclerview() {
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
    }

    private void setWorkExperienceArrayAdapterDetails() {
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