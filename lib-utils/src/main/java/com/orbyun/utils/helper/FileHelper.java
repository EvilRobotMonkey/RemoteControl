package com.orbyun.utils.helper;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;


import com.orbyun.utils.ArryUtils;
import com.orbyun.utils.PubUtil;
import com.orbyun.utils.TimeUtil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @discirbtion:文件管理类
 */
public class FileHelper {
    private static final String TAG = "FileUtil";
    private static FileHelper instance = new FileHelper();
    private TimeUtil timeUtil = TimeUtil.getInstance();

    private FileHelper() {
    }

    public static FileHelper getInstance() {
        return instance;
    }


    /**
     * 将sd卡下的资源copy到程序的系统目录下
     *
     * @param sdCard   sd卡下载的路径
     * @param destPath 目标文件
     * @return
     */
    public File copyFile(String sdCard, String destPath) {
        try {
            InputStream is = new FileInputStream(new File(sdCard));
            File file = new File(destPath);
            FileOutputStream fos = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            fos.flush();
            fos.close();
            is.close();
//			LogUtil.debugI(TAG, "已成功copy文件到" + destPath);
            return file;
        } catch (Exception e) {
//			LogUtil.getInstance().debugE(TAG, "文件copy失败，失败原因：" + e.toString());
            return null;
        }

    }

//	public static void writeLocationToSDcard(AMapLocation location,
//			boolean isWrite) {
//		if (isWrite) {
//			Writer info = new StringWriter();
//			PrintWriter printWriter = new PrintWriter(info);
//			SimpleDateFormat formatter = new SimpleDateFormat(
//					"yyyy年MM月dd日 HH:mm:ss");
//			String time = formatter.format(location.getTime());
//			String addressString = "";
//			if (location.getProvince() != null) {
//				addressString = location.getProvince() + location.getCity()
//						+ location.getDistrict();
//			} else {
//				addressString = location.getCity() + location.getDistrict();
//			}
//			String result = "<Location start >\r\n"
//					+ time
//					+ "    所在位置:"
//					+ addressString
//					+ ";经纬度:"
//					+ location.getLatitude()
//					+ ","
//					+ location.getLongitude()
//					+ "\r\n<Location end>\r\n";
//			printWriter.close();
//			try {
//				byte[] bytes = result.getBytes("UTF-8");
//				File ff = new File(Environment.getExternalStorageDirectory()
//						.toString(), "Location.txt");
//				FileOutputStream out = new FileOutputStream(ff, true);
//				out.write(bytes);
//				out.close();
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}

    /**
     * 判断sd卡状态的
     */
    public boolean getSDCardStatus() {
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

//	/**
//	 * 获取语音存储地址
//	 * @throws java.io.IOException
//	 */
//	public static File getAudioDir(String userId) throws IOException {
//		File file = null;
//		String sdStateString = Environment.getExternalStorageState();
//		if (sdStateString.equals(Environment.MEDIA_MOUNTED)) {
//			file = new File(ImageUtil.getChatVoicePath(userId));
//			LogUtil.debugI(TAG, file + "");
//			// 保证文件存在
//			if (!file.exists())
//				file.mkdir();
//		} else {
//			throw new IOException("sdcard could not be write");
//		}
//		return file;
//	}

    /**
     * 创建根目录
     *
     * @param path 目录路径
     */
    public void createDirFile(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * 创建文件
     *
     * @param path 文件路径
     * @return 创建的文件
     */
    public File createNewFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
//				LogUtil.debugI(TAG, "file==" + file);
            } catch (IOException e) {
                return null;
            }
        }
        return file;
    }

    /**
     * 判断SD是否可以
     *
     * @return
     */
    public boolean isSdcardExist() {
        boolean bRet = false;
        String sdState = "";
        try {
            sdState = Environment.getExternalStorageState();
            if (sdState.equalsIgnoreCase(Environment.MEDIA_REMOVED)) {
                //没有sdCard
                return bRet;
            }
            //在android2.3中，判断内置SD卡是否挂载
            if (sdState.equalsIgnoreCase(Environment.MEDIA_MOUNTED)) {
                //内置SD卡存在
                bRet = true;
                return bRet;
            }
            /*
            //判断外置SD卡是否挂载
            if(Environment.getStorageState(Environment.STORAGE_PATH_SD).equals(Environment.MEDIA_MOUNTED)){
               //外置sd卡存在
               bRet = true;
               return bRet;
            }
            */
        } catch (Exception e) {
            e.printStackTrace();
              Log.d(TAG,e.getMessage());
        } finally {
            return bRet;
        }
    }

    /**
     * 获取存储卡绝对路径
     *
     * @return
     */
    public String getExternalStorageDirectoryPath() {
        boolean bRet = false;
        String path = "";
        //判断是否存在存储卡
        bRet = isSdcardExist();
        if (bRet == false) {
            return null;
        }
        path = Environment.getExternalStorageDirectory().getAbsolutePath();
        return path;
    }

    public boolean isFileExits(String path, String fileName) {
        boolean bRet = false;

        try {
            /*
            if(!isSdcardExist()) {
                return false;
            }
            */
            File file = new File(path, fileName);
            if (file.exists()) {
                bRet = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
              Log.d(TAG,e.getMessage());
        } finally {
            return bRet;
        }
    }

    public static void writeFile(InputStream stream, File file) throws IOException {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);

            byte[] buffer = new byte[1024];

            int leng = 0;
            while ((leng = stream.read(buffer)) != -1) {
                fos.write(buffer, 0, leng);
                fos.flush();
            }
            stream.close();
            fos.close();


        } catch (Exception e) {
            e.printStackTrace();
            if (stream != null) {
                stream.close();
            }

            if (fos != null) {
                fos.close();
            }

        }

    }

