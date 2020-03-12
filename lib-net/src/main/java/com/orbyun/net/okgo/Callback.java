package com.orbyun.net.okgo;


import com.orbyun.base.api.net.ResultInfo;

/**
 * Created by ltyhome on 12/4/17.
 * 通用回调接口
 */

public interface Callback {
    void callResult(ResultInfo result) throws Exception;
}
