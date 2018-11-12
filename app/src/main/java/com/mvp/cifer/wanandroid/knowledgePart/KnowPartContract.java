package com.mvp.cifer.wanandroid.knowledgePart;

import com.mvp.cifer.wanandroid.basemvp.BaseView;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/8
 * - @time   :  15:47
 * - @desc   :
 */
public class KnowPartContract {
    interface KnowPartView extends BaseView{
        void setListData(PartBean.DataBean dataBeans);

        void loading();
    }

    interface KnowPartPresneterInterface{
        void getListData(int id);
    }

}
