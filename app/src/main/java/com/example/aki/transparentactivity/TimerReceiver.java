package com.example.aki.transparentactivity;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by aki on 2018/01/09.
 */

public class TimerReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent data) {
        Intent intent = new Intent(context, AlertDialogActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        try {
            pendingIntent.send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }
}
