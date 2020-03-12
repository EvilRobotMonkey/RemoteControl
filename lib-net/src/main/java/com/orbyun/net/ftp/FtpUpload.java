package com.orbyun.net.ftp;


import com.orbyun.utils.LOG;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * author : wangxf
 * e-mail : wallfacer_no3@163.com
 * date   : 2020-01-1419:48
 * desc   :
 */
public class FtpUpload {
    private static final String TAG = "FtpUpload";

    private static FTPClient ftpClient = new FTPClient();
    private static String LOCAL_CHARSET = "GBK";
    private static String SERVER_CHARSET = "ISO-8859-1";

    /**
     * 文件上传工具方法
     *
     * @param server
     * @param port
     * @param userName
     * @param userPassword
     * @param ftpPath      文件上传目录
     * @param prefix
     * @return
     */
    public static boolean upload(File file, String server, int port,
                                 String userName, String userPassword, String ftpPath, String prefix) {
        boolean result = false;
        try {
            ftpClient.connect(server, port);
            ftpClient.login(userName, userPassword);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            mkDir(ftpPath);// 创建目录
            // 设置上传目录 must
            ftpClient.changeWorkingDirectory("/" + ftpPath);
            if (FTPReply.isPositiveCompletion(ftpClient.sendCommand("OPTS UTF8", "ON"))) {// 开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）.
                LOCAL_CHARSET = "UTF-8";
            }
            String fileName = new String(file.getName().getBytes(LOCAL_CHARSET), SERVER_CHARSET);
            fileName = prefix + "-" + fileName;// 构建上传到服务器上的文件名 20-文件名.后缀
            FTPFile[] fs = ftpClient.listFiles(fileName);
            if (fs.length == 0) {
                LOG.i(TAG, "this file not exist ftp");

            } else if (fs.length == 1) {
                LOG.i(TAG, "this file exist ftp");
                ftpClient.deleteFile(fs[0].getName());
            }
            InputStream is = new FileInputStream(file);

            result = ftpClient.storeFile(fileName, is);
            is.close();
        } catch (IOException e) {
            result = false;
            e.printStackTrace();
        } finally {
            closeConnect();
            LOG.i(TAG, "上传完成。。。");

        }
        return result;
    }

    public static void closeConnect() {
        try {
            ftpClient.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 选择上传的目录，没有创建目录
     *
     * @param ftpPath 需要上传、创建的目录
     * @return
     */
    public static boolean mkDir(String ftpPath) {
        if (!ftpClient.isConnected()) {
            return false;
        }
        try {
            // 将路径中的斜杠统一
            char[] chars = ftpPath.toCharArray();
            StringBuffer sbStr = new StringBuffer(256);
            for (int i = 0; i < chars.length; i++) {
                if ('\\' == chars[i]) {
                    sbStr.append('/');
                } else {
                    sbStr.append(chars[i]);
                }
            }
            ftpPath = sbStr.toString();
            // System.out.println("ftpPath:" + ftpPath);
            if (ftpPath.indexOf('/') == -1) {
                // 只有一层目录
                ftpClient.makeDirectory(new String(ftpPath.getBytes(), "iso-8859-1"));
                ftpClient.changeWorkingDirectory(new String(ftpPath.getBytes(), "iso-8859-1"));
            } else {
                // 多层目录循环创建
                String[] paths = ftpPath.split("/");
                for (int i = 0; i < paths.length; i++) {
                    ftpClient.makeDirectory(new String(paths[i].getBytes(), "iso-8859-1"));
                    ftpClient.changeWorkingDirectory(new String(paths[i].getBytes(), "iso-8859-1"));
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
