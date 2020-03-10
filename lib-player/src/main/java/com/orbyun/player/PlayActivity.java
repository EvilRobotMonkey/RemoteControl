package com.orbyun.player;

import com.orbyun.base.base.BaseActivity;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * author : wangxf
 * e-mail : wallfacer_no3@163.com
 * date   : 2020/3/105:05 PM
 * desc   :
 */
public class PlayActivity extends BaseActivity {

    private VideoView mVideoView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_play;
    }

    @Override
    public void viewInit() {

    }

    @Override
    public void dataInit() {
        mVideoView = (VideoView) findViewById(R.id.vitamio_videoView);
        String path = "rtmp://192.168.0.81:1935/rtmplive/room";
        /*options = new HashMap<>();
        options.put("rtmp_playpath", "");
        options.put("rtmp_swfurl", "");
        options.put("rtmp_live", "1");
        options.put("rtmp_pageurl", "");*/
        mVideoView.setVideoPath(path);
        //mVideoView.setVideoURI(Uri.parse(path), options);
        mVideoView.setMediaController(new MediaController(this));
        mVideoView.requestFocus();

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setPlaybackSpeed(1.0f);
            }
        });

    }

    @Override
    public void eventInit() {

    }
}
