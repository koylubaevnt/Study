package com.koylubaevnt.cat.resultfromactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static String ACTION_THIRD_ACTIVITY = "com.koylubaevnt.cat.resultfromactivity.ThirdActivity";
    static final private int CHOOSE_THIEF = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonChoose:
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(intent, CHOOSE_THIEF);
                break;
            case R.id.buttonAirplane:
                startActivity(
                        new Intent(android.provider.Settings.ACTION_AIRPLANE_MODE_SETTINGS));
                break;
            default:
                startActivity(new Intent(ACTION_THIRD_ACTIVITY));
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        TextView infoTextView = (TextView) findViewById(R.id.textViewInfo);
        if(requestCode == CHOOSE_THIEF) {
            if(resultCode == RESULT_OK) {
                String thiefName = data.getStringExtra(SecondActivity.THIEF);
                infoTextView.setText(thiefName);
            } else {
                infoTextView.setText("");
            }
        }

    }
}
