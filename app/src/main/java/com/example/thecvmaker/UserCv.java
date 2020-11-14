package com.example.thecvmaker;

import android.os.Parcel;
import android.os.Parcelable;

public class UserCv implements Parcelable {
    public static final Creator<UserCv> CREATOR = new Creator<UserCv>() {
        @Override
        public UserCv createFromParcel(Parcel in) {
            return new UserCv(in);
        }

        @Override
        public UserCv[] newArray(int size) {
            return new UserCv[size];
        }
    };
    private String Name;
    private String Address;
    private String PhoneNumber;
    private String EmailAddress;
    private String Dob;
    private String Nationality;
    private String Gender;
    private String Language;

    protected UserCv(Parcel in) {
        Name = in.readString();
        Address = in.readString();
        PhoneNumber = in.readString();
        EmailAddress = in.readString();
        Dob = in.readString();
        Nationality = in.readString();
        Gender = in.readString();
        Language = in.readString();
    }

    public UserCv() {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public String getDob() {
        return Dob;
    }

    public void setDob(String dob) {
        Dob = dob;
    }

    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String nationality) {
        Nationality = nationality;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Name);
        parcel.writeString(Address);
        parcel.writeString(PhoneNumber);
        parcel.writeString(EmailAddress);
        parcel.writeString(Dob);
        parcel.writeString(Nationality);
        parcel.writeString(Gender);
        parcel.writeString(Language);
    }
}