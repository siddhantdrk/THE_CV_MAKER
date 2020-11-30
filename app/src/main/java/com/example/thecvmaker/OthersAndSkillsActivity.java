package com.example.thecvmaker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thecvmaker.adapter.SkillsRvAdapter;
import com.example.thecvmaker.models.SkillsItem;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class OthersAndSkillsActivity extends AppCompatActivity {
    private EditText Hobby;
    private EditText sklDescription;
    private RecyclerView recyclerViewSkills;
    private String hobby, skill_description;
    private TextView AddAnotherSkill;
    private List<SkillsItem> skills_List;
    private SkillsRvAdapter skillsRvAdapter;
    private MaterialButton SaveProceedBtn;
    private UserCv userCv;
    private TextView test;
    private MaterialButton updateSkillsAndOthers;
    boolean checkIntent = false;
    private UserCv skillsToUpdate;
    private List<SkillsItem> skillsListDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others_and_skills);
        initViews();
        skills_List = new ArrayList<>();

        Intent intent = getIntent();
        if (intent.getStringExtra("FromActivity").equals("ProjectContributionActivity")) {
            userCv = intent.getParcelableExtra("SharedUserCv");
            skills_List.add(new SkillsItem());
            setSkillsRecyclerview(skills_List);
        } else {
            SaveProceedBtn.setVisibility(View.GONE);
            updateSkillsAndOthers.setVisibility(View.VISIBLE);
            checkIntent = true;
            MyDbHelper db = new MyDbHelper(OthersAndSkillsActivity.this);
            skillsToUpdate = db.getCv();
            String skillsString = skillsToUpdate.getSkillsOthersListString();
            Type skillListType = new TypeToken<ArrayList<SkillsItem>>() {
            }.getType();
            skillsListDb = new Gson().fromJson(skillsString, skillListType);
            Toast.makeText(this, "" + skillsListDb.size(), Toast.LENGTH_SHORT).show();
            setSkillsRecyclerview(skillsListDb);

            updateSkillsAndOthers.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    if (skillsListDb.size() != 0) {
                        String SkillsListString = new Gson().toJson(skillsListDb);
                        // userCv.setEducationListString(EducationListString);
                        db.updateSkillsDetails(SkillsListString);
                        Toast.makeText(OthersAndSkillsActivity.this, "Skills and Other Details Updated" + skillsListDb.size(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(OthersAndSkillsActivity.this, "Please add your Skills and Others details !", Toast.LENGTH_LONG).show();
                    }

                }
            });
        }

        AddAnotherSkill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isAllDetailsFilled()) {
                    if (checkIntent) {
                        setSkills(skillsListDb);
                        setSkillsRecyclerview(skillsListDb);
                    } else {
                        setSkills(skills_List);
                        setSkillsRecyclerview(skills_List);
                    }
                    ResetSkills();
                } else {
                    Toast.makeText(OthersAndSkillsActivity.this, "Please check and fill all the Details", Toast.LENGTH_LONG).show();
                }
            }
        });

        SaveProceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OthersAndSkillsActivity.this, GenerateDownloadCvActivity.class);
                if (skills_List.size() != 0) {
                    String OthersAndSkillsListString = new Gson().toJson(skills_List);
                    userCv.setSkillsOthersListString(OthersAndSkillsListString);
                    intent.putExtra("SharedUserCv", userCv);
                    intent.putExtra("FromActivity", "OthersAndSkillsActivity");
                    startActivity(intent);
                } else {
                    Toast.makeText(OthersAndSkillsActivity.this, "Please add your Project and Contribution details !", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void initViews() {
        Hobby = findViewById(R.id.skill_hobby_name_edt);
        sklDescription = findViewById(R.id.skill_hobby_description_edt);
        recyclerViewSkills = findViewById(R.id.skills_hobbies_rv_container);
        AddAnotherSkill = findViewById(R.id.add_skill_btn);
        SaveProceedBtn = findViewById(R.id.save_proceed_btn);
        updateSkillsAndOthers = findViewById(R.id.update_skills);
    }

    private boolean isAllDetailsFilled() {
        return Hobby.getText() != null && Hobby.getText().length() != 0 && sklDescription.getText() != null
                && sklDescription.getText().length() != 0 ;
    }

    public void setSkills(List<SkillsItem> skills_List) {
        SkillsItem skill_item = new SkillsItem();
        hobby = Hobby.getText().toString();
        skill_description = sklDescription.getText().toString();
        skill_item.setHobby(hobby);
        skill_item.setSkill_description(skill_description);
        skills_List.add(skill_item);
    }

    public void ResetSkills() {
        Hobby.setText("");
        sklDescription.setText("");
    }

    private void setSkillsRecyclerview(List<SkillsItem> skills_List) {
        recyclerViewSkills.setHasFixedSize(true);
        recyclerViewSkills.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        skillsRvAdapter = new SkillsRvAdapter(OthersAndSkillsActivity.this, skills_List);
        recyclerViewSkills.setAdapter(skillsRvAdapter);
    }

}