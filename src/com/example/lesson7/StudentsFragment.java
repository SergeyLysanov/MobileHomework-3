package com.example.lesson7;

import database.Student;
import database.StudentsContract.StudentEntry;
import android.annotation.TargetApi;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.app.LoaderManager;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class StudentsFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor>
{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(getActivity(), 
				R.layout.students_layout, null,
                new String[] { StudentEntry.COLUMN_NAME_STUDENT_NAME, StudentEntry.COLUMN_NAME_STUDENT_SURNAME, StudentEntry.COLUMN_NAME_GROUP },
                new int[] { R.id.name, R.id.surname, R.id.group }, 0);
		setListAdapter(adapter);
		getActivity().getLoaderManager().initLoader(0, null, this);
	}
	
	@Override
	public Loader<Cursor> onCreateLoader(int loaderId, Bundle bundle) {
		return new CursorLoader(this.getActivity(), 
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
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		Student student = new Student();
        Cursor cursor = (Cursor)getListAdapter().getItem(position);
        
        student.id = cursor.getInt(cursor.getColumnIndex(StudentEntry._ID));
        student.name = cursor.getString(cursor.getColumnIndex(StudentEntry.COLUMN_NAME_STUDENT_NAME));
        student.surname = cursor.getString(cursor.getColumnIndex(StudentEntry.COLUMN_NAME_STUDENT_SURNAME));
        student.groupId = cursor.getInt(cursor.getColumnIndex(StudentEntry.COLUMN_NAME_GROUP));

        StudentDialog dialog  = new StudentDialog(student);
        dialog.show(getFragmentManager(), "StudentDialog");
	}
}
