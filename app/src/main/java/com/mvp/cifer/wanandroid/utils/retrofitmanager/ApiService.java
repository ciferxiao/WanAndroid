package com.mvp.cifer.wanandroid.utils.retrofitmanager;

import com.mvp.cifer.wanandroid.Login.retrofit.LoginBean;
import com.mvp.cifer.wanandroid.home.bean.HomeBannerBean;
import com.mvp.cifer.wanandroid.home.bean.HomeBean;
import com.mvp.cifer.wanandroid.knowledge.bean.Knowledgebean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
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
    @POST("user/login")
    @FormUrlEncoded
    Observable<LoginBean> postLoginRetrofit(@Field("username") String username, @Field("password")String password);


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
    Observable<Knowledgebean> getKnowledgePart(@Query("cid") int page);


}
