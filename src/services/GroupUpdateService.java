package services;

import database.GroupsContract.GroupEntry;
import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;

public class GroupUpdateService extends IntentService {

	  /** 
	   * A constructor is required, and must call the super IntentService(String)
	   * constructor with a name for the worker thread.
	   */
	  public GroupUpdateService() {
	      super("GroupUpdateService");
	  }

	  /**
	   * The IntentService calls this method from the default worker thread with
	   * the intent that started the service. When this method returns, IntentService
	   * stops the service, as appropriate.
	   */
	  @Override
	  protected void onHandleIntent(Intent intent) {
		  Integer id = intent.getIntExtra(GroupEntry._ID, -1);
		  String name = intent.getStringExtra(GroupEntry.COLUMN_NAME_GROUP_NAME);
		  Integer semester = intent.getIntExtra(GroupEntry.COLUMN_NAME_SEMESTER, -1);

          ContentValues cv = new ContentValues();
          cv.put(GroupEntry.COLUMN_NAME_GROUP_NAME, name);
          cv.put(GroupEntry.COLUMN_NAME_SEMESTER, semester);
          
          String where = GroupEntry._ID + "=" + id;

          getContentResolver().update(GroupEntry.CONTENT_URI, cv, where, null); 
	  }
	}