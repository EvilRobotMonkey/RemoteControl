package com.orbyun.player;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orbyun.base.base.BaseActivity;
import com.orbyun.net.NetManager;
import com.orbyun.net.webs.WsocetManager;
import com.orbyun.utils.LOG;
import com.orbyun.utils.helper.DisplayHelper;
import com.orbyun.utils.helper.JsonHelper;
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
    private boolean mNeedResume;
    private TextView localInfo;

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
        localInfo = (TextView) findViewById(R.id.local);


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
        mVideoView.setBufferSize(100);                  // 设置缓存
        mVideoView.setVideoQuality(VIDEOQUALITY_LOW);
        mVideoView.setHardwareDecoder(true);                        // 设置硬件解码
        mVideoView.setVideoChroma(MediaPlayer.VIDEOCHROMA_RGB565);  // 设置视频颜色, 视频一般没有带透明度的, 所以用565即可

        mVideoView.setMyMotionEvent(new VideoView.MyMotionEvent() {

            private ViewGroup.LayoutParams layoutParams;
            private int height;
            private int width;
            private PointF endPointf = new PointF();
            private PointF statPoint = new PointF();

            @Override
            public void touchEventCallBack(final MotionEvent ev) {


                switch (ev.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        layoutParams = mVideoView.getLayoutParams();
                        width = layoutParams.width;
                        height = layoutParams.height;
                        endPointf.set(ev.getX(), ev.getY());
                        statPoint.set(ev.getX(), ev.getY());

                    case MotionEvent.ACTION_MOVE:


                        endPointf.set(ev.getX(), ev.getY());
                        break;
                    case MotionEvent.ACTION_UP:

                        checkPoint(endPointf, width, height);
                        checkPoint(statPoint, width, height);
                        localInfo.setText(String.format("x:%f  y:%f", endPointf.x, endPointf.y));
                        if (statPoint.equals(endPointf.x, endPointf.y)) {
                            String down = GestureInfo.getInstance("DOWN", new PointF(statPoint.x / width, statPoint.y / height), new PointF(endPointf.x / width, endPointf.y / height));
                            NetManager.getInstance().webSocketSendMsg(down);
                        } else {
                            String down = GestureInfo.getInstance("MOVE", new PointF(statPoint.x / width, statPoint.y / height), new PointF(endPointf.x / width, endPointf.y / height));
                            NetManager.getInstance().webSocketSendMsg(down);

                        }

                        Log.i(TAG, "touchEventCallBack: " + width + "  " + height);

                        break;

                }


            }

            private void checkPoint(PointF pointF, int maxWidth, int maxHeight) {
                if (pointF.x > maxWidth) {
                    pointF.x = maxWidth;
                }
                if (pointF.y > maxHeight) {
                    pointF.y = maxHeight;
                }

                if (pointF.x < 0) {
                    pointF.x = 0;
                }
                if (pointF.y == 0) {
                    pointF.y = 0;
                }
            }
        });

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setPlaybackSpeed(1.0f);

            }
        });


        // 设置视频信息监听器
//        mVideoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
//            @Override
//            public boolean onInfo(MediaPlayer mp, int what, int extra) {
//                switch (what) {
//                    case MediaPlayer.MEDIA_INFO_BUFFERING_START: //开始缓存，暂停播放
//                        if (mVideoView.isPlaying()) {
//                            mVideoView.pause();
//                            mNeedResume = true;
//                        }
////                        mPbBuffering.setVisibility(View.VISIBLE);
//                        break;
//                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:   //缓存完成，继续播放
//                        if (mNeedResume) {
//                            mVideoView.start();
//                            mNeedResume = false;
//                        }
////                        mPbBuffering.setVisibility(View.GONE);
//                        break;
//                    case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED: //log下载速度
////                        LOG.i(TAG, "download rate: " + extra + " kb/s");
//                        break;
//                }
//                return true;
//            }
//        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        mVideoView.stopPlayback();
        NetManager.getInstance().closeWebSocket();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initWebSocket();
    }

    @Override
    public void eventInit() {

    }

    public void initWebSocket() {
        NetManager.getInstance().webSocketConnect("http://192.168.0.81:8080/ydtcontrol/websocket", new WsocetManager.WebCallBack() {
            @Override
            public void onError(Exception ex) {
                Log.i(TAG, "onError: " + ex.getMessage());
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                Log.i(TAG, "onClose: ");
            }

            @Override
            public void onMessage(String message) {
                LOG.i(TAG, message);
            }

            @Override
            public void onOpen() {
                Log.i(TAG, "onOpen: ");
                NetManager.getInstance().webSocketSendMsg("sender");

            }

            @Override
            public void connecting() {
                Log.i(TAG, "connecting: ");
            }

            @Override
            public void connectSuccess() {
                Log.i(TAG, "connectSuccess: ");
            }
        });
    }


    public static class GestureInfo {
        String who;
        String model;
        PointF startPoint;
        PointF endPoint;
        int width;
        int height;

        public static GestureInfo gestureInfo = new GestureInfo();

        public static String getInstance(String model, PointF start, PointF end) {

            gestureInfo.setModel(model);
            gestureInfo.setStartPoint(start);
            gestureInfo.setEndPoint(end);
            gestureInfo.setWho("sender");


            String s = JsonHelper.Object2String(gestureInfo);
            return s;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public void setStartPoint(PointF startPoint) {
            this.startPoint = startPoint;
        }

        public void setEndPoint(PointF endPoint) {
            this.endPoint = endPoint;
        }

    }
}
