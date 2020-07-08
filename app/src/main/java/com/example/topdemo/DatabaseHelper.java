package com.example.topdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_NAME = "Tops1";

    private static final String TABLE_USER = "TopsUser";

    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_FIRST_NAME = "fist_name";
    private static final String COLUMN_USER_LAST_NAME = "last_name";
    private static final String COLUMN_USER_EMAIL = "email";
    private static final String COLUMN_USER_MOBILE = "mobile";
    private static final String COLUMN_USER_GENDER = "gender";
    private static final String COLUMN_USER_EDUCATION = "education";
    private static final String COLUMN_USER_COUNTRY = "country";
    private static final String COLUMN_USER_PASSWORD = "password";


    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER_FIRST_NAME + " TEXT,"
            + COLUMN_USER_LAST_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT,"
            + COLUMN_USER_MOBILE + " TEXT,"
            + COLUMN_USER_GENDER + " TEXT,"
            + COLUMN_USER_EDUCATION + " TEXT,"
            + COLUMN_USER_COUNTRY + " TEXT,"
            + COLUMN_USER_PASSWORD + " TEXT" + ")";

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);

    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_FIRST_NAME, user.getFirst_name());
        values.put(COLUMN_USER_LAST_NAME, user.getLast_name());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_MOBILE, user.getMobile());
        values.put(COLUMN_USER_GENDER, user.getGende());
        values.put(COLUMN_USER_EDUCATION, user.getEducation());
        values.put(COLUMN_USER_COUNTRY, user.getCuntry());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public List<User> getAllUser() {
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_EMAIL,
                COLUMN_USER_FIRST_NAME,
                COLUMN_USER_LAST_NAME,
                COLUMN_USER_MOBILE,
                COLUMN_USER_GENDER,
                COLUMN_USER_EDUCATION,
                COLUMN_USER_COUNTRY,
                COLUMN_USER_PASSWORD
        };

        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,
                null,
                null,
                null,
                null,
                "");


        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                user.setFirst_name(cursor.getString(cursor.getColumnIndex(COLUMN_USER_FIRST_NAME)));
                user.setLast_name(cursor.getString(cursor.getColumnIndex(COLUMN_USER_LAST_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                user.setMobile(cursor.getString(cursor.getColumnIndex(COLUMN_USER_MOBILE)));
                user.setGende(cursor.getString(cursor.getColumnIndex(COLUMN_USER_GENDER)));
                user.setEducation(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EDUCATION)));
                user.setCuntry(cursor.getString(cursor.getColumnIndex(COLUMN_USER_COUNTRY)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return userList;
    }

    public boolean checkUser(String email) {
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?";
        String[] selectionArgs = {email};

        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    public boolean checkUser(String email, String password) {
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }
}
