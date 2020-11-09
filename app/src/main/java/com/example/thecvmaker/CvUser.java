package com.example.thecvmaker;

public class CvUser {
    public static String Name;
    public static String Address;
    public static String PhoneNumber;
    public static String EmailAddress;
    public static String Dob;
    public static String Nationality;
    public static String Gender;
    public static String Language;
    private int id;

    public static void setPersonalDetail(String name) {

    }

    public static String getName() {
        return Name;
    }

    public static void setName(String name) {
        Name = name;
    }

    public static String getAddress() {
        return Address;
    }

    public static void setAddress(String address) {
        Address = address;
    }

    public static String getPhoneNumber() {
        return PhoneNumber;
    }

    public static void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public static String getEmailAddress() {
        return EmailAddress;
    }

    public static void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public static String getDob() {
        return Dob;
    }

    public static void setDob(String dob) {
        Dob = dob;
    }

    public static String getNationality() {
        return Nationality;
    }

    public static void setNationality(String nationality) {
        Nationality = nationality;
    }

    public static String getGender() {
        return Gender;
    }

    public static void setGender(String gender) {
        Gender = gender;
    }

    public static String getLanguage() {
        return Language;
    }

    public static void setLanguage(String language) {
        Language = language;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}