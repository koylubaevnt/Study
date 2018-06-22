package com.koylubaevnt.preferencesframework;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private SharedPreferences mPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        mPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    public void showSettings() {
        Intent intent = new Intent(getApplicationContext(), MyPreferenceActivity.class);
        startActivity(intent);
    }

    public void onClick(View view) {
        showSettings();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

        Toast.makeText(getApplicationContext(), "Настройка \"" + s + "\" изменилась", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onStop() {
        super.onStop();
        mPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }
}
