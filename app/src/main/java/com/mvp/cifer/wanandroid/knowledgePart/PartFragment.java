package com.mvp.cifer.wanandroid.knowledgePart;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mvp.cifer.wanandroid.MyWebViewActivity;
import com.mvp.cifer.wanandroid.R;
import com.mvp.cifer.wanandroid.adapter.PartAdapter;
import com.mvp.cifer.wanandroid.basemvp.BaseMVPFragment;
import com.mvp.cifer.wanandroid.basemvp.BasePresenter;
import com.mvp.cifer.wanandroid.basemvp.BaseView;
import com.mvp.cifer.wanandroid.basemvp.HttpConstants;
import com.mvp.cifer.wanandroid.basemvp.OnItemClickListener;
import com.mvp.cifer.wanandroid.utils.CommonUtils;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/8
 * - @time   :  17:02
 * - @desc   :
 */
public class PartFragment extends BaseMVPFragment<KnowPartContract.KnowPartView, KnowPartPresenter> implements KnowPartContract.KnowPartView {
    private PartAdapter partAdapter;
    private static int ID;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    protected KnowPartPresenter createPersneter() {
        return new KnowPartPresenter();
    }

    @Override
    protected PartFragment createView() {
        return this;
    }

    @Override
    public KnowPartPresenter getPresenter() {
        return super.getPresenter();
    }

    protected void lazyLoadData(int id) {
        Log.d("xiao111", " 222222222222222222222");
        getPresenter().getListData(id);
    }

    @BindView(R.id.recyclelist)
    RecyclerView recyclelist;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.part_fragment, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void initView() {
        super.initView();

        partAdapter = new PartAdapter();

        recyclelist.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayout.VERTICAL, false));
        recyclelist.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getActivity()), DividerItemDecoration.VERTICAL));

        recyclelist.setAdapter(partAdapter);

        partAdapter.setOnItemClickListener(new OnItemClickListener<PartBean.DataBean.DataBeans>() {
            @Override
            public void onClick(PartBean.DataBean.DataBeans dataBeans, int position) {
                Intent intent = new Intent(getActivity(), MyWebViewActivity.class);
                intent.putExtra("url", dataBeans.getLink());
                startActivity(intent);
            }
        });

        reload(ID);

        if (CommonUtils.isNetworkConnected()) {
            showLoading();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    public void reload(int id) {
        if (getUserVisibleHint()) {
            lazyLoadData(id);
        }
    }

    public static PartFragment newInstance(int flag) {
        Bundle bundle = new Bundle();
        bundle.putInt("flag", flag);
        PartFragment partFragment = new PartFragment();
        partFragment.setArguments(bundle);
        ID = flag;
        return partFragment;
    }

    @Override
    public void setListData(PartBean.DataBean dataBeans) {
        Log.d("xiao111", " 33333333333333333333333333333");
        List<PartBean.DataBean.DataBeans> list = dataBeans.getDatas();
        partAdapter.addAll(list);
        partAdapter.notifyDataSetChanged();

        showNormal();
    }

    @Override
    public void loading() {

    }

    @Override
    protected void reload() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
