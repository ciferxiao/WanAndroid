package com.mvp.cifer.wanandroid.collection;

import com.mvp.cifer.wanandroid.basemvp.BaseView;

import java.util.List;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/14
 * - @time   :  14:25
 * - @desc   :
 */
public class CollectionContract {
    interface CollectionView extends BaseView{

        void setBasicData(List<CollectionBean.DataBean.ArticleBean> houseItems, boolean isRefresh);

        void showToast(String msg);
    }

    interface CollectionPresenterInterface{

        void getListData();

    }

}
