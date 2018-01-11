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
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

/**
 * <p>以下URLを参考にして作成した
 * https://github.com/google/physical-web/blob/master/android/PhysicalWeb/app/src/main/java/org/physical_web/physicalweb/ScreenListenerService.java</p>
 * <p>アプリが終了された状態でもPUSH通知経由のメッセージを受け取れるようにするためにDialogMessageReceiverを
 * Service内でレシーバーの登録及び解除を行うようにしている。</p>
 * Created by aki on 2018/01/11.
 */

public class DialogMessageListenerService extends Service {
    private static final String TAG = DialogMessageListenerService.class.getSimpleName();

    private BroadcastReceiver receiver = new DialogMessageReceiver();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate()");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("MESSAGE_DIALOG");
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, intentFilter);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy()");
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Nothing should bind to this service
        return null;
    }
}
