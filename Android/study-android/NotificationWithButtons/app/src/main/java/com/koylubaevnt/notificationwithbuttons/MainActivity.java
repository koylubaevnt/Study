package com.koylubaevnt.notificationwithbuttons;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {

        /*
        Для Android 4.1-4.2
         */
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());

        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
        Notification notification =
                builder.setTicker("Пришла посылка!")
                .setContentTitle("Посылка")
                .setContentText("Это я почтальон Печкин. Принес для вас посылку")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .addAction(R.mipmap.ic_launcher, "Открыть", pendingIntent)
                .addAction(R.mipmap.ic_launcher, "Отказаться", pendingIntent)
                .addAction(R.mipmap.ic_launcher, "Другой вариант", pendingIntent)
                .build();

        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, notification);

    }

    public void onClickBigText(View view) {
        String bigText = "Это я, почтальон Печкин. Принес для вас посылку. "
                + "Только я вам ее не отдам. Потому что у вас документов нету. ";
        Intent intent = new Intent(this, SecondActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext())
                .setTicker("Пришла посылка!")
                .setContentIntent(pendingIntent)
                .setContentTitle("Уведомление с большим текстом")
                .setContentText(
                        "Это я, почтальон Печкин. Принес для вас посылку")
                .setSmallIcon(R.mipmap.ic_launcher)
                .addAction(R.mipmap.ic_launcher, "Запустить активность",
                        pendingIntent).setAutoCancel(true);

        Notification notification = new NotificationCompat.BigTextStyle(builder)
                .bigText(bigText).build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
        notificationManager.notify(1, notification);
    }

    public void onClickBigImage(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext())
                .setContentTitle("Большая посылка")
                .setContentIntent(pendingIntent)
                .setTicker("Пришла посылка!")
                .setContentText("Уведомление с большой картинкой")
                .setSmallIcon(R.mipmap.ic_launcher)
                .addAction(R.mipmap.ic_launcher, "Запустить активность",
                        pendingIntent);

        // Подготовим большую картинку
        Notification notification = new NotificationCompat.BigPictureStyle(builder)
                .bigPicture(
                        BitmapFactory.decodeResource(getResources(),
                                R.drawable.cat)).build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
        notificationManager.notify(2, notification);
    }

    public void onClickInbox(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setTicker("Пришла посылка!")
                .setContentIntent(pendingIntent)
                .setContentTitle("Уведомление в стиле Inbox")
                .setContentText("Inbox Style notification!!")
                .setSmallIcon(R.mipmap.ic_launcher)
                .addAction(R.mipmap.ic_launcher, "Запустить активность", pendingIntent);

        Notification notification = new NotificationCompat.InboxStyle(builder)
                .addLine("Первое сообщение").addLine("Второе сообщение")
                .addLine("Третье сообщение").addLine("Четвертое сообщение")
                .setSummaryText("+2 more").build();

        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        //NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
        notificationManager.notify(3, notification);
    }
}
