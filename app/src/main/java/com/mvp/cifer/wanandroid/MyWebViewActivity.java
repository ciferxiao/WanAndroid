package com.mvp.cifer.wanandroid;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.just.agentweb.AgentWeb;
import com.just.agentweb.AgentWebView;
import com.mvp.cifer.wanandroid.app.Constants;

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
public class MyWebViewActivity extends AppCompatActivity  {

    @BindView(R.id.article_detail_web_view)
    FrameLayout webcontent;

    private AgentWeb webView;

    private Bundle bundle;
    private int articleId;
    private String articleLink;
    private String title;

    private boolean isCollect;
    private boolean isCommonSite;
    private boolean isCollectPage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_activity);
        ButterKnife.bind(this);

        getBundleData();


        webView = AgentWeb.with(this)
                .setAgentWebParent(webcontent, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .setMainFrameErrorView(R.layout.error_view, -1)
                .createAgentWeb()
                .ready()
                .go(articleLink);

    }

    private void getBundleData() {
        bundle = getIntent().getExtras();
        assert bundle != null;
        title = (String) bundle.get(Constants.ARTICLE_TITLE);
        articleLink = (String) bundle.get(Constants.ARTICLE_LINK);
        articleId = ((int) bundle.get(Constants.ARTICLE_ID));
        isCommonSite = ((boolean) bundle.get(Constants.IS_COMMON_SITE));
        isCollect = ((boolean) bundle.get(Constants.IS_COLLECT));
        isCollectPage = ((boolean) bundle.get(Constants.IS_COLLECT_PAGE));
    }
}
/*
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

        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                webview.loadUrl(intent.getStringExtra("url"));
                return true;
            }
        });


    }

    @Override
    public void onClick(View v) {

    }*/

