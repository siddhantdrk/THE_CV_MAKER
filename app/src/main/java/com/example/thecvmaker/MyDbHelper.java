package com.example.thecvmaker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDbHelper extends SQLiteOpenHelper {


    public MyDbHelper(Context context) {
        super(context, Params.KEY_DATABASE, null, Params.KEY_DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + Params.TABLE_NAME1 + "(" + Params.KEY_ID + " INTEGER PRIMARY KEY,"
                + Params.KEY_NAME + " TEXT,"
                + Params.KEY_EMAIL + " TEXT, " + Params.KEY_PHONE_NUMBER + " TEXT, " + Params.KEY_DOB + " TEXT,"
                + Params.KEY_GENDER + " TEXT," + Params.KEY_NATIONALITY + " TEXT, " + Params.KEY_ADDRESS + " TEXT,"
                + Params.KEY_LANGUAGE + " TEXT, " + Params.KEY_EDUCATION + " TEXT, " + Params.KEY_WORKEXPERIENCE +
                " TEXT, " + Params.KEY_PROJECTCONTRIBUTIONS + " TEXT, " + Params.KEY_OTHERSKILLS + " TEXT, " + Params.KEY_IMAGE + " BLOB);";

        Log.d("dbharry", "Query being run is : "+ create);
        db.execSQL(create);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addCv(UserCv cvData, int count, byte[] Image) {
        SQLiteDatabase db = this.getWritableDatabase();
        String select = "SELECT * FROM " + Params.TABLE_NAME1;
        Cursor cursor = db.rawQuery(select, null);
        ContentValues values = new ContentValues();
        values.put(Params.KEY_NAME, cvData.getName());
        values.put(Params.KEY_EMAIL, cvData.getEmailAddress());
        values.put(Params.KEY_PHONE_NUMBER, cvData.getPhoneNumber());
        values.put(Params.KEY_DOB, cvData.getDob());
        values.put(Params.KEY_GENDER, cvData.getGender());
        values.put(Params.KEY_NATIONALITY, cvData.getNationality());
        values.put(Params.KEY_ADDRESS, cvData.getAddress());
        values.put(Params.KEY_LANGUAGE, cvData.getLanguage());
        values.put(Params.KEY_EDUCATION, cvData.getEducationListString());
        values.put(Params.KEY_WORKEXPERIENCE, cvData.getWorkExpListString());
        values.put(Params.KEY_PROJECTCONTRIBUTIONS, cvData.getProjectContributionListString());
        values.put(Params.KEY_OTHERSKILLS, cvData.getSkillsOthersListString());
        values.put(Params.KEY_IMAGE, Image);

        if (count == 0) {
            db.insert(Params.TABLE_NAME1, null, values);
        } else {
            db.update(Params.TABLE_NAME1, values, Params.KEY_ID + "=?",
                    new String[]{String.valueOf(getCount())});
        }
        Log.d("dev", "Successfully inserted" + cvData.getName());
    }

    public UserCv getCv() {
        // List<UserCv> CvList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Generate the query to read from the database
        String select = "SELECT * FROM " + Params.TABLE_NAME1;
        Cursor cursor = db.rawQuery(select, null);
        //Loop through now
        cursor.moveToLast();
        UserCv Cv = new UserCv();
        Cv.setName(cursor.getString(1));
        Cv.setEmailAddress(cursor.getString(2));
        Cv.setPhoneNumber(cursor.getString(3));
        Cv.setDob(cursor.getString(4));
        Cv.setGender(cursor.getString(5));
        Cv.setNationality(cursor.getString(6));
        Cv.setAddress(cursor.getString(7));
        Cv.setLanguage(cursor.getString(8));
        Cv.setEducationListString(cursor.getString(9));
        Cv.setWorkExpListString(cursor.getString(10));
        Cv.setProjectContributionListString(cursor.getString(11));
        Cv.setSkillsOthersListString(cursor.getString(12));
        db.close();
        return Cv;
    }

    public void updatePersonDetails(UserCv cvData) {
        SQLiteDatabase db = this.getWritableDatabase();
        String select = "SELECT * FROM " + Params.TABLE_NAME1;
        Cursor cursor = db.rawQuery(select, null);
        ContentValues values = new ContentValues();
        values.put(Params.KEY_NAME, cvData.getName());
        values.put(Params.KEY_EMAIL, cvData.getEmailAddress());
        values.put(Params.KEY_PHONE_NUMBER, cvData.getPhoneNumber());
        values.put(Params.KEY_DOB, cvData.getDob());
        values.put(Params.KEY_GENDER, cvData.getGender());
        values.put(Params.KEY_NATIONALITY, cvData.getNationality());
        values.put(Params.KEY_ADDRESS, cvData.getAddress());
        values.put(Params.KEY_LANGUAGE, cvData.getLanguage());

        //Lets update now
        db.update(Params.TABLE_NAME1, values, Params.KEY_ID + "=?",
                new String[]{String.valueOf(getCount())});
    }

    public void updateEducationDetails(String EducationListString) {
        SQLiteDatabase db = this.getWritableDatabase();
        String select = "SELECT * FROM " + Params.TABLE_NAME1;
        Cursor cursor = db.rawQuery(select, null);
        ContentValues values = new ContentValues();
        values.put(Params.KEY_EDUCATION, EducationListString);
        db.update(Params.TABLE_NAME1, values, Params.KEY_ID + "=?",
                new String[]{String.valueOf(getCount())});

    }

    public void updateWorkExperienceDetails(String WorkExpListString) {
        SQLiteDatabase db = this.getWritableDatabase();
        String select = "SELECT * FROM " + Params.TABLE_NAME1;
        Cursor cursor = db.rawQuery(select, null);
        ContentValues values = new ContentValues();
        values.put(Params.KEY_WORKEXPERIENCE, WorkExpListString);
        db.update(Params.TABLE_NAME1, values, Params.KEY_ID + "=?",
                new String[]{String.valueOf(getCount())});
    }

    public void updateProjectDetails(String ProjectContributionListString) {
        SQLiteDatabase db = this.getWritableDatabase();
        String select = "SELECT * FROM " + Params.TABLE_NAME1;
        Cursor cursor = db.rawQuery(select, null);
        ContentValues values = new ContentValues();
        values.put(Params.KEY_PROJECTCONTRIBUTIONS, ProjectContributionListString);
        db.update(Params.TABLE_NAME1, values, Params.KEY_ID + "=?",
                new String[]{String.valueOf(getCount())});
    }

    public void updateSkillsDetails(String SkillsOthersListString) {
        SQLiteDatabase db = this.getWritableDatabase();
        String select = "SELECT * FROM " + Params.TABLE_NAME1;
        Cursor cursor = db.rawQuery(select, null);
        ContentValues values = new ContentValues();
        values.put(Params.KEY_OTHERSKILLS, SkillsOthersListString);
        db.update(Params.TABLE_NAME1, values, Params.KEY_ID + "=?",
                new String[]{String.valueOf(getCount())});
    }

    public int getCount() {
        String query = "SELECT  * FROM " + Params.TABLE_NAME1;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        return cursor.getCount();
    }

    public byte[] getImageByte() {
        SQLiteDatabase db = this.getReadableDatabase();
        String select = "SELECT * FROM " + Params.TABLE_NAME1;
        Cursor cursor = db.rawQuery(select, null);
        cursor.moveToLast();
        byte[] bytes = cursor.getBlob(13);
        db.close();
        return bytes;
    }
}