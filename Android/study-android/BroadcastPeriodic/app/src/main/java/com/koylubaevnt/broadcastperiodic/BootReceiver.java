package com.koylubaevnt.broadcastperiodic;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BootReceiver extends BroadcastReceiver {

    private Context mContext;
    private final String BOOT_ACTION = "android.intent.action.BOOT_COMPLETED";
    private final String QUICKBOOT_ACTION = "android.intent.action.QUICKBOOT_POWERON";
    private final String QUICKBOOT_HTC_ACTION = "com.htc.intent.action.QUICKBOOT_POWERON";
    private final String POWER_DISCONNECTED_ACTION = "android.intent.action.ACTION_POWER_DISCONNECTED";

    @Override
    public void onReceive(Context context, Intent intent) {

        mContext = context;
        String action = intent.getAction();
        if (BOOT_ACTION.equalsIgnoreCase(action)) {
            Intent intent1 = new Intent(context, com.koylubaevnt.NotifyService.class);
            context.startService(intent);

            Intent activityIntent = new Intent(context, MyActivity.class);
            activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

            Intent serviceIntent = new Intent(context, MyService.class);
            context.startService(serviceIntent);
        } else if (QUICKBOOT_ACTION.equalsIgnoreCase(action) || QUICKBOOT_HTC_ACTION.equalsIgnoreCase(action)) {
            Toast.makeText(context, "QUICK BOOT", Toast.LENGTH_SHORT).show();;
        } else if (POWER_DISCONNECTED_ACTION.equalsIgnoreCase(action)) {
            Toast.makeText(context, "Обнаружено сообщение: " + intent.getAction(), Toast.LENGTH_SHORT).show();;
        }
    }
}
