package com.gptm.app.controller;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gptm.app.R;

public class WaitTimeDialogController {

    private AlertDialog dialog;

    public WaitTimeDialogController(final Context context)    {
        dialog = new AlertDialog.Builder(context)
                //.setView(v)
                .setTitle(R.string.app_name)
                .setMessage("Your est wait time")
                .setPositiveButton(R.string.Try_Again, null) //Set to null. We override the onclick
                //.setNegativeButton(android.R.string.cancel, null)
                .create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialog) {

                Button button = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                button.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Log.i("TAG IDDDDDDDDDD", String.valueOf(view.getId()));
                        Toast.makeText(context, "Try Again", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                Toast.makeText(context, "Dialog Dissmissed", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void show() {
        dialog.show();
    }
}
