package com.mvp.cifer.wanandroid.basemvp;

/**
 * - @author :  Xiao
 * - @date   :  2018/10/30
 * - @time   :  16:33
 * - @desc   :
 */
public abstract class BasePresenter<V> {

    private V view ;

    public V getView(){
        return view;
    }

    public void attachView(V loginview){
        this.view = loginview;
    }

    public void detachView(){
        this.view = null;
    }

}
