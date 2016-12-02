package com.example.hp.todo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hp on 09-11-2016.
 */
public class GoalDbHelper extends SQLiteOpenHelper {

    public GoalDbHelper(Context context) {
        super(context, GoalContract.DB_NAME, null, GoalContract.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + GoalContract.GoalEntry.TABLE + " ( " +
                GoalContract.GoalEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                GoalContract.GoalEntry.COL_GOAL_TITLE + " TEXT NOT NULL);";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + GoalContract.GoalEntry.TABLE);
        onCreate(db);
    } }

