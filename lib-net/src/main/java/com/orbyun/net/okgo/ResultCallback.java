package com.orbyun.net.okgo;

import com.lzy.okgo.callback.AbsCallback;
import com.orbyun.base.api.net.ResultInfo;

import org.json.JSONObject;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * author: ltyhome
 * date:   On 1/25/18
 * email:  ltyhome@yahoo.com.hk
 * annotation:对OKGo的封装
 */
public class ResultCallback extends AbsCallback<ResultInfo<String>> {

    private ResultConvert convert;
    private BaseParser parser;
    private String mTag;

    public ResultCallback(String tag) {
        convert = new ResultConvert(tag);
    }

    public ResultCallback(String tag, BaseParser parser) {
        mTag = tag;
        if (null == parser) {
            convert = new ResultConvert(tag);
        } else {
            this.parser = parser;
        }
    }

    @Override
    public void onSuccess(com.lzy.okgo.model.Response<ResultInfo<String>> response) {

    }

    @Override
    public ResultInfo convertResponse(Response response) throws Throwable {

        ResponseBody body = response.body();
        String json = new String(body.bytes());
        JSONObject jsonObject = new JSONObject(json);
        String data = jsonObject.optString("data");
        String message = jsonObject.optString("message");
        int code = jsonObject.optInt("code");
        int iTotalRecords = jsonObject.optInt("iTotalRecords");
        int total = jsonObject.optInt("total");
        int type = jsonObject.optInt("type");
        String status = jsonObject.optString("status");
        ResultInfo<String> stringResultInfo = new ResultInfo<>(data);
        stringResultInfo.setCode(code);
        stringResultInfo.setMessage(message);
        stringResultInfo.setiTotalRecords(iTotalRecords);
        stringResultInfo.setTotal(total);
        stringResultInfo.setType(type);
        stringResultInfo.setState(status);
        response.close();
        return stringResultInfo;
    }
}
