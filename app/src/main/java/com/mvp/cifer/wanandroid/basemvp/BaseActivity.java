package com.mvp.cifer.wanandroid.basemvp;

import android.bluetooth.BluetoothHealthAppConfiguration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;

/**
 * - @author :  Xiao
 * - @date   :  2018/10/30
 * - @time   :  16:34
 * - @desc   :
 */
public abstract class BaseActivity<V extends BaseView,P extends BasePresenter> extends AppCompatActivity {
    protected abstract V createView();

    protected abstract P createPresenter();

    public P getPresenter() {
        return presenter;
    }

    private P presenter ;
    private V view;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        if(this.presenter == null){
            this.presenter = createPresenter();
        }

        if(this.view == null){
            view = createView();
        }

        if(presenter != null){
            this.presenter.attachView(this.view);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(this.presenter != null && this.view != null ){
            this.presenter.detachView();
        }
    }

}
