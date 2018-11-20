package com.mvp.cifer.wanandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.mvp.cifer.wanandroid.home.HomeFragment;
import com.mvp.cifer.wanandroid.knowledge.KnowledgeFragment;
import com.mvp.cifer.wanandroid.project.ProjectFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/2
 * - @time   :  11:13
 * - @desc   :
 */
public class MainAgent extends FragmentActivity implements ViewPager.OnPageChangeListener,NavigationView.OnNavigationItemSelectedListener{

    @BindView(R.id.view_pagers)
    ViewPager viewPager;

    @BindView(R.id.tab01)
    RadioButton tab01;

    @BindView(R.id.tab02)
    RadioButton tab02;

    @BindView(R.id.tab03)
    RadioButton tab03;

    @BindView(R.id.nv_menu)
    NavigationView nv_menu;

    @BindView(R.id.dl_content)
    DrawerLayout dlContent;

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
        adapter.addFragment(ProjectFragment.newInstance());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
        viewPager.setOffscreenPageLimit(3);
        nv_menu.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Log.d("xiao111"," item onclick ");
        switch (menuItem.getItemId()){
            case R.id.nav_item_setting:

                break;
            case R.id.nav_item_about_us:
                dlContent.closeDrawers();
                String url = "http://wanandroid.com/index";
                Intent intent = new Intent(this,MyWebViewActivity.class);
                intent.putExtra("url",url);
                startActivity(intent);
                return true;
            case R.id.nav_item_logout:
                return true;
        }
        return true;
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

    /**
     * 性别选中事件
     * @param
     * @param **/
    @OnCheckedChanged({R.id.tab01, R.id.tab02 ,R.id.tab03})
    public void onRadioCheck(CompoundButton view, boolean ischanged) {
        switch (view.getId()) {
            case R.id.tab01:
                if (ischanged) {
                    viewPager.setCurrentItem(0);
                    setTab(0);
                }
                break;
            case R.id.tab02:
                if (ischanged) {
                    viewPager.setCurrentItem(1);
                    setTab(1);
                }
                break;
            case R.id.tab03:
                if (ischanged) {
                    viewPager.setCurrentItem(2);
                    setTab(2);
                }
                break;
            default:
                break;
        }
    }

    private void setTab(int postion){
        switch (postion){
            case 0:
                tab01.setChecked(true);
                break;
            case 1:
                tab02.setChecked(true);
                break;
            case 2:
                tab03.setChecked(true);
                break;
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
