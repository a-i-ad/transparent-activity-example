package com.example.aki.transparentactivity;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by aki on 2018/01/09.
 */

public class DialogMessageReceiver extends BroadcastReceiver {
    public static final String TAG = DialogMessageReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent data) {
        Log.i(TAG, "onReceive");
        // sleep 状態かどうかを確認して sleep だったらメッセージを保持してダイアログの表示はしない
        if(AppController.getInstance().isUserPresent()) {
            // ユーザーがアクティブかつ、アプリがバックグラウンドにいるときのみメッセージを表示する
            if(AppController.getInstance().isInBackground()) {
                // ユーザーがアクティブかつアプリがバックグラウンドにいるのでダイアログを表示する
                Intent intent = new Intent(context, AlertDialogActivity.class);

                AppController.getInstance().setShowNotificationDialog(true);

                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT);
                try {
                    pendingIntent.send();
                } catch (PendingIntent.CanceledException e) {
                    e.printStackTrace();
                }
            }
        } else {
            // スリープ状態なのでメッセージを保存して待機
            String message = data.getStringExtra("message");
            message = "dummy"; // TODO ダミーなので後で直す
            AppController.getInstance().setMessageDuringSleep(message);
        }
    }
}
