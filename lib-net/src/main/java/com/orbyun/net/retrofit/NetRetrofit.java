package com.orbyun.net.retrofit;


import com.orbyun.net.ConstantValues;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @package com.guocuhui.gch.network
 * @file NetUtil
 * @date 2017/12/6  下午2:28
 * @autor wangxiongfeng
 * @org www.miduo.com
 */

public class NetRetrofit {
    private static OkHttpClient.Builder builder;
    private static retrofit2.Retrofit.Builder retrofitBuilder;


    private static OkHttpClient getSingleOKhttp() {
        if (builder == null) {

            builder = new OkHttpClient.Builder();

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
            builder.retryOnConnectionFailure(true)
                    .connectTimeout(8, TimeUnit.SECONDS)
//                    .addNetworkInterceptor(new AddCookiesInterceptor())
//                    .addNetworkInterceptor(new ReceivedCookiesInterceptor())
                    .build();

        }

        return builder.build();
    }

    private static retrofit2.Retrofit.Builder getRetrofitInstance() {
        if (retrofitBuilder == null) {
            synchronized (String.class) {
                retrofitBuilder = new retrofit2.Retrofit.Builder();
                retrofitBuilder.baseUrl(ConstantValues.BASE_URL)
                        .client(getSingleOKhttp())
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();
            }

        }
        return retrofitBuilder;

    }

    public static <T> T configRetrofit(Class<T> service) {
        return getRetrofitInstance().build().create(service);
    }


}
