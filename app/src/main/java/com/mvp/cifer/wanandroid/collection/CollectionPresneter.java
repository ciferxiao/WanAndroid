package com.mvp.cifer.wanandroid.collection;

import com.mvp.cifer.wanandroid.basemvp.BasePresenter;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/14
 * - @time   :  14:25
 * - @desc   :
 */
public class CollectionPresneter extends BasePresenter<CollectionContract.CollectionView> {
    private CollectionModel model;

    public CollectionPresneter() {
        model = new CollectionModel();
    }
}
