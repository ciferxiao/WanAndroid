package com.mvp.cifer.wanandroid.utils.retrofitmanager;

import android.content.Context;
import android.content.SharedPreferences;

import com.mvp.cifer.wanandroid.app.WanAndroidApp;

import java.io.IOException;
import java.util.HashSet;

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
        Response originalResponse = chain.proceed(request);
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();
            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }

            SharedPreferences.Editor config = WanAndroidApp.getInstance().getSharedPreferences("config", Context.MODE_PRIVATE)
                    .edit();
            config.putStringSet("cookie", cookies);
            config.apply();
        }
        return originalResponse;
    }
}
