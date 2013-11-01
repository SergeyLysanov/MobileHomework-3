package com.example.lesson7;

import com.example.lesson7.StudentsContract.StudentEntry;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor>{

	private SimpleCursorAdapter mAdapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if(savedInstanceState == null)
		{
			ListView listView = (ListView)findViewById(R.id.listView);
			FillDatabase();
			
			 mAdapter = new SimpleCursorAdapter(this,
	                    android.R.layout.simple_list_item_1, null,
	                    new String[] { StudentEntry.COLUMN_NAME_STUDENT_NAME },
	                    new int[] { android.R.id.text1 }, 0);
			 listView.setAdapter(mAdapter);
		}
	}
	
	private void FillDatabase(){
		DbHelper dbHelper = new DbHelper(this);
		
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		// Create a new map of values, where column names are the keys
		ContentValues values = new ContentValues();
		values.put(StudentEntry.COLUMN_NAME_STUDENT_NAME, "Валерий");
		values.put(StudentEntry.COLUMN_NAME_STUDENT_SURNAME, "Ожиганов");
		values.put(StudentEntry.COLUMN_NAME_GROUP, 1);
		values.put(StudentEntry.COLUMN_NAME_COURSE, 1);

		// Insert the new row, returning the primary key value of the new row
		long newRowId;
		newRowId = db.insert(
				StudentEntry.TABLE_NAME,
				null,
		        values);
	}

	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		// TODO Auto-generated method stub
		
	}

}
