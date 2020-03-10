package com.orbyun.base;

import android.app.Activity;
import android.content.Context;

import com.orbyun.utils.LOG;

import java.util.Stack;

/**
 * @package com.guocuhui.gch.common
 * @file AppActivityManager
 * @date 2017/12/8  上午10:51
 * @autor wangxiongfeng
 * @org www.miduo.com
 */

public class AppActivityManager {
    // Activity栈
    private static Stack<Activity> activityStack;
    // 单例模式
    private static AppActivityManager instance;

    private AppActivityManager() {
    }


    /**
     * 单一实例
     */
    public static AppActivityManager getAppManager() {

        if (instance == null) {
            synchronized (String.class) {

                instance = new AppActivityManager();
            }
        }
        return instance;
    }


    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            synchronized (String.class) {
                activityStack = new Stack<Activity>();
            }
        }
        activityStack.add(activity);
        LOG.d("Activity", "创建activity->" + activity.toString());

    }

    /**
     * 从Activity 堆栈中移除
     */

    public void removeActivity(Activity activity) {
        LOG.d("Activity", "移除activity->" + activity.toString());

        activityStack.remove(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                break;
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0; i < activityStack.size(); i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            //退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        } catch (Exception e) {
        }
    }

    public int activityNum() {
        return activityStack.size();
    }
}
