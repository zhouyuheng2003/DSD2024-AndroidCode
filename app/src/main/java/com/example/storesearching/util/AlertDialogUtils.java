package com.example.storesearching.util;

import android.content.Context;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class AlertDialogUtils {

    public static void ShowAlertDialog(Context context, String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(message);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
