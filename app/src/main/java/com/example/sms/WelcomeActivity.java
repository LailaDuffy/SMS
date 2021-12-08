package com.example.sms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sms.db.SqliteHelper;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity {

    private ListView shipList;

    // Data management variable
    List<String> shipsFromDB = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

       SqliteHelper dbHelper = new SqliteHelper(this);

       // TODO: move code to own method

        shipsFromDB.add("Loud and Proud");
        shipsFromDB.add("Escape Plan");
        shipsFromDB.add("Flat Earth");
        shipsFromDB.add("Star Trek");

        String title = "Loud and Proud";
        String mass = "10t";
        String color = "Blue";
        String location = "Birmingham, UK";
        String image = "";

        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(SqliteHelper.SpaceshipEntry.COLUMN_NAME_TITLE, title);
        values.put(SqliteHelper.SpaceshipEntry.COLUMN_NAME_MASS, mass);
        values.put(SqliteHelper.SpaceshipEntry.COLUMN_NAME_COLOR, color);
        values.put(SqliteHelper.SpaceshipEntry.COLUMN_NAME_LOCATION, location);
        values.put(SqliteHelper.SpaceshipEntry.COLUMN_NAME_IMAGE_URL, image);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(SqliteHelper.SpaceshipEntry.TABLE_NAME, null, values);

        SQLiteDatabase readDb = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                SqliteHelper.SpaceshipEntry.COLUMN_NAME_TITLE
        };

        Cursor cursor = db.query(
                SqliteHelper.SpaceshipEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );


        while(cursor.moveToNext()) {
            String myTitle = cursor.getString(cursor.getColumnIndexOrThrow(SqliteHelper.SpaceshipEntry.COLUMN_NAME_TITLE));
            shipsFromDB.add(myTitle);
        }
        cursor.close();



        // Reference to the layout
        shipList = (ListView) findViewById(R.id.shipList);

        // Create an adapter for Java code to "talk to" Android code
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_activated_1,
                shipsFromDB
        );

        // Assign the adapter
        shipList.setAdapter(arrayAdapter);
    }

    public void createSpaceship(View view) {
        Intent createNewSpaceship = new Intent(this, WelcomeActivity.class);
        startActivity(createNewSpaceship);
    }
}