package com.tao.ndklearn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.orbyun.base.base.BaseActivity;
import com.orbyun.player.PlayManager;
import com.orbyun.rtmp.NativeUtils;
import com.orbyun.utils.ThreadManager;

import java.io.File;

public class MainActivity extends BaseActivity implements SurfaceHolder.Callback {

    private SurfaceHolder surfaceHolder;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void viewInit() {
        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.surface_view);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);

    }

    @Override
    public void dataInit() {

    }

    @Override
    public void eventInit() {

    }

    @Override
    public void surfaceCreated(final SurfaceHolder holder) {
        ThreadManager.getLongPool().execute(new Runnable() {
            @Override
            public void run() {
                String s = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "ws2.mp4";
                PlayManager.getPlayManager().play(surfaceHolder.getSurface(),"rtmp://192.168.0.81:1935/rtmplive/room'");
                PlayManager.getPlayManager().play(surfaceHolder.getSurface(),s);
            }
        });
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public void paly(View view) {
        PlayManager.getPlayManager().nativePlay(this,"");
    }
}
