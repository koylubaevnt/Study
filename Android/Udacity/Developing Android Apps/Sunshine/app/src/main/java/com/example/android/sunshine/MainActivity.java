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
package com.example.android.sunshine;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.sunshine.data.SunshinePreferences;
import com.example.android.sunshine.utilities.NetworkUtils;
import com.example.android.sunshine.utilities.OpenWeatherJsonUtils;

import java.net.URL;

public class MainActivity extends AppCompatActivity implements ForecastAdapter.ForecastAdapterOnClickHandler, LoaderManager.LoaderCallbacks<String[]> {

    private static final String TAG = MainActivity.class.getName();

    private static final int FORECAST_LOADER_ID = 0;

    //private TextView mWeatherTextView;
    private RecyclerView mRecyclerView;
    private ForecastAdapter mForecastAdapter;
    private TextView mErrorMessageDisplay;
    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        /*
         * Using findViewById, we get a reference to our TextView from xml. This allows us to
         * do things like set the text of the TextView.
         */
        //mWeatherTextView = (TextView) findViewById(R.id.tv_weather_data);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_forecast);
        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        /*
        * LinearLayoutManager can support HORIZONTAL or VERTICAL orientations. The reverse layout
        * parameter is useful mostly for HORIZONTAL layouts that should reverse for right to left
        * languages.
        */
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);

        /*
        * Use this setting to improve performance if you know that changes in content do not
        * change the child layout size in the RecyclerView
        */
        mRecyclerView.setHasFixedSize(true);

        /*
        * The ForecastAdapter is responsible for linking our weather data with the Views that
        * will end up displaying our weather data.
        */
        mForecastAdapter = new ForecastAdapter(this);

        /* Setting the adapter attaches it to the RecyclerView in our layout. */
        mRecyclerView.setAdapter(mForecastAdapter);

        /*
        * This ID will uniquely identify the Loader. We can use it, for example, to get a handle
        * on our Loader at a later point in time through the support LoaderManager.
        */
        int loaderId = FORECAST_LOADER_ID;

        /*
        * From MainActivity, we have implemented the LoaderCallbacks interface with the type of
        * String array. (implements LoaderCallbacks<String[]>) The variable callback is passed
        * to the call to initLoader below. This means that whenever the loaderManager has
        * something to notify us of, it will do so through this callback.
        */
        LoaderManager.LoaderCallbacks<String[]> callback = MainActivity.this;
        /*
        * The second parameter of the initLoader method below is a Bundle. Optionally, you can
        * pass a Bundle to initLoader that you can then access from within the onCreateLoader
        * callback. In our case, we don't actually use the Bundle, but it's here in case we wanted
        * to.
        */
        Bundle bundleForLoader = null;

        /*
        * Ensures a loader is initialized and active. If the loader doesn't already exist, one is
        * created and (if the activity/fragment is currently started) starts the loader. Otherwise
        * the last created loader is re-used.
        */
        getSupportLoaderManager().initLoader(FORECAST_LOADER_ID, bundleForLoader, callback);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.forecast, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.action_refresh) {
            invalidateData();
            getSupportLoaderManager().restartLoader(FORECAST_LOADER_ID, null, this);
            return true;
        } else if(itemId == R.id.action_map) {
            openLocationInMap();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * This method will make the View for the weather data visible and
     * hide the error message.
     * <p>
     * Since it is okay to redundantly set the visibility of a View, we don't
     * need to check whether each view is currently visible or invisible.
     */
    private void showWeatherDataView() {
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    /**
    * This method is used when we are resetting data, so that at one point in time during a
    * refresh of our data, you can see that there is no data showing.
    */
    private void invalidateData() {
        mForecastAdapter.setWeatherData(null);
    }

    /**
     * This method will make the error message visible and hide the weather
     * View.
     * <p>
     * Since it is okay to redundantly set the visibility of a View, we don't
     * need to check whether each view is currently visible or invisible.
     */
    private void showErrorMessage() {
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);
    }

    /**
     * This method is overridden by our MainActivity class in order to handle RecyclerView item
     * clicks.
     *
     * @param weatherForDay The weather for the day that was clicked
     */
    @Override
    public void onClick(String weatherForDay) {
        Context context = this;
        Class destinationClass = DetailActivity.class;
        Intent intentToStartDetailActivity = new Intent(context, destinationClass);
        intentToStartDetailActivity.putExtra(Intent.EXTRA_TEXT, weatherForDay);
        startActivity(intentToStartDetailActivity);
        //Toast.makeText(context, weatherForDay, Toast.LENGTH_LONG).show();
    }

    /**
     * Instantiate and return a new Loader for the given ID.
     *
     * @param id The ID whose loader is to be created.
     * @param args Any arguments supplied by the caller.
     *
     * @return Return a new Loader instance that is ready to start loading.
     */
    @Override
    public Loader<String[]> onCreateLoader(int id, final Bundle args) {
        return new AsyncTaskLoader<String[]>(this) {

            /* This String array will hold and help cache our weather data */
            private String[] mCahceData = null;

            /**
             * Subclasses of AsyncTaskLoader must implement this to take care of loading their data.
             */
            @Override
            protected void onStartLoading() {

                if(mCahceData != null) {
                    deliverResult(mCahceData);
                } else {
                    mLoadingIndicator.setVisibility(View.VISIBLE);
                    forceLoad();
                }
            }

            /**
             * This is the method of the AsyncTaskLoader that will load and parse the JSON data
             * from OpenWeatherMap in the background.
             *
             * @return Weather data from OpenWeatherMap as an array of Strings.
             *         null if an error occurs
             */
            @Override
            public String[] loadInBackground() {

                String loation = SunshinePreferences
                        .getPreferredWeatherLocation(MainActivity.this);

                URL weatherRequestUrl = NetworkUtils.buildUrl(loation);
                try {
                    String jsonWeatherResponse = NetworkUtils.getResponseFromHttpUrl(weatherRequestUrl);

                    String[] simpleJsonWeatherData = simpleJsonWeatherData = OpenWeatherJsonUtils
                            .getSimpleWeatherStringsFromJson(MainActivity.this, jsonWeatherResponse);

                    return simpleJsonWeatherData;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            /**
             * Sends the result of the load to the registered listener.
             *
             * @param data The result of the load
             */
            @Override
            public void deliverResult(String[] data) {
                mCahceData = data;
                super.deliverResult(data);
            }
        };
    }

    /**
     * Called when a previously created loader has finished its load.
     *
     * @param loader The Loader that has finished.
     * @param data The data generated by the Loader.
     */
    @Override
    public void onLoadFinished(Loader<String[]> loader, String[] data) {
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        mForecastAdapter.setWeatherData(data);
        if(data != null ) {
            showWeatherDataView();
        } else {
            showErrorMessage();
        }
    }

    /**
     * Called when a previously created loader is being reset, and thus
     * making its data unavailable.  The application should at this point
     * remove any references it has to the Loader's data.
     *
     * @param loader The Loader that is being reset.
     */
    @Override
    public void onLoaderReset(Loader<String[]> loader) {
        /*
        * We aren't using this method in our example application, but we are required to Override
        * it to implement the LoaderCallbacks<String> interface
        */
    }

    public class FetchWeatherTask extends AsyncTask<String, Void, String[]> {

        @Override
        protected void onPreExecute() {
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected String[] doInBackground(String... params) {
            if(params.length == 0) {
                return null;
            }
            String loation = params[0];
            URL weatherRequestUrl = NetworkUtils.buildUrl(loation);
            String[] simpleJsonWeatherData = null;
            try {
                String jsonWeatherResponse = NetworkUtils.getResponseFromHttpUrl(weatherRequestUrl);

                simpleJsonWeatherData = OpenWeatherJsonUtils
                        .getSimpleWeatherStringsFromJson(MainActivity.this, jsonWeatherResponse);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return simpleJsonWeatherData;
        }

        @Override
        protected void onPostExecute(String[] weatherData) {

        }
    }

    /**
     * This method uses the URI scheme for showing a location found on a
     * map. This super-handy intent is detailed in the "Common Intents"
     * page of Android's developer site:
     *
     * @see <a"http://developer.android.com/guide/components/intents-common.html#Maps">
     *
     * Hint: Hold Command on Mac or Control on Windows and click that link
     * to automagically open the Common Intents page
     */
    private void openLocationInMap() {
        String addressString = "1600 Ampitheatre Parkway, CA";
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("geo")
                .path("0,0")
                .query(addressString);
        Uri geoLocation = builder.build();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d(TAG, "Couldn't call " + geoLocation.toString()
                    + ", no receiving apps installed!");
        }
    }
}