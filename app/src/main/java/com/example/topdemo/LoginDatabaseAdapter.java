package com.example.topdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

public class LoginDatabaseAdapter {
    static final String DATABASE_NAME = "database.db";
    String ok = "OK";
    static final int DATABASE_VERSION = 1;
    public static String getPassword = "";
    private static String TableName = "User";
    private static String ColumnID = "_Id";
    private static String ColumnUsername = "username";
    private static String ColumnFirstName = "firstname";
    private static String ColumnLastName = "lastname";
    private static String ColumnAddress = "address";
    private static String ColumnCountry = "country";
    private static String ColumnPassword = "password";
    private static String ColumnEmail = "email";
    private static String ColumnMobile = "mobile";
    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    public static String CreateQuery = "CREATE TABLE " + TableName +
            " ( "
            + ColumnID + " INTEGER PRIMARY KEY,"
            + ColumnUsername + " TEXT,"
            + ColumnFirstName + " TEXT,"
            + ColumnLastName + " TEXT,"
            + ColumnAddress + " TEXT,"
            + ColumnCountry + " TEXT,"
            + ColumnPassword + " TEXT,"
            + ColumnEmail + " TEXT,"
            + ColumnMobile + " TEXT)";
    // Variable to hold the database instance
    public static SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private static DataBaseHelper dbHelper;

    public LoginDatabaseAdapter(Context _context) {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method to openthe Database
    public LoginDatabaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    // Method to close the Database
    public void close() {
        db.close();
    }

    // method returns an Instance of the Database
    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    // method to insert a record in Table
    public String insertEntry(
            String firstName,
            String lastName,
            String email,
            String mobile,
            String gender,
            String education,
            String country,
            String password
    ) {
        try {
            ContentValues newValues = new ContentValues();
            // Assign values for each column.
            newValues.put("FIRSTNAME", firstName);
            newValues.put("LASTNAME", lastName);
            newValues.put("EMAIL", email);
            newValues.put("MOBILE", mobile);
            newValues.put("GENDER", gender);
            newValues.put("EDUCTION", education);
            newValues.put("COUNTRY", country);
            newValues.put("PASSWORD", password);
            // Insert the row into your table
            db = dbHelper.getWritableDatabase();
            long result = db.insert("LOGIN", null, newValues);
            System.out.print(result);
            Toast.makeText(context, "User Info Saved", Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            System.out.println("Exceptions " + ex);
            Log.e("Note", "One row entered");
        }
        return ok;
    }

    // method to delete a Record of UserName
    public int deleteEntry(String UserName) {
        String where = "USERNAME=?";
        int numberOFEntriesDeleted = db.delete("LOGIN", where, new String[]{UserName});
        Toast.makeText(context, "Number fo Entry Deleted Successfully : " + numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }

    // method to get the password  of userName
    public String getSinlgeEntry(String userName) {
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("LOGIN", null, "USERNAME=?", new String[]{userName}, null, null, null);
        if (cursor.getCount() < 1) // UserName Not Exist
            return "NOT EXIST";
        cursor.moveToFirst();
        getPassword = cursor.getString(cursor.getColumnIndex("PASSWORD"));
        return getPassword;
    }

    // Method to Update an Existing
    public void updateEntry(String userName, String password) {
        //  create object of ContentValues
        ContentValues updatedValues = new ContentValues();
        // Assign values for each Column.
        updatedValues.put("USERNAME", userName);
        updatedValues.put("PASSWORD", password);
        String where = "USERNAME = ?";
        db.update("LOGIN", updatedValues, where, new String[]{userName});
    }

}