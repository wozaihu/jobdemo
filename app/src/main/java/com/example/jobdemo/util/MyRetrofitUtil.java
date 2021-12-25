package com.example.jobdemo.util;

import com.example.jobdemo.base.RetrofitInterface;
import com.example.jobdemo.constants.Api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Administrator
 */

public enum MyRetrofitUtil {
    /**
     * 枚举单例
     */
    INSTANCE;
    private final RetrofitInterface retrofitInterface;

    private MyRetrofitUtil() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASEPICTUREURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
    }

    public RetrofitInterface getRetrofitInterface() {
        return retrofitInterface;
    }
}
