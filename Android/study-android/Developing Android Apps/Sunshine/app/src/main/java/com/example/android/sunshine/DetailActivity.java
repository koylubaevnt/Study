package com.example.android.sunshine;

import android.content.Intent;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";

    private TextView mWeatherDisplay;
    private String mForecast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mWeatherDisplay = (TextView) findViewById(R.id.tv_display_weather);

        Intent intentThatStartedThisActivity = getIntent();
        if(intentThatStartedThisActivity  != null && intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
            String wheatherData = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
            mWeatherDisplay.setText(wheatherData);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        menuItem.setIntent(createShareForecastIntent());
        return true;
    }


    /**
    * Uses the ShareCompat Intent builder to create our Forecast intent for sharing. We set the
    * type of content that we are sharing (just regular text), the text itself, and we return the
    * newly created Intent.
    *
    * @return The Intent to use to start our share.
    */
    private Intent createShareForecastIntent() {
        Intent shareIntent = ShareCompat.IntentBuilder
                .from(this)
                .setType("text/plain")
                .setText(mForecast + FORECAST_SHARE_HASHTAG)
                .getIntent();
        return shareIntent;
    }
}
