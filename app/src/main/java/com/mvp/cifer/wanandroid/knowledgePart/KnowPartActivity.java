package com.mvp.cifer.wanandroid.knowledgePart;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toolbar;

import com.mvp.cifer.wanandroid.R;
import com.mvp.cifer.wanandroid.basemvp.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/8
 * - @time   :  15:46
 * - @desc   :
 */
public class KnowPartActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.tabaaa)
    TabLayout tabaaa;

    private ArrayList<Integer> iddatas =new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();

    private List<PartFragment> fragmentList =new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.k_part_activity);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        titles = intent.getStringArrayListExtra("TITLE");
        iddatas = intent.getIntegerArrayListExtra("ID");

        init();
    }

    private void init(){
        String[] titlestrings = new String[titles.size()];
        titles.toArray(titlestrings);

        MyViewPager adapter = new MyViewPager(getSupportFragmentManager(),titlestrings);

        //有多少个标题就有多少个碎片，动态添加
        for(int i=0;i<iddatas.size();i++){
            PartFragment testFm = new PartFragment().newInstance(iddatas.get(i));//TODO 初始化第一个 优化
            fragmentList.add(testFm);
        }

        for (int i=0;i<titles.size();i++){
            tabaaa.addTab(tabaaa.newTab().setText(titles.get(i)));//添加tab选项
        }
        adapter.addFragment(fragmentList);

        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(this);

        tabaaa.setTabsFromPagerAdapter(adapter);//给Tabs设置适配器
        tabaaa.setupWithViewPager(viewPager);

        tabaaa.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                fragmentList.get(tab.getPosition()).reload(iddatas.get(tab.getPosition()));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        fragmentList.get(i).setUserVisibleHint(true);
        fragmentList.get(i).reload(iddatas.get(i));

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    public static class MyViewPager extends FragmentStatePagerAdapter {
        private  List<PartFragment> mFragmentList = new ArrayList<>();
        private String[] titles ;

        MyViewPager(FragmentManager manager,String[] titles) {
            super(manager);
            this.titles = titles;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        void addFragment(List<PartFragment> fragment) {
            mFragmentList = fragment;
        }
    }
}
