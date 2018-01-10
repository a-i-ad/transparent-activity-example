package com.example.aki.transparentactivity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

/**
 * Created by aki on 2018/01/09.
 */
public class AlertDialogActivity extends FragmentActivity {
    public static final String TAG = AlertDialogActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        if(AppController.getInstance().isShowNotificationDialog()) {
            AlertDialogFragment fragment = new AlertDialogFragment();
            fragment.show(getSupportFragmentManager(), "alert_dialog");
        }else{
            finish();
            Log.i(TAG, "finishing....");
            Intent intent = new Intent(AlertDialogActivity.this, MainActivity.class);
            startActivity(intent);
            Log.i(TAG, "starting main....");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

}