package com.orbyun.utils.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

/**
 * author : wangxf
 * e-mail : wallfacer_no3@163.com
 * date   : 2019-11-2613:05
 * desc   :
 */
public class NetHelper {
    private static final String TAG = NetHelper.class.getName();
    private static NetHelper sInstance = null;
    private WifiManager mWifiManager;
    private int networkId;


    public NetHelper(Context context) {
        mWifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
    }

    public static NetHelper getInstance(Context context) {
        if (sInstance == null) {
            synchronized (NetHelper.class) {
                if (sInstance == null) {
                    sInstance = new NetHelper(context);
                }
            }
        }
        return sInstance;
    }


    /**
     * 尝试连接指定wifi
     *
     * @param ssid     wifi名
     * @param password 密码
     * @return 是否连接成功
     */
    public boolean connect(@NonNull String ssid, @NonNull String password) {

        Log.d(TAG, "connect() called with: ssid = [" + ssid + "], password = [" + password + "]");
        Log.d(TAG, "connect: wifi opened = " + openWifi());

        // 状态变成WIFI_STATE_ENABLED的时候才能执行下面的语句
        while (mWifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLING) {
            try {
                // 为了避免程序一直while循环，让它睡个100毫秒在检测……
                Thread.currentThread();
                Thread.sleep(100);
            } catch (InterruptedException ie) {
            }
        }

        boolean isConnected = isConnected(ssid);//当前已连接至指定wifi
        Log.d(TAG, "connect: is already connected = " + isConnected);

        if (isConnected) {
            return true;
        }

        //禁掉所有wifi
        for (WifiConfiguration c : mWifiManager.getConfiguredNetworks()) {
            mWifiManager.disableNetwork(c.networkId);
        }
        networkId = mWifiManager.addNetwork(newWifiConfig(ssid, password, true));

        boolean result = mWifiManager.enableNetwork(networkId, true);
        mWifiManager.saveConfiguration();

        Log.d(TAG, "connect: network enabled = " + result);
        return result;
    }


    /**
     * 根据wifi名与密码配置 WiFiConfiguration, 每次尝试都会先断开已有连接
     *
     * @param isClient 当前设备是作为客户端,还是作为服务端, 影响SSID和PWD
     */
    @NonNull
    private WifiConfiguration newWifiConfig(String ssid, String password, boolean isClient) {
        WifiConfiguration config = new WifiConfiguration();
        config.allowedAuthAlgorithms.clear();
        config.allowedGroupCiphers.clear();
        config.allowedKeyManagement.clear();
        config.allowedPairwiseCiphers.clear();
        config.allowedProtocols.clear();
        if (isClient) {//作为客户端, 连接服务端wifi热点时要加双引号
            config.SSID = "\"" + ssid + "\"";
            config.preSharedKey = "\"" + password + "\"";
        } else {//作为服务端, 开放wifi热点时不需要加双引号
            config.SSID = ssid;
            config.preSharedKey = password;
        }
        config.hiddenSSID = true;
        config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
        config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
        config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
        config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
        config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
        config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
        config.status = WifiConfiguration.Status.ENABLED;
        return config;
    }

    /**
     * @return 热点是否已开启
     */
    public boolean isWifiEnabled() {
        try {
            Method methodIsWifiApEnabled = WifiManager.class.getDeclaredMethod("isWifiApEnabled");
            return (boolean) methodIsWifiApEnabled.invoke(mWifiManager);
        } catch (Exception e) {
            Log.e(TAG, "isWifiEnabled: ", e);
            return false;
        }
    }

    /**
     * 是否已连接指定wifi
     */
    public boolean isConnected(String ssid) {
        WifiInfo wifiInfo = mWifiManager.getConnectionInfo();
        if (wifiInfo == null) {
            return false;
        }
        switch (wifiInfo.getSupplicantState()) {
            case AUTHENTICATING:
            case ASSOCIATING:
            case ASSOCIATED:
            case FOUR_WAY_HANDSHAKE:
            case GROUP_HANDSHAKE:
            case COMPLETED:
                return wifiInfo.getSSID().replace("\"", "").equals(ssid);
            default:
                return false;
        }
    }

    /**
     *  * 已有配置链接
     *  * 
     *  * @param wf
     *  * @return
     *  
     */
    public boolean connectOld(WifiConfiguration wf) {

// 状态变成WIFI_STATE_ENABLED的时候才能执行下面的语句，即当状态为WIFI_STATE_ENABLING时，让程序在while里面跑
        while (mWifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLING) {
            try {
// 为了避免程序一直while循环，让它睡个100毫秒在检测……
                Thread.currentThread();
                Thread.sleep(100);
            } catch (InterruptedException ie) {
            }
        }
        //禁掉所有wifi
        for (WifiConfiguration c : mWifiManager.getConfiguredNetworks()) {
            mWifiManager.disableNetwork(c.networkId);
        }
        boolean bRet = mWifiManager.enableNetwork(wf.networkId, true);
        mWifiManager.saveConfiguration();
        return bRet;
    }

    /**
     * 打开WiFi
     *
     * @return
     */
    public boolean openWifi() {
        boolean opened = true;
        if (!mWifiManager.isWifiEnabled()) {
            opened = mWifiManager.setWifiEnabled(true);
        }
        return opened;
    }

    /**
     * 关闭wifi
     *
     * @return
     */
    public boolean closeWifi() {
        boolean closed = true;
        if (mWifiManager.isWifiEnabled()) {
            closed = mWifiManager.setWifiEnabled(false);
        }
        return closed;
    }


    /**
     * 断开连接
     *
     * @return
     */
    public NetHelper disconnect() {
        if (networkId != 0) {
            mWifiManager.disableNetwork(networkId);
        }
        mWifiManager.disconnect();
        return this;
    }

