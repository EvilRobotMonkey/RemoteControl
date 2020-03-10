package com.orbyun.base;

import android.Manifest;
import android.os.Environment;


import java.io.File;
import java.util.HashMap;

/**
 * Created by lixiong on 2018/5/22.
 */

public class Config {


    public final static String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.MODIFY_AUDIO_SETTINGS,
            Manifest.permission.RECORD_AUDIO,
    };
    public static HashMap<String, String> cookie;
    public static final String BROCASET_FLAG = "com.orbyun.ydt";
    //    public static String ASR_TTS_URL = "103.250.193.58:1028";
    public static String ASR_TTS_URL = "192.168.1.100:9008";

    //RecycleView Item类型
    public static String ROOT_FILE_PATH;


    public static int HEAD_LAYOUT = 2;  //头布局
    public static int FOOT_LAYOUT = 3;  //尾布局

    public static final String SHARED_PREFERENCE_NAME = "preference_qy";
    /* 3.0平台所有文件的根目录，以后其他文件夹都在该目录下  */


    private final static String APP_NAME = "basemoudle";
    private final static String ORDINARY_TOKEN = "bus_ordinary_session";

    public static String ATTACHMENTS_PATH;        // 附件文件夹， 存放下载的文件（功能模块中下载管理）
    public static String BASEINFO_PATH;               // 基础数据资源文件夹（背景、头像、个性签名）
    public static String BMS_PATH;
    public static String DATABASE_PATH;               // 数据库
    public static String LOGS_PATH;                        // 日志
    public static String ISPREADS_PATH;               // 侧滑菜单资源文件夹
    public static String OTHER_PATH;                      // 其他资源文件夹
    public static String IMAGES_PATH;                  // 业务数据图片文件夹
    public static String VOICES_PATH;            // 录音文件
    public static String VIDEO_PATH;                  // 秒拍文件
    public static String CONFIG_PATH;                  // 配置文件

    public final static int IMAGES = 0, VOICES = 1, VIDEO = 2, ATTACHMENTS = 3, BASEINFO = 4, DATABASE = 5, OTHER = 6, CONFIG = 7, LOGS = 8;

    public static final String IMAGE_CACHE_PATH = IMAGES_PATH + "/cache"; // 缓存文件的路径
    public static boolean devMode = false;
    private static String ROOTDIR;

    public static void init() {

        ROOT_FILE_PATH = File.separator + "gtjaydt";
        ATTACHMENTS_PATH = ROOT_FILE_PATH + File.separator + "attachments";        // 附件文件夹， 存放下载的文件（功能模块中下载管理）
        BASEINFO_PATH = ROOT_FILE_PATH + File.separator + "baseinfo";               // 基础数据资源文件夹（背景、头像、个性签名）
        BMS_PATH = ROOT_FILE_PATH + File.separator + "bms";
        DATABASE_PATH = ROOT_FILE_PATH + File.separator + "database";               // 数据库
        LOGS_PATH = ROOT_FILE_PATH + File.separator + "logs";                        // 日志
        ISPREADS_PATH = ROOT_FILE_PATH + File.separator + "ispreads";               // 侧滑菜单资源文件夹
        OTHER_PATH = ROOT_FILE_PATH + File.separator + "other";                      // 其他资源文件夹
        IMAGES_PATH = ROOT_FILE_PATH + File.separator + "image";                  // 业务数据图片文件夹
        VOICES_PATH = ROOT_FILE_PATH + File.separator + "chatVoices";            // 录音文件
        VIDEO_PATH = ROOT_FILE_PATH + File.separator + "video";                  // 秒拍文件
        CONFIG_PATH = ROOT_FILE_PATH + File.separator + "config";                  // 配置文件
        ROOTDIR = Environment.getExternalStorageDirectory().getAbsolutePath();


    }

    public static String getFileTypePath(int fileType) {

        switch (fileType) {
            case IMAGES:
                return ROOTDIR + Config.IMAGES_PATH;
            case VOICES:
                return ROOTDIR + Config.VIDEO_PATH;
            case VIDEO:
                return ROOTDIR + Config.VIDEO_PATH;
            case ATTACHMENTS:
                return ROOTDIR + Config.ATTACHMENTS_PATH;
            case BASEINFO:
                return ROOTDIR + Config.BASEINFO_PATH;
            case DATABASE:
                return ROOTDIR + Config.DATABASE_PATH;
            case CONFIG:
                return ROOTDIR + Config.CONFIG_PATH;
            case OTHER:
                return ROOTDIR + Config.OTHER_PATH;
            case LOGS:
                return ROOTDIR + Config.LOGS_PATH;
            default:
                return ROOTDIR + Config.ROOT_FILE_PATH;
        }
    }

}
