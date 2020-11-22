package com.example.thecvmaker.models;

import android.os.Parcel;
import android.os.Parcelable;

public class SkillsItem implements Parcelable {
    private String hobby, skill_description;

    public SkillsItem(){

    }

    protected SkillsItem(Parcel in) {
        hobby = in.readString();
        skill_description = in.readString();
    }

    public static final Creator<SkillsItem> CREATOR = new Creator<SkillsItem>() {
        @Override
        public SkillsItem createFromParcel(Parcel in) {
            return new SkillsItem(in);
        }

        @Override
        public SkillsItem[] newArray(int size) {
            return new SkillsItem[size];
        }
    };

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getSkill_description() {
        return skill_description;
    }

    public void setSkill_description(String skill_description) {
        this.skill_description = skill_description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(hobby);
        parcel.writeString(skill_description);
    }
}
