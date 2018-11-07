package com.mvp.cifer.wanandroid.Login;

import android.content.Context;

import com.mvp.cifer.wanandroid.basemvp.BaseView;

/**
 * - @author :  Xiao
 * - @date   :  2018/10/30
 * - @time   :  17:12
 * - @desc   :
 */
public class LoginContract  {
    interface LoginView extends BaseView {
        void go();

        void showToast(String msg);

        Context getContext();
    }

    interface LoginPresenterInterface {
        void getLogin(String name,String passwd);

    }
}
