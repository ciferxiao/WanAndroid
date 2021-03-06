package com.mvp.cifer.wanandroid.home;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import com.mvp.cifer.wanandroid.R;
import com.mvp.cifer.wanandroid.adapter.RecycleViewAdapter;
import com.mvp.cifer.wanandroid.basemvp.BaseMVPFragment;
import com.mvp.cifer.wanandroid.basemvp.HttpConstants;
import com.mvp.cifer.wanandroid.home.bean.HomeBean;
import com.mvp.cifer.wanandroid.utils.BusMessageEvent;
import com.mvp.cifer.wanandroid.utils.CommonUtils;
import com.mvp.cifer.wanandroid.utils.GlideImageLoader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/1
 * - @time   :  10:39
 * - @desc   :
 */
public class HomeFragment extends BaseMVPFragment<HomeContract.IHomeView, HomePresenter> implements HomeContract.IHomeView
        , OnBannerListener, RecycleViewAdapter.OnItemClickListener<HomeBean.DataBean.ArticleBean> {

    @BindView(R.id.banner)
    Banner banner;

    @BindView(R.id.normal_view)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.recyclelist)
    RecyclerView recyclerView;

    private int pagenumber = HttpConstants.COMMON_LIST_PAGE_FIRST_NO;
    private RecycleViewAdapter adapter;

    private List<String> title = new ArrayList();
    private List<String> url = new ArrayList();

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public HomePresenter getPresenter() {
        return super.getPresenter();
    }

    @Override
    protected HomePresenter createPersneter() {
        return new HomePresenter();
    }

    @Override
    protected HomeContract.IHomeView createView() {
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        View mRootView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, mRootView);//绑定framgent
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void initView() {
        super.initView();

        adapter = new RecycleViewAdapter();
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(this);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pagenumber = 0;
                getPresenter().getListData(pagenumber, true);
                refreshLayout.finishRefresh(1000);
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getPresenter().getListData(pagenumber, false);
            }
        });

        getPresenter().getData();

        refresh();

        if (CommonUtils.isNetworkConnected()) {
            showLoading();
        }
    }

    private void refresh() {
        pagenumber = 0;

        getPresenter().getListData(pagenumber, true);
    }

    @Override
    public void setBasicData(List<HomeBean.DataBean.ArticleBean> houseItems, boolean isRefresh) {
        if (isRefresh) {
            Log.d("xiao111"," aaaaaaaaaaaaaaaaaaaaaaa");
            pagenumber = HttpConstants.COMMON_LIST_PAGE_FIRST_NO;
            adapter.clear();
        }

        adapter.addAll(houseItems);
        adapter.notifyDataSetChanged();
        pagenumber++;
        refreshLayout.finishLoadMore();
        banner.setVisibility(View.VISIBLE);
        showNormal();
    }

    @Override
    public void setBannerData(@Nullable List<String> images, @Nullable List<String> titles, @Nullable List<String> url) {
        title = titles;
        this.url = url;

        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerTitles(titles);

        banner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

        banner.setOnBannerListener(this);
        showNormal();
    }

    @Override
    public void OnBannerClick(int position) {
        CommonUtils.startArticleDetailActivity(getActivity(), null, 0, title.get(position),
                url.get(position), false, false, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loadData(BusMessageEvent event) {
        if (event.actionType != null) {
            if (event.actionType.equals(HomeFragment.class.getName())) {
                if ("refresh".equals(event.actionKey)) {
                    getPresenter().getData();
                }
            }
        }
    }

    @Override
    protected void reload() {
        getPresenter().getData();
        refresh();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onItemClick(HomeBean.DataBean.ArticleBean articleBean, int position, View view) {
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), view, "share_view");
        CommonUtils.startArticleDetailActivity(getActivity(), options, 0, title.get(position),
                articleBean.getLink(), false, false, false);
    }

    @Override
    public void onCheckBoxClick(HomeBean.DataBean.ArticleBean articleBean, int position, boolean isChecked) {
        Log.d("xiao111"," articlebean == " + articleBean.getId());
        if(isChecked){
            getPresenter().onDisLikeData(articleBean.getId(),position,true);
        }else {
            getPresenter().onLikeData(articleBean.getId(), position, false);
        }

    }

    @Override
    public void setLikeCount(int position, boolean isChecked) {
        adapter.setLikeCount(position, isChecked);
    }
}
