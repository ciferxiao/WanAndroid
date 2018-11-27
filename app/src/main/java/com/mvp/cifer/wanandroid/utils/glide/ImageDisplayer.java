package com.mvp.cifer.wanandroid.utils.glide;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.File;

public class ImageDisplayer {
    private ILoader loader;

    public ImageDisplayer() {
        try {
            loader = LoaderFactory.getLoader(GlideImageLoader.class);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void setLoader(ILoader loader) {
        this.loader = loader;
    }

    public void normalLoad(Context context, ImageView targetImageView, String imgUri, int roundAngle,
                           int loadingPicId) {
        loader.normalLoad(context, targetImageView, imgUri, roundAngle, loadingPicId);
    }

    public void nativeLoad(Context context, ImageView targetImageView, String imgUri, int roundAngle,
                           int loadingPicId) {
        loader.nativeLoad(context, targetImageView, imgUri, roundAngle, loadingPicId);
    }

    public void listLoad(Context context, ImageView targetImageView, String imgUri, int roundAngle,
                         int loadingPicId) {
        loader.listLoad(context, targetImageView, imgUri, roundAngle, loadingPicId);
    }

    public void viewpageLoad(Context context, ImageView targetImageView, String imgUri,
                             int roundAngle, int loadingPicId, ProgressBar progressBar) {
        loader.viewpageLoad(context, targetImageView, imgUri, roundAngle, loadingPicId, progressBar);
    }

    public void verCodeLoad(Context context, ImageView targetImageView, String imgUri, int roundAngle, int loadingPicId, View loadingView, View failView) {
        loader.verCodeLoad(context, targetImageView, imgUri, roundAngle, loadingPicId, loadingView, failView);
    }

    public void gridviewLoad(Context context, ImageView targetImageView, String imgUri,
                             int roundAngle, int loadingPicId) {
        loader.gridviewLoad(context, targetImageView, imgUri, roundAngle, loadingPicId);
    }

    public void downLoadImage(Context context, String imgUri, ImageLoadingListener listener) {
        loader.downLoadImage(context, imgUri, listener);
    }

    public void displayImage(Context context, ImageView targetImageView, String imgUri, ImageLoadingListener listener) {
        loader.displayImage(context, targetImageView, imgUri, listener);
    }

    public void displayImage(Context context, ImageView targetImageView, String imgUri, int loadingPicId, int failPicId, int roundAngle, ImageLoadingListener listener) {
        loader.displayImage(context, targetImageView, imgUri, loadingPicId, failPicId, roundAngle, listener);
    }

    public void clearCache() {
        loader.clearCache();
    }

    public File getCacheDirectory() {
        return loader.getCacheDirectory();
    }

}
