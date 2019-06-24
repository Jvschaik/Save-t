package com.example.save_t;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {


    private static final String TAG = "FCM Service";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO: Handle FCM messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated.
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());


        Map<String, String> data = remoteMessage.getData();
        Config.title = data.get("title");
        Config.content = data.get("content");
        Config.fireSize = data.get("fireSize");
        Config.latitude = data.get("latitude");
        Config.longitude = data.get("longitude");
        Config.inhabitants = data.get("inhabitants");

        sendNotification();

        Intent dialogIntent = new Intent(this, MainActivity.class);
        dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(dialogIntent);

    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.e("NEW_TOKEN",s);
    }

    public static String getToken(Context context) {
        return context.getSharedPreferences("_", MODE_PRIVATE).getString("fb", "empty");
    }

    private void sendNotification(){

        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.logo);

        NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle();
        style.bigPicture(b);

        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent intent = new Intent(getApplicationContext(), NotificationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent,0);

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "101";

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            @SuppressLint("WrongConstant") NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "Notification", NotificationManager.IMPORTANCE_MAX);

            //Configure Notification Channel
            notificationChannel.setDescription("Game Notifications");
            notificationChannel.enableLights(true);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);

            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(Config.title)
                .setAutoCancel(true)
                .setSound(defaultSound)
                .setContentText(Config.content)
                .setContentIntent(pendingIntent)
                .setStyle(style)
                .setLargeIcon(b)
                .setWhen(System.currentTimeMillis())
                .setPriority(Notification.PRIORITY_MAX);

        notificationManager.notify(1, notificationBuilder.build());

    }

}
