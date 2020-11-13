package com.example.thecvmaker;

import android.os.Parcel;
import android.os.Parcelable;

public class CvUser implements Parcelable {
    public static final Creator<CvUser> CREATOR = new Creator<CvUser>() {
        @Override
        public CvUser createFromParcel(Parcel in) {
            return new CvUser(in);
        }

        @Override
        public CvUser[] newArray(int size) {
            return new CvUser[size];
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

    protected CvUser(Parcel in) {
        Name = in.readString();
        Address = in.readString();
        PhoneNumber = in.readString();
        EmailAddress = in.readString();
        Dob = in.readString();
        Nationality = in.readString();
        Gender = in.readString();
        Language = in.readString();
    }

    public CvUser() {

    }

    public static void setPersonalDetail(String name) {

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