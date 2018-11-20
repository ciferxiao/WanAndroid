package com.mvp.cifer.wanandroid.project;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.mvp.cifer.wanandroid.MainAgent;
import com.mvp.cifer.wanandroid.R;
import com.mvp.cifer.wanandroid.adapter.TabListAdapter;
import com.mvp.cifer.wanandroid.basemvp.BaseActivity;
import com.mvp.cifer.wanandroid.basemvp.BaseMVPFragment;
import com.mvp.cifer.wanandroid.project.ProjectListFragment.ProjectListFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/14
 * - @time   :  14:22
 * - @desc   :
 */
public class ProjectFragment extends BaseMVPFragment<ProjectContract.ProjectView,ProjectPresneter>
            implements ProjectContract.ProjectView,ViewPager.OnPageChangeListener{

    public static ProjectFragment newInstance(){
        return new ProjectFragment();
    }

    @BindView(R.id.project_viewpager)
    ViewPager viewPager;

    @BindView(R.id.tabLayout)
    SlidingTabLayout slidingTabLayout;

    @BindView(R.id.project_divider)
    View dividerline;

    @BindView(R.id.recyclelist)

    RecyclerView recyclelist;
    private TabListAdapter listadapter;

    private ArrayList<ProjectListFragment> fragmentList = new ArrayList<>();
    ViewpagerAdapter adapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.project_act,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void initView() {
//        super.initView();
        getPresenter().getProjectPart();

        recyclelist.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayout.HORIZONTAL,false));
        recyclelist.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getActivity()), DividerItemDecoration.VERTICAL));

        int focusSpacing = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, recyclelist.getResources().getDisplayMetrics());
        recyclelist.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                int position = parent.getChildAdapterPosition(view);
                outRect.left = position == 0 ? focusSpacing : 0;
                outRect.right = focusSpacing;
            }
        });

        listadapter = new TabListAdapter();

        listadapter.setOnTitleClickListener(new TabListAdapter.OnTitleClickListener() {
            @Override
            public void onItemClick(ProjectBean.DataBean object, int position) {
                listadapter.setCurrentPosition(position);
                listadapter.notifyDataSetChanged();
                //TODO - -- - -- - -- -
                Log.d("xiao111"," item position = " + position);
                viewPager.setCurrentItem(position);
                fragmentList.get(position).reload(ids.get(position));
            }
        });
        recyclelist.setAdapter(listadapter);
        recyclelist.setNestedScrollingEnabled(false);
    }

    private List<Integer> ids;
    @Override
    public void setBasicTab(List<ProjectBean.DataBean> projectBeans) {
        ArrayList<String> titles = new ArrayList<>();
        ids = new ArrayList<>();
        for(ProjectBean.DataBean bean: projectBeans){
            ids.add(bean.getId());
            titles.add(bean.getName());

            ProjectListFragment fragment =  ProjectListFragment.aaaa(ids.get(0));
            fragmentList.add(fragment);
        }

        String[] titlestrings = new String[titles.size()];
        titles.toArray(titlestrings);

        if (adapter == null){
            adapter = new ViewpagerAdapter(this.getChildFragmentManager(),titlestrings,fragmentList);
            //adapter.addFramgent(fragmentList);
            Log.d("ProjectFragment"," adapter +++++++++++");
        }

        viewPager.setAdapter(adapter);

        listadapter.clear();
        listadapter.addAll(projectBeans);
        listadapter.notifyDataSetChanged();

        //slidingTabLayout.setViewPager(viewPager);
    }


    @Override
    protected ProjectPresneter createPersneter() {
        return new ProjectPresneter();
    }

    @Override
    protected ProjectContract.ProjectView createView() {
        return this;
    }

    @Override
    public ProjectPresneter getPresenter() {
        return super.getPresenter();
    }

    @Override
    protected void reload() {

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    public static class ViewpagerAdapter extends FragmentStatePagerAdapter{
        private ArrayList<ProjectListFragment> fragments = new ArrayList<>();
        private String[] titles;

        ViewpagerAdapter(FragmentManager fm,String[] titles,ArrayList<ProjectListFragment> fragment) {
            super(fm);
            this.titles = titles;
            fragments = fragment;
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return super.isViewFromObject(view, object);
        }

        /*     void addFramgent(ArrayList<ProjectListFragment> fragment){
            fragments = fragment;
        }*/
    }

}
