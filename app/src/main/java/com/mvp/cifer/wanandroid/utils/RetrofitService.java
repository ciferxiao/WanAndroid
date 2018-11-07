package com.mvp.cifer.wanandroid.utils;

import com.mvp.cifer.wanandroid.Login.retrofit.LoginBean;
import com.mvp.cifer.wanandroid.home.bean.HomeBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * - @author :  Xiao
 * - @date   :  2018/10/31
 * - @time   :  10:10
 * - @desc   :
 */
public interface RetrofitService {

    @POST("user/login")
    @FormUrlEncoded
    Call<LoginBean> postLoginRetrofit(@Field("username") String username, @Field("password")String password);


    @GET("login/login.do")
    Call<LoginBean> getLoginRetrofit(@Query("type")String type,@Query("phone")String phone,@Query("password")String password
                    ,@Query("verifyCode")String verifyCode,@Query("isVoice")String isVoice,@Query("wxOpenId")String wxOpenId);

    @GET("banner/json")
    Call<HomeBean> getBannerRetrofit();

}
