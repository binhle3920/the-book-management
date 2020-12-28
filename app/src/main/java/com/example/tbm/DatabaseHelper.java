package com.example.tbm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int version = 1;

    public DatabaseHelper(Context context) {
        super(context, "TBM.db", null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(name text primary key, email text, password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists user");
    }

    //inserting to database
    public boolean insert(String username, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("name", username);
        cv.put("email", email);
        cv.put("password", password);

        long ins =  db.insert("user", null, cv);
        if (ins == 1) return true;
        else return false;
    }

    //check if username exists
    public boolean checkUser(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where name = ?", new String[]{username});
        if (cursor.getCount() > 0) return false;
        else return true;
    }

    //check if email exists
    public boolean checkEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email = ?", new String[]{email});
        if (cursor.getCount() > 0) return false;
        else return true;
    }

    //get password
    public String getPassword(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where name = ?", new String[]{username});
        if (cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex("password"));
        }
        else {
            return "wrong";
        }
    }
}
