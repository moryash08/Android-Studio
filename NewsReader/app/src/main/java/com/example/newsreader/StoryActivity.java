package com.example.newsreader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class StoryActivity extends Activity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        final ProgressBar progressBar = new ProgressBar(StoryActivity.this, null, android.R.attr.progressBarStyleLarge);
        progressBar.setVisibility(View.VISIBLE);

        WebView webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                progressBar.setVisibility(View.GONE);
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);

        Intent intent = getIntent();
        webView.loadUrl(intent.getStringExtra("content"));
    }
}