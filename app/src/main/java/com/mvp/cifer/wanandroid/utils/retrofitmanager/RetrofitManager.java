package com.mvp.cifer.wanandroid.utils.retrofitmanager;

import com.mvp.cifer.wanandroid.basemvp.HttpConstants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * - @author :  Xiao
 * - @date   :  2018/11/5
 * - @time   :  10:30
 * - @desc   :  retrofit 封装二号
 */
public class RetrofitManager  {
    private static RetrofitManager mInstance;
    private static final long DEFAULT_TIMEOUT = 60L;
    private Retrofit retrofit = null;
    //请求头信息
    private final String HEADER_CONNECTION = "keep-alive";

    public static RetrofitManager getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitManager.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitManager();
                }
            }
        }
        return mInstance;
    }

    public Retrofit getRetrofit() {
        if (retrofit == null) {
            synchronized (RetrofitManager.class) {
                if (retrofit == null) {
                    OkHttpClient mClient = new OkHttpClient.Builder()
                            //添加公告查询参数
//                          .addInterceptor(new CommonQueryParamsInterceptor())
//                          .addInterceptor(new MutiBaseUrlInterceptor())
//                          添加离线缓存
//                          .cache(new Cache(File(context.getExternalFilesDir("okhttpCache"), ""), 14 * 1024 * 100))
//                          .addInterceptor(new CacheInterceptor())
//                          .addNetworkInterceptor(new CacheInterceptor())//必须要有，否则会返回504
                            //.addInterceptor(new HeaderInterceptor())
                            .addInterceptor(new LoggingInterceptor())//添加请求拦截(可以在此处打印请求信息和响应信息)
                            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                            .build();
                    retrofit = new Retrofit.Builder()
                            .baseUrl(HttpConstants.baseUrla)//基础URL 建议以 / 结尾
                            .addConverterFactory(GsonConverterFactory.create())//设置 Json 转换器
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//RxJava 适配器
                            .client(mClient)
                            .build();
                }
            }
        }
        return retrofit;
    }

    public ApiService getRequestService() {
        return getRetrofit().create(ApiService.class);
    }



}
