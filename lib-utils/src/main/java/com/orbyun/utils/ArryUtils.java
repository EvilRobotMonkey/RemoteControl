package com.orbyun.utils;

import java.io.File;

/**
 * author : wangxf
 * e-mail : wallfacer_no3@163.com
 * date   : 2020/3/52:06 PM
 * desc   :
 */
public class ArryUtils {
    /**
     * 置空数组
     *
     * @param a
     * @return
     */
    public static byte[] resetArray(byte[] a) {
        byte[] b2 = new byte[a.length];
        for (int i = 0; i < a.length; i++) {
            a[i] = b2[i];
        }
        return a;
    }
    public static File[] resetArray(File[] a) {
        File[] b2 = new File[a.length];
        for (int i = 0; i < a.length; i++) {
            a[i] = b2[i];
        }
        return a;
    }


}
