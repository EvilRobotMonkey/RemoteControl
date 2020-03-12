package com.orbyun.net;

import android.net.ParseException;


import org.json.JSONException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import javax.net.ssl.SSLHandshakeException;

/**
 * @package com.guocuhui.gch.ExceptionEngine
 * @file ConstantValues
 * @date 2017/12/6  下午2:46
 * @autor wangxiongfeng
 * @org www.miduo.com
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


}
