package com.mvp.cifer.wanandroid.knowledge;

import android.annotation.SuppressLint;

import com.mvp.cifer.wanandroid.knowledge.bean.Knowledgebean;
import com.mvp.cifer.wanandroid.utils.AppCallback;
import com.mvp.cifer.wanandroid.utils.retrofitmanager.RetrofitManager;
import com.mvp.cifer.wanandroid.utils.retrofitmanager.RxSchedulers;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/7
 * - @time   :  17:52
 * - @desc   :
 */
public class KnowledgeModel {

    @SuppressLint("CheckResult")
    public void getListData(final AppCallback<Knowledgebean> appcallback){
        RetrofitManager.getInstance().getRequestService(false)
                .getKnowledgeList()
                .compose(RxSchedulers.<Knowledgebean>io_main())
                .subscribeWith(new Observer<Knowledgebean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Knowledgebean knowledgebean) {
                        appcallback.Success(knowledgebean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        appcallback.Error(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
