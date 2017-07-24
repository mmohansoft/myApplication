package com.disney.athome;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private String webSiteURL = "https://disneycruise.disney.go.com/";
    private  AlertDialog.Builder builder;
//    private String webSiteURL = "www.google.com";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAllowContentAccess(true);
        //webView.getSettings().setCacheMode();
        webView.setWebViewClient(new DisneyWebClient(this));
        webView.setWebChromeClient(new DisneyChromeClient());
        webView.addJavascriptInterface(new WebAppInterface(this) {

            @JavascriptInterface
            public void showLogindDialog() {
                builder= new AlertDialog.Builder(mContext);
                builder.setTitle("Title").setMessage("Message").setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(mContext, "ok clicked", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(mContext, "cancel clicked", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();

            }
        }, "android");
        //set URL address:
        webView.loadUrl(webSiteURL);

    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }


}
