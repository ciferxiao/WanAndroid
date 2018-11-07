package com.mvp.cifer.wanandroid.basemvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/1
 * - @time   :  10:41
 * - @desc   :
 */
public abstract class BaseMVPFragment<V extends BaseView,P extends BasePresenter<V>> extends Fragment {
    private P presenter;
    private V view;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(getActivity() != null){
            ButterKnife.bind(getActivity());
        }

        if(presenter == null){
            this.presenter = createPersneter();
        }

        if(view == null){
            this.view = createView();
        }

        if(presenter != null){
            this.presenter.attachView(view);
        }
    }

    public P getPresenter(){return presenter;}

    protected abstract P createPersneter();

    protected abstract V createView();

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (this.presenter != null && this.view != null) {
            this.presenter.detachView();
        }
    }


}
