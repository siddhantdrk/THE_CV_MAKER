package com.example.thecvmaker.models;

import android.os.Parcel;
import android.os.Parcelable;

public class WorkExpItem implements Parcelable {
    private String Start_date;
    private String End_date;
    private String Company;
    private String Position;
    private String Description;

    public WorkExpItem(){

    }

    public static final Creator<WorkExpItem> CREATOR = new Creator<WorkExpItem>() {
        @Override
        public WorkExpItem createFromParcel(Parcel in) {
            return new WorkExpItem(in);
        }

        @Override
        public WorkExpItem[] newArray(int size) {
            return new WorkExpItem[size];
        }
    };
    protected WorkExpItem(Parcel in) {
        Start_date = in.readString();
        End_date = in.readString();
        Company = in.readString();
        Position = in.readString();
        Description = in.readString();
    }

    public String getStart_date() {
        return Start_date;
    }

    public void setStart_date(String start_date) {
        Start_date = start_date;
    }

    public String getEnd_date() {
        return End_date;
    }

    public void setEnd_date(String end_date) {
        End_date = end_date;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Start_date);
        parcel.writeString(End_date);
        parcel.writeString(Company);
        parcel.writeString(Position);
        parcel.writeString(Description);
    }
}
