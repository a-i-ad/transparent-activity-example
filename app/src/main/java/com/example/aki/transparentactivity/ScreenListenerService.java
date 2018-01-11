/*
 * Copyright 2015 Google Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ↑のライセンス標記に基づくオリジナルソースファイルは↓にあるもの。
 * https://github.com/google/physical-web/blob/master/android/PhysicalWeb/app/src/main/java/org/physical_web/physicalweb/ScreenListenerService.java
 *
 * このオリジナルのソースに対して、このアプリの実装のために必要な改修を加えている。
 */

package com.example.aki.transparentactivity;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

/**
 * This is a service registers a broadcast receiver to listen for screen on/off events.
 * It is a very unfortunate service that must exist because we can't register for screen on/off
 * in the manifest.
 */

public class ScreenListenerService extends Service {
    private static final String TAG = ScreenListenerService.class.getSimpleName();

    private BroadcastReceiver mScreenStateBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, intent.getAction());
            if (intent.getAction().equals(Intent.ACTION_USER_PRESENT)) {
                AppController.getInstance().setUserPresent(true);
                // スリープ中に受け取ったメッセージがあればダイアログ表示する
                if(AppController.getInstance().getMessageDuringSleep() != null) {
                    // ここで直接ダイアログ表示はせずブロードキャストのみを行う
                    Intent messageDialogIntent = new Intent();
                    messageDialogIntent.putExtra("message", AppController.getInstance().getMessageDuringSleep());
                    messageDialogIntent.setAction("MESSAGE_DIALOG");
                    LocalBroadcastManager.getInstance(context).sendBroadcast(messageDialogIntent);

                    // スリープ中のメッセージをnullにセットしてリセットしておく
                    AppController.getInstance().setMessageDuringSleep(null);
                }

            } else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                AppController.getInstance().setUserPresent(false);
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate()");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_USER_PRESENT);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(mScreenStateBroadcastReceiver, intentFilter);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy()");
        unregisterReceiver(mScreenStateBroadcastReceiver);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Nothing should bind to this service
        return null;
    }
}