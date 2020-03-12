
package com.orbyun.net;

import android.app.Activity;
import android.text.TextUtils;

import com.orbyun.base.api.net.ResultInfo;

/**
 * Created by Administrator on 2016/11/4.
 */

public class ReturnResultUtil {
    public static int judge(Activity activity, ResultInfo backResult, String state, boolean promptState) {
        if (null != backResult) {
            if (backResult.getCode() == 200||state.equals("OK")) {
                //                if (!TextUtils.isEmpty(backResult.getDesc())) {
                //                    CentreToast.showText(layout, backResult.getDesc());
                //                }
                return 200;
            } else {
//                CentreToast.showText(activity, "获取失败");
                return 444;
            }
        } else {
            if (TextUtils.isEmpty(state))
                return 111;
            if (state.equals("httperror")) {
//                CentreToast.showText(activity, "传输错误");
            } else if (state.equals("NoNetException")) {
//                CentreToast.showText(activity, "网络异常，请检查手机网络");
            } else if (state.equals("JsonSyntaxError")) {
//                CentreToast.showText(activity, "数据错误");
            } else if (state.equals("ERROR")) {
                if (promptState) {
//                            CentreToast.showText(layout, "获取失败");
//                    CentreToast.showText(activity, "获取失败");
                }
            } else {
//                CentreToast.showText(activity, "error");
            }
            return 111;
        }
    }
}
