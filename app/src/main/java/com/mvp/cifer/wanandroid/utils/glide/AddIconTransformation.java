package com.mvp.cifer.wanandroid.utils.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.mvp.cifer.wanandroid.R;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/29
 * - @time   :  14:46
 * - @desc   :  封装水印 gifbmp
 */
public class AddIconTransformation extends BitmapTransformation {

    private static Bitmap waterIcon;
    private Context context;

    public AddIconTransformation(Context context) {
        super(context);
        this.context = context;
        waterIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.arrow);
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        if(waterIcon == null){
            waterIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.arrow);
        }
         return addIcon(pool,toTransform);
    }

    @Override
    public String getId() {
        return getClass().getName() + System.currentTimeMillis();//标识
    }

    private Bitmap addIcon(BitmapPool pool, Bitmap source) {
        int width = source.getWidth();
        int height = source.getHeight();

        int gifbmpWidth = waterIcon.getWidth();
        int gifbmpHeight = waterIcon.getHeight();

        Bitmap result = pool.get(width, height, Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(result);
        //在画布 0，0坐标上开始绘制原始图片
        canvas.drawBitmap(source, 0, 0, null);
        //在画布上绘制水印图片
        canvas.drawBitmap(waterIcon, (width - gifbmpWidth), (height - gifbmpHeight), null);
        // 保存
        canvas.save();
        // 存储
        canvas.restore();

        source.recycle();
        waterIcon.recycle();
        waterIcon = null;

        return result;
    }

}
