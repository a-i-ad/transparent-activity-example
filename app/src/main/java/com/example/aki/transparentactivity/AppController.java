package com.example.aki.transparentactivity;

import android.app.Application;

/**
 * Created by aki on 2018/01/09.
 */

public class AppController extends Application {
    private static AppController mInstance;

    /** ダイアログを表示するかどうかのフラグでダイアログが消える時に都度falseにセットし
     * 不要な時にダイアログが出ないようにするためのもの
     */
    private boolean showNotificationDialog = false;

    /** スリープやロック状態で受け取ったメッセージを保持する変数。
     * この値がnullで無ければロック解除時にダイアログを出すといった動作のために使う
     */
    private String messageDuringSleep = null;

    /** ユーザーのデバイスがアクティブかどうかのフラグでスリープ状態やロック状態での
     * 挙動判定に使う */
    private boolean userPresent = true;

    /** このアプリがバックグラウンドにいるかどうかを検出する */
    private boolean isInBackground = false;

    /** UnLockReceiverが登録されているかどうかのフラグ */
    private boolean isUnLockReceiverRegistered = false;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public void setShowNotificationDialog(boolean val) {
        showNotificationDialog = val;
    }

    public boolean isShowNotificationDialog() {
        return showNotificationDialog;
    }

    public void setMessageDuringSleep(String val) {
        messageDuringSleep = val;
    }

    public String getMessageDuringSleep(){
        return messageDuringSleep;
    }

    public void setUserPresent(boolean val) {
        userPresent = val;
    }

    public boolean isUserPresent() {
        return userPresent;
    }

    public void setInBackground(boolean val) {
        isInBackground = val;
    }

    public boolean isInBackground() {
        return isInBackground;
    }

    public void setUnLockReceiverRegistered(boolean val) {
        isUnLockReceiverRegistered = val;
    }

    public boolean isUnLockReceiverRegistered() {
        return isUnLockReceiverRegistered;
    }


}
