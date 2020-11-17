package com.example.thecvmaker;

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

import java.util.ArrayList;
import java.util.List;

public class OthersAndSkillsActivity extends AppCompatActivity {
    EditText Hobby;
    EditText sklDescription;
    RecyclerView recyclerViewSkills;
    String hobby, skill_description;
    TextView AddAnotherSkill;
    private List<SkillsItem> skills_List;
    private SkillsRvAdapter skillsRvAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others_and_skills);

        initViews();
        skills_List= new ArrayList<>();
        AddAnotherSkill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isAllDetailsFilled()) {
                    setSkills();
                    ResetSkills();
                }
                else{
                    Toast.makeText(OthersAndSkillsActivity.this, "Please check and fill all the Details", Toast.LENGTH_LONG).show();
                }
            }
        });
        setSkillsRecyclerview();
    }

    public void initViews() {
        Hobby = findViewById(R.id.skill_hobby_name_edt);
        sklDescription = findViewById(R.id.skill_hobby_description_edt);
        AddAnotherSkill = findViewById(R.id.add_skill_btn);
    }

    private boolean isAllDetailsFilled() {
        return Hobby.getText() != null && Hobby.getText().length() != 0 && sklDescription.getText() != null
                && sklDescription.getText().length() != 0 ;
    }

    public void setSkills() {
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

    private void setSkillsRecyclerview() {
        recyclerViewSkills.setHasFixedSize(true);
        recyclerViewSkills.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        skillsRvAdapter = new SkillsRvAdapter(OthersAndSkillsActivity.this, skills_List);
        recyclerViewSkills.setAdapter(skillsRvAdapter);
    }


}