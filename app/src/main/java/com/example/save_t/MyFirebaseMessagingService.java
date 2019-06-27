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
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
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


        Bundle args = new Bundle();
        args.putString("title", remoteMessage.getData().get("title"));
        args.putString("content", remoteMessage.getData().get("content"));
        args.putString("fireSize", remoteMessage.getData().get("fireSize"));
        args.putString("latitude", remoteMessage.getData().get("latitude"));
        args.putString("longitude", remoteMessage.getData().get("longitude"));
        args.putString("inhabitants", remoteMessage.getData().get("inhabitants"));
        args.putString("incidentLocation", remoteMessage.getData().get("incidentLocation"));
        args.putString("street", remoteMessage.getData().get("street"));
        args.putString("houseNumber", remoteMessage.getData().get("houseNumber"));
        args.putString("postalCode", remoteMessage.getData().get("postalCode"));
        args.putString("city", remoteMessage.getData().get("city"));
        args.putString("type", remoteMessage.getData().get("type"));

        sendNotification();

        Intent dialogIntent = new Intent(this, MainActivity.class);
        dialogIntent.putExtras(args);
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

        Intent activityIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, activityIntent,0 );
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        Intent broadCastIntent = new Intent(this, NotificationReceiver.class);
        broadCastIntent.putExtra("toastMessage", Config.title);
        PendingIntent actionIntent = PendingIntent.getBroadcast(this, 1, broadCastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this, "101")
                .setSmallIcon(R.drawable.logo)
                .setContentTitle(Config.title)
                .setContentText(Config.content)
                .setContentIntent(contentIntent)
                .addAction(R.mipmap.ic_launcher, "BRANDWEER INLICHTEN", actionIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(1, notification);

    }

}
