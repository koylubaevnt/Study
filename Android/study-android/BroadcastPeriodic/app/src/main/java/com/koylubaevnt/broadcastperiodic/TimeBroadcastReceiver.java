package com.koylubaevnt.broadcastperiodic;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        StringBuilder builder = new StringBuilder("Текущее время: ");
        Format format = new SimpleDateFormat("hh:mm:ss a");
        builder.append(format.format(new Date()));
        Toast.makeText(context, builder, Toast.LENGTH_SHORT).show();
    }
}
