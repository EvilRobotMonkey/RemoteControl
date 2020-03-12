package com.orbyun.net.webs;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.enums.ReadyState;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * author : wangxf
 * e-mail : wallfacer_no3@163.com
 * date   : 2020/3/56:02 PM
 * desc   :
 */
public class WsocetManager extends WebSocketClient {


    private static WsocetManager wsocetManager;
    private static WebCallBack webCallBack;


    public static WsocetManager create(String url, WebCallBack callBackTemp) throws URISyntaxException {
        webCallBack = callBackTemp;
        if (wsocetManager == null)
            wsocetManager = new WsocetManager(new URI(url));
        return wsocetManager;
    }

    public static WsocetManager getWsocetManager() {
        return wsocetManager;
    }

    public void begin() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                connect();
                while (!getReadyState().equals(ReadyState.OPEN)) {
                    webCallBack.connecting();
                }
                webCallBack.connectSuccess();
            }
        }).start();
    }

    public void close() {
        super.close();
        wsocetManager = null;
    }

    public void sendMessage(String msg) {
        if (isOpen()) {
            send(msg);
        }
    }


    public WsocetManager(URI serverUri) {
        super(serverUri);
    }

    public WsocetManager(URI serverUri, Draft protocolDraft) {
        super(serverUri, protocolDraft);
    }

    public WsocetManager(URI serverUri, Map<String, String> httpHeaders) {
        super(serverUri, httpHeaders);
    }

    public WsocetManager(URI serverUri, Draft protocolDraft, Map<String, String> httpHeaders) {
        super(serverUri, protocolDraft, httpHeaders);
    }

    public WsocetManager(URI serverUri, Draft protocolDraft, Map<String, String> httpHeaders, int connectTimeout) {
        super(serverUri, protocolDraft, httpHeaders, connectTimeout);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        webCallBack.onOpen();
    }

    @Override
    public void onMessage(final String message) {
        webCallBack.onMessage(message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        webCallBack.onClose(code, reason, remote);
    }

    @Override
    public void onError(Exception ex) {
        webCallBack.onError(ex);
    }

    public interface WebCallBack {
        void onError(Exception ex);

        void onClose(int code, String reason, boolean remote);

        void onMessage(String message);

        void onOpen();

        void connecting();

        void connectSuccess();
    }


}
