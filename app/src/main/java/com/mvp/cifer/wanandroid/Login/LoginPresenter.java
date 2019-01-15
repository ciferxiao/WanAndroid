package com.mvp.cifer.wanandroid.Login;

import android.widget.Toast;

import com.mvp.cifer.wanandroid.Login.retrofit.LoginBean;
import com.mvp.cifer.wanandroid.basemvp.BaseBean;
import com.mvp.cifer.wanandroid.basemvp.BasePresenter;
import com.mvp.cifer.wanandroid.utils.AppCallback;
import com.mvp.cifer.wanandroid.utils.cookies.CookiesManager;

/**
 * - @author :  Xiao
 * - @date   :  2018/10/30
 * - @time   :  17:12
 * - @desc   :
 */
public class LoginPresenter extends BasePresenter<LoginContract.LoginView> implements LoginContract.LoginPresenterInterface {
    private LoginModel model;

    public LoginPresenter() {
        model = new LoginModel();
    }


    public void getLogin(String name, String passwd) {
        model.LoginHttp(getView().getContext(), name, passwd, new AppCallback<BaseBean>() {
            @Override
            public void Success(BaseBean bean) {
                if (bean.getErrorCode() == 0) {
                    getView().go();
                    CookiesManager.clearAllCookies();
                } else {
                    getView().showToast(bean.getErrorMsg());
                }
            }

            @Override
            public void Error(String error) {
                getView().showToast(error);
            }
        });

    }
}
