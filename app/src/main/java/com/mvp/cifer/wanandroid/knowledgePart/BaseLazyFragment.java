package com.mvp.cifer.wanandroid.knowledgePart;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.mvp.cifer.wanandroid.basemvp.BaseMVPFragment;
import com.mvp.cifer.wanandroid.basemvp.BasePresenter;
import com.mvp.cifer.wanandroid.basemvp.BaseView;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/9
 * - @time   :  15:23
 * - @desc   :
 */
public abstract class BaseLazyFragment extends BaseMVPFragment<KnowPartContract.KnowPartView,KnowPartPresenter>{
    protected boolean isViewCreated;//视图是否已经创建
    private boolean isUiVisible;//该fragment是否对用户可见

    @Override
    public KnowPartPresenter getPresenter() {
        return super.getPresenter();
    }

    @Override
    protected KnowPartPresenter createPersneter() {
        return new KnowPartPresenter();
    }

    @Override
    protected KnowPartContract.KnowPartView createView() {
        return new PartFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
        lazyLoad();

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            isUiVisible = true;
            lazyLoad();
        }else{
            isUiVisible = false;
        }

    }

    private void lazyLoad(){
        if (isViewCreated && isUiVisible) {
            lazyLoadData();
            //数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false;
            isUiVisible = false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isViewCreated = false;
        isUiVisible = false;
    }

    protected abstract void lazyLoadData();

}
