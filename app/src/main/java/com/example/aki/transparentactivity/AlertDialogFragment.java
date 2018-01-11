package com.example.aki.transparentactivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;

public class AlertDialogFragment extends DialogFragment {
    public static final String TAG = AlertDialogFragment.class.getSimpleName();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.i(TAG, "onCreateDialog");
        String message = getArguments().getString("message");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        Dialog dialog = builder
                .setIcon(R.mipmap.ic_launcher_round)
                .setTitle(R.string.app_name).setMessage(message)
            .setPositiveButton("表示", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // OK button pressed
                    Intent intent = new Intent(getActivity(), DummyActivity.class);
                    // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            })
            .setNegativeButton("閉じる", null)
            .create();
        //dialog.setCanceledOnTouchOutside(true);

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