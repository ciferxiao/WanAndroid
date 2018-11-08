package com.mvp.cifer.wanandroid.knowledge;

import com.mvp.cifer.wanandroid.basemvp.BaseView;
import com.mvp.cifer.wanandroid.knowledge.bean.Knowledgebean;

import java.util.List;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/7
 * - @time   :  17:51
 * - @desc   :
 */
public class KnowledgeContract {

    interface KnowledgeView extends BaseView{
    void setListData(List<Knowledgebean.DataBean> listData);

    }

    interface klPresenterInterface{
        void getList();

    }

}
