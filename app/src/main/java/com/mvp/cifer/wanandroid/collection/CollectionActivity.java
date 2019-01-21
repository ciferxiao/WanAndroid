package com.mvp.cifer.wanandroid.collection;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mvp.cifer.wanandroid.R;
import com.mvp.cifer.wanandroid.adapter.RecycleViewAdapter;
import com.mvp.cifer.wanandroid.basemvp.BaseActivity;
import com.mvp.cifer.wanandroid.basemvp.HttpConstants;
import com.mvp.cifer.wanandroid.home.bean.HomeBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/14
 * - @time   :  14:22
 * - @desc   :
 */
public class CollectionActivity extends BaseActivity<CollectionContract.CollectionView, CollectionPresneter>
        implements CollectionContract.CollectionView {

    @BindView(R.id.listview)
    RecyclerView recyclerView;

    @BindView(R.id.refresh_view)
    SmartRefreshLayout refreshLayout;

    private CollectionAdapter adapter;

    public static void openActivity(Context context) {
        Intent intent = new Intent(context, CollectionActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collection_act);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        adapter = new CollectionAdapter();
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getPresenter().getListData();
            }
        });
        getPresenter().getListData();
    }

    @Override
    public void setBasicData(List<CollectionBean.DataBean.ArticleBean> houseItems, boolean isRefresh) {
        adapter.clear();
        adapter.addAll(houseItems);
        adapter.notifyDataSetChanged();
        refreshLayout.finishLoadMore();
    }

    @Override
    protected CollectionContract.CollectionView createView() {
        return this;
    }

    @Override
    protected CollectionPresneter createPresenter() {
        return new CollectionPresneter();
    }

    @Override
    public void showToast(String msg) {
        Log.d("xiao111"," msg == " + msg);
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
