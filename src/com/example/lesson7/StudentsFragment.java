package com.example.lesson7;

import database.StudentsContract.StudentEntry;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.app.LoaderManager;
import android.widget.SimpleCursorAdapter;

@SuppressLint("ValidFragment")
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class StudentsFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor>
{
	private Context mContext;
	
	public StudentsFragment(){}
	public StudentsFragment(Context context)
	{
		this.mContext = context;
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(context, 
				R.layout.students_layout, null,
                new String[] { StudentEntry.COLUMN_NAME_STUDENT_NAME, StudentEntry.COLUMN_NAME_STUDENT_SURNAME, StudentEntry.COLUMN_NAME_GROUP },
                new int[] { R.id.name, R.id.surname, R.id.group }, 0);
		setListAdapter(adapter);
	}
	
	@Override
	public void onStart() {
		getActivity().getLoaderManager().initLoader(0, null, this);
		super.onStart();
	}
	
	@Override
	public Loader<Cursor> onCreateLoader(int loaderId, Bundle bundle) {
		return new CursorLoader(mContext, 
				StudentEntry.CONTENT_URI,
				new String[] {StudentEntry._ID, StudentEntry.COLUMN_NAME_STUDENT_NAME, StudentEntry.COLUMN_NAME_STUDENT_SURNAME, StudentEntry.COLUMN_NAME_GROUP}, 
                null, null, null);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		// Swap the new cursor in.  (The framework will take care of closing the
	    // old cursor once we return.)
		SimpleCursorAdapter adapter =  (SimpleCursorAdapter) getListAdapter();
		adapter.swapCursor(data);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		// This is called when the last Cursor provided to onLoadFinished()
	    // above is about to be closed.  We need to make sure we are no
	    // longer using it.
		SimpleCursorAdapter adapter =  (SimpleCursorAdapter) getListAdapter();
		adapter.swapCursor(null);
	}
}
