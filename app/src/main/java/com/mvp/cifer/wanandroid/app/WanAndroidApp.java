package com.mvp.cifer.wanandroid.app;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDelegate;

import com.mvp.cifer.wanandroid.R;
import com.mvp.cifer.wanandroid.utils.glide.ImageDisplayer;
import com.scwang.smartrefresh.header.DeliveryHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/13
 * - @time   :  10:39
 * - @desc   :
 */
public class WanAndroidApp extends SdkApplication {

    public static final int REQUEST_PERMISSION_STORAGE = 0x01;

    private static WanAndroidApp instance;

    private ImageDisplayer displayer;

    //static 代码段可以防止内存泄露, 全局设置刷新头部及尾部，优先级最低
    static {
        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO);
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            //全局设置主题颜色
            layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
            //指定为Delivery Header，默认是贝塞尔雷达Header
            return new DeliveryHeader(context);
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
            //默认是 BallPulseFooter
            return new BallPulseFooter(context).setAnimatingColor(ContextCompat.getColor(context, R.color.colorPrimary));
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        initDisplayer();
    }

    public void initDisplayer(){
        displayer = new ImageDisplayer();
    }

    public boolean checkPermission(@NonNull String permission) {
        return ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public ImageDisplayer getDisplayer(Context context) {

        if (!WanAndroidApp.getInstance().checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_STORAGE);
        }

        return displayer;
    }

    public static synchronized WanAndroidApp getInstance() {
        return instance;
    }

}
