package com.orbyun.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

/**
 * @package com.boci.smart.utils
 * @file PermissionsUtils
 * @date 2018/9/19  下午3:34
 * @autor wangxiongfeng
 */
public class PermissionsUtils {
    private static boolean flag = false;

    public static boolean requestRecordAudio(Context context) {
        flag = false;
        new RxPermissions((Activity) context)
                //request中申请多个权限
                .request(Manifest.permission.RECORD_AUDIO, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        flag = aBoolean;
                    }
                });
        return flag;

    }


    public static boolean requestAlertWindow(Context context) {
        flag = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(context)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + context.getPackageName()));
            ((Activity) context).startActivityForResult(intent, 1);
            return false;
        } else {
            return true;
        }

    }

    public static boolean requestWriteSettings(Context context) {
        flag = false;
        new RxPermissions((Activity) context)
                //request中申请多个权限
                .request(Manifest.permission.WRITE_SETTINGS,Manifest.permission.CHANGE_CONFIGURATION)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        flag = aBoolean;
                    }
                });
        return flag;


    }

}




