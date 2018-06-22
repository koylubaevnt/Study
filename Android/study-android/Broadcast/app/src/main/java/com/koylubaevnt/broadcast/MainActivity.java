package com.koylubaevnt.broadcast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String WHERE_MY_CAT_ACTION = "com.koylubaevnt.action.CAT";
    private static final String ALARM_MESSAGE = "Срочно пришлите кота!";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setAction(WHERE_MY_CAT_ACTION);
        intent.putExtra("com.koylubaevnt.broadcast.Message", ALARM_MESSAGE);
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(intent);
    }
}
