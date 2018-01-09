package com.example.aki.transparentactivity;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by aki on 2018/01/09.
 */
public class AlertDialogActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialogFragment fragment = new AlertDialogFragment();
        fragment.show(getSupportFragmentManager(), "alert_dialog");
    }
}