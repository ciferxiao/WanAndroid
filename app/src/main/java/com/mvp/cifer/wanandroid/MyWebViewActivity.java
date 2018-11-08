package com.mvp.cifer.wanandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/7
 * - @time   :  16:50
 * - @desc   :
 */
public class MyWebViewActivity extends AppCompatActivity {
    @BindView(R.id.webview)
    WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_activity);
        ButterKnife.bind(this);

        initwebView();
    }

    private void  initwebView(){
        Intent intent = getIntent();
        webView.loadUrl(intent.getStringExtra("article_url"));
    }
}
