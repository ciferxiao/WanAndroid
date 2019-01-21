package com.mvp.cifer.wanandroid.collection;

import android.util.Log;

import com.mvp.cifer.wanandroid.basemvp.BasePresenter;
import com.mvp.cifer.wanandroid.utils.AppCallback;
import com.mvp.cifer.wanandroid.utils.CommonUtil;

import java.util.List;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/14
 * - @time   :  14:25
 * - @desc   :
 */
public class CollectionPresneter extends BasePresenter<CollectionContract.CollectionView> implements CollectionContract.CollectionPresenterInterface {
    private CollectionModel model;

    public CollectionPresneter() {
        model = new CollectionModel();
    }

    @Override
    public void getListData() {
        model.getListData(new AppCallback<CollectionBean>() {
            @Override
            public void Success(CollectionBean collectionBean) {
                if(collectionBean.getErrorCode() == 0 ){
                    CollectionBean.DataBean dataBean= collectionBean.getData();
                    List<CollectionBean.DataBean.ArticleBean> lists = dataBean.getDatas();

                    getView().setBasicData(lists,false);

                }else{
                    getView().showToast(collectionBean.getErrorMsg());
                }
            }

            @Override
            public void Error(String error) {

                Log.d("xiao111"," mmmsmanfajfaojiofjao");

                String data = "{\"data\":{\"curPage\":1,\"datas\":[{\"author\":\"xujun9411\",\"chapterId\":0,\"chapterName\":\"\",\"courseId\":13,\"desc\":\"\",\"envelopePic\":\"\",\"id\":44179,\"link\":\"https://www.jianshu.com/p/6b7bdb8e5a22\",\"niceDate\":\"6小时前\",\"origin\":\"\",\"originId\":-1,\"publishTime\":1547775958000,\"title\":\"Android Fragment 的妙用 - 优雅地申请权限和处理 onActivityResult\",\"userId\":12287,\"visible\":0,\"zan\":0},{\"author\":\"玉刚说\",\"chapterId\":0,\"chapterName\":\"\",\"courseId\":13,\"desc\":\"\",\"envelopePic\":\"\",\"id\":44099,\"link\":\"https://mp.weixin.qq.com/s/4w202K0WnNrazmEHd6grQA\",\"niceDate\":\"22小时前\",\"origin\":\"\",\"originId\":-1,\"publishTime\":1547718363000,\"title\":\"看完这篇 Android ANR 分析，就可以和面试官装逼了！\",\"userId\":12287,\"visible\":0,\"zan\":0}],\"offset\":0,\"over\":true,\"pageCount\":1,\"size\":20,\"total\":2},\"errorCode\":0,\"errorMsg\":\"\"}";
                CollectionBean collectionBean = CommonUtil.strToBean(data,CollectionBean.class);
                CollectionBean.DataBean dataBean= collectionBean.getData();
                List<CollectionBean.DataBean.ArticleBean> lists = dataBean.getDatas();

                getView().setBasicData(lists,false);

               getView().showToast(error);
            }
        });
    }


    /*@Override
    public void onDisLikeData(String id, int position, boolean isCheck) {
        model.getDisLikeCount(id, new AppCallback<BaseBean>() {
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
                getView().showToast(error);
            }
        });

    }*/

}
