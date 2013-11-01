package com.example.lesson7;

import database.DbHelper;
import database.StudentsContract.StudentEntry;

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
			 mAdapter = new SimpleCursorAdapter(this,
	                    android.R.layout.simple_list_item_1, null,
	                    new String[] { StudentEntry.COLUMN_NAME_STUDENT_NAME },
	                    new int[] { android.R.id.text1 }, 0);
			 listView.setAdapter(mAdapter);
		}
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
