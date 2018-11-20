package com.mvp.cifer.wanandroid.project;

import com.mvp.cifer.wanandroid.basemvp.BaseView;

import java.util.List;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/14
 * - @time   :  14:25
 * - @desc   :
 */
public class ProjectContract {
    interface ProjectView extends BaseView{

        void setBasicTab(List<ProjectBean.DataBean> projectBean);
    }

    interface ProjectPresenterInterface{
        void getProjectPart();

    }

}
