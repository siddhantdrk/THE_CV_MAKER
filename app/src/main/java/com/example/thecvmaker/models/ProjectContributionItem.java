package com.example.thecvmaker.models;

import android.widget.TextView;

public class ProjectContributionItem {
    private TextView projectTitle, projectStartDate, projectEndDate, projectCategory, projectDescription;

    public TextView getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(TextView projectTitle) {
        this.projectTitle = projectTitle;
    }

    public TextView getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(TextView projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public TextView getProjectEndDate() {
        return projectEndDate;
    }

    public void setProjectEndDate(TextView projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    public TextView getProjectCategory() {
        return projectCategory;
    }

    public void setProjectCategory(TextView projectCategory) {
        this.projectCategory = projectCategory;
    }

    public TextView getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(TextView projectDescription) {
        this.projectDescription = projectDescription;
    }
}
