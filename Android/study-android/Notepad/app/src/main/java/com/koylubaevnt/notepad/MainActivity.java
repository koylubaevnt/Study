package com.koylubaevnt.notepad;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private final static String FILENAME = "sample.txt";

    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = (EditText) findViewById(R.id.editText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_open:
                openFile(FILENAME);
                return true;
            case R.id.action_save:
                saveFile(FILENAME);
                return true;
            case R.id.action_settings:
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return true;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if(prefs.getBoolean("pref_openmode", false)) {
            openFile(FILENAME);
        }
        float fSize = Float.parseFloat(prefs.getString("pref_size", "20"));
        mEditText.setTextSize(fSize);

        String regular = prefs.getString("pref_style", "");
        int typeface = Typeface.NORMAL;
        if(regular.contains("Полужирный"))
            typeface += Typeface.BOLD;

        if(regular.contains("Курсив")) {
            typeface += Typeface.ITALIC;
        }

        mEditText.setTypeface(null, typeface);

        boolean isBlack = prefs.getBoolean("text_color_black", true);
        if(isBlack) {
            mEditText.setTextColor(Color.BLACK);
        } else {
            boolean isRed = prefs.getBoolean("text_color_red", true);
            boolean isGreen = prefs.getBoolean("text_color_green", true);
            boolean isBlue = prefs.getBoolean("text_color_blue", true);

            mEditText.setTextColor(Color.rgb((isRed ? 255 : 0), (isGreen ? 255 : 0), (isBlue ? 255 : 0)));

        }
    }

    private void openFile(String fileName) {
        try {
            InputStream inputStream = openFileInput(fileName);

            if(inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line;
                StringBuilder builder = new StringBuilder();
                while((line = bufferedReader.readLine()) != null) {
                    builder.append(line).append("\n");
                }
                inputStream.close();
                mEditText.setText(builder.toString());
            }
        } catch (Throwable e) {
            Toast.makeText(getApplicationContext(), "Exception: " + e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    private void saveFile(String fileName) {
        try {
            OutputStream outputStream = openFileOutput(fileName, 0);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            outputStreamWriter.write(mEditText.getText().toString());
            outputStreamWriter.flush();
            outputStream.close();
        } catch (Throwable e) {
            Toast.makeText(getApplicationContext(), "Exception: " + e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
