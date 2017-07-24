package com.disney.athome;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 * Created by Admin on 6/25/2017.
 */

public class DisneyWebClient extends WebViewClient {


    DisneyWebClient(Context context){

    }

    private static final String TAG = DisneyWebClient.class.getSimpleName();
    ProgressDialog mPDialog = null;

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url); // you are using siteView here instead of view
        return true;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        Log.d(TAG, "onPageStarted: " + url);
        super.onPageStarted(view, url, favicon);
        if (mPDialog == null) {
            mPDialog = new ProgressDialog(view.getContext(), R.style.AppCompatAlertDialogStyle);
            mPDialog.setMessage("Loading . . ");
            mPDialog.setCancelable(false);
            mPDialog.setCanceledOnTouchOutside(false);
        }
        mPDialog.show();
    }


    @Override
    public void onPageFinished(WebView view, String url) {
        Log.d(TAG, "onPageFinished: " + url);

        if (mPDialog != null && mPDialog.isShowing()) {
            mPDialog.dismiss();
        }
        if (url.startsWith("https://disneycruise.disney.go.com/login")) {
            view.loadUrl("javascript: (function() {document.getElementById('loginPageUsername').value = 'Murali';" +
                    "document.getElementById('loginPagePassword').value = 'MuraliPassword';" +
                    "document.getElementById('loginPageSubmitButton').addEventListener('click', function() {android.showLogindDialog();})" +
                    " " +
                    "}) ();");


//        view.loadUrl("javascript:  (function(){document.getElementById('loginPageSubmitButton').addEventListener('click', function() {android.showLogindDialog();} )})();");
        }
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
        Toast.makeText(view.getContext(), "" +errorCode, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        Toast.makeText(view.getContext(), "" +error.toString(), Toast.LENGTH_SHORT).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
        super.onReceivedHttpError(view, request, errorResponse);
        Toast.makeText(view.getContext(), "" +errorResponse.getStatusCode(), Toast.LENGTH_SHORT).show();

    }
}
