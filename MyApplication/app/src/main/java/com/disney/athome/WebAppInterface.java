package com.disney.athome;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public abstract class WebAppInterface {
    Context mContext;

    /** Instantiate the interface and set the context */
    WebAppInterface(Context c) {
        mContext = c;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public abstract void showLogindDialog();

    @JavascriptInterface
    public void showToastMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("Title").setMessage("Message").setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setNegativeButton(android.R.string.cancel, null);
        builder.show();
        Toast.makeText(mContext, "Signin click on test git", Toast.LENGTH_SHORT).show();
    }

}