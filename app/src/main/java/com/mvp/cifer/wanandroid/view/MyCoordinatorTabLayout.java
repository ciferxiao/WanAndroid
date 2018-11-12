package com.mvp.cifer.wanandroid.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import cn.hugeterry.coordinatortablayout.CoordinatorTabLayout;
import cn.hugeterry.coordinatortablayout.listener.LoadHeaderImagesListener;
import cn.hugeterry.coordinatortablayout.utils.SystemView;

import static android.support.design.widget.TabLayout.MODE_FIXED;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/8
 * - @time   :  17:27
 * - @desc   :
 */
public class MyCoordinatorTabLayout extends CoordinatorLayout {

    private int[] mImageArray, mColorArray;

    private Context mContext;
    private Toolbar mToolbar;
    private ActionBar mActionbar;
    private TabLayout mTabLayout;
    private ImageView mImageView;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private LoadHeaderImagesListener mLoadHeaderImagesListener;

    public MyCoordinatorTabLayout(Context context) {
        super(context);
        mContext = context;
    }

    public MyCoordinatorTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        if (!isInEditMode()) {
            initView(context);
            initWidget(context, attrs);
        }
    }

    public MyCoordinatorTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        if (!isInEditMode()) {
            initView(context);
            initWidget(context, attrs);
        }
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(cn.hugeterry.coordinatortablayout.R.layout.view_coordinatortablayout, this, true);
        initToolbar();
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(cn.hugeterry.coordinatortablayout.R.id.collapsingtoolbarlayout);
        mTabLayout = (TabLayout) findViewById(cn.hugeterry.coordinatortablayout.R.id.tabLayout);
        mImageView = (ImageView) findViewById(cn.hugeterry.coordinatortablayout.R.id.imageview);
    }

    private void initWidget(Context context, AttributeSet attrs) {
        @SuppressLint("CustomViewStyleable") TypedArray typedArray = context.obtainStyledAttributes(attrs
                , cn.hugeterry.coordinatortablayout.R.styleable.CoordinatorTabLayout);

        TypedValue typedValue = new TypedValue();
        mContext.getTheme().resolveAttribute(cn.hugeterry.coordinatortablayout.R.attr.colorPrimary, typedValue, true);
        int contentScrimColor = typedArray.getColor(
                cn.hugeterry.coordinatortablayout.R.styleable.CoordinatorTabLayout_contentScrim, typedValue.data);
        mCollapsingToolbarLayout.setContentScrimColor(contentScrimColor);

        int tabIndicatorColor = typedArray.getColor(cn.hugeterry.coordinatortablayout.R.styleable.CoordinatorTabLayout_tabIndicatorColor, Color.WHITE);
        mTabLayout.setSelectedTabIndicatorColor(tabIndicatorColor);

        int tabTextColor = typedArray.getColor(cn.hugeterry.coordinatortablayout.R.styleable.CoordinatorTabLayout_tabTextColor, Color.WHITE);
        mTabLayout.setTabTextColors(ColorStateList.valueOf(tabTextColor));
        typedArray.recycle();
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(cn.hugeterry.coordinatortablayout.R.id.toolbar);
        ((AppCompatActivity) mContext).setSupportActionBar(mToolbar);
        mActionbar = ((AppCompatActivity) mContext).getSupportActionBar();
    }

    /**
     * 设置Toolbar标题
     *
     * @param title 标题
     * @return
     */
    public MyCoordinatorTabLayout setTitle(String title) {
        if (mActionbar != null) {
            mActionbar.setTitle(title);
        }
        return this;
    }

    /**
     * 设置Toolbar显示返回按钮及标题
     *
     * @param canBack 是否返回
     * @return
     */
    public MyCoordinatorTabLayout setBackEnable(Boolean canBack) {
        if (canBack && mActionbar != null) {
            mActionbar.setDisplayHomeAsUpEnabled(true);
            mActionbar.setHomeAsUpIndicator(cn.hugeterry.coordinatortablayout.R.drawable.ic_arrow_white_24dp);
        }
        return this;
    }

    /**
     * 设置每个tab对应的头部图片
     *
     * @param imageArray 图片数组
     * @return
     */
    public MyCoordinatorTabLayout setImageArray(@NonNull int[] imageArray) {
        mImageArray = imageArray;
        setupTabLayout();
        return this;
    }

    /**
     * 设置每个tab对应的头部照片和ContentScrimColor
     *
     * @param imageArray 图片数组
     * @param colorArray ContentScrimColor数组
     * @return
     */
    public MyCoordinatorTabLayout setImageArray(@NonNull int[] imageArray, @NonNull int[] colorArray) {
        mImageArray = imageArray;
        mColorArray = colorArray;
        setupTabLayout();
        return this;
    }

    /**
     * 设置每个tab对应的ContentScrimColor
     *
     * @param colorArray 图片数组
     * @return
     */
    public MyCoordinatorTabLayout setContentScrimColorArray(@NonNull int[] colorArray) {
        mColorArray = colorArray;
        return this;
    }

    private void setupTabLayout() {
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mImageView.startAnimation(AnimationUtils.loadAnimation(mContext, cn.hugeterry.coordinatortablayout.R.anim.anim_dismiss));
                if (mLoadHeaderImagesListener == null) {
                    if (mImageArray != null) {
                        mImageView.setImageResource(mImageArray[tab.getPosition()]);
                    }
                } else {
                    mLoadHeaderImagesListener.loadHeaderImages(mImageView, tab);
                }
                if (mColorArray != null) {
                    mCollapsingToolbarLayout.setContentScrimColor(
                            ContextCompat.getColor(
                                    mContext, mColorArray[tab.getPosition()]));
                }
                mImageView.setAnimation(AnimationUtils.loadAnimation(mContext, cn.hugeterry.coordinatortablayout.R.anim.anim_show));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    /**
     * 设置与该组件搭配的ViewPager
     *
     * @param viewPager 与TabLayout结合的ViewPager
     * @return
     */
    public MyCoordinatorTabLayout setupWithViewPager(ViewPager viewPager) {
        mTabLayout.setupWithViewPager(viewPager);
        return this;
    }

    /**
     * 获取该组件中的ActionBar
     */
    public ActionBar getActionBar() {
        return mActionbar;
    }

    /**
     * 获取该组件中的TabLayout
     */
    public TabLayout getTabLayout() {
        return mTabLayout;
    }

    /**
     * 获取该组件中的ImageView
     */
    public ImageView getImageView() {
        return mImageView;
    }

    /**
     * 设置LoadHeaderImagesListener
     *
     * @param loadHeaderImagesListener 设置LoadHeaderImagesListener
     * @return
     */
    public MyCoordinatorTabLayout setLoadHeaderImagesListener(LoadHeaderImagesListener loadHeaderImagesListener) {
        mLoadHeaderImagesListener = loadHeaderImagesListener;
        setupTabLayout();
        return this;
    }

    /**
     * 设置透明状态栏
     *
     * @param activity
     * @return
     */
    public MyCoordinatorTabLayout setTransulcentStatusBar(@NonNull Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return this;
        } else {
            mToolbar.setPadding(0, SystemView.getStatusBarHeight(activity)/2, 0, 0);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        return this;
    }
//---------------------------------------xiao add -------------------------------------
    /**
     * @param tabMode  tab的模式
     * @param tabGravity tabGravity的模式
     *
     * 设置tab的模式,tab有两种模式:
     *      MODE_FIXED  表示宽度始终是tablayout控件指定的宽度，如果标签过多，那么就无限挤压控件
     *      MODE_SCROLLABLE  表示每个标签都保持自身宽度，一旦标签过多，给标题栏提供支持横向滑动的功能
     *
     *      如果为 MODE_FIXED ,可以设置tabGravity.
     *              TabGravity有两个可选参数：
     *              GRAVITY_FILL    让每个标签平分TabLayout的全部宽度
     *              GRAVITY_CENTER  让每个标签显示自身宽度，然后所有标签居中显示
     *
     */
    public MyCoordinatorTabLayout setTabMode(int tabMode,int tabGravity) {
        mTabLayout.setTabMode(tabMode);
        if(tabMode == MODE_FIXED) {
            mTabLayout.setTabGravity(tabGravity);
        }
        return this;
    }

    /**
     * 设置指示器的高度
     *
     * @param height
     * @return
     */
    public MyCoordinatorTabLayout setTabIndicatorHeight(int height) {
        mTabLayout.setSelectedTabIndicatorHeight(height);
        return this;
    }

    /**
     * 设置tab的颜色,选中时的颜色,下划线(指示器)颜色
     * (参数color的格式为 0xFFFF0000)
     *
     * @param color          tab未选中时颜色
     * @param selectedColor  tab选中时颜色
     * @param indicatorColor 指示器颜色
     * @return
     */
    public MyCoordinatorTabLayout setTabColor(int color, int selectedColor, int indicatorColor) {
        mTabLayout.setTabTextColors(color, selectedColor);
        mTabLayout.setSelectedTabIndicatorColor(indicatorColor);
        return this;
    }
}
