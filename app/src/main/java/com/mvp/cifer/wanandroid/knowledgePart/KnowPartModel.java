package com.mvp.cifer.wanandroid.knowledgePart;

import android.annotation.SuppressLint;

import com.mvp.cifer.wanandroid.basemvp.BaseBean;
import com.mvp.cifer.wanandroid.knowledge.bean.Knowledgebean;
import com.mvp.cifer.wanandroid.utils.AppCallback;
import com.mvp.cifer.wanandroid.utils.retrofitmanager.RetrofitManager;
import com.mvp.cifer.wanandroid.utils.retrofitmanager.RxSchedulers;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/8
 * - @time   :  15:46
 * - @desc   :
 */
public class KnowPartModel {

    @SuppressLint("CheckResult")
    public void getData(int id , final AppCallback<PartBean> appCallback){
        RetrofitManager.getInstance().getRequestService(false)
                .getKnowledgePart(id)
                .compose(RxSchedulers.<PartBean>io_main())
                .subscribeWith(new Observer<PartBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        if(!d.isDisposed()){
                            //TODO

                        }

                    }

                    @Override
                    public void onNext(PartBean partBean) {
                        appCallback.Success(partBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        appCallback.Error(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });



    }

}
