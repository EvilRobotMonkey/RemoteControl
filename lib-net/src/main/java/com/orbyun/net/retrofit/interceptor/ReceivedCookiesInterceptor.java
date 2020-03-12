package com.orbyun.net.retrofit.interceptor;


import java.io.IOException;
import java.util.HashMap;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * @author wangxiongfeng
 * @date 2017/7/27 0027 15:00
 */

public class ReceivedCookiesInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());


        try {
            if (!originalResponse.headers("Set-Cookie").isEmpty()) {

                for (String header : originalResponse.headers("Set-Cookie")) {
                    if (header.contains("MDSESSION")) {
                        Cookie.cookie.put("MDSESSION", (header.substring(0, header.indexOf(";")).split("=")[1]));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return originalResponse;
    }
}
