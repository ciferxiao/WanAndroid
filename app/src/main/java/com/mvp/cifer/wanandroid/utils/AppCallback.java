package com.mvp.cifer.wanandroid.utils;

import com.mvp.cifer.wanandroid.Login.retrofit.LoginBean;
import com.mvp.cifer.wanandroid.basemvp.BaseBean;

/**
 * - @author :  Xiao
 * - @date   :  2018/10/31
 * - @time   :  10:17
 * - @desc   :
 */
public interface AppCallback<T> {

    //成功的方法
    void Success(T t);
    //失败的方法
    void Error(String error);
}
