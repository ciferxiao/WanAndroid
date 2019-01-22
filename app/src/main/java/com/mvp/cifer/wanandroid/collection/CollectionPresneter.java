package com.mvp.cifer.wanandroid.collection;

import android.util.Log;

import com.mvp.cifer.wanandroid.basemvp.BasePresenter;
import com.mvp.cifer.wanandroid.utils.AppCallback;
import com.mvp.cifer.wanandroid.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/14
 * - @time   :  14:25
 * - @desc   :
 */
public class CollectionPresneter extends BasePresenter<CollectionContract.CollectionView> implements CollectionContract.CollectionPresenterInterface {
    private CollectionModel model;

    public CollectionPresneter() {
        model = new CollectionModel();
    }

    @Override
    public void getListData() {
        model.getListData(new AppCallback<CollectionBean>() {
            @Override
            public void Success(CollectionBean collectionBean) {
                if(collectionBean.getErrorCode() == 0 ){
                    ArrayList<CollectionBean.DataBean> dataBeans = collectionBean.getData();
                    Log.d("xiao111"," right 1111111111111111111111111");
                    getView().showToast("right 111111111111");
                }else{
                    getView().showToast(collectionBean.getErrorMsg());
                }
            }

            @Override
            public void Error(String error) {
                Log.d("xiao111"," 2222222222222222222222222222222");
                //CollectionBean.DataBean dataBean= collectionBean.getData();
                //List<CollectionBean.DataBean.ArticleBean> lists = dataBean.getDatas();
             //   getView().setBasicData(lists,false);
               getView().showToast(error);
            }
        });
    }


    /*@Override
    public void onDisLikeData(String id, int position, boolean isCheck) {
        model.getDisLikeCount(id, new AppCallback<BaseBean>() {
            @Override
            public void Success(BaseBean bean) {
                if (bean.getErrorCode() == 0){
                    getView().setLikeCount(position,isCheck);
                }else{
                    getView().showToast(bean.getErrorMsg());
                }
            }

            @Override
            public void Error(String error) {
                getView().showToast(error);
            }
        });

    }*/

}
