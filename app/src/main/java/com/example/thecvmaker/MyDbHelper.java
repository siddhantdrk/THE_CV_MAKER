package com.example.thecvmaker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MyDbHelper extends SQLiteOpenHelper {

    public MyDbHelper(Context context) {
        super(context, Params.KEY_DATABASE, null, Params.KEY_DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + Params.TABLE_NAME1 + "("
                + Params.KEY_ID + " INTEGER PRIMARY KEY," + Params.KEY_NAME + " TEXT,"
                + Params.KEY_EMAIL + " TEXT," + Params.KEY_PHONE_NUMBER + " TEXT," + Params.KEY_DOB + " TEXT,"
                + Params.KEY_GENDER + " TEXT," + Params.KEY_NATIONALITY + " TEXT," + Params.KEY_ADDRESS + " TEXT,"
                + Params.KEY_LANGUAGE + " TEXT," + Params.KEY_SCHOOL_NAME + " TEXT," + Params.KEY_COLLEGE_NAME + " TEXT,"
                + Params.KEY_SCORE_PERCENTAGE + " TEXT," + Params.KEY_YEAR_OF_PASSING + " TEXT," + Params.KEY_DESIGNATION + " TEXT,"
                + Params.KEY_ORGANISATION_NAME + " TEXT," + Params.KEY_WORKING_PERIOD + " TEXT," + Params.KEY_FIELD_OF_INTEREST + " TEXT,"
                + Params.KEY_SKILLS + " TEXT," + Params.KEY_HOBBY + " TEXT," + Params.KEY_STRENGTH + " TEXT,"
                + Params.KEY_ACHIEVEMENT + " TEXT," + Params.KEY_CARRIER_OBJECTIVE + " TEXT," + Params.KEY_TITLE + " TEXT,"
                + Params.KEY_PERIOD + " TEXT," +Params.KEY_ROLE + " TEXT," + Params.KEY_DESCRIPTION + " TEXT," + Params.KEY_TEAM_SIZE +")";

        Log.d("dbharry", "Query being run is : "+ create);
        db.execSQL(create);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addContact(CvData cvData){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Params.KEY_NAME, cvData.getName());
        values.put(Params.KEY_EMAIL, cvData.getEmailAddress());
        values.put(Params.KEY_PHONE_NUMBER, cvData.getPhoneNumber());
        values.put(Params.KEY_DOB, cvData.getDob());
        values.put(Params.KEY_GENDER, cvData.getGender());
        values.put(Params.KEY_NATIONALITY, cvData.getNationality());
        values.put(Params.KEY_ADDRESS, cvData.getAddress());
        values.put(Params.KEY_LANGUAGE, cvData.getLanguage());
        values.put(Params.KEY_SCHOOL_NAME, cvData.getSchoolName());
        values.put(Params.KEY_COLLEGE_NAME, cvData.getCollegeName());
        values.put(Params.KEY_SCORE_PERCENTAGE, cvData.getPercentage());
        values.put(Params.KEY_YEAR_OF_PASSING, cvData.getPassingYear());
        values.put(Params.KEY_DESIGNATION, cvData.getDesignation());
        values.put(Params.KEY_ORGANISATION_NAME, cvData.getOrganisationName());
        values.put(Params.KEY_WORKING_PERIOD, cvData.getWorkingPeriod());
        values.put(Params.KEY_FIELD_OF_INTEREST, cvData.getInterestedField());
        values.put(Params.KEY_SKILLS, cvData.getSkills());
        values.put(Params.KEY_HOBBY, cvData.getHobby());
        values.put(Params.KEY_STRENGTH, cvData.getStrength());
        values.put(Params.KEY_ACHIEVEMENT, cvData.getAchievement());
        values.put(Params.KEY_CARRIER_OBJECTIVE, cvData.getCarrierObjective());
        values.put(Params.KEY_TITLE, cvData.getTitle());
        values.put(Params.KEY_PERIOD,cvData.getPeriod());
        values.put(Params.KEY_ROLE, cvData.getRole());
        values.put(Params.KEY_DESCRIPTION, cvData.getDescription());
        values.put(Params.KEY_TEAM_SIZE, cvData.getTeamSize());

        db.insert(Params.TABLE_NAME1, null, values);
        Log.d("dev", "Successfully inserted");
        db.close();


    }

    public List<CvData> getAllContacts(){
        List<CvData> contactList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Generate the query to read from the database
        String select = "SELECT * FROM " + Params.TABLE_NAME1;
        Cursor cursor = db.rawQuery(select, null);
        //Loop through now
        if(cursor.moveToFirst()){
            do{
                CvData contact = new CvData();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setEmailAddress(cursor.getString(2));
                contact.setPhoneNumber(cursor.getString(2));
                contact.setDob(cursor.getString(2));
                contact.setGender(cursor.getString(2));
                contact.setNationality(cursor.getString(2));
                contact.setAddress(cursor.getString(2));
                contact.setLanguage(cursor.getString(2));
                contact.setSchoolName(cursor.getString(2));
                contact.setCollegeName(cursor.getString(2));
                contact.setPercentage(cursor.getString(2));
                contact.setPassingYear(cursor.getString(2));
                contact.setDesignation(cursor.getString(2));
                contact.setOrganisationName(cursor.getString(2));
                contact.setWorkingPeriod(cursor.getString(2));
                contact.setInterestedField(cursor.getString(2));
                contact.setSkills(cursor.getString(2));
                contact.setHobby(cursor.getString(2));
                contact.setStrength(cursor.getString(2));
                contact.setAchievement(cursor.getString(2));
                contact.setCarrierObjective(cursor.getString(2));
                contact.setTitle(cursor.getString(2));
                contact.setPeriod(cursor.getString(2));
                contact.setRole(cursor.getString(2));
                contact.setDescription(cursor.getString(2));
                contact.setTeamSize(cursor.getString(2));
                //contactList.add(contact);
            }while(cursor.moveToNext());
        }
        return contactList;
    }

    public int updateContact(CvData cvData){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Params.KEY_NAME, cvData.getName());
        values.put(Params.KEY_EMAIL, cvData.getEmailAddress());
        values.put(Params.KEY_PHONE_NUMBER, cvData.getPhoneNumber());
        values.put(Params.KEY_DOB, cvData.getDob());
        values.put(Params.KEY_GENDER, cvData.getGender());
        values.put(Params.KEY_NATIONALITY, cvData.getNationality());
        values.put(Params.KEY_ADDRESS, cvData.getAddress());
        values.put(Params.KEY_LANGUAGE, cvData.getLanguage());
        values.put(Params.KEY_SCHOOL_NAME, cvData.getSchoolName());
        values.put(Params.KEY_COLLEGE_NAME, cvData.getCollegeName());
        values.put(Params.KEY_SCORE_PERCENTAGE, cvData.getPercentage());
        values.put(Params.KEY_YEAR_OF_PASSING, cvData.getPassingYear());
        values.put(Params.KEY_DESIGNATION, cvData.getDesignation());
        values.put(Params.KEY_ORGANISATION_NAME, cvData.getOrganisationName());
        values.put(Params.KEY_WORKING_PERIOD, cvData.getWorkingPeriod());
        values.put(Params.KEY_FIELD_OF_INTEREST, cvData.getInterestedField());
        values.put(Params.KEY_SKILLS, cvData.getSkills());
        values.put(Params.KEY_HOBBY, cvData.getHobby());
        values.put(Params.KEY_STRENGTH, cvData.getStrength());
        values.put(Params.KEY_ACHIEVEMENT, cvData.getAchievement());
        values.put(Params.KEY_CARRIER_OBJECTIVE, cvData.getCarrierObjective());
        values.put(Params.KEY_TITLE, cvData.getTitle());
        values.put(Params.KEY_PERIOD,cvData.getPeriod());
        values.put(Params.KEY_ROLE, cvData.getRole());
        values.put(Params.KEY_DESCRIPTION, cvData.getDescription());
        values.put(Params.KEY_TEAM_SIZE, cvData.getTeamSize());

        //Lets update now
        return db.update(Params.TABLE_NAME1, values, Params.KEY_ID + "=?",
                new String[]{String.valueOf(cvData.getId())});
    }

    public int getCount(){
        String query = "SELECT  * FROM " + Params.TABLE_NAME1;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        return cursor.getCount();

    }

}