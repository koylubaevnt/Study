package com.koylubaevnt.broadcastperiodic;

import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TimeBroadcastReceiver mTimeBroadcastReceiver = new TimeBroadcastReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void registerBroadcastReceiver(View view) {
        this.registerReceiver(mTimeBroadcastReceiver, new IntentFilter("android.intent.action.TIME_TICK"));
        Toast.makeText(getApplicationContext(), "Приемник включен", Toast.LENGTH_SHORT).show();
    }

    public void unregisterBroadcastReceiver(View view) {
        this.unregisterReceiver(mTimeBroadcastReceiver);

        Toast.makeText(getApplicationContext(), "Приемник выключен", Toast.LENGTH_SHORT).show();
    }
}
