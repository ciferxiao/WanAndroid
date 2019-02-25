package com.mvp.cifer.wanandroid.Login;

import android.annotation.SuppressLint;
import android.util.Log;
import android.widget.Toast;

import com.mvp.cifer.wanandroid.Login.retrofit.LoginBean;
import com.mvp.cifer.wanandroid.basemvp.BaseBean;
import com.mvp.cifer.wanandroid.basemvp.BasePresenter;
import com.mvp.cifer.wanandroid.utils.AppCallback;
import com.mvp.cifer.wanandroid.utils.cookies.CookiesManager;
import com.mvp.cifer.wanandroid.utils.retrofitmanager.RetrofitManager;
import com.mvp.cifer.wanandroid.utils.retrofitmanager.RxSchedulers;

import io.reactivex.observers.DisposableObserver;

/**
 * - @author :  Xiao
 * - @date   :  2018/10/30
 * - @time   :  17:12
 * - @desc   :
 */
public class LoginPresenter extends BasePresenter<LoginContract.LoginView> implements LoginContract.LoginPresenterInterface {
/*
    @Override
    public void getLogin(String name, String passwd) {}
*/


    @SuppressLint("CheckResult")
    @Override
    public void getLoginB(String name , String passwd){
        RetrofitManager.getInstance()
                .getRequestService(true)
                .postLoginRetrofit(name,passwd).compose(RxSchedulers.<LoginBean>io_main())
                .subscribeWith(new DisposableObserver<LoginBean>() {
                    @Override
                    public void onNext(LoginBean bean) {
                        Log.d("TAG"," error code = " + bean.getErrorCode());
                        Log.d("TAG"," error Message = " + bean.getErrorMsg());
                        if (bean.getErrorCode() == 0) {
                            getView().go();
                            CookiesManager.clearAllCookies();
                        } else {
                            getView().showToast(bean.getErrorMsg());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showToast(e.toString());
                    }

                });
    }

}
