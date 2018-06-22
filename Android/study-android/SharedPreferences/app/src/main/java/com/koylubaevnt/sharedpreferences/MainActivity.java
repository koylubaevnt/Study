package com.koylubaevnt.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String APP_PREFERENCES = "mysettings";

    public static final String APP_PREFERENCES_COUNTER = "counter";

    private TextView mInfoTextView;

    private SharedPreferences mSettings;
    private int mCounter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        mInfoTextView = (TextView) findViewById(R.id.textView);

    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor editor = mSettings.edit();
        editor.putInt(APP_PREFERENCES_COUNTER, mCounter);
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(mSettings.contains(APP_PREFERENCES_COUNTER)) {
            mCounter = mSettings.getInt(APP_PREFERENCES_COUNTER, 0);
            mInfoTextView.setText("Я насчитал " + mCounter + " ворон");
        }
    }

    public void onClick(View view) {
        mCounter++;
        mInfoTextView.setText("Я насчитал " + mCounter + " ворон");
    }
}
