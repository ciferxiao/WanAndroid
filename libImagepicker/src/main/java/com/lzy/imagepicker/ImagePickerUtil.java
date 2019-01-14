package com.lzy.imagepicker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;

import java.util.ArrayList;

/**
 * - @author :  JianTing
 * - @date   :  2018/8/10
 * - @time   :  15: 50
 * - @desc   :  选择图片
 */
public class ImagePickerUtil {
    private volatile static ImagePickerUtil mSingleton;

    private ImagePickerUtil() {
    }

    public static ImagePickerUtil getInstance() {
        if (mSingleton == null) {
            synchronized (ImagePickerUtil.class) {
                if (mSingleton == null) {
                    mSingleton = new ImagePickerUtil();
                }
            }
        }
        return mSingleton;
    }

    /**
     * 选择图片
     *
     * @param activity
     * @param selectLimit
     */
    public void selectImage(Activity activity, int selectLimit, int requestCode) {
        ImagePicker instance = ImagePicker.getInstance();
        //设置图片选择数量
        instance.setSelectLimit(selectLimit);
        //设置不裁剪
        instance.setCrop(false);
        //选择照片
        Intent intent = new Intent(activity, ImageGridActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 图片预览(带删除)
     *
     * @param arrayList
     * @param position  图片下标
     */
    public void previewDeleteImage(Activity activity, ArrayList<ImageItem> arrayList, int position, int requestCode) {
        Intent intentPreview = new Intent(activity, ImagePreviewDelActivity.class);
        intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, arrayList);
        intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
        intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
        activity.startActivityForResult(intentPreview, requestCode);
    }

    public void previewImage(Activity activity, ArrayList<ImageItem> arrayList, int position, int requestCode) {
        Intent intentPreview = new Intent(activity, ImagePreviewActivity.class);
        intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, arrayList);
        intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
        intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
        activity.startActivityForResult(intentPreview, requestCode);
    }

}
