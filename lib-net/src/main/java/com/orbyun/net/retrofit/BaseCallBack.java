package com.orbyun.net.retrofit;




import com.orbyun.base.api.net.ResultInfo;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @package com.guocuhui.gch.network
 * @file BaseCallBack
 * @date 2017/12/18  下午11:51
 * @autor wangxiongfeng
 */

public abstract class BaseCallBack<T extends ResultInfo> implements Callback<T> {
    private Callback<T> callback = null;
    private final String STATE = "OK";


    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        callback = this;
        if (response.raw().code() == 200) {//200是服务器有合理响应
            if (STATE.equals(response.body().getState())) {
                onSucess(response);
            } else {
                onFail("", 0);
            }
        } else {//失败响应
            onFailure(call, new RuntimeException(response.raw().toString()));
        }


    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (t instanceof SocketTimeoutException) {
            //
        } else if (t instanceof ConnectException) {
            //
        } else if (t instanceof RuntimeException) {
            //
        }
        t.printStackTrace();
        BaseCallBack.this.onFail("", 0);

    }

    public abstract void onSucess(Response<T> response);

    public abstract void onFail(String msg, int code);


}
