package com.mvp.cifer.wanandroid.knowledge;

import android.content.Context;
import android.util.Log;

import com.mvp.cifer.wanandroid.basemvp.BasePresenter;
import com.mvp.cifer.wanandroid.knowledge.bean.Knowledgebean;
import com.mvp.cifer.wanandroid.utils.AppCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/7
 * - @time   :  17:50
 * - @desc   :
 */
public class KnowledgePresenter extends BasePresenter<KnowledgeContract.KnowledgeView> {
    private KnowledgeModel model;

    public KnowledgePresenter(){
        model = new KnowledgeModel();
    }

    public void getList(){
        model.getListData(new AppCallback<Knowledgebean>() {
            @Override
            public void Success(Knowledgebean knowledgebean) {
                if (knowledgebean.getErrorCode() == 0){

                    Log.d("xiao111"," aaaaaaaaaaaaa");
                    getView().setListData(knowledgebean.getData());
                }else{
                    //TODO
                }
            }

            @Override
            public void Error(String error) {

            }
        });
    }

    private void setData(Knowledgebean knowledgebean){
        List<String> title = new ArrayList<>();
        List<String> subtitle = new ArrayList<>();


        for(Knowledgebean.DataBean dataBean :knowledgebean.getData() ){

        }

    }

}
