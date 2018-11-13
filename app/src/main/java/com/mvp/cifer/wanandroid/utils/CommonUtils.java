package com.mvp.cifer.wanandroid.utils;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;

import com.mvp.cifer.wanandroid.app.WanAndroidApp;

import java.util.Random;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/13
 * - @time   :  10:34
 * - @desc   :
 */
public class CommonUtils {

    /**
     * 检查是否有可用网络
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) WanAndroidApp.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        return connectivityManager.getActiveNetworkInfo() != null;
    }


    /**
     * 获取随机rgb颜色值
     */
    public static int randomColor() {
        Random random = new Random();
        //0-190, 如果颜色值过大,就越接近白色,就看不清了,所以需要限定范围
        int red =random.nextInt(150);
        //0-190
        int green =random.nextInt(150);
        //0-190
        int blue =random.nextInt(150);
        //使用rgb混合生成一种新的颜色,Color.rgb生成的是一个int数
        return Color.rgb(red,green, blue);
    }

}
