package com.orbyun.net.ftp;



import com.orbyun.base.api.net.NetResultInterFace;
import com.orbyun.base.api.net.ResultInfo;

import java.io.File;

/**
 * author : wangxf
 * e-mail : wallfacer_no3@163.com
 * date   : 2019-12-2714:34
 * desc   :
 */
public class Ftp {
    private static final String TAG = "Ftp 上传";
    private static Ftp ftp;

    public static Ftp getInstance() {
        synchronized (Object.class) {
            if (ftp == null) {
                ftp = new Ftp();
            }
            return ftp;
        }
    }

    private static String ftpHost;

    private static String ftpSaveFilePath;

    private static String ftpEncode;

    private static int defaultTimeout;

    private static int ftpPort;

    private static String useName;

    private static String ftpPassWord;

    private static String ftpUseName;

    static {
        try {
            ftpHost = "192.168.1.118";
            ftpPort = 21;
            ftpUseName = "testftp";
            ftpPassWord = "12345678";
            ftpEncode = "UTF-8";
            ftpSaveFilePath = "ydtlog";
            defaultTimeout = 30000;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void doFileUpLoad(String tag, String url, int loadFielType, File file, String server, int port,
                             String userName, String userPassword, NetResultInterFace mNetResultInterFace) {

        if (FtpUpload.upload(file, ftpHost, ftpPort, ftpUseName, ftpPassWord, ftpSaveFilePath, "0")) {
            mNetResultInterFace.netWork(new ResultInfo(file.getName() + "@" + "success"), tag);
        } else {
            mNetResultInterFace.netWork(new ResultInfo("faile"), tag);

        }
    }

}
