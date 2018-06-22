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
package com.example.android.quakereport;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Earthquake>> {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    private static final String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query";
    private static final int USGS_REQUEST_LOADER = 1;

    /** TextView that is displayed when the list is empty */
    private TextView mTextViewEmptyList;

    private ProgressBar mProgressBar;

    private EarthquakeArrayAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        mTextViewEmptyList = (TextView) findViewById(R.id.emptyList);
        earthquakeListView.setEmptyView(mTextViewEmptyList);

        mProgressBar = (ProgressBar) findViewById(R.id.loading_indicator);

        // Create a new {@link ArrayAdapter} of earthquakes
        mAdapter = new EarthquakeArrayAdapter(this, new ArrayList<Earthquake>());
        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(mAdapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Earthquake earthquake = mAdapter.getItem(position);
                Uri earthquakeUri = Uri.parse(earthquake.getUrl());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
                startActivity(websiteIntent);
            }
        });

        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
//        EarthquakeAsyncTask task = new EarthquakeAsyncTask();
//        task.execute(USGS_REQUEST_URL);
            getLoaderManager().initLoader(USGS_REQUEST_LOADER, null, this);
        } else {
            mTextViewEmptyList.setText(R.string.no_internet_connection);
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public Loader<List<Earthquake>> onCreateLoader(int id, Bundle args) {
        Log.d(LOG_TAG, "onCreateLoader()");

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String minMagnitude = preferences.getString(
                getString(R.string.settings_min_magnitude_key),
                getString(R.string.settings_min_magnitude_default));
        String orderBy = preferences.getString(
                getString(R.string.settings_order_by_key),
                getString(R.string.settings_order_by_default)
        ) ;

        Uri baseUri = Uri.parse(USGS_REQUEST_URL);
        Uri.Builder builder = baseUri.buildUpon();

        builder.appendQueryParameter("format", "geojson");
        builder.appendQueryParameter("limit", "10");
        builder.appendQueryParameter("minmag", minMagnitude);
        builder.appendQueryParameter("orderby", orderBy);

        return new EarthquakeLoader(EarthquakeActivity.this, builder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> data) {
        Log.d(LOG_TAG, "onLoadFinished()");

        // Hide loading indicator because the data has been loaded
        mProgressBar.setVisibility(View.GONE);

        // Set empty state text to display "No earthquakes found."
        mTextViewEmptyList.setText(R.string.no_earthquakes);

        // Clear the adapter of previous earthquake data
        mAdapter.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if(data != null && !data.isEmpty()) {
            mAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {
        Log.d(LOG_TAG, "onLoaderReset()");
        mAdapter.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_settings) {
            Intent settingsIintent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIintent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class EarthquakeAsyncTask extends AsyncTask<String, Void, List<Earthquake>> {

        @Override
        protected List<Earthquake> doInBackground(String... urls) {
            if(urls.length < 1 || urls[0] == null) {
                return null;
            }

            List<Earthquake> result = QuertyUtils.fetchEarthquakeData(urls[0]);
            return result;
        }

        @Override
        protected void onPostExecute(List<Earthquake> earthquakes) {
            mAdapter.clear();

            if(earthquakes != null && !earthquakes.isEmpty()) {
                mAdapter.addAll(earthquakes);
            }
        }
    }

}
