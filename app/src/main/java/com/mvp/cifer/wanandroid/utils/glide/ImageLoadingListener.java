package com.mvp.cifer.wanandroid.utils.glide;

import android.graphics.Bitmap;
import android.view.View;

public interface ImageLoadingListener {

    void onLoadingStarted(String imageUri, View view);

    void onLoadingFailed(String imageUri, View view, String msg);

    void onLoadingComplete(String imageUri, View view, Bitmap loadedImage);

    void onLoadingCancelled(String imageUri, View view);

}
