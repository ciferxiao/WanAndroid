package com.mvp.cifer.wanandroid.utils.retrofitmanager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/5
 * - @time   :  11:02
 * - @desc   :
 */
public class HeaderInterceptor implements Interceptor{
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request requestBuilder = request.newBuilder()
                .addHeader("username","cifer_xiao")
                .addHeader("password","xiao123")
                .method(request.method(), request.body())
                .build();
        return chain.proceed(requestBuilder);
    }
}
