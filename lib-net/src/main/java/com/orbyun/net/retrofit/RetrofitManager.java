package com.orbyun.net.retrofit;

import com.orbyun.base.api.net.NetRequestAPI;
import com.orbyun.base.api.net.NetResultInterFace;

import java.util.HashMap;

/**
 * @package com.orbyun.net.retrofit
 * @file RetrofitManager
 * @date 2019/4/29  4:14 PM
 * @autor wangxiongfeng
 */
public class RetrofitManager implements NetRequestAPI {
    @Override
    public void doGet(String tag, String url, HashMap<String, String> parameter, boolean prompt, NetResultInterFace mNetResultInterFace) {

    }

    @Override
    public void doPost(String tag, String url, HashMap<String, String> params, Boolean prompt, NetResultInterFace mNetResultInterFace) {

    }

    @Override
    public void doJson(String tag, String url, String json, Boolean prompt, String showText, NetResultInterFace mNetResultInterFace) {

    }

    @Override
    public void doFileLoad(String tag, String url, int loadFielType, String path, String name, NetResultInterFace mNetResultInterFace) {

    }

    @Override
    public void doFileUpLoad(String tag, String url, int loadFielType, String fileName, NetResultInterFace mNetResultInterFace) {

    }
}
