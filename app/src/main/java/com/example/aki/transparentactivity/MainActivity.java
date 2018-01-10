package com.example.aki.transparentactivity;

import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private final MainActivity self = this;

    private UnLockReceiver receiver = new UnLockReceiver();

    private DialogMessageReceiver dialogMessageReceiver = new DialogMessageReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("MainActivity", "onClick");
                startService(new Intent(self, TimerIntentService.class));
            }
        });

        if(!AppController.getInstance().isUnLockReceiverRegistered()) {
            IntentFilter intentFilter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
            intentFilter.addAction(Intent.ACTION_USER_PRESENT);
            registerReceiver(receiver, intentFilter);
            AppController.getInstance().setUnLockReceiverRegistered(true); // レシーバーを登録したのでフラグをON
        }

        final IntentFilter filter = new IntentFilter();
        filter.addAction("MESSAGE_DIALOG");
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(dialogMessageReceiver, filter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppController.getInstance().setInBackground(false);
    }

    @Override
    protected void onPause() {
        super.onPause();
        AppController.getInstance().setInBackground(true);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected  void onDestroy() {
        super.onDestroy();
        Log.i("MainActivity", "onDestroy");
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(dialogMessageReceiver);
    }
}
