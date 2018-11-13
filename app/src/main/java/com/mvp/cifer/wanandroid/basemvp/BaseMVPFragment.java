package com.mvp.cifer.wanandroid.basemvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.mvp.cifer.wanandroid.R;

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

    private LottieAnimationView mLoadingAnimation;
    private View mErrorView;
    private View mLoadingView;
    private ViewGroup mNormalView;

    private FragmentActivity activity;

    private static final int NORMAL_STATE = 0;
    private static final int LOADING_STATE = 1;
    public static final int ERROR_STATE = 2;

    private int currentState = NORMAL_STATE;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(getActivity() != null){
            activity = getActivity();
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

        initView();
    }


    public P getPresenter(){return presenter;}

    protected abstract P createPersneter();

    protected abstract V createView();

    protected abstract void reload();

    public void initView(){
        if (getView() == null) {
            return;
        }

        mNormalView = getView().findViewById(R.id.normal_view);

        ViewGroup parent = (ViewGroup) mNormalView.getParent();
        View.inflate(activity, R.layout.loading_view, parent);
        View.inflate(activity, R.layout.error_view, parent);
        mLoadingView = parent.findViewById(R.id.loading_group);
        mErrorView = parent.findViewById(R.id.error_group);
        TextView reloadTv = mErrorView.findViewById(R.id.error_reload_tv);
        reloadTv.setOnClickListener(v -> reload());
        mLoadingAnimation = mLoadingView.findViewById(R.id.loading_animation);
        Log.d("xiao333"," gone ==============");
        mErrorView.setVisibility(View.GONE);
        mLoadingView.setVisibility(View.GONE);
        mNormalView.setVisibility(View.VISIBLE);

    }

    public void showLoading() {
        if (currentState == LOADING_STATE || mLoadingView == null) {
            return;
        }
        hideCurrentView();
        currentState = LOADING_STATE;
        mLoadingView.setVisibility(View.VISIBLE);
        mLoadingAnimation.setAnimation("loading_bus.json");
        mLoadingAnimation.loop(true);
        mLoadingAnimation.playAnimation();
    }

    public void showNormal() {
        if (currentState == NORMAL_STATE) {
            return;
        }
        hideCurrentView();
        currentState = NORMAL_STATE;
        mNormalView.setVisibility(View.VISIBLE);
    }

    private void hideCurrentView() {
        switch (currentState) {
            case NORMAL_STATE:
                if (mNormalView == null) {
                    return;
                }
                mNormalView.setVisibility(View.INVISIBLE);
                break;
            case LOADING_STATE:
                mLoadingAnimation.cancelAnimation();
                mLoadingView.setVisibility(View.GONE);
                break;
            case ERROR_STATE:
                mErrorView.setVisibility(View.GONE);
            default:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (mLoadingAnimation != null) {
            mLoadingAnimation.cancelAnimation();
        }

        if (this.presenter != null && this.view != null) {
            this.presenter.detachView();
        }
    }


}
