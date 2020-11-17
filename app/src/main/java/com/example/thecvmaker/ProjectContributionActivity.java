package com.example.thecvmaker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thecvmaker.adapter.ProjectContributionRvAdapter;
import com.example.thecvmaker.adapter.WorkExperienceRvAdapter;
import com.example.thecvmaker.models.ProjectContributionItem;
import com.example.thecvmaker.models.WorkExpItem;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.radiobutton.MaterialRadioButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ProjectContributionActivity extends AppCompatActivity {

    private EditText proStartDateEdt, proEndDateEdt,proProjectName,proCategory,proDescriptionRole;
    private DatePickerDialog.OnDateSetListener mDateSetListener1;
    private DatePickerDialog.OnDateSetListener mDateSetListener2;

    private List<ProjectContributionItem> ProjectContributionItemList;
    private final UserCv userCv = new UserCv();

    private CheckBox current_working;
    private TextView AddProject;
    private ProjectContributionItem proItem;
    RecyclerView projectContributionRecyclerView;
    ProjectContributionRvAdapter projectContributionRvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_contribution);

        initViews();
        ProjectContributionItemList = new ArrayList<>();
        proStartDateEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(ProjectContributionActivity.this,
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
                proStartDateEdt.setText(date);
            }
        };

        proEndDateEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(ProjectContributionActivity.this,
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
                proEndDateEdt.setText(date);
            }
        };

        AddProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isAllDetailsFilled()) {
                    setProjectContributionArrayAdapterDetails();
                    ResetProjectContributionDetails();
                }
                else{
                    Toast.makeText(ProjectContributionActivity.this, "Please check and fill all the Details", Toast.LENGTH_LONG).show();
                }
            }
        });

        ProjectContributionItemList.add(new ProjectContributionItem());
        setProjectContributionRecyclerView();
    }

    private void setProjectContributionRecyclerView() {
        projectContributionRecyclerView.setHasFixedSize(true);
        projectContributionRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        projectContributionRvAdapter = new ProjectContributionRvAdapter(ProjectContributionActivity.this, ProjectContributionItemList);
        projectContributionRecyclerView.setAdapter(projectContributionRvAdapter);
    }

    private boolean isAllDetailsFilled() {
        return proStartDateEdt.getText() != null && proStartDateEdt.getText().length() != 0 &&
                proEndDateEdt.getText() != null && proEndDateEdt.getText().length() != 0 &&
                proProjectName.getText() != null && proProjectName.getText().length() != 0 &&
                proCategory.getText() != null && proCategory.getText().length() != 0 &&
                proDescriptionRole.getText() != null && proDescriptionRole.getText().length() != 0;
    }

    private void initViews() {

        proStartDateEdt = findViewById(R.id.project_start_date_edt);
        proEndDateEdt = findViewById(R.id.project_end_date_edt);
        proProjectName = findViewById(R.id.project_name_edt);
        proCategory = findViewById(R.id.project_category_edt);
        proDescriptionRole = findViewById(R.id.project_description_edt);
        current_working = findViewById(R.id.pro_current_checkbox_btn);
        AddProject = findViewById(R.id.add_project_btn);
        projectContributionRecyclerView = findViewById(R.id.projects_rv_container);
    }

    private void setProjectContributionArrayAdapterDetails() {
        proItem = new ProjectContributionItem();
        proItem.setProjectStartDate(proStartDateEdt.getText().toString());
        if (current_working.isChecked()) {
            proItem.setProjectEndDate(current_working.getText().toString());

        } else {
            proItem.setProjectEndDate(proEndDateEdt.getText().toString());
        }

        proItem.setProjectTitle(proProjectName.getText().toString());
        proItem.setProjectCategory(proCategory.getText().toString());
        proItem.setProjectDescription(proDescriptionRole.getText().toString());
        ProjectContributionItemList.add(proItem);
    }

    public void ResetProjectContributionDetails() {
        proStartDateEdt.setText("");
        proEndDateEdt.setText("");
        proProjectName.setText("");
        proCategory.setText("");
        proDescriptionRole.setText("");
        current_working.setChecked(false);
    }
}