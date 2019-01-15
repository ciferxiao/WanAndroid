package com.mvp.cifer.wanandroid.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * - @author :  Xiao
 * - @date   :  2019/1/15
 * - @time   :  15:27
 * - @desc   :
 */

//禁止滑动的viewpager
public class NoScrollViewpager extends ViewPager {
    private boolean isScroll;

    public NoScrollViewpager(@NonNull Context context) {
        super(context);
    }

    public NoScrollViewpager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setNoScroll(boolean isScroll){
        this.isScroll = isScroll;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return isScroll && super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isScroll&&super.onInterceptTouchEvent(ev);
    }
}
