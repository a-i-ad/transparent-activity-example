package com.example.aki.transparentactivity;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by aki on 2018/01/09.
 */

public class TimerIntentService extends IntentService {

    public TimerIntentService(String name) {
        super(name);
    }

    public TimerIntentService() {
        super("TimerIntentService");
    }

    @Override
    protected void onHandleIntent(Intent data) {

        SystemClock.sleep(5000);

        Intent intent = new Intent();
        intent.setAction("MESSAGE_DIALOG");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

}
