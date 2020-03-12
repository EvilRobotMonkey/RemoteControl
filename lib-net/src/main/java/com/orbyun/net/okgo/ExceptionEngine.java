package com.orbyun.net.okgo;

import android.net.ParseException;

import com.google.gson.JsonParseException;
import com.lzy.okgo.exception.HttpException;
import com.orbyun.base.api.net.ResultInfo;

import org.json.JSONException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import javax.net.ssl.SSLHandshakeException;

/**
 * author: ltyhome
 * date:   On 3/20/18
 * email:  ltyhome@yahoo.com.hk
 * annotation:异常处理类
 */
public class ExceptionEngine {
    public static final String HTTP_ERROR_TEXT = "网络错误";
    public static final int ANALYTIC_ERROR = 1000;
    public static final String ANALYTIC_ERROR_TEXT = "解析错误";
    public static final int CONNECT_ERROR = 1001;
    public static final String CONNECT_ERROR_TEXT = "连接失败";
    public static final int TIME_OUT_ERROR = 1002;
    public static final String TIME_OUT_ERROR_TEXT = "网络超时";
    public static final int SSL_ERROR = 1003;
    public static final String SSL_ERROR_TEXT = "证书验证失败";
    public static final int UN_KNOWN_ERROR = 1005;
    public static final String UN_KNOWN_ERROR_TEXT = "未知错误";

    /**
     * 捕获异常信息
     *
     * @param e Throwable
     * @return 自定义异常
     */
    public static ApiException handleException(Throwable e) {
        ApiException ex;
        if (e instanceof HttpException) {/**HTTP错误均视为网络错误**/
            HttpException httpExc = (HttpException) e;
            ex = new ApiException(e, httpExc.code());
            ex.setMsg(HTTP_ERROR_TEXT);
            return ex;
        } else if (e instanceof ServerException) {    //服务器返回的错误
            ServerException serverExc = (ServerException) e;
            ex = new ApiException(serverExc, serverExc.getCode());
            ex.setMsg(serverExc.getMsg());
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {  //解析数据错误
            ex = new ApiException(e, ANALYTIC_ERROR);
            ex.setMsg(ANALYTIC_ERROR_TEXT);
            return ex;
        } else if (e instanceof ConnectException ||
                e instanceof IOException) {
            ex = new ApiException(e, CONNECT_ERROR);
            ex.setMsg(CONNECT_ERROR_TEXT);
            return ex;
        } else if (e instanceof SocketTimeoutException
                || e instanceof SocketException) {
            ex = new ApiException(e, TIME_OUT_ERROR);
            ex.setMsg(TIME_OUT_ERROR_TEXT);
            return ex;
        } else if (e instanceof SSLHandshakeException) {
            ex = new ApiException(e, SSL_ERROR);
            ex.setMsg(SSL_ERROR_TEXT);
            return ex;
        } else {
            ex = new ApiException(e, UN_KNOWN_ERROR);
            ex.setMsg(UN_KNOWN_ERROR_TEXT);
            return ex;
        }
    }

    /**
     * 将异常对象转化成通用对象
     *
     * @param apiExc 异常对象
     * @param tag    标记
     * @return 通用Result
     */
    public static ResultInfo covert(ApiException apiExc, String tag) {
        ResultInfo result = new ResultInfo("");
        result.setMessage(apiExc.getMsg());
        result.setTag(tag);
        result.setCode(apiExc.getCode());
        return result;
    }
}
