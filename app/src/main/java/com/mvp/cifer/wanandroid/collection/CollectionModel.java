package com.mvp.cifer.wanandroid.collection;

import android.annotation.SuppressLint;

import com.mvp.cifer.wanandroid.utils.AppCallback;
import com.mvp.cifer.wanandroid.utils.retrofitmanager.RetrofitManager;
import com.mvp.cifer.wanandroid.utils.retrofitmanager.RxSchedulers;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/14
 * - @time   :  14:27
 * - @desc   :
 */
public class CollectionModel {

    @SuppressLint("CheckResult")
    public void getListData(final AppCallback<CollectionBean> callback){
        RetrofitManager.getInstance().getRequestService(false)
                .getCollecitonList()
                .compose(RxSchedulers.<CollectionBean>io_main())
                .subscribeWith(new Observer<CollectionBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CollectionBean homeBean) {
                        callback.Success(homeBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.Error(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}


