package com.nvisions.solutionsforaccessibility.WebView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nvisions.solutionsforaccessibility.R;

public class WebViewWithAccessibilityActivity extends AppCompatActivity {
    WebView browser;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_with_accessibility);

        browser=findViewById(R.id.browser);
        progressBar = findViewById(R.id.progressBar);

        WebSettings websettings = browser.getSettings();
        websettings.setDomStorageEnabled(true);  // Open DOM storage function
        websettings.setAppCacheMaxSize(1024*1024*8);
        String appCachePath = getApplicationContext().getCacheDir().getAbsolutePath();
        websettings.setAppCachePath(appCachePath);
        websettings.setAllowFileAccess(true);    // Readable file cache
        websettings.setAppCacheEnabled(true);    //Turn on the H5(APPCache) caching function
        websettings.setJavaScriptEnabled(true);

        //alert가 작동하려면 아래 WebChromeClient 지정해야 함
        browser.setWebViewClient(new WebViewClient() {
            public void onPageStarted(WebView view, String url,
                                      android.graphics.Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            };


            public void onPageFinished(WebView view, String url) {
                // do your stuff here
                progressBar.setVisibility(View.INVISIBLE);

                //웹페이지를 읽지 않는 경우가 있어 1초 딜레이 줌 
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        browser.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED);
                    }
                }, 1000);


            }
        });
        browser.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);
                //setContentDescription를 해야 음성 안내함
                progressBar.setContentDescription(newProgress+"%");
            }
        });


        browser.loadUrl("https://a11y-nvisions.github.io/Solutions/WEB/example.radioButton/index.html");
    }

    @Override
    public void onBackPressed() {
        if (browser.canGoBack()) {
            browser.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
