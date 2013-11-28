package com.example.lesson7;

import database.Student;
import database.StudentsContract.StudentEntry;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.app.LoaderManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class StudentsFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor>
{
	View mHeaderView;
	SimpleCursorAdapter mAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//ListView listView = (ListView)super.onCreateView(inflater, container, savedInstanceState);
		mHeaderView = inflater.inflate(R.layout.header, null);
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		getListView().addHeaderView(mHeaderView, null, false);
		setListAdapter(mAdapter);
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mAdapter = new SimpleCursorAdapter(getActivity(), 
				R.layout.students_layout, null,
                new String[] { StudentEntry.COLUMN_NAME_STUDENT_NAME, StudentEntry.COLUMN_NAME_STUDENT_SURNAME, StudentEntry.COLUMN_NAME_GROUP },
                new int[] { R.id.name, R.id.surname, R.id.group }, 0);
		//setListAdapter(adapter);
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
		//SimpleCursorAdapter adapter =  (SimpleCursorAdapter) getListAdapter();
		mAdapter.swapCursor(data);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		// This is called when the last Cursor provided to onLoadFinished()
	    // above is about to be closed.  We need to make sure we are no
	    // longer using it.
		//SimpleCursorAdapter adapter =  (SimpleCursorAdapter) getListAdapter();
		mAdapter.swapCursor(null);
	}
	
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		
        Cursor cursor = (Cursor)getListAdapter().getItem(position);
        
        Integer studentId = cursor.getInt(cursor.getColumnIndex(StudentEntry._ID));
        String name = cursor.getString(cursor.getColumnIndex(StudentEntry.COLUMN_NAME_STUDENT_NAME));
        String surname = cursor.getString(cursor.getColumnIndex(StudentEntry.COLUMN_NAME_STUDENT_SURNAME));
        Integer groupId = cursor.getInt(cursor.getColumnIndex(StudentEntry.COLUMN_NAME_GROUP));
        
        Student student = new Student(studentId, name, surname, groupId);
        
        StudentDialog dialog  = new StudentEditDialog(student);
        dialog.show(getFragmentManager(), "StudentDialog");
	}
}
