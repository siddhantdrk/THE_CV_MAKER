package com.example.thecvmaker.models;

import android.os.Parcel;
import android.os.Parcelable;

public class EducationItem implements Parcelable {
    private String EduDegreeTitle, EduStartDate, EduEndDate, EduSchoolInstitute, EduDescription;

    public EducationItem() {

    }

    public static final Creator<EducationItem> CREATOR = new Creator<EducationItem>() {
        @Override
        public EducationItem createFromParcel(Parcel in) {
            return new EducationItem(in);
        }

        @Override
        public EducationItem[] newArray(int size) {
            return new EducationItem[size];
        }
    };

    protected EducationItem(Parcel in) {
        EduDegreeTitle = in.readString();
        EduStartDate = in.readString();
        EduEndDate = in.readString();
        EduSchoolInstitute = in.readString();
        EduDescription = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(EduDegreeTitle);
        dest.writeString(EduStartDate);
        dest.writeString(EduEndDate);
        dest.writeString(EduSchoolInstitute);
        dest.writeString(EduDescription);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getEduDegreeTitle() {
        return EduDegreeTitle;
    }

    public void setEduDegreeTitle(String eduDegreeTitle) {
        EduDegreeTitle = eduDegreeTitle;
    }

    public String getEduStartDate() {
        return EduStartDate;
    }

    public void setEduStartDate(String eduStartDate) {
        EduStartDate = eduStartDate;
    }

    public String getEduEndDate() {
        return EduEndDate;
    }

    public void setEduEndDate(String eduEndDate) {
        EduEndDate = eduEndDate;
    }

    public String getEduSchoolInstitute() {
        return EduSchoolInstitute;
    }

    public void setEduSchoolInstitute(String eduSchoolInstitute) {
        EduSchoolInstitute = eduSchoolInstitute;
    }

    public String getEduDescription() {
        return EduDescription;
    }

    public void setEduDescription(String eduDescription) {
        EduDescription = eduDescription;
    }
}
