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
package com.example.android.datafrominternet;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.datafrominternet.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    /* A constant to save and restore the URL that is being displayed */
    private static final String SEARCH_QUERY_URL_EXTRA = "query";

    private static final int GITHUB_SEARCH_LOADER = 0;

    private EditText mSearchBoxEditText;
    private TextView mUrlDisplayTextView;
    private TextView mSearchResultsTextView;
    private TextView mErrorMessageDisplay;
    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchBoxEditText = (EditText) findViewById(R.id.et_search_box);
        mUrlDisplayTextView = (TextView) findViewById(R.id.tv_url_display);
        mSearchResultsTextView = (TextView) findViewById(R.id.tv_github_search_results_json);
        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        if(savedInstanceState != null) {
            String queryUrl = savedInstanceState.getString(SEARCH_QUERY_URL_EXTRA);
            mUrlDisplayTextView.setText(queryUrl);
        }

        LoaderManager loaderManager = getSupportLoaderManager();
        loaderManager.initLoader(GITHUB_SEARCH_LOADER, null, this);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        String queryUrl = mUrlDisplayTextView.getText().toString();
        outState.putString(SEARCH_QUERY_URL_EXTRA, queryUrl);
    }

    private void showJsonDataView() {
        mSearchResultsTextView.setVisibility(View.VISIBLE);
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
    }

    private void showErrorMessage() {
        mSearchResultsTextView.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

    private void makeGithubSearchQuery() {
        String githubQuery = mSearchBoxEditText.getText().toString();

        if(githubQuery.isEmpty()) {
            mUrlDisplayTextView.setText("No query entered, nothing to search for.");
            return;
        }
        URL githubSearchUrl = NetworkUtils.buildUrl(githubQuery);
        mUrlDisplayTextView.setText(githubSearchUrl.toString());

        Bundle queryBundle = new Bundle();
        queryBundle.putString(SEARCH_QUERY_URL_EXTRA, githubSearchUrl.toString());

        LoaderManager loaderManager = getSupportLoaderManager();
        Loader loader = loaderManager.getLoader(GITHUB_SEARCH_LOADER);
        if(loader == null) {
            loaderManager.initLoader(GITHUB_SEARCH_LOADER, queryBundle, this);
        } else {
            loaderManager.restartLoader(GITHUB_SEARCH_LOADER, queryBundle, this);
        }
    }

    @Override
    public Loader<String> onCreateLoader(int id, final Bundle args) {
        return new AsyncTaskLoader<String>(this) {

            private String mGithubJson;

            @Override
            protected void onStartLoading() {
                if(args == null) {
                    return;
                }
                mLoadingIndicator.setVisibility(View.VISIBLE);

                if(mGithubJson != null) {
                    deliverResult(mGithubJson);
                }
                forceLoad();
            }

            @Override
            public String loadInBackground() {
                String queryUrl = args.getString(SEARCH_QUERY_URL_EXTRA);
                if(queryUrl == null || queryUrl.isEmpty()) {
                    return null;
                }
                try {
                    URL searchUrl = new URL(queryUrl);
                    String githubSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
                    return githubSearchResults;
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            public void deliverResult(String data) {
                mGithubJson = data;
                super.deliverResult(data);
            }
        };

    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        if (data != null && !data.equals("")) {
            showJsonDataView();
            mSearchResultsTextView.setText(data);
        } else {
            showErrorMessage();
        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                //Context context = MainActivity.this;
                //String message = "Search clicked!";
                //Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                makeGithubSearchQuery();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
