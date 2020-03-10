package com.orbyun.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.util.DisplayMetrics;


import com.orbyun.utils.helper.FileHelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @discribtion:系统工具类
 */
public class PubUtil {
//    private static final String TAG = "PubUtil";
//    private static PubUtil instance = new PubUtil();
//    private DateHelper mDateHelper = DateHelper.getInstance();
//
//    private PubUtil() {
//    }
//
//    public static PubUtil getInstance() {
//        return instance;
//    }
//
//    public String generate(String imageUri) {
//        byte[] md5 = this.getMD5(imageUri.getBytes());
//        BigInteger bi = (new BigInteger(md5)).abs();
//        return bi.toString(36);
//    }
//
//    private byte[] getMD5(byte[] data) {
//        byte[] hash = null;
//
//        try {
//            MessageDigest e = MessageDigest.getInstance("MD5");
//            e.update(data);
//            hash = e.digest();
//        } catch (NoSuchAlgorithmException var4) {
//            LOG.d(TAG, "getMD5: " + var4);
//        }
//
//        return hash;
//    }
//
//
//    public int getScreenWidth(Activity activity) {
//        int width = 0;
//        try {
//            DisplayMetrics metric = new DisplayMetrics();
//            activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
//            width = metric.widthPixels;     // 屏幕宽度（像素）
//        } catch (Exception e) {
//            writeLog(getErrorInfoFromException(e));
//        } finally {
//            return width;
//        }
//    }
//
//
//    /**
//     * 初始化数据库文件路径
//     *
//     * @return
//     */
//    public String initDbPath(boolean devMode) {
//        String targetDirectory = "";
//        String path = "";//存储卡绝对路径
//        try {
//            if (devMode) {
//                path = FileHelper.getInstance().getExternalStorageDirectoryPath();
//                targetDirectory = path + Config.DATABASE_PATH;
//            } else {
//                targetDirectory = null;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            PubUtil.getInstance().writeLog(e);
//        } finally {
//            return targetDirectory;
//        }
//
//    }
//
//    /**
//     * 获取当前系统的版本号
//     *
//     * @return
//     */
//    public int getSystemVersion() {
//        return android.os.Build.VERSION.SDK_INT;
//    }
//
//    /**
//     * 获取当前应用程序版本
//     * PackageManager主要是管理应用程序包，通过它就可以获取应用程序信息。
//     *
//     * @return
//     */
//    public String getVersionName(Context context) {
//        String version = "";
//        PackageManager packageManager = null;
//        PackageInfo packInfo = null;
//        String packageName = "";
//        try {
//            packageManager = context.getPackageManager();
//            packageName = context.getPackageName();
//            if (null != packageManager) {
//                if (null != packageName && !(packageName.equalsIgnoreCase(""))) {
//                    packInfo = packageManager.getPackageInfo(packageName, 0);
//                    if (null != packInfo) {
//                        version = packInfo.versionName;
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            PubUtil.getInstance().writeLog(e);
//        } finally {
//            return version;
//        }
//    }
//
//
//    /**
//     * 获取本机sdcard卡根目录
//     *
//     * @return
//     */
//    public String getSDcardPath() {
//        String sdPath = "";
//        sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
//        return sdPath;
//        // return "/data/data/com.bmodule";
//    }
//
//    /**
//     * 获取本机WiFi的MAC地址
//     */
//    public String getWifiMAC(Context context) {
//        Environment.getExternalStorageDirectory().getAbsolutePath();
//        WifiManager mWifi = (WifiManager) context
//                .getSystemService(Context.WIFI_SERVICE);
//        String WifiMac = "";
//        // if (!mWifi.isWifiEnabled()) {
//        // mWifi.setWifiEnabled(true);
//        // }
//
//        WifiInfo wifiInfo = mWifi.getConnectionInfo();
//        if ((WifiMac = wifiInfo.getMacAddress()) == null) {
//            WifiMac = "No Wifi Device";
//        }
//        return WifiMac;
//    }
//
//    /**
//     * 获取设备上SIM卡的IMSI (国际移动用户ID) IMSI 与 SIM唯一对应 IMSI是一个 唯一的数字， 标识了GSM和UMTS
//     * 网络里的唯一一个用户. 它 存储 在手机的SIM卡里，它会通过手机发送到网络上.
//     * 要在AndroidManifest.xml中加上读取手机状态的权限<uses-permission
//     * android:name="android.permission.READ_PHONE_STATE"/>
//     */
////	public  String getIMSI(Context context) {
////		TelephonyManager mTelephonyMgr = (TelephonyManager) context
////				.getSystemService(Context.TELEPHONY_SERVICE);
////		String imsi = mTelephonyMgr.getSubscriberId();
////		if (null == imsi || "".equalsIgnoreCase(imsi)) {
////			imsi = "";
////		}
////		return imsi;
////	}
//
//    /**
//     * 获取设备的IMEI (国际移动设备ID). IMEI 与 设备唯一对应. IMEI也是一串唯一的数字， 标识了 GSM 和
//     * UMTS网络里的唯一一个手机. 它通常被打印在手机里电池下面的那一面，拨 *#06# 也能看到它.
//     * 要在AndroidManifest.xml中加上读取手机状态的权限<uses-permission
//     * android:name="android.permission.READ_PHONE_STATE"/>
//     * @param context
//     * @return
//     */
////	public  String getIMEI(Context context) {
////		TelephonyManager mTelephonyMgr = (TelephonyManager) context
////				.getSystemService(Context.TELEPHONY_SERVICE);
////		String imei = mTelephonyMgr.getDeviceId();
////		if (null == imei || "".equalsIgnoreCase(imei)) {
////			imei = "";
////		}
////		return imei;
////	}
//
//    /**
//     * 获取本机的手机号码, 目前失败率很高。不是所有的SIM卡都支持读取手机号码
//     *
//     * @param context
//     * @return
//     */
////	public  String getPhoneNum(Context context) {
////		TelephonyManager mTelephonyMgr = (TelephonyManager) context
////				.getSystemService(Context.TELEPHONY_SERVICE);
////		String phoneNum = mTelephonyMgr.getLine1Number();
////		if (null == phoneNum || "".equalsIgnoreCase(phoneNum)) {
////			phoneNum = "";
////		}
////		return phoneNum;
////	}
//
//    /**
//     * 清理内存 要谨慎使用，特别是尽量不要在线程内使用
//     */
//    public void clearMemory() {
//        System.gc();// 强制垃圾回收器释放无任何引用的对象空间
//        try {
//            Thread.sleep(200);
//        } catch (InterruptedException ie) {
//            writeLog("在可用内存不足的时候强制垃圾回收失败(PubUtil--clearMemory())：");
//            ie.printStackTrace();
//            return;
//        }
//        System.runFinalization(); // 在可用内存不足的时候由系统分配执行清理
//        try {
//            Thread.sleep(200);
//        } catch (InterruptedException ie) {
//            writeLog("在可用内存不足的时候由系统分配执行清理失败：");
//            ie.printStackTrace();
//            return;
//        }
//    }
//
//    /**
//     * 检查网络连接状况
//     *
//     * @return true为有连接
//     */
//    public boolean checkNetwork(Context context) {
//        boolean bRet = false;
//        ConnectivityManager connectivityManager = null;
//        NetworkInfo networkInfo = null;
//        try {
//            // 实例化ConnectivityManager
//            connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//
//            // 获得当前网络信息
//            networkInfo = connectivityManager.getActiveNetworkInfo();
//            // 判断是否连接
//            if (null != networkInfo && networkInfo.isConnected()) {
//                bRet = true;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            PubUtil.getInstance().writeLog(e);
//        } finally {
//            return bRet;
//        }
//    }
//
//    /**
//     * UUID
//     *
//     * @return
//     */
//    public String getUUID() {
//        String uuid = UUID.randomUUID().toString();
//        return uuid;
//    }
//
//    /**
//     * MD5转码
//     *
//     * @return
//     */
//    public String getMD5(String srcStr) {
//        int length = srcStr.length();
//        byte[] source = new byte[length];
//        source = srcStr.getBytes();
//
//        String s = null;
//        char hexDigits[] = { // 用来将字节转换成 16 进制表示的字符
//                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
//                'e', 'f'};
//        try {
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            md.update(source);
//            byte tmp[] = md.digest(); // MD5 的计算结果是一个 128 位的长整数，
//            // 用字节表示就是 16 个字节
//            char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
//            // 所以表示成 16 进制需要 32 个字符
//            int k = 0; // 表示转换结果中对应的字符位置
//            for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
//                // 转换成 16 进制字符的转换
//                byte byte0 = tmp[i]; // 取第 d 个字节
//                str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
//                // >>> 为逻辑右移，将符号位一起右移
//                str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
//            }
//            s = new String(str); // 换后的结果转换为字符串
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            return s;
//        }
//
//    }
//
//    /**
//     * 检测log内容为最近三天的 删除其他日期的日志
//     *
//     * @param currDate 系统当前日期
//     * @return
//     */
//    public ArrayList<File> findLogFile(String currDate) {
//        ArrayList<File> fileList = null;
//        String logPath = "";
//        List dateList = null;
//        File[] files = null;
//        String fileName = "";
//        String pDate = "";//前一天的日期
//        boolean isExists = false;
//        try {
//            //String tmp = DateHelper.getInstance().getCurrentTimestampForString();
//            currDate = currDate.split(" ")[0];
//            currDate = currDate.replaceAll("-", "");
//            dateList = new ArrayList();
//            dateList.add(currDate);
//            for (int m = 1; m <= 2; m++) {
//                pDate = "";
//                pDate = DateHelper.getInstance()
//                        .dateToString(DateHelper.getInstance()
//                                .modifyDate(DateHelper.getInstance().stringToUtilDate(DateHelper.getInstance().getCurrentTimestampForString(),
//                                        "yyyy-MM-dd HH:mm:ss"),
//                                        0, 0, -m, 0, 0, 0), "yyyy-MM-dd HH:mm:ss");
//                pDate = pDate.split(" ")[0];
//                pDate = pDate.replaceAll("-", "");
//                dateList.add(pDate);
//            }
//
//
//            logPath = FileHelper.getInstance().getExternalStorageDirectoryPath() + Config.LOGS_PATH + "/";
//            File file = new File(logPath);
//            if (null == file) return fileList;
//            if (file.isDirectory()) {
//                files = file.listFiles();
//                if (null == files || files.length == 0) return fileList;
//            } else {
//                return fileList;
//            }
////            LogUtil.debugE("info", "info当前log下的文件大小" + files.length);
//            for (int i = 0; i < files.length; i++) {
//                fileName = files[i].getName();
////                LogUtil.debugE("info", logPath + "路径下的文件有" + fileName);
//                fileName = fileName.replaceAll("log_", "");
//                fileName = fileName.replaceAll(".txt", "");
//                fileName = fileName.replaceAll(".log", "");
//                fileName = fileName.replaceAll("msg", "");
//                fileName = fileName.replaceAll("crash_exc", "");
//
//                // 判断log日期的时间与当前时间大于三天则被删除
//                fileName = fileName.replaceAll("-", "");
//
//                for (int n = 0; n < dateList.size(); n++) {
//                    pDate = dateList.get(n).toString();
//                    if (pDate.equalsIgnoreCase(fileName)) {
//                        isExists = true;
//                        break;
//                    }
//                }
//                if (isExists == false) {
//                    if (null == fileList) {
//                        fileList = new ArrayList<File>();
//                    }
//                    fileList.add(files[i]);//待删除的文件
//                }
//                isExists = false;
//            }
//            //清空文件数组，防止野指针
//            if (null != files) {
//                files = PubUtil.getInstance().resetArray(files);
//            }
//            files = null;
//
//            if (null != fileList && fileList.size() > 0) {
////                LogUtil.debugE("info", "当前系统可以删除的文件个数：" + fileList.size());
//            } else {
////                LogUtil.debugE("info", "当前系统可以删除的文件个数：" + "0");
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            // writeLog(e);
//
//        } finally {
//            return fileList;
//        }
//    }
//
//    /**
//     * 输出系统日志
//     *
//     * @param content
//     */
//    public boolean writeLog(String content) {
//        boolean bRet = false;
//        String sURL = "";
//        try {
//            //发布时，不需要打印日志
//            //if(UskytecApplication.devModle)
////                if (1 == 0) return false;
//            String dateStr = mDateHelper.getCurrentTimestampForString()
//                    .substring(0, 10);// 当天日期
//            String sdPath = getSDcardPath();
//            String logfilePath = sdPath + Config.ROOT_FILE_PATH + "/logs/log_" + dateStr
//                    + ".txt";
//            FileOutputStream fs = null;
//            File logfile = new File(logfilePath);
//            if (!(logfile.exists())) {
//                // 不存在，则新建
//                logfile.createNewFile();
//                fs = new FileOutputStream(logfilePath);
//            } else {
//                // 尾部追加的方式
//                fs = new FileOutputStream(logfilePath, true);
//            }
//
//            content = mDateHelper.getCurrentTimestampForString() + " " + content
//                    + "\r\n";//
//            // content = new String(content.getBytes(),"GBK");
//            byte[] bytes = content.getBytes();
//            fs.write(bytes, 0, bytes.length);
//            fs.close();
//            bytes = resetArray(bytes);
//            bRet = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            return bRet;
//        }
//    }
//
//    /**
//     * 判断应用市场中有是否有当前应用
//     *
//     * @param context
//     * @return
//     */
//    public static boolean judge(Context context) {
//        boolean bRet = false;
//        PackageManager packageManager = null;
//        Intent intent = null;
//        List<ResolveInfo> infos = null;
//        try {
//            intent = new Intent();
//            intent.setAction("android.intent.action.MAIN");
//            intent.addCategory("android.intent.category.APP_MARKET");
//            packageManager = context.getPackageManager();
//            if (null == packageManager) return bRet;
//            infos = packageManager.queryIntentActivities(intent, 0);
//            if ((null != infos) && (infos.size() > 0)) {
//                return bRet;
//            }
//            bRet = true;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            PubUtil.getInstance().writeLog(e);
//        } finally {
//            return bRet;
//        }
//    }
//
//    /**
//     * 转换异常后，再输出系统日志
//     *
//     * @param exception 异常
//     */
//    public boolean writeLog(Exception exception) {
//
//        if (Config.devMode) {
//            boolean bRet = false;
//            String content = "";
//            try {
//                content = this.getErrorInfoFromException(exception);
//                this.writeLog(content);
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                return bRet;
//            }
//        } else {
//            return false;
//        }
//    }
//
//    /**
//     * 创建外部文件
//     *
//     * @param content
//     */
//    public void genFile(String content, String filePath) {
//        boolean bRet = false;
//        String sURL = "";
//        try {
//
//            FileOutputStream fs = null;
//            File logfile = new File(filePath);
//            if (!(logfile.exists())) {
//                // 不存在，则新建
//                logfile.createNewFile();
//                fs = new FileOutputStream(filePath);
//            } else {
//                // 覆盖的方式
//                fs = new FileOutputStream(filePath, false);
//            }
//            byte[] bytes = content.getBytes();
//            fs.write(bytes, 0, bytes.length);
//            fs.close();
//            bytes = resetArray(bytes);
//            bRet = true;
//        } catch (Exception e) {
////			LOG.d("业务异常", "创建外部文件" + filePath + "失败");
//            e.printStackTrace();
//            writeLog("创建外部文件" + filePath + "失败:" + getErrorInfoFromException(e));
//        }
//    }
//
//    /**
//     * 创建目录
//     *
//     * @param filePath : 路径,不包括文件名
//     * @return
//     */
//    public boolean genFolder(String filePath) throws Exception {
//        boolean bRet = false;
//        File fileNew = new File(filePath);
//        if (!fileNew.exists()) {
//            // 如果不存在指定的目录，则创建
//            fileNew.mkdirs();
//        }
//        bRet = true;
////		LogUtil.debugE("info", "创建的数据库路径为" + fileNew);
//        return bRet;
//
//    }
//
//    /**
//     * 删除指定的文件
//     *
//     * @param fileName :文件路径,包括文件名
//     * @return
//     */
//    public boolean deleteFile(String fileName) {
//        boolean bRet = false;
//        File fileOld;
//        try {
//            fileOld = new File(fileName);
//            if (fileOld.exists()) {
//                // 如果存在指定的文件，则删除
//                fileOld.delete();
//            }
//            bRet = true;
//        } catch (Exception ex) {
//            bRet = false;
////			LOG.d("业务异常", "写日志异常" + ex.getCause() + "\r\n");
//            ex.printStackTrace();
//        } finally {
//            return bRet;
//        }
//    }
//    /*
//    // 删除已存在的文件
//    public boolean deleteExitsFiles(List<File> files) {
//       try {
//           if(null == files && files.size() <= 0) {
//               return false;
//           } else {
//               for(int d = 0; d < files.size(); d++) {
//                   files.get(d).delete();
//                   LogUtil.debugE("Application", files.get(d).getAbsolutePath());
//               }
//               return true;
//           }
//       } catch (Exception e) {
//           e.printStackTrace();
//           LogUtil.getInstance().e2f("PubUtil-DeleteExitsFile", e);
//           return false;
//       }
//    }
//    */
//
//
//    /**
//     * 向文件中写内容
//     *
//     * @param fileName :全路径的文件名
//     * @param content  :待写入的内容
//     * @param isRenew  :是否重建(有的时候需要尾部追加的方式写文件)
//     * @return
//     * @throws IOException
//     */
//    public boolean genFile(String fileName, String content,
//                           boolean isRenew) throws IOException {
//        boolean bRet = false;
//        FileOutputStream fs = null;
//        try {
//            File file = new File(fileName);
//            file.createNewFile();
//            if (isRenew == true) {
//                // 删除后重建
//                file.deleteOnExit();
//                Thread.sleep(20);
//                fs = new FileOutputStream(fileName);
//            } else {
//                // 尾部追加
//                fs = new FileOutputStream(fileName, true);
//            }
//
//            byte[] bytes = content.getBytes();
//            fs.write(bytes, 0, bytes.length);
//            fs.close();
//            Thread.sleep(100);
//            bytes = resetArray(bytes);
//            bRet = true;
//        } catch (Exception ex) {
//            bRet = false;
//            writeLog("向文件中写内容时出错(请先确认目录是否存在)：" + fileName + "\r\n"
//                    + ex.getCause() + "\r\n");
//            ex.printStackTrace();
//            writeLog(getErrorInfoFromException(ex));
//        } finally {
//            if (null != fs)
//                fs.close();
//            return bRet;
//        }
//    }
//
//    /**
//     * 向文件中写内容
//     *
//     * @param fileName :全路径的文件名
//     * @param content  :待写入的内容
//     * @param isRenew  :是否重建(有的时候需要尾部追加的方式写文件)
//     * @return
//     * @throws IOException
//     */
//    public boolean genFile(String fileName, byte[] content,
//                           boolean isRenew) throws IOException {
//        boolean bRet = false;
//        FileOutputStream fs = null;
//        try {
//            File file = new File(fileName);
//            file.createNewFile();
//            if (isRenew == true) {
//                // 删除后重建
//                file.deleteOnExit();
//                Thread.sleep(20);
//                fs = new FileOutputStream(fileName);
//            } else {
//                // 尾部追加
//                fs = new FileOutputStream(fileName, true);
//            }
//
//            // byte[] bytes = content.getBytes();
//            fs.write(content, 0, content.length);
//
//            fs.close();
//            Thread.sleep(100);
//            bRet = true;
//        } catch (Exception ex) {
//            bRet = false;
//            writeLog("向文件中写内容时出错：" + fileName + "\r\n" + ex.getCause() + "\r\n");
//            ex.printStackTrace();
//            writeLog(getErrorInfoFromException(ex));
//        } finally {
//            if (null != fs)
//                fs.close();
//            return bRet;
//        }
//    }
//
//    /**
//     * 将Exception.printStackTrace()转换为String输出
//     *
//     * @param e
//     * @return
//     */
//    public String getErrorInfoFromException(Exception e) {
//        try {
//            StringWriter sw = new StringWriter();
//            PrintWriter pw = new PrintWriter(sw);
//            e.printStackTrace(pw);
//            return "\r\n" + sw.toString() + "\r\n";
//        } catch (Exception e2) {
//            return "bad getErrorInfoFromException";
//        }
//    }
//
//    /**
//     * 生成随机数
//     */
//    public int randoms() {
//        int send[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
//                17, 18, 19};
//        int temp1, temp2, temp3;
//        Random r = new Random();
//        for (int i = 0; i < send.length; i++) // 随机交换send.length次
//        {
//            temp1 = Math.abs(r.nextInt()) % (send.length - 1); // 随机产生一个位置
//            temp2 = Math.abs(r.nextInt()) % (send.length - 1); // 随机产生另一个位置
//            if (temp1 != temp2) {
//                temp3 = send[temp1];
//                send[temp1] = send[temp2];
//                send[temp2] = temp3;
//            }
//        }
//        return send[1];
//        // return send;
//    }
//
//    /**
//     * 获取文件的字符集编码
//     *
//     * @param flieName
//     * @return
//     * @throws IOException
//     */
//    public String getCharSet(String flieName) throws IOException {
//        byte[] head = new byte[3];
//        InputStream inputStream = new FileInputStream(flieName);
//        inputStream.read(head);
//        String code = "";
//
//        code = "gb2312";
//        if (head[0] == -1 && head[1] == -2)
//            code = "UTF-16";
//        if (head[0] == -2 && head[1] == -1)
//            code = "Unicode";
//        if (head[0] == -17 && head[1] == -69 && head[2] == -65)
//            code = "UTF-8";
//
//        return code;
//    }
//
//    /**
//     * 将数字转换成俩位，不够左边补0
//     */
//    public String LeftPad_Tow_Zero(int str) {
//        java.text.DecimalFormat format = new java.text.DecimalFormat("00");
//        return format.format(str);
//    }
//
//    /**
//     * 判断字符是否手机号
//     */
//    public boolean isMobileNO(String mobiles) {
//        if (mobiles.length() >= 5) {
//            final String number = "0123456789";
//            for (int i = 0; i < mobiles.length(); i++) {
//                if (number.indexOf(mobiles.charAt(i)) == -1) {
//                    return false;
//                }
//            }
//            return true;
//        } else {
//            return false;
//        }
//        /*
//         * Pattern p = Pattern
//         * .compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$"); Matcher m =
//         * p.matcher(mobiles); return m.matches();
//         */
//    }
//
//    /**
//     * 判断是否是IP
//     *
//     * @param ipAddress
//     * @return
//     */
//    public boolean isIp(String ipAddress) {
//        String test = "([1-9]|[1-9]\\d|1\\d{2}|2[0-1]\\d|22[0-3])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
//        Pattern pattern = Pattern.compile(test);
//        Matcher matcher = pattern.matcher(ipAddress);
//        return matcher.matches();
//    }
//
//    /**
//     * 判断URL是否合法
//     * 目前不好使，暂时弃用
//     *
//     * @param sourceUrl
//     * @return
//     */
//    public boolean validateURL(String sourceUrl) {
//        boolean bRet = false;
//        String regEx = "";
//        Pattern pattern = null;
//        Matcher matcher = null;
//        try {
//            if (null == sourceUrl || sourceUrl.equalsIgnoreCase("")) return bRet;
//            regEx = "^(http|https|ftp)//://([a-zA-Z0-9//.//-]+(//:[a-zA-"
//                    + "Z0-9//.&%//$//-]+)*@)?((25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{"
//                    + "2}|[1-9]{1}[0-9]{1}|[1-9])//.(25[0-5]|2[0-4][0-9]|[0-1]{1}"
//                    + "[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)//.(25[0-5]|2[0-4][0-9]|"
//                    + "[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)//.(25[0-5]|2[0-"
//                    + "4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|([a-zA-Z0"
//                    + "-9//-]+//.)*[a-zA-Z0-9//-]+//.[a-zA-Z]{2,4})(//:[0-9]+)?(/"
//                    + "[^/][a-zA-Z0-9//.//,//?//'///////+&%//$//=~_//-@]*)*$";
//            pattern = Pattern.compile(regEx);
//            matcher = pattern.matcher(sourceUrl);
//            bRet = matcher.matches();
//        } catch (Exception e) {
//            e.printStackTrace();
//            writeLog(e);
//        } finally {
//            return bRet;
//        }
//    }
//
//    /**
//     * 获得当前日期时间的字符串
//     *
//     * @param format 日期或时间格式 (示例格式: yyyy-MM-dd HH:mm:ss)
//     * @return
//     */
//    public String getLocalDateTime(String format) {
//        String dateStr = "";
//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat(format);
//        dateStr = sdf.format(date);
//        return dateStr;
//    }
//
//    /**
//     * 判断是否是汉字
//     *
//     * @return
//     */
//    public boolean isChinese(String chSource) {
//        for (int index = 0; index < chSource.length(); index++) {
//            char cc = chSource.charAt(index);
//            Character.UnicodeBlock ub = Character.UnicodeBlock.of(cc);
//            if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
//                    || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
//                    || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
//                    || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
//                    || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
//                    || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//    /**
//     * 汉字到拼音(全拼)的转换
//     *
//     * @param hanzhis
//     * @return
//     */
//
//   /* public String toPinYin(String hanzhis) { //
//        // 需要考虑汉字英文字符混合的情况,如：SUN孙RUI芮，将它变成 SUNRUI hanzhis = hanzhis.trim();
//
//        int count = 0;
//        String regEx = "[\\u4e00-\\u9fa5]"; //
//        System.out.println(regEx);
//        String str = ""; // System.out.println(str);
//        Pattern p = Pattern.compile(regEx);
//        Matcher m = p.matcher(hanzhis);
//        while
//        (m.find()) {
//            System.out.print(m.group(0) + " ");
//            str = str + m.group(0);
//        }
//        LOG.d("提取出来的中文有：", str); // / System.out.println(); //
//        System.out.println(p.matches(regEx, hanzhis));
//
//        hanzhis = str;
//        String result = "";
//        for (int d = 0; d < hanzhis.length();
//             d++) {
//            String temp = hanzhis.substring(d, d + 1);
//            if
//            (temp.matches("[a-zA-Z]") || temp.matches("[0-9]") ||
//                    temp.matches("[ , ]")) { // result=result+temp; } else { //
//                result = result + "";
//                result = result + temp;
//            }
//        }
//        CharSequence s = result; //
//        CharSequence s = hanzhis;
//
//        char[] hanzhi = new char[s.length()];
//        for (int d = 0; d < s.length();
//             d++) {
//            hanzhi[d] = s.charAt(d);
//        }
//
//        char[] t1 = hanzhi;
//        String[] t2 = new String[s.length()]; // 设置输出格式
//        net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat t3 = new
//                HanyuPinyinOutputFormat();
//        t3.setCaseType(HanyuPinyinCaseType.UPPERCASE);
//        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
//        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
//
//        int t0 = t1.length;
//        String py = "";
//        try {
//            for (int d = 0; d < t0; d++) {
//                t2 = PinyinHelper.toHanyuPinyinStringArray(t1[d], t3);
//                py = py +
//                        t2[0].toString();
//            }
//        } catch (BadHanyuPinyinOutputFormatCombination e1) {
//            e1.printStackTrace();
//        }
//
//        return py.trim();
//    }*/
//
//
//    /**
//     汉字到拼音(简拼)的转换
//     *
//     * @param hanzhis
//     *            :要转换的汉字串
//     * @return
//     */
//    /*
//     * public  String toPY(String hanzhis) { StringBuilder sRet = new
//     * StringBuilder(); hanzhis = hanzhis.trim(); try { //
//     * 需要考虑汉字英文字符混合的情况,如：SUN孙RUI芮，将它变成 SUNRUI int count = 0; String regEx =
//     * "[\\u4e00-\\u9fa5]"; String str = ""; Pattern p = Pattern.compile(regEx);
//     * Matcher m = p.matcher(hanzhis); while (m.find()) { str = str +
//     * m.group(0); } LOG.d("提取出来的中文有：", str); // 开始转换 hanzhis = str; for (int j
//     * = 0; j < hanzhis.length(); j++) { char word = hanzhis.charAt(j); String[]
//     * pinyinArray = PinyinHelper .toHanyuPinyinStringArray(word); if
//     * (pinyinArray != null) { sRet.append(pinyinArray[0].charAt(0)); } else {
//     * sRet.append(word); } } } catch (Exception ex) { ex.printStackTrace(); }
//     * finally { return sRet.toString().toUpperCase(); } }
//     *//**
//     * 汉字到全拼、简拼的转换
//     *
//     * @param hanzhis
//     * @return
//     */
//    /*
//     * public  String toPinYinAndPY(String hanzhis) { //
//     * 需要考虑汉字英文字符混合的情况,如：SUN孙RUI芮，将它变成 SUNRUI hanzhis = hanzhis.trim();
//     *
//     * int count = 0; String regEx = "[\\u4e00-\\u9fa5]"; //
//     * System.out.println(regEx); String str = ""; // System.out.println(str);
//     * // 使用正则表达式过滤出汉字 Pattern p = Pattern.compile(regEx); Matcher m =
//     * p.matcher(hanzhis); while (m.find()) { // System.out.print(m.group(0) +
//     * " "); str = str + m.group(0); } // LOG.d("提取出来的中文有：", str); // /
//     * System.out.println(); // System.out.println(p.matches(regEx, hanzhis));
//     *
//     * hanzhis = str; String result = ""; for (int d = 0; d < hanzhis.length();
//     * d++) { String temp = hanzhis.substring(d, d + 1); if
//     * (temp.matches("[a-zA-Z]") || temp.matches("[0-9]") ||
//     * temp.matches("[ , ]")) { // result=result+temp; } else { //
//     * result=result+""; result = result + temp; } } CharSequence s = result; //
//     * CharSequence s = hanzhis;
//     *
//     * char[] hanzhi = new char[s.length()]; for (int d = 0; d < s.length();
//     * d++) { hanzhi[d] = s.charAt(d); }
//     *
//     * char[] t1 = hanzhi; String[] t2 = new String[s.length()]; // 设置输出格式
//     * net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat t3 = new
//     * HanyuPinyinOutputFormat(); t3.setCaseType(HanyuPinyinCaseType.UPPERCASE);
//     * t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
//     * t3.setVCharType(HanyuPinyinVCharType.WITH_V);
//     *
//     * int t0 = t1.length; String py = ""; try { // 开始转换全拼 for (int d = 0; d <
//     * t0; d++) { t2 = PinyinHelper.toHanyuPinyinStringArray(t1[d], t3); py = py
//     * + t2[0].toString(); } py = py + ",";// 全拼、简拼之间使用半角逗号间隔 // 开始转换简拼 for (int
//     * j = 0; j < hanzhis.length(); j++) { char word = hanzhis.charAt(j);
//     * String[] pinyinArray = PinyinHelper .toHanyuPinyinStringArray(word); if
//     * (pinyinArray != null) { py += pinyinArray[0].charAt(0); } else { py +=
//     * word; } } py = py.toUpperCase();// 转化成大写 } catch
//     * (BadHanyuPinyinOutputFormatCombination e1) { e1.printStackTrace(); }
//     *
//     * return py.trim(); }
//     */
//
//    /**
//     * 更新本地的webservices服务器地址
//     *
//     * @param ip : 124.42.126.206:88
//     * @return
//     */
//    public boolean genServerURL(String ip, Context context) {
//        boolean bRet = false;
//        String wsURL = "";// webservices数据同步地址
//        try {
//            String sdPath = getSDcardPath();// 存储卡路径
//            if (1 == 0) {
//                wsURL = "http://" + ip + "/services/PosServer";
//            } else {
//                wsURL = "http://" + ip + "/UContacts/services/PosServer";
//            }
//            PersistHelper.saveString("WEBSERVICEURL", wsURL);
////            LogUtil.debugE("WEBSERVICEURL","新的webservices地址为：" + wsURL);
//            String wsFileName = sdPath + "/ucontactspf/WebServices.txt";
//
//            File wsFile = new File(wsFileName);
//            if (wsFile.exists()) {
//                // 先删除
//                if (!(wsFile.delete()))
//                    writeLog("删除" + wsFileName + "失败");
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException ie) {
//                }
//
//            }
//            // 创建新的文件
//            File file = new File(wsFileName);
//            file.createNewFile();
//            FileOutputStream fs = new FileOutputStream(wsFileName);
//            byte[] bytes = wsURL.getBytes();
//            fs.write(bytes, 0, bytes.length);
//            try {
//                Thread.sleep(300);
//            } catch (InterruptedException ie) {
//            }
//            fs.close();
//            bRet = true;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            return bRet;
//        }
//    }
//
//    /**
//     * 获得设备的总内存，单位：字节
//     *
//     * @param activity
//     * @return
//     */
//
//    public long getTotalMemory(Activity activity) {
//        String str1 = "/proc/meminfo";// 系统内存信息文件
//        String str2 = "";
//        String[] arrayOfString;
//        long initial_memory = 0;
//        try {
//            FileReader localFileReader = new FileReader(str1);
//            BufferedReader localBufferedReader = new BufferedReader(
//                    localFileReader, 8192);
//            str2 = localBufferedReader.readLine();// 读取meminfo第一行，系统总内存大小
//
//            arrayOfString = str2.split("\\s+");
//
//            initial_memory = Integer.valueOf(arrayOfString[1]).intValue() * 1024;// 获得系统总内存，单位是KB，乘以1024转换为Byte
//            localBufferedReader.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            writeLog(e);
//        } finally {
//            return initial_memory;// Byte转换为KB或者MB，内存大小规格化
//        }
//    }
//
//    /**
//     * 判断内存，当内存不足时，进行提示
//     *
//     * @param activity
//     */
//    public String memoryManage(Activity activity) {
//        String strRet = "";
//        try {
//            long availMemory = getAvailMemory(activity);
//            long sys_Memory = getTotalMemory(activity);
////			LOG.d("test", "可用内存：" + availMemory + "/n" + "所需内存：");
////			LOG.d("系统内存", sys_Memory + "");
//            if (availMemory <= 30000000) {
//                strRet = "内存不足，请您及时清理";
//                writeLog("系统内存不足，可用内存：" + availMemory);
//            }
//        } catch (Exception e) {
////			LOG.d("memoryManage", "计算内存异常");
//            writeLog("系统内存不足，计算内存异常"
//                    + getErrorInfoFromException(e));
//        } finally {
//            return strRet;
//        }
//    }
//
//    /**
//     * 获得设备的可用内存，单位:字节
//     *
//     * @param activity
//     * @return
//     */
//    public long getAvailMemory(Activity activity) {
//        ActivityManager am = null;
//        MemoryInfo mi = null;
//        long lAvailMem = 0L;
//        try {
//            am = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
//            mi = new MemoryInfo();
//            am.getMemoryInfo(mi);
//            lAvailMem = mi.availMem;
//        } catch (Exception e) {
//            e.printStackTrace();
//            writeLog(e);
//        } finally {
//            return lAvailMem;
//        }
//
//    }
//
//
//    /**
//     * 发送彩信 recer: 接收人
//     */
//    /*
//     * public  Intent sendMMS(Context context, String recer) { boolean
//     * bRet = false; Intent intent = null; try { intent = new
//     * Intent(Intent.ACTION_SEND); PhoneInfoDto dto =
//     * PhoneInfoHelper.getInstance().getPhoneInfo( context); if (null == dto) {
//     * intent.setClassName("com.android.mms",
//     * "com.android.mms.ui.ComposeMessageActivity"); if
//     * (intent.resolveActivity(context.getPackageManager()) == null) {
//     * return intent; } } else { String manufacturer = dto.getManufacturer(); if
//     * (null == manufacturer) manufacturer = ""; String brand = dto.getBrand();
//     * if (null == brand) brand = ""; String fingerprint = dto.getFingerprint();
//     * if (null == fingerprint) fingerprint = ""; if
//     * (fingerprint.indexOf("samsung") >= 0 || manufacturer.indexOf("samsung")
//     * >= 0 || brand.indexOf("samsung") >= 0) { LOG.d("",
//     * "三星手机发送彩信比较另类,还需要选择东东"); PubUtil.wrtieLog("三星手机发送彩信比较另类,还需要选择东东"); } else
//     * { LOG.d("", "不是三星手机,直接进入发送彩信的界面,否则没有程序可选择");
//     * PubUtil.wrtieLog("不是三星手机,直接进入发送彩信的界面,否则没有程序可选择");
//     * intent.setClassName("com.android.mms",
//     * "com.android.mms.ui.ComposeMessageActivity"); if
//     * (intent.resolveActivity(context.getPackageManager()) == null) {
//     * return intent; } } }
//     *
//     * intent.putExtra("subject", "发送彩信");// 发送彩信 intent.putExtra("sms_body",
//     * "彩信发送（短信文字体）");// 彩信发送（短信文字体）" intent.setType("image/*"); //
//     * intent.setType("image/jpeg"); intent.putExtra("address", recer);
//     * intent.putExtra("compose_mode", false); intent.putExtra("exit_on_sent",
//     * true);
//     *
//     * bRet = true; } catch (Exception e) { LOG.d("sendMMS", "发送彩信失败");
//     * PubUtil.wrtieLog("发送彩信失败" + PubUtil.getErrorInfoFromException(e)); }
//     * finally { if (bRet == false) intent = null; return intent; } }
//     */
//
//    /**
//     * 判断是否是整数
//     *
//     * @param str
//     * @return
//     * @author 孙兵团 E-mail:sunbingtuan163@163.com
//     * @version 创建时间：Mar 12, 2010 4:33:48 PM
//     */
//    public boolean isInteger(String str) {
//        int begin = 0;
//        if (null == str || str.trim().equals("")) {
//            return false;
//        }
//        str = str.trim();
//        if (str.startsWith("+") || str.startsWith("-")) {
//            if (str.length() == 1) {
//                // "+" "-"
//                return false;
//            }
//            begin = 1;
//        }
//        for (int i = begin; i < str.length(); i++) {
//            if (!Character.isDigit(str.charAt(i))) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    /**
//     * 根据月日判断星座
//     *
//     * @return int
//     */
//    public String getConstellation(int m, int d) {
//        final String[] constellationArr = {"魔羯座", "水瓶座", "双鱼座", "白羊座", "金牛座",
//                "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "魔羯座"};
//        final int[] startDay = {19, 18, 20, 19, 20, 21, 22, 22, 22, 23, 22, 21};
//        int month = m;
//        int day = d;
//        if (day <= startDay[month - 1]) {
//            month = month - 1;
//        }
//        if (month >= 0) {
//            return constellationArr[month];
//        }
//        return constellationArr[11];
//    }
//
//    public String[] getAgeAndStar(String sdata) {
//        int age;
//        long od = 0;
//        String syear = sdata.substring(0, 4);
//        String smonth = sdata.substring(5, 7);
//        String sday = sdata.substring(8, 10);
//        int iMonth = Integer.parseInt(smonth);
//        int iDay = Integer.parseInt(sday);
//        age = getAge(Integer.parseInt(syear), iMonth, iDay);
////		od = olders(Integer.parseInt(syear), iMonth, iDay);
////		age = (int) od / 365;
//        String sstar = getConstellation(iMonth, iDay);
//        String[] str = {age + "", sstar};
//        return str;
//    }
//
//    public long olders(int year, int month, int day) {
//        Calendar nowDate = Calendar.getInstance(), oldDate = Calendar
//                .getInstance();
//        nowDate.setTime(new Date());// 设置为当前系统时间
//        oldDate.set(year, month, day);// 设置为1990年（6）月29日
//
//        long timeNow = nowDate.getTimeInMillis();
//        long timeOld = oldDate.getTimeInMillis();
//        long days = (timeNow - timeOld) / (1000 * 60 * 60 * 24);// 化为天
//        return days;
//    }
//
//    public int getAge(int year, int month, int day) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = new Date();
//        String format = sdf.format(date);
//        String[] split = format.split("-");
//        System.out.println(split[0] + split[1] + split[2]);
//        int currYear = Integer.parseInt(split[0]);
//        int currMonth = Integer.parseInt(split[1]);
//        int currDay = Integer.parseInt(split[2]);
//
//
//        int ageY = currYear - year;
//        int ageM = currMonth - month;
//        int ageD = currDay - day;
//        if (ageY <= 0) {
//            return 0;
//        }
//        if (ageY > 0) {//年大
//            if (month < currMonth) {//判断月
//                return ageY;
//            } else if (month > currMonth) {
//                return ageY - 1;
//            } else {//比较day
//                if (day <= currDay) {
//                    return ageY;
//                } else {
//                    return ageY - 1;
//                }
//            }
//        } else {
//            return 0;
//        }
//    }
//
//    /**
//     * 截取字符串
//     */
//    public String subString(String srt) {
//        if (!TextUtils.isEmpty(srt)) {
//            if (srt.length() > 16) {
//                srt = srt.substring(0, 16) + "...";
//            }
//        }
//        return srt;
//
//    }
//
//    public String subString(String srt, int num) {
//        if (!TextUtils.isEmpty(srt)) {
//            if (srt.length() > num) {
//                srt = srt.substring(0, num) + "...";
//
//            }
//        }
//        return srt;
//
//    }
//
//    public static int dip2px(Context context, float dipValue) {
//        final float scale = context.getResources().getDisplayMetrics().density;
//        return (int) (dipValue * scale + 0.5f);
//    }
//
//    /**
//     * 设置背景图片 将本地图片路径转换成drawable
//     */
//    public static Drawable getDrawable(String path) {
//        return new BitmapDrawable(BitmapFactory.decodeFile(path));
//    }
//
//
//    public boolean isGpsOpen(Context context) {
//        boolean bRet = false;
//        try {
//            LocationManager locationManager = (LocationManager) context.
//                    getSystemService(Context.LOCATION_SERVICE);
//            bRet = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
////            if (!bRet) {
////                bRet = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
////            }
//        } catch (Exception e) {
//            bRet = false;
//            PubUtil.getInstance().writeLog(PubUtil.getInstance().getErrorInfoFromException(e));
//        } finally {
//            return bRet;
//        }
//    }
//
//    //验证手机号
//    public boolean isMobile(String mobiles) {
////        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
////        Pattern p = Pattern.compile("^1(3|4|5|7|8)\\d{9}$");
//        Pattern p = Pattern.compile("(1[0-9]{10})");
//        Matcher m = p.matcher(mobiles);
//        return m.matches();
//    }
//
//    /**
//     * 判断邮箱是否合法
//     *
//     * @param email
//     * @return
//     */
//    public boolean isEmail(String email) {
//        if (null == email || "".equals(email)) return false;
//        //Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
//        Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
//        Matcher m = pattern.matcher(email);
//        return m.matches();
//    }
//
//    public SpannableString changeTextSize(Context context, String str, int start, int end, int sp) {
//        SpannableString spannableString = new SpannableString(str);
//        spannableString.setSpan(new AbsoluteSizeSpan(DisplayHelper.getInstance().sp2px(context, sp)), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        return spannableString;
//    }
//
//    /**
//     * 轉換時間字符串
//     *
//     * @param crtime 正常时间 2017-01-12 16:46:11
//     * @param type   需要类型 如需要16:46 传HH:mm
//     * @return 返回解析后的字符串
//     * @throws ParseException
//     */
//    public static String getRuleDate(String crtime, String type) throws ParseException {
//        String res = "";
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date da = new Date();
//        Date xiao = df.parse(crtime);
//        long l = da.getTime() - xiao.getTime();
//        long day = l / (24 * 60 * 60 * 1000);
//        long hour = (l / (60 * 60 * 1000) - day * 24);
//        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
//        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
//        // System.out.println(day + "天" + hour + "小时" + min + "分" + s + "秒");
//        // if (day > 7) {
//        // if ("list".equals(type)) {
//        // // 列表:MM-DD
//        // SimpleDateFormat dff = new SimpleDateFormat("MM-dd");
//        // res = dff.format(xiao);
//        // } else {
//        // // 详细:YY-MM-DD HH：MM
//        // SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        // res = dff.format(xiao);
//        // }
//        // } else if (day >= 2 && day <= 7) {
//        // res = day + "天前";
//        // } else if (day >= 1 && day < 2) {
//        // res = "1天前";
//        // } else if (day == 0 && hour >= 2 && hour < 24) {
//        // // HH：MM
//        // SimpleDateFormat dff = new SimpleDateFormat("HH:mm");
//        // res = dff.format(xiao);
//        // }
//        // else if (day == 0 && hour >= 1 && hour < 2) {
//        // // 1小时前
//        // res = "1小时前";
//        // } else if (day == 0 && hour == 0 && min >= 59 && min < 60) {
//        // // 59分钟前
//        // res = "59分钟前";
//        // } else if (day == 0 && hour == 0 && min >= 2 && min < 59) {
//        // // 1分钟前
//        // res = min + "分钟前";
//        // } else if (day == 0 && hour == 0 && min >= 1 && min < 2) {
//        // // 1分钟前
//        // res = "1分钟前";
//        // } else {
//        // // 1分钟前
//        // res = "1分钟前";
//        // }
//        // System.out.println(res);
//        // 新版规则：当时间为当日内 返回为几点几分
//        if (da.getDate() == xiao.getDate() && da.getMonth() == xiao.getMonth() && da.getYear() == xiao.getYear()) {
//            // HH：MM
//            SimpleDateFormat dff = new SimpleDateFormat("HH:mm");
//            res = dff.format(xiao);
//        } else {
//            // 当时间不在当日内时,返回日期
//            if ("list".equals(type)) {
//                // 列表:MM-DD
//                SimpleDateFormat dff = new SimpleDateFormat("MM-dd");
//                res = dff.format(xiao);
//            } else {
//                // 详细:YY-MM-DD HH：MM
//                SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                res = dff.format(xiao);
//            }
//        }
//
//        return res;
//    }
//
//    public static String getNowDateStr() {
//        SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        Date xiao = new Date();
//        String res = dff.format(xiao);
//        return res;
//    }
//
//    public static void main(String[] gar) throws ParseException {
//        String xiao = "2014-11-17 18:32:46";
//        getRuleDate(xiao, "list");
//    }
}
