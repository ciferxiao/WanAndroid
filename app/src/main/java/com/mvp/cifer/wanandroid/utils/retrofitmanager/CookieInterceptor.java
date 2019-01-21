package com.mvp.cifer.wanandroid.utils.retrofitmanager;

import android.content.Context;
import android.util.Log;

import com.mvp.cifer.wanandroid.app.WanAndroidApp;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * - @author :  Xiao
 * - @date   :  2019/1/17
 * - @time   :  17:25
 * - @desc   :
 */
public class CookieInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException{
        Request.Builder builder = chain.request().newBuilder();
        HashSet<String> preferences = (HashSet) WanAndroidApp.getInstance().getSharedPreferences("config",
                Context.MODE_PRIVATE).getStringSet("cookie", null);
        if (preferences != null) {
            for (String cookie : preferences) {
                builder.addHeader("Cookie", cookie);
                Log.v("OkHttp", "Adding Header: " + cookie); // This is done so I know which headers are being added; this interceptor is used after the normal logging of OkHttp
            }
        }
        return chain.proceed(builder.build());
    }
}
