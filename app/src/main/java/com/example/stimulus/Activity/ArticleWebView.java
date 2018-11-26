package com.example.stimulus.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.example.stimulus.R;

public class ArticleWebView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_web_view);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        WebView webView = findViewById(R.id.webView);
        webView.loadUrl("http://gateway.ipfs.io/ipfs/" + id);
        WebChromeClient webChromeClient = new WebChromeClient(){
            public void onCloseWindow(WebView w){
                super.onCloseWindow(w);
                Intent intent1 = new Intent(ArticleWebView.this, MainActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);
            }
        };
        webView.setWebChromeClient(webChromeClient);
        finish();
        }

}