    /**
     * 是否连接过指定Wifi
     */
    @Nullable
    public WifiConfiguration everConnected(String ssid) {
        List<WifiConfiguration> existingConfigs = mWifiManager.getConfiguredNetworks();
        if (existingConfigs == null || existingConfigs.isEmpty()) {
            return null;
        }
        ssid = "\"" + ssid + "\"";
        for (WifiConfiguration existingConfig : existingConfigs) {
            if (existingConfig.SSID.equals(ssid)) {
                return existingConfig;
            }
        }
        return null;
    }


    private String convertIp(int ipAddress) {
        if (ipAddress == 0) return null;
        return ((ipAddress & 0xff) + "." + (ipAddress >> 8 & 0xff) + "."
                + (ipAddress >> 16 & 0xff) + "." + (ipAddress >> 24 & 0xff));
    }

    public WifiManager getWifiManager() {
        return mWifiManager;
    }

    public List<ScanResult> getWifiList() {
        mWifiManager.startScan();

        List<ScanResult> scanWifiList = mWifiManager.getScanResults();
        List<ScanResult> wifiList = new ArrayList<>();
        if (scanWifiList != null && scanWifiList.size() > 0) {
            HashMap<String, Integer> signalStrength = new HashMap<String, Integer>();
            for (int i = 0; i < scanWifiList.size(); i++) {
                ScanResult scanResult = scanWifiList.get(i);
                if (!scanResult.SSID.isEmpty()) {
                    String key = scanResult.SSID + " " + scanResult.capabilities;
                    if (!signalStrength.containsKey(key)) {
                        signalStrength.put(key, i);
                        wifiList.add(scanResult);
                    }
                }
            }
        } else {
//            Log.e(ConstantUtils.TAG, "没有搜索到wifi");
        }
        return wifiList;
    }

    public String getGPRSIP() {

        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String getWIFIIP(Context context) {
        //获取wifi服务
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        //判断wifi是否开启
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();


        return (ipAddress & 0xFF) + "." +
                ((ipAddress >> 8) & 0xFF) + "." +
                ((ipAddress >> 16) & 0xFF) + "." +
                (ipAddress >> 24 & 0xFF);

    }

    public static String getWIFISSID(Context context) {
        //获取wifi服务
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        //判断wifi是否开启
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        String ssid = wifiInfo.getSSID();


        return ssid;

    }

    public String checkCurrentWifiState(Context mContext) {
        //获取wifi服务
        WifiManager wifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        //判断wifi是否开启
        if (wifiManager.isWifiEnabled()) {
            WifiInfo mWifiInfo = mWifiManager.getConnectionInfo();
            int wifi = mWifiInfo.getRssi();//获取wifi信号强度
            if (wifi > -50 && wifi < 0) {//最强
                return "最强";
            } else if (wifi > -70 && wifi < -50) {//较强
                return "较强";
            } else if (wifi > -80 && wifi < -70) {//较弱

                return "较弱";
            } else if (wifi > -100 && wifi < -80) {//微弱

                return "微弱";
            } else {
                //无连接
                return "无wifi连接";
            }
        }
        return "无wifi连接";
    }

    public boolean checkWifi(String ssid) {
        int wifi = 0;
        try {
            List<ScanResult> list = getWifiList();
            if (list != null) {
                for (ScanResult scanResult : list) {
                    if (scanResult.SSID.equals(ssid)) {
                        wifi = WifiManager.calculateSignalLevel(
                                scanResult.level, 10);
                        if (wifi > 3)
                            return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    // 定义几种加密方式，一种是WEP，一种是WPA，还有没有密码的情况
    public enum WifiCipherType {
        WIFICIPHER_WEP, WIFICIPHER_WPA, WIFICIPHER_NOPASS, WIFICIPHER_INVALID
    }

    /**
     * 获取ssid的加密方式
     */
    public WifiCipherType getCipherType(String ssid) {
        if (mWifiManager == null) {
            return null;
        }
        List<ScanResult> list = mWifiManager.getScanResults();

        for (ScanResult scResult : list) {

            if (!TextUtils.isEmpty(scResult.SSID) && scResult.SSID.equals(ssid)) {
                String capabilities = scResult.capabilities;
                if (!TextUtils.isEmpty(capabilities)) {

                    if (capabilities.contains("WPA")
                            || capabilities.contains("wpa")) {
                        Log.e("wifidemo", "wpa");
                        return WifiCipherType.WIFICIPHER_WPA;
                    } else if (capabilities.contains("WEP")
                            || capabilities.contains("wep")) {
                        Log.e("wifidemo", "wep");
                        return WifiCipherType.WIFICIPHER_WEP;
                    } else {
                        Log.e("wifidemo", "no");
                        return WifiCipherType.WIFICIPHER_NOPASS;
                    }
                }
            }
        }
        return WifiCipherType.WIFICIPHER_INVALID;
    }


    /**
     * GPRS网络开关 反射ConnectivityManager中hide的方法setMobileDataEnabled 可以开启和关闭GPRS网络
     *
     * @param isEnable
     * @throws Exception
     */
    public void toggleGprs(Context context, boolean isEnable) throws Exception {
        try {
            ConnectivityManager connManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            Class<?> cmClass = connManager.getClass();
            Class<?>[] argClasses = new Class[1];
            argClasses[0] = boolean.class;

            // 反射ConnectivityManager中hide的方法setMobileDataEnabled，可以开启和关闭GPRS网络
            Method method = cmClass.getMethod("setMobileDataEnabled", argClasses);
            method.invoke(connManager, isEnable);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
