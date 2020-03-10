package com.orbyun.utils.helper;

import android.util.Base64;

/**
 * Created by sunbingtuan on 2015/1/13.
 */
public class Base64Helper {
    private static Base64Helper instance = null;

    private Base64Helper(){}

    public static Base64Helper getInstance() {
        if(null == instance) {
            instance = new Base64Helper();
        }
        return instance;
    }

    /**
     * Base64解码
     * @param str
     * @return
     */
    public String decode(String str) {
        byte[] decode = Base64.decode(str, Base64.DEFAULT);
        return new String(decode);
    }

    /**
     * 编码
     * @param str
     * @return
     */
    public String encode(String str) {
        byte[] encode = Base64.encode(str.getBytes(), Base64.DEFAULT);
        return new String(encode);
    }

}
