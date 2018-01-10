package com.example.aki.transparentactivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;

public class AlertDialogFragment extends DialogFragment {
    public static final String TAG = AlertDialogFragment.class.getSimpleName();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.i(TAG, "onCreateDialog");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        Dialog dialog = builder.setTitle("通知").setMessage("").create();
        dialog.setCanceledOnTouchOutside(true);

        return dialog;
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
        AppController.getInstance().setShowNotificationDialog(false);
        getActivity().finish();
    }
}