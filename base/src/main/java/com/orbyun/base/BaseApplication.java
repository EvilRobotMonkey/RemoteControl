package com.orbyun.base;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;



/**
 * author : wangxf
 * e-mail : wallfacer_no3@163.com
 * date   : 2020-01-1512:31
 * desc   :
 */
public class BaseApplication extends Application {

    private AppActivityManager appManager;
    private static final String TAG = "BaseApplication";

    @Override

    public void onCreate() {
        super.onCreate();
        registerActivityListener();


    }



    private void registerActivityListener() {
        appManager = AppActivityManager.getAppManager();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {

            registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
                @Override
                public void onActivityCreated(Activity activity, Bundle bundle) {
                    appManager.addActivity(activity);
                }

                @Override
                public void onActivityStarted(Activity activity) {

                }

                @Override
                public void onActivityResumed(Activity activity) {

                }

                @Override
                public void onActivityPaused(Activity activity) {

                }

                @Override
                public void onActivityStopped(Activity activity) {

                }

                @Override
                public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

                }

                @Override
                public void onActivityDestroyed(Activity activity) {

                    appManager.removeActivity(activity);
                }
            });
        }
    }
}
