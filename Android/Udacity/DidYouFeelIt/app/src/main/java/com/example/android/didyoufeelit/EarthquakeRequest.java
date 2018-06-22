package com.example.android.didyoufeelit;

import android.content.Context;
import android.os.AsyncTask;

import java.net.URL;

/**
 * Created by KoylubaevNT on 27.09.2017.
 */

public class EarthquakeRequest extends AsyncTask<String, Void, Event> {

    private MainActivity activity;

    public EarthquakeRequest(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    protected Event doInBackground(String... urls) {
        if(urls.length < 1 || urls[0] == null) {
            return null;
        }
        Event earthquake = Utils.fetchEarthquakeData(urls[0]);
        return earthquake;
    }

    @Override
    protected void onPostExecute(Event event) {
        if(event == null) {
            return;
        }
        activity.updateUi(event);
    }
}