//    /**
//     * 获取语音存储地址
//     *
//     * @return
//     * @throws IOException
//     */
//    public File getAudioDir(String userId) throws IOException {
//        File file = null;
//        String sdStateString = Environment.getExternalStorageState();
//        if (sdStateString.equals(Environment.MEDIA_MOUNTED)) {
//            file = new File(getChatVoicePath(userId));
////            LogUtil.debugI(TAG, file + "");
//            // 保证文件存在
//            if (!file.exists())
//                file.mkdir();
//        } else {
//            throw new IOException("sdcard could not be write");
//        }
//        return file;
//    }
//
//    /**
//     * 返回聊天的语音聊天信息地址
//     *
//     * @param userId 接收人的id
//     * @return
//     */
//    private String getChatVoicePath(String userId) {
//        String target = getExternalStorageDirectoryPath() + File.separator
//                + Config.VOICES_PATH + File.separator + userId + File.separator;
//        File file = new File(target);
//        if (!file.exists()) {
//            file.mkdirs();
//        }
//        return target;
//    }

    /**
     * 删除文件
     **/
    public boolean deleteFile(String fileName) {
        if (!isSdcardExist()) {
            return true;
        }
        try {
            File file = new File(fileName);
            if (file.exists()) {
                file.delete();
                return true;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取音频文件
     */
//    public List<MediaData> getLocalAudios(Context context) {
//        List<MediaData> mediaDatas = null;
//        try {
//            ContentResolver resolver = context.getContentResolver();
//
//            Uri VIDEO_URI = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//            String[] projection = new String[]{MediaStore.Audio.Media.DATA,
//                    MediaStore.Audio.Media.DATE_MODIFIED, MediaStore.Audio.Media.SIZE};
//
//            Cursor cursor = resolver.query(VIDEO_URI, projection, MediaStore.Audio.Media.SIZE + "<?"
//                    , new String[]{"10485760"}, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
//
//            if (null == cursor) {
//                return null;
//            }
//            mediaDatas = new ArrayList<MediaData>();
//            long size = 0;
//            while (cursor.moveToNext()) {
//                MediaData mediaData = new MediaData();
//                mediaData.setTitle(stringHelper.splitFileNameFromPath(cursor.getString(0)));
//                mediaData.setPath(cursor.getString(0));
//                mediaData.setModifyDate(timeUtil.formatLocalFileDate(cursor.getString(1)));
//                size = Long.parseLong(cursor.getString(2));
//                mediaData.setSize(stringHelper.FormetFileSize(size));
//                mediaData.setType(0);
//                mediaDatas.add(mediaData);
//            }
//            cursor.close();
//            return mediaDatas;
//        } catch (Exception e) {
//            e.printStackTrace();
//            PubUtil.getInstance().writeLog("获取视频文件："+e);
//        }
//        return null;
//    }

    /**
     * 获取手机中的视频文件
     */
//    public List<MediaData>  getLocalVideos(Context context) {
//        List<MediaData> mediaDatas = null;
//        try {
//            ContentResolver resolver = context.getContentResolver();
//
//            Uri VIDEO_URI = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
//            String[] projection = new String[]{MediaStore.Video.Media.DATA,
//                    MediaStore.Video.Media.DATE_MODIFIED, MediaStore.Video.Media.SIZE};
//
//            Cursor cursor = resolver.query(VIDEO_URI, projection, MediaStore.Video.Media.SIZE + "<?"
//                    , new String[]{"10485760"}, MediaStore.Video.Media.DEFAULT_SORT_ORDER);
//
//            if (null == cursor) {
//                return null;
//            }
//
//            mediaDatas = new ArrayList<MediaData>();
//            long size = 0;
//            while (cursor.moveToNext()) {
//                MediaData mediaData = new MediaData();
//                mediaData.setTitle(stringHelper.splitFileNameFromPath(cursor.getString(0)));
//                mediaData.setPath(cursor.getString(0));
//                mediaData.setModifyDate(timeUtil.formatLocalFileDate(cursor.getString(1)));
//                size = Long.parseLong(cursor.getString(2));
//                mediaData.setSize(stringHelper.FormetFileSize(size));
//                mediaData.setType(0);
//                mediaDatas.add(mediaData);
//            }
//            cursor.close();
//            return mediaDatas;
//        } catch (Exception e) {
//            e.printStackTrace();
//            PubUtil.getInstance().writeLog("获取视频文件"+e);
//        }
//        return null;
//    }

    // 获取当前目录下所有的mp4文件
    public static Vector<String> GetVideoFileName(String fileAbsolutePath) {
        Vector<String> vecFile = new Vector<String>();
        File file = new File(fileAbsolutePath);
        File[] subFile = file.listFiles();

        for (int iFileLength = 0; iFileLength < subFile.length; iFileLength++) {
            // 判断是否为文件夹
            if (!subFile[iFileLength].isDirectory()) {
                String filename = subFile[iFileLength].getName();
                // 判断是否为MP4结尾
                if (filename.trim().toLowerCase().endsWith(".mp4")) {
                    vecFile.add(filename);
                }
            }
        }
        return vecFile;
    }

    public List<File> getFiles() {
        String rootPath = getExternalStorageDirectoryPath();
        File rootFile = new File(rootPath);
        List<File> files = new ArrayList<File>();
        getAllFiles(rootFile, files);
        return files;
    }

    // 遍历接收一个文件路径，然后把文件子目录中的所有文件遍历并输出来
    private void getAllFiles(File root, List<File> file) {
        File[] fs = null;
        File[] files = null;
        try {
            files = root.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (f.isDirectory()) {
                        fs = f.listFiles(IMAGES_FILTER);
                        if (null != fs) {
                            file.addAll(Arrays.asList(fs));
//                            fs = pubUtil.resetArray(fs);
//                            fs = null;
                        }
//                        if(null == files) {
//                            files = pubUtil.resetArray(fs);
//                            files = null;
//                        }
                        Thread.sleep(10);
                        // FIXME 暂时只搜索一次
//                        getAllFiles(f, file);
                    }
                }
            }
            if (null != fs) {
                fs = ArryUtils.resetArray(fs);
                fs = null;
                files = ArryUtils.resetArray(files);
                files = null;

            }
        } catch (Exception e) {
            e.printStackTrace();
              Log.d(TAG,e.getMessage());
        } finally {
            return;
        }
    }

    private final java.io.FileFilter IMAGES_FILTER = new java.io.FileFilter() {

        public boolean accept(File f) {
            return f.getName().matches("^.*?\\.(doc|dot|docx|xls|xlt" +
                    "|xlsx|ppt|pot|pps|pdf|wps|wpt|et|ett|dps|dpt|txt|rar|zip|dps)$");
        }
    };

    /**
     * 递归删除文件和文件夹
     *
     * @param file
     * @param isDelDirectory:是否删除目录 true 删除 false 不删除
     *                              要删除的根目录
     */
    public boolean DeleteFile(File file, boolean isDelDirectory) {
        boolean bRet = false;
        File[] childFile = null;
        try {
            if (file.isFile()) {
                bRet = file.delete();
                return bRet;
            }
            if (file.isDirectory()) {
                childFile = file.listFiles();
                if (null == childFile || childFile.length == 0) {
                    if (isDelDirectory) {
                        bRet = file.delete();
                    }
                    return bRet;
                }
                for (File f : childFile) {
                    DeleteFile(f, isDelDirectory);
                }
                if (isDelDirectory) {
                    bRet = file.delete();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
              Log.d(TAG,e.getMessage());
        } finally {
            if (null != childFile) {
                childFile = ArryUtils.resetArray(childFile);
                childFile = null;
            }
            return bRet;
        }
    }

    public static final int SIZETYPE_B = 1;// 获取文件大小单位为B的double值
    public static final int SIZETYPE_KB = 2;// 获取文件大小单位为KB的double值
    public static final int SIZETYPE_MB = 3;// 获取文件大小单位为MB的double值
    public static final int SIZETYPE_GB = 4;// 获取文件大小单位为GB的double值

    /**
     * 获取文件指定文件的指定单位的大小
     *
     * @param filePath 文件路径
     * @param sizeType 获取大小的类型1为B、2为KB、3为MB、4为GB
     * @return double值的大小
     */
    public static double getFileOrFilesSize(String filePath, int sizeType) {
        File file = new File(filePath);
        long blockSize = 0;
        try {
            if (file.isDirectory()) {
                blockSize = getFileSizes(file);
            } else {
                blockSize = getFileSize(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FormetFileSize(blockSize, sizeType);
    }

    /**
     * 调用此方法自动计算指定文件或指定文件夹的大小
     *
     * @param filePath 文件路径
     * @return 计算好的带B、KB、MB、GB的字符串
     */
    public static String getAutoFileOrFilesSize(String filePath) {
        File file = new File(filePath);
        long blockSize = 0;
        try {
            if (file.isDirectory()) {
                blockSize = getFileSizes(file);
            } else {
                blockSize = getFileSize(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FormetFileSize(blockSize);
    }

    /**
     * 获取指定文件大小
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static long getFileSize(File file) throws Exception {
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available();
        } else {
//            file.createNewFile();
        }
        return size;
    }

    /**
     * 获取指定文件夹
     *
     * @param f
     * @return
     * @throws Exception
     */
    public static long getFileSizes(File f) throws Exception {
        long size = 0;
        File flist[] = f.listFiles();
        for (int i = 0; i < flist.length; i++) {
            if (flist[i].isDirectory()) {
                size = size + getFileSizes(flist[i]);
            } else {
                size = size + getFileSize(flist[i]);
            }
        }
        return size;
    }

    /**
     * 转换文件大小
     *
     * @param fileS
     * @return
     */
    public static String FormetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        String wrongSize = "0B";
        if (fileS == 0) {
            return wrongSize;
        }
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "GB";
        }
        return fileSizeString;
    }

    /**
     * 转换文件大小,指定转换的类型
     *
     * @param fileS
     * @param sizeType
     * @return
     */
    public static double FormetFileSize(long fileS, int sizeType) {
        DecimalFormat df = new DecimalFormat("#.00");
        double fileSizeLong = 0;
        switch (sizeType) {
            case SIZETYPE_B:
                fileSizeLong = Double.valueOf(df.format((double) fileS));
                break;
            case SIZETYPE_KB:
                fileSizeLong = Double.valueOf(df.format((double) fileS / 1024));
                break;
            case SIZETYPE_MB:
                fileSizeLong = Double.valueOf(df.format((double) fileS / 1048576));
                break;
            case SIZETYPE_GB:
                fileSizeLong = Double.valueOf(df
                        .format((double) fileS / 1073741824));
                break;
            default:
                break;
        }
        return fileSizeLong;
    }


    public static boolean writeFile(String str, File file) throws Exception {
        if (file.exists()) {


            FileOutputStream fos = new FileOutputStream(file);
            fos.write(str.getBytes());
            fos.close();
            return true;

        } else {
            return false;
        }
    }

    public static String readFile(File file) throws Exception {
        if (file.canRead()) {
            StringBuffer sb = new StringBuffer();
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file), "utf-8");
            BufferedReader bfreader = new BufferedReader(reader);
            String line;
            while ((line = bfreader.readLine()) != null) {//包含该行内容的字符串，不包含任何行终止符，如果已到达流末尾，则返回 null
                sb.append(line);
            }
            reader.close();
            bfreader.close();
            return sb.toString();
        } else {
            return "";
        }


    }

    public static List<String> freadFile(File file) throws Exception {
        List<String> strList = new ArrayList<>();

        if (!file.exists())
            return strList;
        StringBuffer sb = new StringBuffer();
        InputStreamReader reader = new InputStreamReader(new FileInputStream(file), "utf-8");
        BufferedReader bfreader = new BufferedReader(reader);
        String line;
        while ((line = bfreader.readLine()) != null) {//包含该行内容的字符串，不包含任何行终止符，如果已到达流末尾，则返回 null
            if (!TextUtils.isEmpty(line)) {
                strList.add(line);
            }

        }
        reader.close();
        bfreader.close();
        return strList;

    }

    public static boolean createFile(String path, String name) throws Exception {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        if (!TextUtils.isEmpty(name)) {
            File nameFile = new File(path, name);
            if (nameFile.exists())
                return true;
            nameFile.createNewFile();
        }
        return true;

    }

    public static boolean appendFile(String file, String content) {
        Log.e(TAG, "写入内容: " + content);
        FileWriter fw = null;
        try {
            File f = new File(file);
            fw = new FileWriter(f, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        pw.println(content);
        pw.print("\n");
        pw.flush();
        try {
            fw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return true;

    }

    /**
     * 压缩文件
     * srcFileString 要压缩的文件或文件夹
     * zipFileString 压缩完成的Zip路径
     * 将文件进行压缩
     */
    public static void zipFolder(String srcFileString, String zipFileString) throws Exception {
        //创建Zip包
        java.util.zip.ZipOutputStream outZip = new java.util.zip.ZipOutputStream(new java.io.FileOutputStream(zipFileString));

        //打开要输出的文件
        java.io.File file = new java.io.File(srcFileString);

        //压缩
        zipFolder(file.getParent() + java.io.File.separator, file.getName(), outZip);

        //完成,关闭
        outZip.finish();
        outZip.close();
    }

    /**
     * 压缩功能
     */
    private static void zipFolder(String folderString, String fileString, java.util.zip.ZipOutputStream zipOutputSteam) throws Exception {

        if (zipOutputSteam == null)
            return;

        java.io.File file = new java.io.File(folderString + fileString);

        //判断是不是文件
        if (file.isFile()) {

            java.util.zip.ZipEntry zipEntry = new java.util.zip.ZipEntry(fileString);
            java.io.FileInputStream inputStream = new java.io.FileInputStream(file);
            zipOutputSteam.putNextEntry(zipEntry);

            int len;
            byte[] buffer = new byte[4096];

            while ((len = inputStream.read(buffer)) != -1) {
                zipOutputSteam.write(buffer, 0, len);
            }

            zipOutputSteam.closeEntry();
        } else {

            //文件夹的方式,获取文件夹下的子文件
            String fileList[] = file.list();

            //如果没有子文件, 则添加进去即可
            if (fileList.length <= 0) {
                java.util.zip.ZipEntry zipEntry = new java.util.zip.ZipEntry(fileString + java.io.File.separator);
                zipOutputSteam.putNextEntry(zipEntry);
                zipOutputSteam.closeEntry();
            }

            //如果有子文件, 遍历子文件
            for (String aFileList : fileList) {
                zipFolder(folderString, fileString + File.separator + aFileList, zipOutputSteam);
            }

        }
    }

    /**
     * 解压缩功能.
     * 将zipFile文件解压到folderPath目录下.
     *
     * @throws Exception
     */
    public static int upZipFile(File zipFile, String folderPath) {
        //public static void upZipFile() throws Exception{
        ZipFile zfile = null;
        try {
            zfile = new ZipFile(zipFile);
            Enumeration zList = zfile.entries();
            ZipEntry ze = null;
            byte[] buf = new byte[1024];
            while (zList.hasMoreElements()) {
                ze = (ZipEntry) zList.nextElement();
                if (ze.isDirectory()) {
                    String dirstr = folderPath + "/" + ze.getName();
                    //dirstr.trim();
                    try {
                        dirstr = new String(dirstr.getBytes("8859_1"), "GB2312");
                    } catch (UnsupportedEncodingException e) {
                        System.out.println("3 e " + e.toString());
                        e.printStackTrace();
                    }
                    File f = new File(dirstr);
                    f.mkdir();
//                System.out.println("f = " + f.getName());
                    continue;
                }
                File flocal = new File(folderPath + File.separator + ze.getName());
//            System.out.println("ze.getName() = " + ze.getName());
                if (flocal == null) {
                    break;
                }
                OutputStream os = null;
                try {
//              System.out.println("flocal = " + flocal.exists() + " " + flocal.getAbsolutePath());
                    os = new BufferedOutputStream(new FileOutputStream(flocal));
                } catch (FileNotFoundException e) {
                    System.out.println("4 e " + e.toString());
                    e.printStackTrace();
                }
                InputStream is = null;
                try {
                    is = new BufferedInputStream(zfile.getInputStream(ze));
                } catch (IOException e) {
                    System.out.println("5 e " + e.toString());
                    e.printStackTrace();
                }
                int readLen = 0;
                try {
                    while ((readLen = is.read(buf, 0, 1024)) != -1) {
                        os.write(buf, 0, readLen);
                    }
                    os.flush();
                    is.close();
                    os.close();
                } catch (IOException e) {
                    System.out.println("6 e " + e.toString());
                    e.printStackTrace();
                }
            }
            try {
                zfile.close();
            } catch (IOException e) {
                System.out.println("7 e " + e.toString());
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getFileName(String[] split) {
        String name = null;
        if (split.length > 1) {
            name = split[split.length - 1];
        }
        return name;
    }
}
