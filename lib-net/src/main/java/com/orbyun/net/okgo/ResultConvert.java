package com.orbyun.net.okgo;

import android.text.TextUtils;

import com.lzy.okgo.convert.Converter;
import com.orbyun.base.api.net.ResultInfo;

import java.util.Map;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * author: ltyhome
 * date:   On 1/25/18
 * email:  ltyhome@yahoo.com.hk
 * annotation:对OKGo的封装
 */
public class ResultConvert implements Converter<ResultInfo> {
    private String mTag;

    public ResultConvert(String tag) {
        mTag = tag;
    }

    @Override
    public ResultInfo convertResponse(Response response) throws Throwable {
        ResultInfo result = new ResultInfo("");
        result.setTag(mTag);
        ResponseBody responseBody = response.body();
        if (responseBody == null) return result;
        String body = responseBody.string();


        return result;
    }

    /**
     * 重写valueOf方法
     **/
    public String valueOf(Object obj) {
        return null == obj ? "" : obj.toString();
    }

    public static String optInfoString(Map<String, String> map, String... strings) {
        for (String s : strings) {
            String result = map.get(s);
            if (!TextUtils.isEmpty(result))
                return result;
        }
        return "";
    }
}
