package com.example.unitec.parsingxml_example;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class ReedWeb extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reed_web);
        webView = (WebView) findViewById(R.id.webView);
        Intent intent = getIntent();
        String link = intent.getStringExtra("link");
        webView.loadUrl(link);
        webView.setWebViewClient(new WebViewClient());


  //     try {
  //         Intent i = new Intent("android.intent.action.MAIN");
  //         i.setComponent(ComponentName.unflattenFromString("com.android.chrome/com.android.chrome.Main"));
  //         i.addCategory("android.intent.category.LAUNCHER");
  //         i.setData(Uri.parse(link));
  //         startActivity(i);
  //     } catch (ActivityNotFoundException e) {
  //         // Chrome is not installed
  //         Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
  //         startActivity(i);
  //     }
    }

}
