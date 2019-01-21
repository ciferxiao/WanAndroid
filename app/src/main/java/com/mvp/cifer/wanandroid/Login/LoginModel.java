package com.mvp.cifer.wanandroid.Login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.mvp.cifer.wanandroid.Login.retrofit.LoginBean;
import com.mvp.cifer.wanandroid.basemvp.BaseBean;
import com.mvp.cifer.wanandroid.utils.AppCallback;
import com.mvp.cifer.wanandroid.utils.RetrofitService;
import com.mvp.cifer.wanandroid.utils.RetrofitUtils;
import com.mvp.cifer.wanandroid.utils.retrofitmanager.RetrofitManager;
import com.mvp.cifer.wanandroid.utils.retrofitmanager.RxSchedulers;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.DisposableObserver;
import okhttp3.Cookie;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * - @author :  Xiao
 * - @date   :  2018/10/30
 * - @time   :  17:16
 * - @desc   :
 */
public class LoginModel {
    private static RetrofitService service;
    private List<Cookie> mlist =new ArrayList<>();



    @SuppressLint("CheckResult")
    public void LoginHttp(final Context context,String name , String passwd, final AppCallback<BaseBean> loginListener){

        RetrofitManager.getInstance().getRequestService(true).postLoginRetrofit(name,passwd).compose(RxSchedulers.<LoginBean>io_main())
                .subscribeWith(new DisposableObserver<LoginBean>() {
                    @Override
                    public void onNext(LoginBean bean) {
                        Log.d("TAG"," error code = " + bean.getErrorCode());
                        Log.d("TAG"," error Message = " + bean.getErrorMsg());
                        loginListener.Success(bean);
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        loginListener.Error(e.toString());
                    }

                });
    }


}
