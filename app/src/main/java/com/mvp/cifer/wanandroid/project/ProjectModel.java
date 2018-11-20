package com.mvp.cifer.wanandroid.project;

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
public class ProjectModel {

    @SuppressLint("CheckResult")
    public void getProjectPart(AppCallback<ProjectBean> appCallback){
        RetrofitManager.getInstance().getRequestService()
                .getProjectPart()
                .compose(RxSchedulers.io_main())
                .subscribeWith(new Observer<ProjectBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ProjectBean projectBean) {
                        appCallback.Success(projectBean);
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
