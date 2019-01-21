package com.mvp.cifer.wanandroid.home;

import com.mvp.cifer.wanandroid.basemvp.BaseView;
import com.mvp.cifer.wanandroid.home.bean.ArticleBean;
import com.mvp.cifer.wanandroid.home.bean.HomeBean;

import java.util.List;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/1
 * - @time   :  10:49
 * - @desc   :
 */
public class HomeContract  {

    public interface IHomeView extends BaseView{

        void setBasicData(List<HomeBean.DataBean.ArticleBean> list, boolean isrefresh);

        void setBannerData(List<String> images, List<String> titles,List<String> url);

        void showToast(String msg);

        void setLikeCount(int position,boolean isChecked);
    }

    public interface HomePresenterInterface{

        void getData();

        void getListData(int pageNum,boolean isrefresh);

        void onLikeData(String id,int position,boolean isCheck);

        void onDisLikeData(String id,int position,boolean isCheck);
    }

}
