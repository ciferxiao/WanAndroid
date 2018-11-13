package com.mvp.cifer.wanandroid.app;

import android.annotation.SuppressLint;
import android.app.Application;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDelegate;

import com.mvp.cifer.wanandroid.R;
import com.scwang.smartrefresh.header.DeliveryHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/13
 * - @time   :  10:39
 * - @desc   :
 */
public class WanAndroidApp extends Application {

    private static WanAndroidApp instance;

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
    }

    public static synchronized WanAndroidApp getInstance() {
        return instance;
    }

}
