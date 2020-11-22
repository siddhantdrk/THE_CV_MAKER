package com.example.thecvmaker.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ProjectContributionItem implements Parcelable {
    private String projectTitle, projectStartDate, projectEndDate, projectCategory, projectDescription;

    public ProjectContributionItem(){

    }

    protected ProjectContributionItem(Parcel in) {
        projectTitle = in.readString();
        projectStartDate = in.readString();
        projectEndDate = in.readString();
        projectCategory = in.readString();
        projectDescription = in.readString();
    }

    public static final Creator<ProjectContributionItem> CREATOR = new Creator<ProjectContributionItem>() {
        @Override
        public ProjectContributionItem createFromParcel(Parcel in) {
            return new ProjectContributionItem(in);
        }

        @Override
        public ProjectContributionItem[] newArray(int size) {
            return new ProjectContributionItem[size];
        }
    };

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(String projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public String getProjectEndDate() {
        return projectEndDate;
    }

    public void setProjectEndDate(String projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    public String getProjectCategory() {
        return projectCategory;
    }

    public void setProjectCategory(String projectCategory) {
        this.projectCategory = projectCategory;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(projectTitle);
        parcel.writeString(projectStartDate);
        parcel.writeString(projectEndDate);
        parcel.writeString(projectCategory);
        parcel.writeString(projectDescription);
    }
}
