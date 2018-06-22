package com.example.android.pets.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.pets.data.PetContract.PetEntry;

/**
 * Created by koylu on 09.10.2017.
 */

public class PetDbHelper extends SQLiteOpenHelper {

    /** Name of the database file */
    private static final String DATABASE_NAME = "shelter.db";
    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /** String that contains the SQL statement to create the pets table */
    private static String SQL_CREATE_TABLE = "CREATE TABLE " + PetEntry.TABLE_NAME + "(" +
            PetEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            PetEntry.COLUMN_NAME + " TEXT NOT NULL," +
            PetEntry.COLUMN_BREED + " TEXT," +
            PetEntry.COLUMN_GENDER + " INTEGER NOT NULL," +
            PetEntry.COLUMN_WEIGHT + " INTEGER NOT NULL DEFAULT 0);";

    /**
     * Constructs a new instance of {@link PetDbHelper}.
     *
     * @param context of the app
     */
    public PetDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION
        );
    }


    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
