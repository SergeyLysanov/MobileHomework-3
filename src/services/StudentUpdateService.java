package services;

import database.StudentsContract.StudentEntry;
import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;

public class StudentUpdateService extends IntentService {

	  /** 
	   * A constructor is required, and must call the super IntentService(String)
	   * constructor with a name for the worker thread.
	   */
	  public StudentUpdateService() {
	      super("StudentUpdateService");
	  }

	  /**
	   * The IntentService calls this method from the default worker thread with
	   * the intent that started the service. When this method returns, IntentService
	   * stops the service, as appropriate.
	   */
	  @Override
	  protected void onHandleIntent(Intent intent) {
		  Integer id = intent.getIntExtra(StudentEntry._ID, -1);
		  String name = intent.getStringExtra(StudentEntry.COLUMN_NAME_STUDENT_NAME);
          String surname = intent.getStringExtra(StudentEntry.COLUMN_NAME_STUDENT_SURNAME);
          Integer groupId = intent.getIntExtra(StudentEntry.COLUMN_NAME_GROUP, -1);

          ContentValues cv = new ContentValues();
          cv.put(StudentEntry.COLUMN_NAME_STUDENT_NAME, name);
          cv.put(StudentEntry.COLUMN_NAME_STUDENT_SURNAME, surname);
          cv.put(StudentEntry.COLUMN_NAME_GROUP, groupId);
          
          String where = StudentEntry._ID + "=" + id;

          getContentResolver().update(StudentEntry.CONTENT_URI, cv, where, null); 
	  }
	}