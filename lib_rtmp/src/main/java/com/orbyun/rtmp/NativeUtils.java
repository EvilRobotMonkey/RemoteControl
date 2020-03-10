package com.orbyun.rtmp;

/**
 * author : wangxf
 * e-mail : wallfacer_no3@163.com
 * date   : 2020/3/81:52 PM
 * desc   :
 */
public class NativeUtils {
    static {
        System.loadLibrary("jniutils");
    }

    public static native void method();
}
