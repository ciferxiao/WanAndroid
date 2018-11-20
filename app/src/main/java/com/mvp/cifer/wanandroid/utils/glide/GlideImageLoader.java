package com.mvp.cifer.wanandroid.utils.glide;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.mvp.cifer.wanandroid.R;
import com.mvp.cifer.wanandroid.app.WanAndroidApp;

import java.io.File;

import static com.mvp.cifer.wanandroid.app.WanAndroidApp.REQUEST_PERMISSION_STORAGE;

/**
 * Created by 20164241 on 2016/10/24.
 */

public class GlideImageLoader implements ILoader {

    @Override
    public void setIsDenyNetwork(boolean iDenyNetworks) {

    }

    private RequestListener<String, GlideDrawable> requestListener = new RequestListener<String, GlideDrawable>() {
        @Override
        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
            // todo log exception
            Log.d("xiao_glide"," e ==" + e.toString());
            // important to return false so the error placeholder can be placed
            return false;
        }

        @Override
        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
            return false;
        }
    };


    @Override
    public void normalLoad(Context context, ImageView targetImageView, String imgUri, int roundAngle, int loadingPicId) {
        if (context == null) {
            return;
        }

        Glide.with(context)
                .load(imgUri).dontAnimate()
                .placeholder(loadingPicId)
                .error(loadingPicId)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .transform(new GlideRoundTransform(context, roundAngle))
                .into(targetImageView);
    }

    @Override
    public void nativeLoad(Context context, ImageView targetImageView, String imgUri, int roundAngle, int loadingPicId) {
        if (context == null) {
            return;
        }



        if (!imgUri.startsWith("file://")) {
            imgUri = "file://" + imgUri;
        }

        Glide.with(context)
                .load(imgUri).dontAnimate()
                .placeholder(loadingPicId)
                .error(loadingPicId)
                .transform(new GlideRoundTransform(context, roundAngle))
                .listener(requestListener)
                .into(targetImageView);
    }

    @Override
    public void listLoad(Context context, ImageView targetImageView, String imgUri, int roundAngle, int loadingPicId) {
        if (context == null) {
            return;
        }
        Glide.with(context)
                .load(imgUri).dontAnimate()
                .placeholder(loadingPicId)
                .error(loadingPicId)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .transform(new GlideRoundTransform(context, roundAngle))
                .into(targetImageView);
    }

    @Override
    public void viewpageLoad(Context context, ImageView targetImageView, String imgUri, int roundAngle, int loadingPicId, ProgressBar progressBar) {
        if (context == null) {
            return;
        }
        Glide.with(context)
                .load(imgUri)
                .dontAnimate()
                .error(loadingPicId)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .transform(new GlideRoundTransform(context, roundAngle))
                .into(targetImageView);
    }

    @Override
    public void verCodeLoad(Context context, ImageView targetImageView, String imgUri, int roundAngle, int loadingPicId, final View loadingView, final View failView) {
        if (context == null) {
            return;
        }
        Glide.with(context)
                .load(imgUri).crossFade(500)
                .placeholder(loadingPicId)
                .error(loadingPicId)
                .transform(new GlideRoundTransform(context, roundAngle))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(new GlideDrawableImageViewTarget(targetImageView) {
                    @Override
                    public void onStart() {
                        super.onStart();
                        if (null != loadingView) {
                            loadingView.setVisibility(View.VISIBLE);
                        }
                        if (null != failView) {
                            failView.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                        super.onResourceReady(resource, animation);
                        if (null != loadingView) {
                            loadingView.setVisibility(View.GONE);
                        }
                        if (null != failView) {
                            failView.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        super.onLoadFailed(e, errorDrawable);
                        if (null != loadingView) {
                            loadingView.setVisibility(View.GONE);
                        }
                        if (null != failView) {
                            failView.setVisibility(View.VISIBLE);
                        }
                    }

                });
    }

    @Override
    public void gridviewLoad(Context context, ImageView targetImageView, String imgUri, int roundAngle, int loadingPicId) {
        if (context == null) {
            return;
        }
        Glide.with(context)
                .load(imgUri).dontAnimate()
                .placeholder(loadingPicId)
                .error(loadingPicId)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .transform(new GlideRoundTransform(context, roundAngle))
                .into(targetImageView);
    }

    @Override
    public void downLoadImage(Context context, final String imgUri, final ImageLoadingListener listener) {
        if (context == null) {
            return;
        }
        Glide.with(context)
                .load(imgUri)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        if (listener != null) {
                            listener.onLoadingStarted(imgUri, null);
                        }

                    }

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        super.onLoadFailed(e, errorDrawable);
                        if (listener != null) {
                            listener.onLoadingFailed(imgUri, null, e == null ? "" : e.getMessage());
                        }

                    }

                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        if (listener != null) {
                            listener.onLoadingComplete(imgUri, null, resource);
                        }
                    }
                });
    }

    @Override
    public void displayImage(Context context, ImageView targetImageView, final String imgUri, final ImageLoadingListener listener) {
        if (context == null) {
            return;
        }
        displayImage(context, targetImageView, imgUri, R.drawable.ic_loading_big_rectangle, R.drawable.ic_loading_big_rectangle, 0, listener);
    }

    @Override
    public void displayImage(Context context, ImageView targetImageView, final String imgUri, int loadingPicId, int failPicId, int roundAngle, final ImageLoadingListener listener) {
        if (context == null) {
            return;
        }
        Glide.with(context)
                .load(imgUri)
                .asBitmap()
                .placeholder(loadingPicId)
                .error(failPicId)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        if (listener != null) {
                            listener.onLoadingStarted(imgUri, null);

                        }
                    }

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        super.onLoadFailed(e, errorDrawable);
                        if (listener != null) {
                            listener.onLoadingFailed(imgUri, null, e == null ? "" : e.getMessage());

                        }

                    }

                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        if (listener != null) {
                            listener.onLoadingComplete(imgUri, null, resource);

                        }
                    }
                });
    }

    @Override
    public void clearCache() {

    }

    @Override
    public File getCacheDirectory() {
        return null;
    }

}
