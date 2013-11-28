package services;

import database.StudentsContract.StudentEntry;
import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;

public class StudentAddService extends IntentService {

	  /** 
	   * A constructor is required, and must call the super IntentService(String)
	   * constructor with a name for the worker thread.
	   */
	  public StudentAddService() {
	      super("StudentAddService");
	  }

	  /**
	   * The IntentService calls this method from the default worker thread with
	   * the intent that started the service. When this method returns, IntentService
	   * stops the service, as appropriate.
	   */
	  @Override
	  protected void onHandleIntent(Intent intent) {
  
		  String name = intent.getStringExtra(StudentEntry.COLUMN_NAME_STUDENT_NAME);
          String surname = intent.getStringExtra(StudentEntry.COLUMN_NAME_STUDENT_SURNAME);
          Integer groupId = intent.getIntExtra(StudentEntry.COLUMN_NAME_GROUP, -1);
          
          String projection[] = {StudentEntry._ID};
		  String mSelectionClause =  StudentEntry.COLUMN_NAME_STUDENT_NAME + " = ? AND " + StudentEntry.COLUMN_NAME_STUDENT_SURNAME + " = ? ";
		  String[] selectionArgs ={name, surname};
		  
		  Cursor cursor = getContentResolver().query(StudentEntry.CONTENT_URI, projection, mSelectionClause, selectionArgs, null);
		  
		  if (cursor.getCount() >= 1) return;//Student already exists
		  

          ContentValues cv = new ContentValues();
          cv.put(StudentEntry.COLUMN_NAME_STUDENT_NAME, name);
          cv.put(StudentEntry.COLUMN_NAME_STUDENT_SURNAME, surname);
          cv.put(StudentEntry.COLUMN_NAME_GROUP, groupId);
          
          getContentResolver().insert(StudentEntry.CONTENT_URI, cv); 
	  }
	}