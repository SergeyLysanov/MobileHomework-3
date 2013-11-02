package database;


import database.GroupsContract.GroupEntry;
import database.StudentsContract.StudentEntry;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

/**
 * A very simple implementation of a content provider.
 */
public class SimpleProvider extends ContentProvider {
	
	public static final String AUTHORITY = "com.example.databaseApp.SimpleProvider";
	
    // Uri matcher to decode incoming URIs.
    private final UriMatcher mUriMatcher;

    private static final int GROUP = 1;
    private static final int STUDENTS = 2;

    // Handle to a new DatabaseHelper.
    private DbHelper mOpenHelper;

    /**
     * Global provider initialization.
     */
    public SimpleProvider() {
        // Create and initialize URI matcher.
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI(AUTHORITY, GroupEntry.TABLE_NAME, GROUP);
        mUriMatcher.addURI(AUTHORITY, StudentEntry.TABLE_NAME, STUDENTS);
    }

    /**
     * Perform provider creation.
     */
    @Override
    public boolean onCreate() {
        mOpenHelper = new DbHelper(getContext());
        // Assumes that any failures will be reported by a thrown exception.
        return true;
    }

    /**
     * Handle incoming queries.
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
            String[] selectionArgs, String sortOrder) {

        // Constructs a new query builder and sets its table name
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        
        switch (mUriMatcher.match(uri)) {
            case GROUP:
            	qb.setTables(GroupEntry.TABLE_NAME);
                break;

            case STUDENTS:
            	qb.setTables(StudentEntry.TABLE_NAME);
            	break;

            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        Cursor c = qb.query(db, projection, selection, selectionArgs,
                null /* no group */, null /* no filter */, sortOrder);

        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    /**
     * Return the MIME type for an known URI in the provider.
     */
    @Override
    public String getType(Uri uri) {
        return null;
    }

    /**
     * Handler inserting new data.
     */
    @Override
    public Uri insert(Uri uri, ContentValues initialValues) {
    	return null;
    }

    /**
     * Handle deleting data.
     */
    @Override
    public int delete(Uri uri, String where, String[] whereArgs) {
       return 0;
    }

    /**
     * Handle updating data.
     */
    @Override
    public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int count;

        switch (mUriMatcher.match(uri)) {
            case GROUP:
                // If URI is main table, update uses incoming where clause and args.
                count = db.update(GroupEntry.TABLE_NAME, values, where, whereArgs);
                break;

            case STUDENTS:
            	// If URI is main table, update uses incoming where clause and args.
                count = db.update(StudentEntry.TABLE_NAME, values, where, whereArgs);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return count;
    }
}