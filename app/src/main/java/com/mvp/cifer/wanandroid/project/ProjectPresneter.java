package com.mvp.cifer.wanandroid.project;

import com.mvp.cifer.wanandroid.basemvp.BasePresenter;
import com.mvp.cifer.wanandroid.utils.AppCallback;

import java.util.List;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/14
 * - @time   :  14:25
 * - @desc   :
 */
public class ProjectPresneter extends BasePresenter<ProjectContract.ProjectView> implements ProjectContract.ProjectPresenterInterface{
    private ProjectModel model;

    public ProjectPresneter() {
        model = new ProjectModel();
    }

    @Override
    public void getProjectPart() {

        model.getProjectPart(new AppCallback<ProjectBean>() {
            @Override
            public void Success(ProjectBean projectBean) {
                if(projectBean.getErrorCode() == 0){
                    List<ProjectBean.DataBean> dataBeans = projectBean.getData();
                    getView().setBasicTab(dataBeans);
                }else{
                    //TODO
                }
            }

            @Override
            public void Error(String error) {
                //TODO
            }
        });
    }
}
