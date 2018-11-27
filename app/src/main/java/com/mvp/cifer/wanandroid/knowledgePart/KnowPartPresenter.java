package com.mvp.cifer.wanandroid.knowledgePart;

import android.util.Log;

import com.mvp.cifer.wanandroid.basemvp.BasePresenter;
import com.mvp.cifer.wanandroid.utils.AppCallback;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/8
 * - @time   :  15:46
 * - @desc   :
 */
public class KnowPartPresenter extends BasePresenter<KnowPartContract.KnowPartView> implements KnowPartContract.KnowPartPresneterInterface{

    private KnowPartModel model;

    public KnowPartPresenter() {
        model = new KnowPartModel();
    }

    @Override
    public void getListData(int id) {
        model.getData(id, new AppCallback<PartBean>() {
            @Override
            public void Success(PartBean partBean) {
                if(partBean.getErrorCode() == 0 ){
                    getView().setListData(partBean.getData());
                }else{

                }
            }

            @Override
            public void Error(String error) {
            }
        });

    }
}
