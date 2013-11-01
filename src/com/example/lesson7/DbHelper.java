package com.example.lesson7;

import com.example.lesson7.StudentsContract.StudentEntry;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper
{
	private static final String TEXT_TYPE = " TEXT";
	private static final String INTEGER_TYPE = " INTEGER"; 
	private static final String COMMA_SEP = ",";
	private static final String SQL_CREATE_ENTRIES =
	    "CREATE TABLE " + StudentEntry.TABLE_NAME + " (" +
	    		StudentEntry._ID + " INTEGER PRIMARY KEY," +
	    		StudentEntry.COLUMN_NAME_STUDENT_NAME + TEXT_TYPE + COMMA_SEP +
	    StudentEntry.COLUMN_NAME_STUDENT_SURNAME + TEXT_TYPE + COMMA_SEP +  
	    StudentEntry.COLUMN_NAME_GROUP + INTEGER_TYPE + COMMA_SEP +  
	    StudentEntry.COLUMN_NAME_COURSE + INTEGER_TYPE + " )";

	private static final String SQL_DELETE_ENTRIES =
	    "DROP TABLE IF EXISTS " + StudentEntry.TABLE_NAME;
	
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Students.db";

	public DbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	    db.execSQL(SQL_CREATE_ENTRIES);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
	}
	
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
	
}
