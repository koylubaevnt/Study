package com.koylubaevnt.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final int NOTIFY_ID = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClick(View view) {
        Context context = getApplicationContext();
        Resources res = context.getResources();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        if(view.getId() == R.id.button) {

            Intent notificationIntent = new Intent(context, MainActivity.class);
            //тобы увидеть, уже запущенную активность
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

            builder.setContentIntent(pendingIntent)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.hungrycat))
                    .setTicker(res.getString(R.string.warning))
                    .setContentTitle(res.getString(R.string.notifytitle))
                    .setContentText(res.getString(R.string.notifytext));
        } else if(view.getId() == R.id.button2) {
            Intent notificationIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://developer.alexanderklimov.ru/android/"));
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

            Uri ringUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            long[] vibrate = new long[] { 1000, 1000, 1000, 1000, 1000 };

            builder.setContentTitle("Посетите мой сайт")
                    .setContentText("http://developer.alexanderklimov.ru/android/")
                    .setTicker("Внимание!").setWhen(System.currentTimeMillis())
                    .setContentIntent(pendingIntent)
                    .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE).setAutoCancel(true)//DEFAULT_ALL
                    .setSound(ringUri)
                    .setVibrate(vibrate)
                    //.setSmallIcon(R.mipmap.ic_launcher)
            //.setSmallIcon(R.animator.animations)
            .setSmallIcon(android.R.drawable.stat_sys_upload);
        }
        //Notification notification = builder.getNotification();
        Notification notification = builder.build();

        //NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFY_ID, notification);

    }
}
