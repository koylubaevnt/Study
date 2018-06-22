package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by KoylubaevNT on 29.09.2017.
 */

public class EarthquakeLoader  extends AsyncTaskLoader<List<Earthquake>> {

    public static final String LOG_TAG = EarthquakeLoader.class.getName();

    private String mUrl;

    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading()
    {
        Log.d(LOG_TAG, "onStartLoading()");
        forceLoad();
    }

    @Override
    public List<Earthquake> loadInBackground() {
        Log.d(LOG_TAG, "loadInBackground()");
        if(mUrl == null) {
            return null;
        }

        List<Earthquake> result = QuertyUtils.fetchEarthquakeData(mUrl);
        return result;
    }
}
