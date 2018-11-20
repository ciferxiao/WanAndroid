package com.mvp.cifer.wanandroid.project.ProjectListFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mvp.cifer.wanandroid.R;
import com.mvp.cifer.wanandroid.adapter.ProjectAdapter;
import com.mvp.cifer.wanandroid.utils.AppCallback;
import com.mvp.cifer.wanandroid.utils.retrofitmanager.RetrofitManager;
import com.mvp.cifer.wanandroid.utils.retrofitmanager.RxSchedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/16
 * - @time   :  15:20
 * - @desc   :
 */
public class ProjectListFragment extends Fragment{
    private static int initialid;

    private final static String TAG = "ProjectListFragment";

    @BindView(R.id.xiaolist)
    RecyclerView recyclelist;

    private ProjectAdapter adapter;


    public static ProjectListFragment getNewInstance(int id){
        initialid = id;
        Log.d(TAG," ]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]");
        return new ProjectListFragment();
    }

    public static ProjectListFragment aaaa(int id){
        Bundle bundle = new Bundle();
        bundle.putInt("id",id);
        ProjectListFragment fragment = new ProjectListFragment();
        fragment.setArguments(bundle);
        initialid = id;
        return  fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.project_list,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG," onActivityCreated ------------------");
        initView();
    }

    private void initView(){

        adapter = new ProjectAdapter();
        Log.d(TAG," initView ------------------");

        recyclelist.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayout.VERTICAL, false));
        recyclelist.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getActivity()), DividerItemDecoration.VERTICAL));

        reload(294);
        recyclelist.setAdapter(adapter);
    }

    private AppCallback<ProjectListBean> appCallback = new AppCallback<ProjectListBean>() {
        @Override
        public void Success(ProjectListBean projectListBean) {
            setBeanData(projectListBean);
        }

        @Override
        public void Error(String error) {
            Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
        }
    };

    public void reload(int id) {
        if (getUserVisibleHint()) {
            initData(id,appCallback);
        }
    }

    @SuppressLint("CheckResult")
    private void initData(int id ,AppCallback<ProjectListBean> callback){
        RetrofitManager.getInstance().getRequestService()
                .getProjectList(id)
                .compose(RxSchedulers.io_main())
                .subscribeWith(new Observer<ProjectListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ProjectListBean projectListBean) {
                        callback.Success(projectListBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    ProjectListBean.DataBean dataBean;

    private void setBeanData(ProjectListBean projectListBean){
        if(projectListBean != null){
            dataBean = projectListBean.getData();
            ArrayList<ProjectListBean.DataBean.DataBeans> list = dataBean.getDatas();
            Log.d(TAG," setBeanData ======================");
            adapter.clear();
            adapter.addAll(list);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG," onDestroyView ------------------");
        super.onDestroyView();
    }

}
