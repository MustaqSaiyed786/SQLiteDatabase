package com.example.topdemo.kotlin

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.topdemo.java.User
import java.util.*

class DatabaseHelperk(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    private val CREATE_USER_TABLE = ("CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER_FIRST_NAME + " TEXT,"
            + COLUMN_USER_LAST_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT,"
            + COLUMN_USER_MOBILE + " TEXT,"
            + COLUMN_USER_GENDER + " TEXT,"
            + COLUMN_USER_EDUCATION + " TEXT,"
            + COLUMN_USER_COUNTRY + " TEXT,"
            + COLUMN_USER_PASSWORD + " TEXT" + ")")
    private val DROP_USER_TABLE = "DROP TABLE IF EXISTS $TABLE_USER"
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_USER_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(DROP_USER_TABLE)
        onCreate(db)
    }

    fun addUser(user: User) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_USER_FIRST_NAME, user.first_name)
        values.put(COLUMN_USER_LAST_NAME, user.last_name)
        values.put(COLUMN_USER_EMAIL, user.email)
        values.put(COLUMN_USER_MOBILE, user.mobile)
        values.put(COLUMN_USER_GENDER, user.gende)
        values.put(COLUMN_USER_EDUCATION, user.education)
        values.put(COLUMN_USER_COUNTRY, user.cuntry)
        values.put(COLUMN_USER_PASSWORD, user.password)

        // Inserting Row
        db.insert(TABLE_USER, null, values)
        db.close()
    }

    //Table to query
    val allUser: List<User>
        get() {
            val columns = arrayOf(
                    COLUMN_USER_ID,
                    COLUMN_USER_EMAIL,
                    COLUMN_USER_FIRST_NAME,
                    COLUMN_USER_LAST_NAME,
                    COLUMN_USER_MOBILE,
                    COLUMN_USER_GENDER,
                    COLUMN_USER_EDUCATION,
                    COLUMN_USER_COUNTRY,
                    COLUMN_USER_PASSWORD
            )
            val userList: MutableList<User> = ArrayList()
            val db = this.readableDatabase
            val cursor = db.query(TABLE_USER,  //Table to query
                    columns,
                    null,
                    null,
                    null,
                    null,
                    "")
            if (cursor.moveToFirst()) {
                do {
                    val user = User()
                    user.id = cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID)).toInt()
                    user.first_name = cursor.getString(cursor.getColumnIndex(COLUMN_USER_FIRST_NAME))
                    user.last_name = cursor.getString(cursor.getColumnIndex(COLUMN_USER_LAST_NAME))
                    user.email = cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL))
                    user.mobile = cursor.getString(cursor.getColumnIndex(COLUMN_USER_MOBILE))
                    user.gende = cursor.getString(cursor.getColumnIndex(COLUMN_USER_GENDER))
                    user.education = cursor.getString(cursor.getColumnIndex(COLUMN_USER_EDUCATION))
                    user.cuntry = cursor.getString(cursor.getColumnIndex(COLUMN_USER_COUNTRY))
                    user.password = cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD))
                    userList.add(user)
                } while (cursor.moveToNext())
            }
            cursor.close()
            db.close()
            return userList
        }

    fun checkUser(email: String): Boolean {
        val columns = arrayOf(
                COLUMN_USER_ID
        )
        val db = this.readableDatabase
        val selection = "$COLUMN_USER_EMAIL = ?"
        val selectionArgs = arrayOf(email)
        val cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null)
        val cursorCount = cursor.count
        cursor.close()
        db.close()
        return cursorCount > 0
    }

    fun checkUser(email: String, password: String): Boolean {
        val columns = arrayOf(
                COLUMN_USER_ID
        )
        val db = this.readableDatabase
        val selection = "$COLUMN_USER_EMAIL = ? AND $COLUMN_USER_PASSWORD = ?"
        val selectionArgs = arrayOf(email, password)
        val cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null)
        val cursorCount = cursor.count
        cursor.close()
        db.close()
        return cursorCount > 0
    }

    companion object {
        private const val DATABASE_VERSION = 2
        private const val DATABASE_NAME = "Tops1"
        private const val TABLE_USER = "TopsUser"
        private const val COLUMN_USER_ID = "user_id"
        private const val COLUMN_USER_FIRST_NAME = "fist_name"
        private const val COLUMN_USER_LAST_NAME = "last_name"
        private const val COLUMN_USER_EMAIL = "email"
        private const val COLUMN_USER_MOBILE = "mobile"
        private const val COLUMN_USER_GENDER = "gender"
        private const val COLUMN_USER_EDUCATION = "education"
        private const val COLUMN_USER_COUNTRY = "country"
        private const val COLUMN_USER_PASSWORD = "password"
    }
}