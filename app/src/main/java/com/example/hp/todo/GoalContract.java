package com.example.hp.todo;

import android.provider.BaseColumns;

/**
 * Created by hp on 09-11-2016.
 */
public class GoalContract {
    public static final String DB_NAME = "com.example.hp.todo.dbG";
    public static final int DB_VERSION = 1;

    public class GoalEntry implements BaseColumns {
        public static final String TABLE = "goals";

        public static final String COL_GOAL_TITLE = "title1";}
}
