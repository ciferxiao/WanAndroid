package com.mvp.cifer.wanandroid.project.ProjectListFragment;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mvp.cifer.wanandroid.R;
import com.mvp.cifer.wanandroid.adapter.ProjectAdapter;
import com.mvp.cifer.wanandroid.project.ProjectBean;
import com.mvp.cifer.wanandroid.utils.AppCallback;
import com.mvp.cifer.wanandroid.utils.CommonUtils;
import com.mvp.cifer.wanandroid.utils.retrofitmanager.RetrofitManager;
import com.mvp.cifer.wanandroid.utils.retrofitmanager.RxSchedulers;

import org.greenrobot.eventbus.util.ErrorDialogManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import previewlibrary.GPreviewBuilder;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/16
 * - @time   :  15:20
 * - @desc   :
 */
public class ProjectListFragment extends Fragment {

    public final static String TAG = "ProjectListFragment";

    @BindView(R.id.xiaolist)
    RecyclerView recyclelist;

    private ProjectAdapter adapter;

    public static ProjectListFragment getNewInstance(int id) {
        ProjectListFragment fragment = new ProjectListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("ID", id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.project_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private int id;

    private void initView() {
        Bundle bundle = getArguments();
        assert bundle != null;
        id = bundle.getInt("ID");
        adapter = new ProjectAdapter();

        adapter.onItemClickListener(new ProjectAdapter.OnTitleClickListener() {
            @Override
            public void onItemClick(ProjectListBean.DataBean.DataBeans object, int position,View itemView) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), itemView, "share_view");
                CommonUtils.startArticleDetailActivity(getActivity(),options,0,"",
                        object.getLink(),false,false,false);
            }

            @Override
            public void onLookBigPic(ImageView view, String url) {
                GPreviewBuilder.from(getActivity())
                        .setSingleData(computeBoundsBackward(view, url))
                        .setCurrentIndex(0)
                        .setSingleFling(true)
                        .setType(GPreviewBuilder.IndicatorType.Number)
                        .start();
            }
        });

        recyclelist.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayout.VERTICAL, false));
        recyclelist.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getActivity()), DividerItemDecoration.VERTICAL));

        reload(id);
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
            initData(id, appCallback);
    }

    @SuppressLint("CheckResult")
    private void initData(int id, AppCallback<ProjectListBean> callback) {
        RetrofitManager.getInstance().getRequestService(false)
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

    private void setBeanData(ProjectListBean projectListBean) {
        if (projectListBean != null) {
            ArrayList<ProjectListBean.DataBean.DataBeans> list = projectListBean.getData().getDatas();
            adapter.clear();
            adapter.addAll(list);
            adapter.notifyDataSetChanged();
        }
    }

    private ProjectListBean.DataBean.DataBeans computeBoundsBackward(ImageView iv_img, String picUrl) {
        ProjectListBean.DataBean.DataBeans dataBeans = new ProjectListBean.DataBean.DataBeans();
        dataBeans.setEnvelopePic(picUrl);
        Rect bounds = new Rect();
        if (iv_img != null) {
            iv_img.getGlobalVisibleRect(bounds);
        }
        dataBeans.setmBounds(bounds);
        return dataBeans;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
