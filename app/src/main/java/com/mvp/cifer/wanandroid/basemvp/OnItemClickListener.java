package com.mvp.cifer.wanandroid.basemvp;

import android.view.View;

/**
 * User: Jianting
 * Date: 2017-02-28
 * Time: 12:53
 * desc:
 */
public interface OnItemClickListener<T> {
    void onClick(T t, int position,View view);
}
