package com.orbyun.net.netstate;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * author : wangxf
 * e-mail : wallfacer_no3@163.com
 * date   : 2019-10-1017:44
 * desc   :
 */
public class NetworkUtils {
    /**
     * 网络是否可用
     * @return
     */
    @SuppressWarnings("MissingPermission")
    public static boolean isAvailable() {
        ConnectivityManager connmagr = (ConnectivityManager) NetStateManager.getInstance().getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connmagr == null) {
            return false;
        }
        NetworkInfo[] allNetworkInfo = connmagr.getAllNetworkInfo();
        if (allNetworkInfo != null) {
            for (NetworkInfo networkInfo : allNetworkInfo) {
                if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }


        return false;
    }

    /**
     * 获取网络类型
     * @return
     */
    public static NetType getNetType() {
        ConnectivityManager connmagr = (ConnectivityManager) NetStateManager.getInstance().getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connmagr == null) {
            return NetType.NONE;
        }
        NetworkInfo activeNetworkInfo = connmagr.getActiveNetworkInfo();
        if (activeNetworkInfo != null) { int type = activeNetworkInfo.getType();
            if (type == ConnectivityManager.TYPE_MOBILE||type == ConnectivityManager.TYPE_ETHERNET) {
                if (activeNetworkInfo.getExtraInfo().toLowerCase().equals("cmnet")) {
                    return NetType.CMNET;
                } else {
                    return NetType.CMWAP;
                }
            } else if (type == ConnectivityManager.TYPE_WIFI) {
                return NetType.WIFI;

            }
        }
        return NetType.NONE;
    }

    public static void openNetSetting(Activity context, int code) {
        Intent intent = new Intent("/");
        ComponentName componentName = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
        intent.setComponent(componentName);
        context.startActivityForResult(intent, code);
    }

    /**
     * 判断是否有网络连接
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }


    /**
     * 判断WIFI网络是否可用
     */
    public static boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null) {
                return mWiFiNetworkInfo.isConnected();
            }
        }
        return false;
    }

    /**
     * 判断MOBILE网络是否可用
     */
    public static boolean isMobileConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetworkInfo != null) {
                return mMobileNetworkInfo.isConnected();
            }
        }
        return false;
    }
}
