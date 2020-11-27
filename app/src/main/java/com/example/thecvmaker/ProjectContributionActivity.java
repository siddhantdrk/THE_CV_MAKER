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

import com.example.thecvmaker.adapter.ProjectContributionRvAdapter;
import com.example.thecvmaker.models.ProjectContributionItem;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ProjectContributionActivity extends AppCompatActivity {

    private EditText proStartDateEdt, proEndDateEdt, proProjectName, proCategory, proDescriptionRole;
    private DatePickerDialog.OnDateSetListener mDateSetListener1;
    private DatePickerDialog.OnDateSetListener mDateSetListener2;

    private List<ProjectContributionItem> ProjectContributionItemList;
    private UserCv userCv;
    private CheckBox current_working;
    private TextView AddProject;
    private MaterialButton nextToOthersSkills;
    private RecyclerView projectContributionRecyclerView;
    private ProjectContributionRvAdapter projectContributionRvAdapter;
    private MaterialButton updateProjectDetails;
    boolean checkIntent = false;
    private UserCv projectContributionToUpdate;
    private List<ProjectContributionItem> projectContributionListDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_contribution);

        initViews();
        ProjectContributionItemList = new ArrayList<>();

        Intent intent = getIntent();
        if (intent.getStringExtra("FromActivity").equals("WorkExperienceActivity")) {
            userCv = intent.getParcelableExtra("SharedUserCv");
            ProjectContributionItemList.add(new ProjectContributionItem());
            setProjectContributionRecyclerView(ProjectContributionItemList);
        } else {
            nextToOthersSkills.setVisibility(View.GONE);
            updateProjectDetails.setVisibility(View.VISIBLE);
            checkIntent = true;
            MyDbHelper db = new MyDbHelper(ProjectContributionActivity.this);
            projectContributionToUpdate = db.getCv();
            String extractProjectContributionString = projectContributionToUpdate.getProjectContributionListString();
            Type projectContributionListType = new TypeToken<ArrayList<ProjectContributionItem>>() {
            }.getType();
            projectContributionListDb = new Gson().fromJson(extractProjectContributionString, projectContributionListType);
            Toast.makeText(this, "" + projectContributionListDb.size(), Toast.LENGTH_SHORT).show();
            setProjectContributionRecyclerView(projectContributionListDb);

            updateProjectDetails.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    if (projectContributionListDb.size() != 0) {
                        String ProjectContributionListString = new Gson().toJson(projectContributionListDb);
                        // userCv.setEducationListString(EducationListString);
                        db.updateProjectDetails(ProjectContributionListString);
                        Toast.makeText(ProjectContributionActivity.this, "Education Details Updated" + projectContributionListDb.size(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ProjectContributionActivity.this, "Please add your education details !", Toast.LENGTH_LONG).show();
                    }

                }
            });
        }

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
                proEndDateEdt.setText(date);
            }
        };

        current_working.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(current_working.isChecked()) {
                    proEndDateEdt.setText("currently working");
                    proEndDateEdt.setHint("Currently working");
                }
                else{
                    proEndDateEdt.setHint("Select");
                    proEndDateEdt.setText("");
                }
            }
        });

        AddProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isAllDetailsFilled()) {
                    if (checkIntent) {
                        setProjectContributionArrayAdapterDetails(projectContributionListDb);
                        setProjectContributionRecyclerView(projectContributionListDb);
                    } else {
                        setProjectContributionArrayAdapterDetails(ProjectContributionItemList);
                        setProjectContributionRecyclerView(ProjectContributionItemList);
                    }
                    ResetProjectContributionDetails();
                } else {
                    Toast.makeText(ProjectContributionActivity.this, "Please check and fill all the Details", Toast.LENGTH_LONG).show();
                }
            }
        });


        nextToOthersSkills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProjectContributionActivity.this, OthersAndSkillsActivity.class);
                if (ProjectContributionItemList.size() != 0) {
                    String ProjectContributionListString = new Gson().toJson(ProjectContributionItemList);
                    userCv.setProjectContributionListString(ProjectContributionListString);
                    intent.putExtra("SharedUserCv", userCv);
                    intent.putExtra("FromActivity", "ProjectContributionActivity");
                    startActivity(intent);
                } else {
                    Toast.makeText(ProjectContributionActivity.this, "Please add your Project and Contribution details !", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setProjectContributionRecyclerView(List<ProjectContributionItem> ProjectContributionItemList) {
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
        nextToOthersSkills = findViewById(R.id.next_skills_others);
        updateProjectDetails = findViewById(R.id.update_project_details_contribution);
    }

    private void setProjectContributionArrayAdapterDetails(List<ProjectContributionItem> ProjectContributionItemList) {
        ProjectContributionItem proItem = new ProjectContributionItem();
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