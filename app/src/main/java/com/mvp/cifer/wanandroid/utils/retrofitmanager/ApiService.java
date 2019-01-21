package com.mvp.cifer.wanandroid.utils.retrofitmanager;

import com.mvp.cifer.wanandroid.Login.retrofit.LoginBean;
import com.mvp.cifer.wanandroid.basemvp.BaseBean;
import com.mvp.cifer.wanandroid.collection.CollectionBean;
import com.mvp.cifer.wanandroid.home.bean.HomeBannerBean;
import com.mvp.cifer.wanandroid.home.bean.HomeBean;
import com.mvp.cifer.wanandroid.knowledge.bean.Knowledgebean;
import com.mvp.cifer.wanandroid.knowledgePart.PartBean;
import com.mvp.cifer.wanandroid.project.ProjectBean;
import com.mvp.cifer.wanandroid.project.ProjectListFragment.ProjectListBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/5
 * - @time   :  10:33
 * - @desc   :
 */
public interface ApiService {

    String WAN_HOST = "use_host:wan";

    @POST("user/login")
    @FormUrlEncoded
    Observable<LoginBean> postLoginRetrofit(@Field("username") String username, @Field("password")String password);

    @Headers(WAN_HOST)
    @GET("login/login.do")
    Observable<Object> getLoginRetrofit(@Query("type")String type, @Query("phone")String phone, @Query("password")String password
            , @Query("verifyCode")String verifyCode, @Query("isVoice")String isVoice, @Query("wxOpenId")String wxOpenId);

    @GET("banner/json")
    Observable<HomeBannerBean> getBannerRetrofit();

    @GET("article/list/{page}/json")//http://www.wanandroid.com/article/list/0/json
    Observable<HomeBean> getHomeArticle(@Path("page") int page);

    @GET("tree/json")
    Observable<Knowledgebean> getKnowledgeList();

    @GET("article/list/0/json")//http://www.wanandroid.com/article/list/0/json?cid=60
    Observable<PartBean> getKnowledgePart(@Query("cid") int id);

    @GET("project/tree/json")
    Observable<ProjectBean> getProjectPart();

    @GET("project/list/1/json")
    Observable<ProjectListBean> getProjectList(@Query("cid") int id);

    //收藏站内纹章接口post
    @POST("lg/collect/{id}/json")
    Observable<BaseBean> postinnerCollection(@Path("id") String id);

    //取消收藏纹章接口:文章列表
    @POST("lg/uncollect_originId/{id}/json")
    Observable<BaseBean> cancelCollectionList(@Path("id")String id);

    //收藏站外纹章接口post
    @POST("lg/collect/add/json")
    @FormUrlEncoded
    Observable<BaseBean> postoutCollection(@Field("title") String title, @Field("author")String author, @Field("link")String link);

    //收藏纹章列表
    @GET("lg/collect/usertools/json")
    Observable<CollectionBean> getCollecitonList();

}
