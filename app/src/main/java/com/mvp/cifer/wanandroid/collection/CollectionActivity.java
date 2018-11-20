package com.mvp.cifer.wanandroid.collection;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.mvp.cifer.wanandroid.R;
import com.mvp.cifer.wanandroid.basemvp.BaseActivity;

import java.util.Collection;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/14
 * - @time   :  14:22
 * - @desc   :
 */
public class CollectionActivity extends BaseActivity<CollectionContract.CollectionView,CollectionPresneter>
            implements CollectionContract.CollectionView{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.collection_act);
    }

    @Override
    protected CollectionContract.CollectionView createView() {
        return this;
    }

    @Override
    protected CollectionPresneter createPresenter() {
        return new CollectionPresneter();
    }

}
