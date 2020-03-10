package com.orbyun.player;

import android.content.Context;
import android.content.Intent;

/**
 * author : wangxf
 * e-mail : wallfacer_no3@163.com
 * date   : 2020/3/1010:55 AM
 * desc   :
 */
public class PlayManager {
    static {
        System.loadLibrary("playtools");
    }

    private static PlayManager playManager = new PlayManager();

    public static PlayManager getPlayManager() {
        return playManager;
    }

    public native int play(Object surface, String rtmp_url);

    public void nativePlay(Context context, String rtmp_url) {
        Intent intent = new Intent(context, PlayActivity.class);
        context.startActivity(intent);

    }

}
