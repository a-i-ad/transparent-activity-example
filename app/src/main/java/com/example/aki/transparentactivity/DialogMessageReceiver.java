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
                intent.putExtra("message", data.getStringExtra("message"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);

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
            Log.i(TAG, "message saved");
            String message = data.getStringExtra("message");
            AppController.getInstance().setMessageDuringSleep(message);
        }
    }
}
