package com.mvp.cifer.wanandroid.knowledgePart;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.mvp.cifer.wanandroid.basemvp.BaseActivity;

import butterknife.ButterKnife;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/8
 * - @time   :  15:46
 * - @desc   :
 */
public class KnowPartActivity extends BaseActivity<KnowPartContract.KnowPartView,KnowPartPresenter> implements KnowPartContract.KnowPartView {

    @Override
    protected KnowPartContract.KnowPartView createView() {
        return this;
    }

    @Override
    protected KnowPartPresenter createPresenter() {
        return new KnowPartPresenter();
    }

    @Override
    public KnowPartPresenter getPresenter() {
        return super.getPresenter();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
