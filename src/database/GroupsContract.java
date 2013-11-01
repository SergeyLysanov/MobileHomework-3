package database;

import android.provider.BaseColumns;

public final class GroupsContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public GroupsContract() {}

    /* Inner class that defines the table contents */
    public static abstract class GroupEntry implements BaseColumns {
        public static final String TABLE_NAME = "group";
        public static final String COLUMN_NAME_GROUP_NAME = "name";
        public static final String COLUMN_NAME_SEMESTER = "semester";
    }
}