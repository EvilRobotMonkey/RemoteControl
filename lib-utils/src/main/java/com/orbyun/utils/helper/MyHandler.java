package com.orbyun.utils.helper;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

public abstract class MyHandler<T> extends Handler {

    private WeakReference<T> target;

    public MyHandler(T view) {
        this.target = new WeakReference<>(view);
    }

    @Override
    public void handleMessage(Message msg) {
        Context myTarget = (Context) target.get();
        if (myTarget != null) {
            this.handleMessage(myTarget, msg);
        }
    }

    protected abstract void handleMessage(Context context, Message msg);


//    public class RecycleHandler extends MyHandler<T> {
//
//
//        public static RecycleHandler createRecycleHandle(Activity activity) {
//            RecycleHandler nameHandler = new RecycleHandler(activity);
//            nameHandler.removeCallbacksAndMessages(null);
//            nameHandler.sendEmptyMessage(100);
//
//            return nameHandler;
//
//        }
//
//
//        public RecycleHandler(T view) {
//            super(view);
//        }
//
//        @Override
//        protected void handleMessage(Context context, Message msg) {
//
//        }
//
//    }
}