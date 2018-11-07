package com.mvp.cifer.wanandroid.utils;

import com.mvp.cifer.wanandroid.basemvp.HttpConstants;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * - @author :  Xiao
 * - @date   :  2018/10/31
 * - @time   :  10:22
 * - @desc   :  retrofit 工具类
 *              封装一号
 */
public class RetrofitUtils {
    private static RetrofitUtils retrofitUtils;
    private static RetrofitService service;

    public RetrofitUtils(){}

    public static RetrofitService getService() {
        return service ;
    }

    //Builder模式
    public static class Builder{
        List<CallAdapter.Factory> calladapterfactorys = new ArrayList<>();
        List<Converter.Factory> converterfactorys = new ArrayList<>();
        private OkHttpClient okHttpClient;

        public Builder addCallAdapterFactory(CallAdapter.Factory factory){
            calladapterfactorys.add(factory);
            return this;
        }

        public Builder addConverterFactory(Converter.Factory factory){
            converterfactorys.add(factory);

            return this;
        }

        public Builder client(OkHttpClient client){
            this.okHttpClient = client;
            return this;
        }

        public RetrofitUtils build(){
            Retrofit.Builder builder = new Retrofit.Builder();
            if(converterfactorys!=null&&converterfactorys.size()==0){
                builder.addConverterFactory(GsonConverterFactory.create());
            }

            for (int i = 0; i<converterfactorys.size();i++){
                builder.addConverterFactory(converterfactorys.get(i));
            }

            if(calladapterfactorys!=null&&calladapterfactorys.size()==0){
                builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            }

            assert calladapterfactorys != null;
            for (int i = 0; i < calladapterfactorys.size(); i++) {
                builder.addCallAdapterFactory(calladapterfactorys.get(i));
            }

            //第一步创建retrofit对象
            Retrofit retrofit = builder.client(okHttpClient).baseUrl(HttpConstants.baseUrla).build();
            //第二步创建Rxjava
            service = retrofit.create(RetrofitService.class);
            //第三步请求逻辑
            retrofitUtils = new RetrofitUtils();
            return retrofitUtils;
        }


    }


}
