package com.koylubaevnt.manual;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        WebView webView = (WebView) findViewById(R.id.webView);

        Intent intent = getIntent();

        String resName = "n" + intent.getIntExtra("head", 0);
        Log.i("name", resName);

        Context context = getBaseContext();

        String text = readRawTextFile(context, getResources().getIdentifier(resName, "raw", "com.koylubaevnt.manual"));

        webView.loadDataWithBaseURL(null, text, "text/tml", "UTF-8", null);
    }

    public static String readRawTextFile(Context context, int resId) {

        InputStream inputStream = context.getResources().openRawResource(resId);

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line;
        StringBuilder builder = new StringBuilder();

        try {
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line)
                        .append("\n");
            }
        } catch (IOException e) {
            return null;
        }
        return builder.toString();
    }
}
