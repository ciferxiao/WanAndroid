package com.mvp.cifer.wanandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;

import com.mvp.cifer.wanandroid.home.HomeFragment;
import com.mvp.cifer.wanandroid.knowledge.KnowledgeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/2
 * - @time   :  11:13
 * - @desc   :
 */
public class MainAgent extends FragmentActivity implements ViewPager.OnPageChangeListener{

    @BindView(R.id.view_pagers)
    ViewPager viewPager;

    @BindView(R.id.tab01)
    RadioButton tab01;

    @BindView(R.id.tab05)
    RadioButton tab05;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(HomeFragment.newInstance());
        adapter.addFragment(KnowledgeFragment.newInstance());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        setTab(i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    private void setTab(int postion){
        switch (postion){
            case 0:
                tab01.setChecked(true);
                break;
            case 1:
                tab05.setChecked(true);
                break;
        }
    }

    private static class ViewpagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();

        ViewpagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public Fragment getItem(int i) {
            return (Fragment) mFragmentList.get(i);
        }

        private void addFragment(Fragment fragment){
            mFragmentList.add(fragment);

        }
    }

    public static class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();

        ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        void addFragment(Fragment fragment) {
            mFragmentList.add(fragment);
        }
    }
}
