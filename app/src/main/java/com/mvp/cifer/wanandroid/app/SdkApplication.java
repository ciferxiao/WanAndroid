package com.mvp.cifer.wanandroid.app;

import android.annotation.SuppressLint;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.mvp.cifer.wanandroid.utils.glide.GlideZoomMediaLoader;

import previewlibrary.ZoomMediaLoader;

/**
 * - @author :  Xiao
 * - @date   :  2019/1/14
 * - @time   :  17:02
 * - @desc   :
 */
@SuppressLint("Registered")
public class SdkApplication extends MultiDexApplication{

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("xiao111"," application start ");

        initImage();
    }

    private void initImage(){
        ZoomMediaLoader.getInstance().init(new GlideZoomMediaLoader());
    }
}
