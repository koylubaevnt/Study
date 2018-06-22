/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.pets;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.android.pets.data.PetContract.PetEntry;

/**
 * Displays list of pets that were entered and stored in the app.
 */
public class CatalogActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int PET_LOADER = 0;

    private PetCursorAdapter mCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });


        // Find the ListView which will be populated with the pet data
        ListView listView = (ListView) findViewById(R.id.list);

        // Find and set empty view on the ListView, so that it only shows when the list has 0 items.
        View emptyView = findViewById(R.id.empty_view);
        listView.setEmptyView(emptyView);

        // Setup an Adapter to create a list item for each row of pet data in the Cursor
        // There is o pet data yet (until the loader finishes) so pass in null fo the Cursor.
        mCursorAdapter = new PetCursorAdapter(this, null);
        listView.setAdapter(mCursorAdapter);

        // Setup otem click listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Create new intent to go to {@link EditorActivity}
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);

                // Form the content URI that represents the specific pet that  was clicked on,
                // by appending the "id" {passed as input to this method} onto the
                // {@link PetEntry#CONTENT_URI}.
                // For exmple, the URI would be "content:/com.example.android.pets/pets/2"
                // if the pet with ID 2 was cicked on.
                Uri currentPetUri = ContentUris.withAppendedId(PetEntry.CONTENT_URI, id);

                // Set the URI on the data field of the intent
                intent.setData(currentPetUri);

                //Launch the {@link EditorActivity} to display te data for the current pet.
                startActivity(intent);
            }
        });

        //kik off the loader
        getLoaderManager().initLoader(PET_LOADER, null, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertPet();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                deleteAllPets();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteAllPets() {
        int count = getContentResolver().delete(PetEntry.CONTENT_URI, null, null);
        Log.v("CatalogActivity", "Deleted " + count + " rows.");
    }

    private void insertPet() {


        //// Get the data repository in write mode
        //SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and Toto's pet attributes are the values.
        ContentValues contentValues = new ContentValues();
        contentValues.put(PetEntry.COLUMN_NAME, "Toto");
        contentValues.put(PetEntry.COLUMN_BREED, "Terrier");
        contentValues.put(PetEntry.COLUMN_GENDER, PetEntry.GENDER_MALE);
        contentValues.put(PetEntry.COLUMN_WEIGHT, 7);

        // Insert a new row for Toto into the provider using the ContentResolver.
        // Use the {@link PetEntry#CONTENT_URI} to indicate that we want to insert
        // into the pets database table.
        // Receive the new content URI that will allow us to access Toto's data in the future.
        Uri newUri = getContentResolver().insert(PetEntry.CONTENT_URI, contentValues);

//        long newRowId = db.insert(PetEntry.TABLE_NAME, null, contentValues);
        Log.v("CatalogActivity", "New row ID " + String.valueOf(ContentUris.parseId(newUri)));
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // Define a projection thet specified which columns from the database
        // you will actualy use after this query/
        String[] projection = {
                PetEntry.COLUMN_ID,
                PetEntry.COLUMN_NAME,
                PetEntry.COLUMN_BREED,
                PetEntry.COLUMN_GENDER,
                PetEntry.COLUMN_WEIGHT
        };
        // This loader will execute the ContentProvider's query method on a background thread
        return new CursorLoader(
                this,                       // Parent activity context
                PetEntry.CONTENT_URI,       // Provider content URI to query
                projection,                 // Columns to include in the resulting Cursor
                null,                       // No selection clause
                null,                       // No selection arguments
                null);                      // Default sort order
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        //Update {@link PetCursorAdapter} with this new cursor containing updated et data
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        //Callback called when the data needs to be deleted
        mCursorAdapter.swapCursor(null);
    }
}
