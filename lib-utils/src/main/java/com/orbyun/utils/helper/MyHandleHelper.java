package com.orbyun.utils.helper;

import android.os.Handler;
import android.os.Looper;

/**
 * author : wangxf
 * e-mail : wallfacer_no3@163.com
 * date   : 2019-10-3013:33
 * desc   :
 */
public class MyHandleHelper {
    private static  MyHandleHelper myHandler=new MyHandleHelper();
    private Handler  handler;

    public static MyHandleHelper getInstance() {
        return myHandler;
    }

    public Handler init(){
        synchronized (this){
            if(handler==null){
                handler=new Handler(Looper.getMainLooper());
            }
        }
        return handler;
    }
}
