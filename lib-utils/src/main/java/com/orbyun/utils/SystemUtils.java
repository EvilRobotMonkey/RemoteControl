package com.orbyun.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.text.format.Formatter;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * @package com.boci.smart.utils
 * @file SystemUtils
 * @date 2018/8/14  上午10:42
 * @autor wangxiongfeng
 * @org www.orbyun.com
 */
public class SystemUtils {


    public static String loadFileAsString(String filePath) throws java.io.IOException {
        StringBuffer fileData = new StringBuffer(1000);
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead = 0;
        while ((numRead = reader.read(buf)) != -1) {
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
        }
        reader.close();
        return fileData.toString();
    }

    public static String getMacAddress() {
        try {
            return loadFileAsString("/sys/class/net/eth0/address").toUpperCase().substring(0, 17);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Boolean getActiveNetwork(Context context) {
        if (context == null)
            return false;
        ConnectivityManager mConnMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (mConnMgr == null)
            return false;
        NetworkInfo aActiveInfo = mConnMgr.getActiveNetworkInfo(); // 获取活动网络连接信息
        if (aActiveInfo == null)
            return false;

        boolean isWifiConn = aActiveInfo.isConnected();
        NetworkInfo networkInfo = mConnMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        boolean isMobileConn = networkInfo.isConnected();

        return isWifiConn || isMobileConn;
    }

    /**
     * 设置当前系统的亮度模式 SCREEN_BRIGHTNESS_MODE_AUTOMATIC=1 为自动调节屏幕亮度
     * SCREEN_BRIGHTNESS_MODE_MANUAL=0 为手动调节屏幕亮度
     */
    public static void setBrightnessMode(Context context, int brightnessMode) {
        try {
            Settings.System.putInt(context.getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS_MODE, brightnessMode);
        } catch (Exception e) {
        }
    }


    /**
     * 获得当前系统的亮度模式 SCREEN_BRIGHTNESS_MODE_AUTOMATIC=1 为自动调节屏幕亮度
     * SCREEN_BRIGHTNESS_MODE_MANUAL=0 为手动调节屏幕亮度
     */
    public static int getBrightnessMode(Context context) {
        int brightnessMode = Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL;
        try {
            brightnessMode = Settings.System.getInt(
                    context.getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS_MODE);
        } catch (Exception e) {

        }
        return brightnessMode;
    }

    /**
     * 获得当前系统的亮度值： 0~255
     */
    public static int getSysScreenBrightness(Context context) {
        int screenBrightness = 0;
        try {
            screenBrightness = Settings.System.getInt(
                    context.getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS);
        } catch (Exception e) {

        }
        return screenBrightness;
    }

    /**
     * 设置当前系统的亮度值:0~255
     */
    public static void setSysScreenBrightness(int brightness, Context context) {
        try {
            ContentResolver resolver = context.getContentResolver();
            Uri uri = Settings.System
                    .getUriFor(Settings.System.SCREEN_BRIGHTNESS);
            Settings.System.putInt(resolver, Settings.System.SCREEN_BRIGHTNESS,
                    brightness);
            resolver.notifyChange(uri, null); // 实时通知改变
        } catch (Exception e) {
        }
    }

    /**
     * 设置屏幕亮度，这会反映到真实屏幕上
     *
     * @param activity
     * @param brightness
     */
    public static void setActScreenBrightness(final Activity activity,
                                              final int brightness) {
        final WindowManager.LayoutParams lp = activity.getWindow()
                .getAttributes();
        lp.screenBrightness = brightness / (float) 255;
        activity.getWindow().setAttributes(lp);
    }

    public static void NavigationBarStatusBar(Activity activity, boolean hasFocus) {
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = activity.getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_IMMERSIVE);
        }
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void exitAPP1(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.AppTask> appTaskList = activityManager.getAppTasks();
        for (ActivityManager.AppTask appTask : appTaskList) {
            appTask.finishAndRemoveTask();
        }
        System.exit(0);
    }

    // 显示底部导航栏
    public static void showBar(Context context) {
        //显示
        Intent intent = new Intent();
        intent.setAction("com.crust.nav.add");
        context.sendBroadcast(intent);
    }

    // 隐藏底部导航栏
    public static void hideBar(Context context) {
        Intent intent = new Intent();
        intent.setAction("com.crust.nav.remove");
        context.sendBroadcast(intent);
    }

    public static boolean installApp(Context context, String apkPath) {

        boolean b = ShellUtils.checkRootPermission();
        if (b) {
            int resultCode = PackageUtils.installSilent(context, apkPath);
            if (resultCode != PackageUtils.INSTALL_SUCCEEDED) {
                Toast.makeText(context, "升级失败", Toast.LENGTH_SHORT).show();
            }
        }
        return b;
    }

    /**
     * 获取当前手机系统语言。
     *
     * @return 返回当前系统语言。例如：当前设置的是“中文-中国”，则返回“zh-CN”
     */
    public static String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取当前系统上的语言列表(Locale列表)
     *
     * @return 语言列表
     */
    public static Locale[] getSystemLanguageList() {
        return Locale.getAvailableLocales();
    }

    /**
     * 获取当前手机系统版本号
     *
     * @return 系统版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     *
     * @return 手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return 手机厂商
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

    /**
     * 获取手机IMEI(需要“android.permission.READ_PHONE_STATE”权限)
     *
     * @return 手机IMEI
     */
    public static String getIMEI(Context ctx) {
//        TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Activity.TELEPHONY_SERVICE);
//        if (tm != null) {
//            return tm.getDeviceId();
//        }
        return null;
    }

    /* 获得SD卡总大小
     *
     * @return
     */
    public static String getSDTotalSize(Context mContext) {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return Formatter.formatFileSize(mContext, blockSize * totalBlocks);
    }

    /**
     * 获得sd卡剩余容量，即可用大小
     *
     * @return
     */
    public static String getSDAvailableSize(Context mContext) {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return Formatter.formatFileSize(mContext, blockSize * availableBlocks);
    }

    /**
     * 获得机身ROM总大小
     *
     * @return
     */
    public static String getRomTotalSize(Context mContext) {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return Formatter.formatFileSize(mContext, blockSize * totalBlocks);
    }

    /**
     * 获得机身可用ROM
     *
     * @return
     */
    public static String getRomAvailableSize(Context mContext) {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return Formatter.formatFileSize(mContext, blockSize * availableBlocks);
    }

    /**
     * 获取android当前可用内存大小
     */
    public static String getAvailMemory(Context context) {

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        return (double) mi.totalMem / (1024 * 1024 * 1024) + "GB";
    }

    /**
     *
     */
    public static String displayBriefMemory(Context context) {
        final ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(info);
        return (info.totalMem - info.availMem) / (1024 * 1024) + "MB";

//        Log.d(tag, "系统剩余内存:" + ( >> 10) + "k");
//        Log.d(tag, "系统是否处于低内存运行：" + info.lowMemory);
//        Log.d(tag, "当系统剩余内存低于" + info.threshold + "时就看成低内存运行");
    }

    public static String availableMemory(Context context) {
        final ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(info);
        return (info.totalMem) / (1024 * 1024) + "MB";

//        Log.d(tag, "系统剩余内存:" + ( >> 10) + "k");
//        Log.d(tag, "系统是否处于低内存运行：" + info.lowMemory);
//        Log.d(tag, "当系统剩余内存低于" + info.threshold + "时就看成低内存运行");
    }

}
