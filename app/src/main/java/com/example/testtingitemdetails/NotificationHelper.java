package com.example.testtingitemdetails;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationHelper
{
   private static final int NOTIFICATION_ID = 1;
   private static final String CHANNEL_ID = "TestChannel";

   public void showNotification(Context context, String title, String text)
   {
       Intent intent = new Intent(context, MainActivity.class);
       PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

       NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
               .setSmallIcon(R.drawable.ic_spade)
               .setContentTitle(title)
               .setContentText(text)
               .setAutoCancel(true)
               .setContentIntent(pendingIntent);

       NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);


       if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
       {
           NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "TestName", NotificationManager.IMPORTANCE_DEFAULT);
           notificationManager.createNotificationChannel(channel);
       }

       notificationManager.notify(NOTIFICATION_ID, builder.build());
   }

}
