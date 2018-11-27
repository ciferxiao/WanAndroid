package com.mvp.cifer.wanandroid.home;

import android.util.Log;

import com.mvp.cifer.wanandroid.basemvp.BaseBean;
import com.mvp.cifer.wanandroid.basemvp.BasePresenter;
import com.mvp.cifer.wanandroid.home.bean.HomeBannerBean;
import com.mvp.cifer.wanandroid.home.bean.HomeBean;
import com.mvp.cifer.wanandroid.utils.AppCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/1
 * - @time   :  10:51
 * - @desc   :
 */
public class HomePresenter extends BasePresenter<HomeContract.IHomeView> implements HomeContract.HomePresenterInterface{

    private HomeModel model;

    public HomePresenter(){
        model = new HomeModel();
    }

    @Override
    public void getData() {
        model.getData(new AppCallback<HomeBannerBean>() {
            @Override
            public void Success(HomeBannerBean bean) {
                if(bean.getErrorCode() == 0 ){
                    setData(bean);
                }else{
                    getView().showToast(bean.getErrorMsg());
                }
            }

            @Override
            public void Error(String error) {

            }
        });
    }

    @Override
    public void getListData(int pagenum, final boolean isrefresh) {
        model.getListData(pagenum,new AppCallback<HomeBean>() {
            @Override
            public void Success(HomeBean homeBean) {
                if(homeBean.getErrorCode() == 0 ){

                    HomeBean.DataBean dataBean= homeBean.getDataBean();
                    List<HomeBean.DataBean.ArticleBean> lists = dataBean.getDatas();

                    getView().setBasicData(lists,false);

                }else{
                    getView().showToast(homeBean.getErrorMsg());
                }
            }

            @Override
            public void Error(String error) {
                getView().showToast(error);
            }
        });
    }

    @Override
    public void onLikeData(String title, String author, String link,int position, boolean isCheck) {
        model.getLikeCount(title, author, link, new AppCallback<BaseBean>() {
            @Override
            public void Success(BaseBean bean) {
                if (bean.getErrorCode() == 0){
                    getView().setLikeCount(position,isCheck);
                }else{
                    getView().showToast(bean.getErrorMsg());
                }

            }

            @Override
            public void Error(String error) {

            }
        });

    }

    private void setData(HomeBannerBean homeBannerBean){
        ArrayList<HomeBannerBean.DataBean> dataBeans = homeBannerBean.getData();

        List<String> imageList = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        List<String> url = new ArrayList<>();
        for (HomeBannerBean.DataBean dataBean : dataBeans){
            imageList.add(dataBean.getImagePath());
            titles.add(dataBean.getTitle());
            url.add(dataBean.getUrl());
        }
        getView().setBannerData(imageList,titles,url);
    }

}
