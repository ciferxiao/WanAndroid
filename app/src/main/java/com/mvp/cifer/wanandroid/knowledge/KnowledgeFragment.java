package com.mvp.cifer.wanandroid.knowledge;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mvp.cifer.wanandroid.R;
import com.mvp.cifer.wanandroid.adapter.KnowledgeAdapter;
import com.mvp.cifer.wanandroid.adapter.RecycleViewAdapter;
import com.mvp.cifer.wanandroid.basemvp.BaseMVPFragment;
import com.mvp.cifer.wanandroid.basemvp.BasePresenter;
import com.mvp.cifer.wanandroid.basemvp.BaseView;
import com.mvp.cifer.wanandroid.basemvp.OnItemClickListener;
import com.mvp.cifer.wanandroid.knowledge.bean.Knowledgebean;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/7
 * - @time   :  17:49
 * - @desc   :
 */
public class KnowledgeFragment extends BaseMVPFragment<KnowledgeContract.KnowledgeView,KnowledgePresenter> implements
        KnowledgeContract.KnowledgeView ,OnItemClickListener<Knowledgebean.DataBean>{
    @BindView(R.id.recyclelist)
    RecyclerView recyclerView;

    public static KnowledgeFragment newInstance(){
        KnowledgeFragment fragment = new KnowledgeFragment();
        return fragment;
    }

    private KnowledgeAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.knowledge_fragment,container,false);
        if (getActivity()!= null){
            ButterKnife.bind(this,view);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init(){
        adapter =new KnowledgeAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayout.VERTICAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getActivity()),DividerItemDecoration.VERTICAL));

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(this);
        getPresenter().getList();
    }

    @Override
    public void onClick(Knowledgebean.DataBean o, int position) {
        Log.d("xiao111"," position--" + position);
        

    }

    @Override
    public void setListData(List<Knowledgebean.DataBean> listData) {
        if (listData != null){
            Log.d("xiao111"," listdata size =" + listData.size());

            adapter.clear();
            adapter.addAll(listData);
            adapter.notifyDataSetChanged();
        }else{
            recyclerView.setVisibility(View.GONE);
        }

    }

    @Override
    public KnowledgePresenter getPresenter() {
        return super.getPresenter();

    }

    @Override
    protected KnowledgePresenter createPersneter() {
        return new KnowledgePresenter();
    }

    @Override
    protected KnowledgeContract.KnowledgeView createView() {
        return this;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}