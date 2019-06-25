package com.example.save_t;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra("toastMessage");
        Toast.makeText(context, message, Toast.LENGTH_SHORT);
        Intent activityIntent = new Intent(context, MainActivity.class);
        activityIntent.putExtra("fragment", "dangerFragment");
        activityIntent.putExtra("room", "woonkamer");
        activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(activityIntent);
    }
}
