package com.orbyun.net;

import android.app.Application;

import com.orbyun.base.Config;
import com.orbyun.base.api.net.NetRequestAPI;
import com.orbyun.base.api.net.NetResultInterFace;
import com.orbyun.net.ftp.Ftp;
import com.orbyun.net.okgo.OkgoManager;
import com.orbyun.net.webs.WebSocketHelper;
import com.orbyun.net.webs.WsocetManager;
import com.orbyun.utils.LOG;

import java.io.File;
import java.net.URISyntaxException;
import java.util.HashMap;

import okhttp3.WebSocket;

/**
 * @package com.orbyun.base.net
 * @file NetManager
 * @date 2019/4/29  4:09 PM
 * @autor wangxiongfeng
 */
public class NetManager implements NetRequestAPI {

    public NetRequestAPI mAPI;
    private static NetManager mNetManager = new NetManager();
    private static final String TAG = "NetManager";


    public void init(Application app) {
        OkgoManager.init(app, null, true, "ROBOT", 3000, Config.getFileTypePath(Config.DATABASE));
    }

    public static NetManager getInstance() {

        return mNetManager;
    }


    private void setAPI(NetRequestAPI API) {
        mAPI = API;
    }

    @Override
    public void doGet(String tag, String url, HashMap<String, String> parameter, boolean prompt, NetResultInterFace mNetResultInterFace) {
        OkgoManager.getInstance().doGet(tag, url, parameter, false, mNetResultInterFace);
    }

    @Override
    public void doPost(String tag, String url, HashMap<String, String> params, Boolean prompt, NetResultInterFace mNetResultInterFace) {

    }

    @Override
    public void doJson(String tag, String url, String json, Boolean prompt, String showText, NetResultInterFace mNetResultInterFace) {
        OkgoManager.getInstance().doJson(tag, url, json, false, "", mNetResultInterFace);

    }

    @Override
    public void doFileLoad(String tag, String url, int loadFielType, final String path, final String name, NetResultInterFace mNetResultInterFace) {
        OkgoManager.getInstance().doFileLoad(tag, url, loadFielType, path, name, mNetResultInterFace);

    }

    @Override
    public void doFileUpLoad(final String tag, String url, int loadFielType, String fileName, final NetResultInterFace mNetResultInterFace) {
        OkgoManager.getInstance().doFileUpLoad(tag, url, loadFielType, fileName, mNetResultInterFace);

    }

    public void cancleDownload() {
        OkgoManager.getInstance().cancleDownload();
    }


    public void ftpUpload(String tag, String url, int loadFielType, File file, String server, int port,
                          String userName, String userPassword, NetResultInterFace mNetResultInterFace) {
        Ftp.getInstance().doFileUpLoad(tag, url, 0, file, server, port, userName, userPassword, mNetResultInterFace);

    }


    public void webSocketConnect(String url, WsocetManager.WebCallBack webCallBack) {
        try {
            WsocetManager.create(url, webCallBack).begin();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void webSocketSendMsg(String msg) {
        LOG.i(TAG, msg);
        WsocetManager wsocetManager = WsocetManager.getWsocetManager();
        if (wsocetManager != null && wsocetManager.isOpen()) {
            WsocetManager.getWsocetManager().send(msg);
        } else {
            LOG.i(TAG, "未连接 websocket");
        }

    }

    public void closeWebSocket() {
        WsocetManager wsocetManager = WsocetManager.getWsocetManager();
        wsocetManager.close();
    }
}
