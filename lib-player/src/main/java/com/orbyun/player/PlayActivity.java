package com.orbyun.player;

import android.view.Display;
import android.view.ViewGroup;

import com.orbyun.base.base.BaseActivity;
import com.orbyun.utils.LOG;
import com.orbyun.utils.helper.DisplayHelper;
import com.orbyun.utils.helper.MyHandleHelper;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

import static io.vov.vitamio.MediaPlayer.VIDEOQUALITY_LOW;

/**
 * author : wangxf
 * e-mail : wallfacer_no3@163.com
 * date   : 2020/3/105:05 PM
 * desc   :
 */
public class PlayActivity extends BaseActivity {

    private static final String TAG = "PlayActivity";
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
        mVideoView.setBufferSize(100);
        mVideoView.setVideoQuality(VIDEOQUALITY_LOW);


        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setPlaybackSpeed(1.0f);

                final int videoHeight = mVideoView.getVideoHeight();
                final int videoWidth = mVideoView.getVideoWidth();
                LOG.i(TAG, String.format("videoHeight: %d  videoWidth: %d ", videoHeight, videoWidth));


                Display d = getWindowManager().getDefaultDisplay();
                int mWidth = d.getWidth();
                int mHeight = d.getHeight();

                //判定是横屏还是竖屏
                if (videoWidth > videoHeight) {
                    //横屏
                } else {
                    //竖屏
                    //判断视频长度是否大于屏幕尺寸
                    float Vw = mWidth * 1.0f / videoWidth * 1.0f;
                    float Vh = mHeight * 1.0f / videoHeight * 1.0f;
                    float scal = 1;
                    if (Vw < Vh) {
                        scal = Vw;
                    } else {
                        scal = Vh;
                    }
                    final float newWidth = videoWidth * scal;
                    final float newHeigth = videoHeight * scal;
                    MyHandleHelper.getInstance().init().post(new Runnable() {
                        @Override
                        public void run() {
                            ViewGroup.LayoutParams layoutParams = mVideoView.getLayoutParams();
                            layoutParams.width = (int) newWidth;
                            layoutParams.height = (int) newHeigth;
                            mVideoView.setLayoutParams(layoutParams);

                        }
                    });

                }
            }
        });

    }

    @Override
    public void eventInit() {

    }
}
