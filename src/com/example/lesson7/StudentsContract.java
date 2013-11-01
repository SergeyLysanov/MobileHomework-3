package com.example.lesson7;

import android.provider.BaseColumns;

public final class StudentsContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public StudentsContract() {}

    /* Inner class that defines the table contents */
    public static abstract class StudentEntry implements BaseColumns {
        public static final String TABLE_NAME = "student";
        public static final String COLUMN_NAME_STUDENT_NAME = "name";
        public static final String COLUMN_NAME_STUDENT_SURNAME = "surname";
        public static final String COLUMN_NAME_GROUP = "group";
        public static final String COLUMN_NAME_COURSE = "course";   
    }
}
