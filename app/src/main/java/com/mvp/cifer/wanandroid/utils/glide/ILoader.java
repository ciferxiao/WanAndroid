package com.mvp.cifer.wanandroid.utils.glide;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.File;

public interface ILoader {

    void setIsDenyNetwork(boolean iDenyNetworks);

    /**
     * Method name: normalLoad <BR>
     * Description: 普通图片加载 <BR>
     * Remark: <BR>
     *
     * @param context
     * @param targetImageView
     * @param imgUri
     * @param roundAngle
     * @param loadingPicId    void<BR>
     */
    void normalLoad(Context context, ImageView targetImageView, String imgUri, int roundAngle,
                    int loadingPicId);

    /**
     * Method name: nativeLoad <BR>
     * Description: 本地图片加载 <BR>
     * Remark: <BR>
     *
     * @param context
     * @param targetImageView
     * @param imgUri
     * @param roundAngle
     * @param loadingPicId    void<BR>
     */
    void nativeLoad(Context context, ImageView targetImageView, String imgUri, int roundAngle, int loadingPicId);

    /**
     * Method name: normalLoad <BR>
     * Description: listview图片加载 <BR>
     * Remark: <BR>
     *
     * @param context
     * @param targetImageView
     * @param imgUri          图片地址
     * @param roundAngle      图片圆角狐度
     * @param loadingPicId    默认加载图片R.id. void<BR>
     */
    void listLoad(Context context, ImageView targetImageView, String imgUri, int roundAngle, int loadingPicId);

    /**
     * Method name: normalLoad <BR>
     * Description: viewpager图片加载 <BR>
     * Remark: <BR>
     *
     * @param context
     * @param targetImageView
     * @param imgUri
     * @param roundAngle
     * @param loadingPicId    void<BR>
     */
    void viewpageLoad(Context context, ImageView targetImageView, String imgUri, int roundAngle, int loadingPicId, ProgressBar progressBar);

    /**
     * Method name: verCodeLoad <BR>
     * Description: 验证码图片加载 <BR>
     * Remark: <BR>
     *
     * @param context
     * @param targetImageView
     * @param imgUri
     * @param roundAngle
     * @param loadingPicId    void<BR>
     */
    void verCodeLoad(Context context, ImageView targetImageView, String imgUri, int roundAngle, int loadingPicId, View loadingView, View failView);

    /**
     * Method name: normalLoad <BR>
     * Description: gridview图片加载 <BR>
     * Remark: <BR>
     *
     * @param context
     * @param targetImageView
     * @param imgUri
     * @param roundAngle
     * @param loadingPicId    void<BR>
     */
    void gridviewLoad(Context context, ImageView targetImageView, String imgUri, int roundAngle, int loadingPicId);

    /**
     * Method name: downLoadImage <BR>
     * Description: 下载图片 <BR>
     * Remark: <BR>
     *
     * @param context
     * @param imgUri
     */
    void downLoadImage(Context context, String imgUri, ImageLoadingListener listener);

    /**
     * Method name: downLoadImage <BR>
     * Description: 下载图片 <BR>
     * Remark: <BR>
     *
     * @param context
     * @param imgUri
     */
    void displayImage(Context context, ImageView targetImageView, String imgUri, ImageLoadingListener listener);

    /**
     * Method name: downLoadImage <BR>
     * Description: 下载图片 <BR>
     * Remark: <BR>
     *
     * @param context
     * @param imgUri
     */
    void displayImage(Context context, ImageView targetImageView, String imgUri, int loadingPicId, int failPicId, int roundAngle, ImageLoadingListener listener);

    /**
     * Method name: clearCache <BR>
     * Description: 清空缓存 <BR>
     * Remark: <BR>
     */
    void clearCache();

    /**
     * Method name: getCacheDirectory <BR>
     * Description: 获取缓存路径 <BR>
     * Remark: <BR>
     */
    File getCacheDirectory();

    /**
     * Method name: downLoadImage <BR>
     * Description: 下载图片 <BR>
     * Remark: <BR>
     *
     * @param context
     * @param imgUri
     */
    void downLoadImageWithIcon(Context context, String imgUri, ImageLoadingListener listener);
}
