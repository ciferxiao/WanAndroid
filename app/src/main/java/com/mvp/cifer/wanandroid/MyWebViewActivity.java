package com.mvp.cifer.wanandroid;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * class name:MyWebViewActivity <BR>
 * class description: 网页加载容器 <BR>
 * Remark: <BR>
 *
 * @version 1.00 2014-12-3
 */
public class MyWebViewActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.webview)
    WebView webview;
    private static String loadUrl;

    public static MyWebViewActivity newInstance(String url){
        loadUrl = url;
        MyWebViewActivity activity= new MyWebViewActivity();
        return activity;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_activity);
        ButterKnife.bind(this);
        Intent intent = getIntent();

        webview.loadUrl(intent.getStringExtra("url"));
    }

    @Override
    public void onClick(View v) {

    }
}
