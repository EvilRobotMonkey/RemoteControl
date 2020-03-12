package com.orbyun.net.okgo;


import com.orbyun.base.api.net.ResultInfo;

import okhttp3.Response;

/**
 * Created by lixiong on 2018/5/21.
 */

public interface BaseParser {
    ResultInfo parseParams(Response response, String mTag);
}
