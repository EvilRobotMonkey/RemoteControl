package com.orbyun.base.base;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.orbyun.base.Config;
import com.orbyun.base.view.SoftKeyboardStateHelper;
import com.orbyun.utils.SystemUtils;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseActivity extends FragmentActivity {
    protected static int REQUEST_CODE_FOR_PERMISSIONS = 0;
    private static final int PERMISSON_REQUESTCODE = 0;
    public static boolean isNeedCheck = true; //判断是否需要检测，防止不停的弹框


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        //保持屏幕常亮
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        viewInit();

        checkPermissions(Config.needPermissions);

        dataInit();
        eventInit();


    }

    @Override
    protected void onStart() {
        super.onStart();
        //隐藏状态栏
        SystemUtils.NavigationBarStatusBar(this, true);
        //去除导航栏
        SystemUtils.hideBar(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private boolean checkPermissions(String... permissions) {
        List<String> needRequestPermissonList = findDeniedPermissions(permissions);
        if (null != needRequestPermissonList && needRequestPermissonList.size() > 0) {
            ActivityCompat.requestPermissions(this, needRequestPermissonList.toArray(
                    new String[needRequestPermissonList.size()]), PERMISSON_REQUESTCODE);
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获取权限集中需要申请权限的列表
     */
    private List<String> findDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissonList = new ArrayList<String>();
        for (String perm : permissions) {
            if (ContextCompat.checkSelfPermission(this, perm)
                    != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.shouldShowRequestPermissionRationale(this, perm)) {
                needRequestPermissonList.add(perm);
            }
        }

        return needRequestPermissonList;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] paramArrayOfInt) {
        if (requestCode == PERMISSON_REQUESTCODE) {
            if (verifyPermissions(paramArrayOfInt)) {
                dataInit();
                eventInit();
            }
        }
    }

    /**
     * 检测是否说有的权限都已经授权
     */
    protected boolean verifyPermissions(int[] grantResults) {
        if (grantResults.length > 0) {
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    //事件分发收缩键盘
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            SoftKeyboardStateHelper.hideKeyboard(v, ev);
        }
        return super.dispatchTouchEvent(ev);
    }


    @Override
    @CallSuper
    protected void onStop() {
        super.onStop();
    }

    @Override
    @CallSuper
    protected void onDestroy() {
        super.onDestroy();

    }

    public abstract int getLayoutId();

    public abstract void viewInit();

    public abstract void dataInit();

    public abstract void eventInit();
}

