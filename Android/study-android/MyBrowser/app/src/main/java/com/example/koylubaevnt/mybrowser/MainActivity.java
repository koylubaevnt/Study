package com.example.koylubaevnt.mybrowser;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    public static final java.lang.String ACTION_VIEW = "android.intent.action.VIEW";

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = (WebView) findViewById(R.id.webView);
        //Support JavaScript
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl("http://developer.alexanderklimov.ru/android");

    }

    @Override
    public void onBackPressed() {
        if(mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    public void onClick(View view) {
        //Intent intent = new Intent("com.example.koylubaevnt.Browser");
        Intent intent = new Intent(ACTION_VIEW);
        intent.setData(Uri.parse("http://developer.alexanderklimov.ru/android"));
        //startActivity(intent);
        startActivity(Intent.createChooser(intent, "Мяу..."));
    }

    private class MyWebViewClient extends WebViewClient {

        @TargetApi(value = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //Uri.parse(url).getHost().
            view.loadUrl(url);
            return true;
        }
    }
}
