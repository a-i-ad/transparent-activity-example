package com.example.aki.transparentactivity;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

/**
 * <p>このレシーバーはスクリーンのON/OFFを検出するためのもの。スクリーンON/OFFの状態を
 * AppControllerの変数に都度保存している。このレシーバーはACTION_SCREEN_OFFを検出する
 * 必要があるのでActivityからレシーバーの登録を行う必要がある。Manifestに記述するとACTION_SCREEN_OFF
 * を受け取ることができない（これはAndroidの仕様なので仕方無い）。</p>
 * Created by aki on 2018/01/09.
 */
public class UnLockReceiver  extends BroadcastReceiver {
    public static final String TAG = UnLockReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent data) {
        Log.i(TAG, "onReceive " + data.getAction());
        if (Intent.ACTION_USER_PRESENT.equals(data.getAction())) {
            AppController.getInstance().setUserPresent(true);
            // スリープ中に受け取ったメッセージがあればダイアログ表示する
            if(AppController.getInstance().getMessageDuringSleep() != null) {
                // ここで直接ダイアログ表示はせずブロードキャストのみを行う
                Intent intent = new Intent();
                intent.putExtra("message", AppController.getInstance().getMessageDuringSleep());
                intent.setAction("MESSAGE_DIALOG");
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

                // スリープ中のメッセージをnullにセットしてリセットしておく
                AppController.getInstance().setMessageDuringSleep(null);
            }
        } else if (Intent.ACTION_SCREEN_OFF.equals(data.getAction())) {
            AppController.getInstance().setUserPresent(false);
        }

    }

}
