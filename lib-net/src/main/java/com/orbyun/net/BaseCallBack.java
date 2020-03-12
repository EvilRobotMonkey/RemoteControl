package com.orbyun.net;


import com.orbyun.base.api.net.ResultInfo;

import retrofit2.Response;

/**
 * @package com.orbyun.base.net
 * @file BaseCallBack
 * @date 2019/4/29  4:58 PM
 * @autor wangxiongfeng
 */
public abstract class BaseCallBack<T extends ResultInfo> {


    public abstract void onSucess(Response<T> response);

    public abstract void onFail(String msg, int code);

}
