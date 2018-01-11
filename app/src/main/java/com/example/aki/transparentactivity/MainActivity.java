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

        // サービスを開始してスクリーンのアンロック、スリープを検知できるようにする
        Intent intent = new Intent(this, ScreenListenerService.class);
        startService(intent);

        //
        Intent dialogMessageListenerService = new Intent(this, DialogMessageListenerService.class);
        startService(dialogMessageListenerService);

    }

    @Override
    protected void onResume() {
        super.onResume();
        AppController.getInstance().setInBackground(false);
    }

    @Override
    protected void onPause() {
        AppController.getInstance().setInBackground(true);
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected  void onDestroy() {
        Log.i("MainActivity", "onDestroy");
        super.onDestroy();
    }
}
