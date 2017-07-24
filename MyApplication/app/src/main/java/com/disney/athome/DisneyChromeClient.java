package com.disney.athome;

import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by Admin on 6/25/2017.
 */

public class DisneyChromeClient extends WebChromeClient {

    @Override
    public boolean onJsAlert(WebView view, String url, String message,
                             final JsResult result) {
        return true;
    }

    @Override
    public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
        return true;
    }

    @Override
    public boolean onJsPrompt(WebView view, String url, String message,
                              String defaultValue, final JsPromptResult result) {
        return true;
    }

}
