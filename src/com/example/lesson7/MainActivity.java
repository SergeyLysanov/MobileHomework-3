package com.example.lesson7;

import database.StudentsContract.StudentEntry;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.LoaderManager;
import android.app.ActionBar.Tab;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;

import android.widget.SimpleCursorAdapter;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<Cursor>{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

        final ActionBar bar = getActionBar();
        bar.addTab(bar.newTab().setText("Студенты").setTabListener(new TabListener()));
        bar.addTab(bar.newTab().setText("Группы").setTabListener(new TabListener()));    
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
    	bar.setDisplayShowHomeEnabled(false);
    	bar.setDisplayShowTitleEnabled(false);
		
        FragmentTransaction transaction = getSupportFragmentManager()
                        .beginTransaction();
        transaction.replace(R.id.fragment_container, new StudentsFragment(this), "StudentFragment");
        transaction.commit();
	}
	
	@Override
	protected void onStart() {
		getLoaderManager().initLoader(0, null, this);
		super.onStart();
	}

	@Override
	public Loader<Cursor> onCreateLoader(int loaderId, Bundle bundle) {
		return new CursorLoader(this, 
				StudentEntry.CONTENT_URI,
				new String[] {StudentEntry._ID, StudentEntry.COLUMN_NAME_STUDENT_NAME, StudentEntry.COLUMN_NAME_STUDENT_SURNAME, StudentEntry.COLUMN_NAME_GROUP}, 
                null, null, null);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		// Swap the new cursor in.  (The framework will take care of closing the
	    // old cursor once we return.)
		ListFragment studentsFragment = (ListFragment) getSupportFragmentManager().findFragmentByTag("StudentFragment");
		SimpleCursorAdapter adapter =  (SimpleCursorAdapter) studentsFragment.getListAdapter();
		adapter.swapCursor(data);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		// This is called when the last Cursor provided to onLoadFinished()
	    // above is about to be closed.  We need to make sure we are no
	    // longer using it.
		ListFragment studentsFragment = (ListFragment) getSupportFragmentManager().findFragmentByTag("StudentFragment");
		SimpleCursorAdapter adapter =  (SimpleCursorAdapter) studentsFragment.getListAdapter();
		adapter.swapCursor(null);
	}
	
	private class TabListener implements ActionBar.TabListener {
		@Override
		public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}
    }
}
