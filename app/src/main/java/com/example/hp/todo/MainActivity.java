package com.example.hp.todo;

import android.app.*;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.multidex.MultiDex;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private TaskDbHelper mHelper;
    private GoalDbHelper mHelperG;
    private ArrayAdapter<String> mAdapter;
    TextView tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       Button txt3 = (Button) findViewById(R.id.btn);
      Typeface font3 = Typeface.createFromAsset(getAssets(), "Capture_it.ttf");
        txt3.setTypeface(font3);

     Button txt2 = (Button) findViewById(R.id.angry_btn);
       Typeface font2 = Typeface.createFromAsset(getAssets(), "Capture_it.ttf");
      txt2.setTypeface(font2);

        TextView txt = (TextView) findViewById(R.id.custom_font);
        Typeface font = Typeface.createFromAsset(getAssets(), "Curve_OT.otf");
        txt.setTypeface(font);

        TextView txt1 = (TextView) findViewById(R.id.task_title);
        Typeface font1 = Typeface.createFromAsset(getAssets(), "Curve_OT.otf");
        txt1.setTypeface(font1);

        //TASKS
        mHelper = new TaskDbHelper(this);
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(TaskContract.TaskEntry.TABLE,
                new String[]{TaskContract.TaskEntry._ID, TaskContract.TaskEntry.COL_TASK_TITLE},
                null, null, null, null, null);
        while(cursor.moveToNext()) {
            int idx = cursor.getColumnIndex(TaskContract.TaskEntry.COL_TASK_TITLE);
          //  Log.d(TAG, "Task: " + cursor.getString(idx));
        }
        cursor.close();

        db.close();

//GOALS

        mHelperG = new GoalDbHelper(this);
        SQLiteDatabase dbG = mHelperG.getReadableDatabase();
        Cursor cursorG = dbG.query(GoalContract.GoalEntry.TABLE,
                new String[]{GoalContract.GoalEntry._ID, GoalContract.GoalEntry.COL_GOAL_TITLE},
                null, null, null, null, null);
        while(cursorG.moveToNext()) {
            int idG = cursorG.getColumnIndex(GoalContract.GoalEntry.COL_GOAL_TITLE);
            //  Log.d(TAG, "Task: " + cursor.getString(idx));
        cursorG.close();

        dbG.close();

        // ATTENTION: This was auto-generated to implement the App Indexing API.

    }}

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

     public void Daily_task(View view){
         Intent i=  new Intent(this,ListActivity.class);
         startActivity(i);
     }

    public void Goals(View view){
        Intent i=  new Intent(this,GoalsActivity.class);
        startActivity(i);
    }



}
