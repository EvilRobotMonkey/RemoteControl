package com.orbyun.net.okgo;


import com.orbyun.base.api.net.ResultInfo;

import java.io.IOException;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by zhangbiao on 2018/5/3.
 */

public class CommonParser implements BaseParser {

    @Override
    public ResultInfo parseParams(Response response, String mTag) {
        String body = "";
        ResultInfo result = new ResultInfo(response.code());
        ResponseBody responseBody = response.body();
        if (responseBody == null) return result;
        try {
            body = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        result.setTag(mTag);
        result.setData(body);
        if (result.getData() != null) {
            result.setState("200");
        }
        return result;
    }

}
