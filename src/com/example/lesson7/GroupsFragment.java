package com.example.lesson7;

import database.Group;
import database.GroupsContract.GroupEntry;
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

public class GroupsFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor>
{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(getActivity(), 
				R.layout.groups_layout, null,
                new String[] { GroupEntry._ID, GroupEntry.COLUMN_NAME_GROUP_NAME, GroupEntry.COLUMN_NAME_SEMESTER },
                new int[] { R.id.idGroup, R.id.nameGroup, R.id.semester }, 0);
		setListAdapter(adapter);
		getActivity().getLoaderManager().initLoader(1, null, this);
	}
	
	@Override
	public Loader<Cursor> onCreateLoader(int loaderId, Bundle bundle) {
		return new CursorLoader(this.getActivity(), 
				GroupEntry.CONTENT_URI,
				new String[] {GroupEntry._ID, GroupEntry.COLUMN_NAME_GROUP_NAME, GroupEntry.COLUMN_NAME_SEMESTER}, 
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
		
		Group group = new Group();
        Cursor cursor = (Cursor)getListAdapter().getItem(position);
        
        group.id = cursor.getInt(cursor.getColumnIndex(GroupEntry._ID));
        group.name = cursor.getString(cursor.getColumnIndex(GroupEntry.COLUMN_NAME_GROUP_NAME));
        group.semester = cursor.getInt(cursor.getColumnIndex(GroupEntry.COLUMN_NAME_SEMESTER));

        GroupDialog dialog  = new GroupDialog(group);
        dialog.show(getFragmentManager(), "GroupDialog");
	}
}
