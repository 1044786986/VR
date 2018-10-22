package com.example.ljh.vr.utils;

import com.example.ljh.vr._base.IRetrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils<T>{
    private static RetrofitUtils mRetrofitUtils;
    private Retrofit mRetrofit;
    private IRetrofit mIRetrofit;
    public static final String BASE_URL = "http://www.liangjiehao.top/vr/servlet/";
//    public static final String BASE_URL = "http://xunself.top/oe/ljh/";
    private static final int READ_TIME = 5;
    private static final int CONN_TIME = 5;
    private final OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
            .readTimeout(READ_TIME, TimeUnit.SECONDS)
            .connectTimeout(CONN_TIME,TimeUnit.SECONDS)
            .build();

    public static RetrofitUtils getInstance(){
        if(mRetrofitUtils == null){
            synchronized(RetrofitUtils.class){
                mRetrofitUtils = new RetrofitUtils();
            }
        }
        return mRetrofitUtils;
    }

    /**
     * 手动输入baseUrl,返回Retrofit
     * @param baseUrl
     * @return
     */
    public Retrofit getRetrofitRx2Gson(String baseUrl){
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return mRetrofit;
    }

    /**
     * 使用默认BASE_URL,返回Retrofit
     * @return
     */
    public Retrofit getRetrofitRx2Gson(){
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return mRetrofit;
    }

    /**
     * 使用默认baseUrl，返回IRetrofit
     * @return
     */
    public IRetrofit getIRetrofitRx2Gson(){
         mIRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IRetrofit.class);
        return mIRetrofit;
    }

    public Retrofit getRetrofitRx(String baseUrl){
        mRetrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return mRetrofit;
    }

    public Retrofit getRetrofitGson(String baseUrl){
        mRetrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return mRetrofit;
    }

    public OkHttpClient getOkHttpClient(){
        return mOkHttpClient;
    }
}
