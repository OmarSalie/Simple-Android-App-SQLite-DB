package com.example.basiccrudwithsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "studentDB.db";

    public static final String TABLE_NAME = "Student";
    public static final String COLUMN_ID = "StudentId";
    public static final String COLUMN_NAME = "StudentName";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = " CREATE TABLE " + TABLE_NAME + "(" +
                                COLUMN_ID + " INTEGER PRIMARY KEY," +
                                COLUMN_NAME + " TEXT )";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public String loadHandler() {
        String result = "";
        String query =  "SELECT * " +
                        "FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            result += String.valueOf(result_0) + " " + result_1 +
                    System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }

    public void addHandler(Student student) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, student.getStudentId());
        values.put(COLUMN_NAME, student.getStudentName());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Student findHandler(String studentname) {
        String query =  "SELECT * " +
                        "FROM " + TABLE_NAME + " " +
                        "WHERE " + COLUMN_NAME + " = " + "'" + studentname + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Student student = new Student();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            student.setStudentId(Integer.parseInt(cursor.getString(0)));
            student.setStudentName(cursor.getString(1));
            cursor.close();
        } else {
            student = null;
        }
        db.close();
        return student;
    }

    public boolean deleteHandler(int id) {
        boolean result = false;
        String query =  "SELECT * " +
                        "FROM " + TABLE_NAME + " " +
                        "WHERE " + COLUMN_ID + " = '" + String.valueOf(id) + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Student student = new Student();
        if (cursor.moveToFirst()) {
            student.setStudentId(Integer.parseInt(cursor.getString(0)));
            db.delete(
                TABLE_NAME,
                COLUMN_ID + "=?",
                new String[] {
                    String.valueOf(student.getStudentId())
                }
            );
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public boolean updateHandler(int id, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_ID, id);
        args.put(COLUMN_NAME, name);
        return db.update(TABLE_NAME, args, COLUMN_ID + " = " + id, null) > 0;
    }
}
