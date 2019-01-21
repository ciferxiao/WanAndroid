package com.mvp.cifer.wanandroid.home;

import android.annotation.SuppressLint;

import com.mvp.cifer.wanandroid.Login.retrofit.LoginBean;
import com.mvp.cifer.wanandroid.basemvp.BaseBean;
import com.mvp.cifer.wanandroid.home.bean.HomeBannerBean;
import com.mvp.cifer.wanandroid.home.bean.HomeBean;
import com.mvp.cifer.wanandroid.utils.AppCallback;
import com.mvp.cifer.wanandroid.utils.retrofitmanager.RetrofitManager;
import com.mvp.cifer.wanandroid.utils.retrofitmanager.RxSchedulers;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/1
 * - @time   :  11:29
 * - @desc   :
 */

public class HomeModel {

    @SuppressLint("CheckResult")
    public void getData(final AppCallback<HomeBannerBean> callback){
        RetrofitManager.getInstance()
                .getRequestService(false)
                .getBannerRetrofit()
                .compose(RxSchedulers.<HomeBannerBean>io_main())
                .subscribeWith(new Observer<HomeBannerBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeBannerBean homeBannerBean) {
                        callback.Success(homeBannerBean);
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

    @SuppressLint("CheckResult")
    public void getListData(int page,final AppCallback<HomeBean> callback){
        RetrofitManager.getInstance()
                .getRequestService(false)
                .getHomeArticle(page)
                .compose(RxSchedulers.<HomeBean>io_main())
                .subscribeWith(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeBean homeBean) {
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

    @SuppressLint("CheckResult")
    public void getLikeCount(String id, AppCallback appCallback){
        RetrofitManager.getInstance().getRequestService(false)
                .postinnerCollection(id)
                .compose(RxSchedulers.io_main())
                .subscribeWith(new Observer<BaseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        appCallback.Success(baseBean);
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

    @SuppressLint("CheckResult")
    public void getDisLikeCount(String id, AppCallback appCallback){
        RetrofitManager.getInstance().getRequestService(false)
                .cancelCollectionList(id)
                .compose(RxSchedulers.io_main())
                .subscribeWith(new Observer<BaseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        appCallback.Success(baseBean);
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
