package com.example.nasrmohamed.translateit.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.nasrmohamed.translateit.R;

import dmax.dialog.SpotsDialog;

public class LoadingDialogues {
    private static AlertDialog loadingDialogue;
    private static AlertDialog errorDialogue;
    private Context context;

    public LoadingDialogues(Context context) {
        this.context = context;
    }

    public void showLoadingDialogue(String text,boolean isCancel){

        loadingDialogue = new SpotsDialog.Builder().setContext(context)
                .setMessage(text+"...")
                .setCancelable(isCancel)
                .build();
        loadingDialogue.show();
    }

    public void removeLoadingDialogue(){
        loadingDialogue.dismiss();
    }

    public void showErrorDialogue(String title, String message, final boolean exit){
        errorDialogue= new AlertDialog.Builder(context).create();
        errorDialogue.setTitle(title);
        errorDialogue.setMessage(message);
        errorDialogue.setIcon(R.drawable.snack_warning);
        errorDialogue.setButton(context.getText(R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if(exit){
                    ((Activity) context).finish();
                }else {
                    errorDialogue.dismiss();
                }
            }
        });
        errorDialogue.show();
    }

    public boolean isLoadingShown(){
        return loadingDialogue.isShowing();
    }
}
