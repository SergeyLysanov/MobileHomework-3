package database;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import database.GroupsContract.GroupEntry;
import database.StudentsContract.StudentEntry;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper
{
	private static final String TEXT_TYPE = " TEXT";
	private static final String INTEGER_TYPE = " INTEGER"; 
	private static final String PRIMARY_TYPE = " INTEGER PRIMARY KEY"; 
	private static final String COMMA_SEP = ",";
	
	private static final String SQL_CREATE_STUDENTS =
		    "CREATE TABLE " + StudentEntry.TABLE_NAME + " (" +
		    StudentEntry._ID + PRIMARY_TYPE + COMMA_SEP + 
		    StudentEntry.COLUMN_NAME_STUDENT_NAME + TEXT_TYPE + COMMA_SEP +
		    StudentEntry.COLUMN_NAME_STUDENT_SURNAME + TEXT_TYPE + COMMA_SEP +  
		    StudentEntry.COLUMN_NAME_GROUP + INTEGER_TYPE + " )";
	
	private static final String SQL_CREATE_GROUPS =
		    "CREATE TABLE " + GroupEntry.TABLE_NAME + " (" +
		    GroupEntry._ID + PRIMARY_TYPE + COMMA_SEP + 
		    GroupEntry.COLUMN_NAME_GROUP_NAME + TEXT_TYPE + COMMA_SEP +
		    GroupEntry.COLUMN_NAME_SEMESTER + INTEGER_TYPE + " )";

	private static final String SQL_DELETE_STUDENTS =
	    "DROP TABLE IF EXISTS " + StudentEntry.TABLE_NAME;
	private static final String SQL_DELETE_GROUPS =
		"DROP TABLE IF EXISTS " + GroupEntry.TABLE_NAME;
	
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "Students.db";

	public DbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	private void fillStudents(SQLiteDatabase db){
		List<String> studentsName = Arrays.asList("Антон", "Вася", "Гоша", "Петя" , "Катя", "Люся", "Дмитрий", "Дмитрий", "Павлик", "Ваня", "Карл", "Марк");
		List<String> studentsSurname = Arrays.asList("Антонов", "Васильев", "Петров", "Сидоров" , "Кабанова", "Максимова", "Гришин", "Волошин", "Морозов", "Ванин", "Маркс", "Марков");
		
		for(int i = 0; i < studentsName.size(); ++i){
			ContentValues values = new ContentValues();
			values.put(StudentEntry.COLUMN_NAME_STUDENT_NAME, studentsName.get(i));
			values.put(StudentEntry.COLUMN_NAME_STUDENT_SURNAME, studentsSurname.get(i));
			values.put(StudentEntry.COLUMN_NAME_GROUP, getRandom(1, 5));
			
			db.insert(StudentEntry.TABLE_NAME, null, values);
		}
	}
	
	private void fillGroups(SQLiteDatabase db){
		List<String> groupName = Arrays.asList("РК10", "ИУ5", "СМ7", "РК6" , "РК9", "ИУ7", "МТ9", "РЛ5");
		List<String> semesterNum = Arrays.asList("2", "3", "1", "7" , "3", "9", "5", "4");
		
		for(int i = 0; i < groupName.size(); ++i){
			ContentValues values = new ContentValues();
			values.put(GroupEntry.COLUMN_NAME_GROUP_NAME, groupName.get(i));
			values.put(GroupEntry.COLUMN_NAME_SEMESTER, semesterNum.get(i));
			
			db.insert(GroupEntry.TABLE_NAME, null, values);
		}
	}
	
	private Integer getRandom(Integer Min, Integer Max){
		return Min + (int)(Math.random() * ((Max - Min) + 1));
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	    db.execSQL(SQL_CREATE_STUDENTS);
	    db.execSQL(SQL_CREATE_GROUPS);
	    
	    fillGroups(db);
	    fillStudents(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(SQL_DELETE_STUDENTS);
		db.execSQL(SQL_DELETE_GROUPS);
        onCreate(db);
	}
	
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
	
}
