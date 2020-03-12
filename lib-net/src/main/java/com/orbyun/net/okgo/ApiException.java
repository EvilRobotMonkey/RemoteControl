package com.orbyun.net.okgo;

/**
 * author: ltyhome
 * date:   On 3/20/18
 * email:  ltyhome@yahoo.com.hk
 * annotation:通过解析Throwable转换成统一的错误类ApiException
 */
public class ApiException extends Exception {
    private int code;//错误码
    private String msg;//错误信息

    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
