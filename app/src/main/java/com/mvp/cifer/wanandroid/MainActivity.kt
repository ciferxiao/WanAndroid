package com.mvp.cifer.wanandroid

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.widget.RadioButton
import butterknife.BindView
import butterknife.OnClick
import com.mvp.cifer.wanandroid.home.HomeFragment
import java.util.*

/**
 * - @author :  Xiao
 * - @date   :  2018/11/1
 * - @time   :  10:05
 * - @desc   :
 */

class MainActivity : FragmentActivity() ,ViewPager.OnPageChangeListener{
    override fun onPageScrollStateChanged(p0: Int) {
    }

    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
    }

    override fun onPageSelected(p0: Int) {
        setTab(p0)
    }

    @BindView(R.id.view_pager)
    internal var viewPager: ViewPager? = null

    @BindView(R.id.tab01)
    internal var tab01: RadioButton? = null

    @BindView(R.id.tab05)
    internal var tab05: RadioButton? = null

    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init();
    }

    fun init(){
        var adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(HomeFragment.newInstance())
        //adapter.addFragment()
        viewPager?.adapter = adapter
        viewPager?.offscreenPageLimit = 2
        viewPager?.addOnPageChangeListener(this)
    }

    fun setTab(position: Int){
        when(position){
            0 ->{
                tab01?.isChecked = true
            }

            1 ->{
                tab05?.isChecked = true
            }

        }

    }

    @OnClick(R.id.tab01,R.id.tab05)
    fun onClick(radioButton: RadioButton) {
        when(radioButton){
            tab01->{

            }
            tab05->{

            }
        }
    }

    class ViewPagerAdapter internal constructor(manager: FragmentManager) : FragmentStatePagerAdapter(manager) {

        private val mFragmentList = ArrayList<Fragment>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        internal fun addFragment(fragment: Fragment) {
            mFragmentList.add(fragment)
        }
    }

}