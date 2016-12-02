package com.example.hp.todo;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

/**
 * Created by hp on 30-10-2016.
 */
public class GoalsActivity extends AppCompatActivity {

    ListView mGoalsListView;
    private GoalDbHelper helper;
    private ArrayAdapter<String> adapter;
    TextView Goals;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.list_view_dream);

      //  TextView txt5 = (TextView) findViewById(R.id.task_title);
      //  Typeface font5 = Typeface.createFromAsset(getAssets(), "Curve_OT.otf");
      //  txt5.setTypeface(font5);

        helper = new GoalDbHelper(this);
        mGoalsListView = (ListView) findViewById(R.id.list_goal);

        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        assert fab1 != null;

        TextView txt = (TextView) findViewById(R.id.goal_title);
        Typeface font = Typeface.createFromAsset(getAssets(), "Capture_it.ttf");
        txt.setTypeface(font);

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(GoalContract.GoalEntry.TABLE,
                new String[]{GoalContract.GoalEntry._ID, GoalContract.GoalEntry.COL_GOAL_TITLE},
                null, null, null, null, null);
        while (cursor.moveToNext()) {
            int idx = cursor.getColumnIndex(GoalContract.GoalEntry.COL_GOAL_TITLE);
            //  Log.d(TAG, "Task: " + cursor.getString(idx));
        }
        cursor.close();
       updateUI();
        db.close();

        // ATTENTION: This was auto-generated to implement the App Indexing API.

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText goalEditText = new EditText(GoalsActivity.this);
                AlertDialog dialog = new AlertDialog.Builder(GoalsActivity.this)
                        .setMessage("What's your next big dream?")
                        .setView(goalEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task = String.valueOf(goalEditText.getText());
                                SQLiteDatabase db = helper.getWritableDatabase();
                                ContentValues values = new ContentValues();
                                values.put(GoalContract.GoalEntry.COL_GOAL_TITLE, task);
                                db.insertWithOnConflict(GoalContract.GoalEntry.TABLE, null, values,
                                        SQLiteDatabase.CONFLICT_REPLACE);
                                updateUI();
                                db.close();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                updateUI();
                dialog.show();

            }
        });

    }

    private void updateUI() {
        ArrayList<String> goalList = new ArrayList<>();
        SQLiteDatabase db =helper.getReadableDatabase();
        Cursor cursor = db.query(GoalContract.GoalEntry.TABLE,
                new String[]{GoalContract.GoalEntry._ID, GoalContract.GoalEntry.COL_GOAL_TITLE},
                null, null, null, null, null);
        while (cursor.moveToNext()) {
            int idx = cursor.getColumnIndex(GoalContract.GoalEntry.COL_GOAL_TITLE);
            goalList.add(cursor.getString(idx));
        }

        if (adapter == null) {
            adapter = new ArrayAdapter<>(this,
                    R.layout.item_goal,
                    R.id.goal_title,
                    goalList);
            mGoalsListView.setAdapter(adapter);
        } else {
            adapter.clear();
            adapter.addAll(goalList);
            adapter.notifyDataSetChanged();
        }

        cursor.close();
        db.close();
    }

    public void deleteGoal(View view) {
        View parent = (View) view.getParent();
        TextView goalTextView = (TextView) parent.findViewById(R.id.goal_title);
        String goal = String.valueOf(goalTextView.getText());
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(GoalContract.GoalEntry.TABLE,
                GoalContract.GoalEntry.COL_GOAL_TITLE + " = ?",
                new String[]{goal});
        db.close();
        Toast.makeText(GoalsActivity.this,"Congradulations!", Toast.LENGTH_SHORT).show();
        updateUI();
    }



}

