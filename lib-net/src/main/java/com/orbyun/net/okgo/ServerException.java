package com.orbyun.net.okgo;

/**
 * author: ltyhome
 * date:   On 3/20/18
 * email:  ltyhome@yahoo.com.hk
 * annotation: 服务器返回错误(一般我们开发中都会跟服务器约定一种接口请求返回的数据,比如0代表成功，-1代表失败等)
 */
public class ServerException extends RuntimeException {
    private int code;
    private String msg;

    public ServerException(int code, String msg) {
        this.code = code;
        this.msg = msg;
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
