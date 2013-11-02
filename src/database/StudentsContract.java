package database;

import android.net.Uri;
import android.provider.BaseColumns;

public final class StudentsContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public StudentsContract() {}

    /* Inner class that defines the table contents */
    public static abstract class StudentEntry implements BaseColumns {
    	public static final Uri CONTENT_URI =  Uri.parse("content://" + SimpleProvider.AUTHORITY + "/student");
    	
        public static final String TABLE_NAME = "student";
        public static final String COLUMN_NAME_STUDENT_NAME = "name";
        public static final String COLUMN_NAME_STUDENT_SURNAME = "surname";
        public static final String COLUMN_NAME_GROUP = "groupId";  
    }
}
