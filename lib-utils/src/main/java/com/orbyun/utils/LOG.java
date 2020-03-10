package com.orbyun.utils;

import android.text.TextUtils;
import android.util.Log;


import com.orbyun.utils.helper.FileHelper;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @package com.boci.smart.utils
 * @file LOGs
 * @date 2018/9/26  下午2:48
 * @autor wangxiongfeng
 */
public class LOG {

    protected static final String TAG = "log";
    public static String tagPrefix = "";
    public static int fileSize = 1024 * 1024;
    public static int logFiles = 10;
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");// 用于格式化日期,作为日志文件名的一部分
    private static SimpleDateFormat formatSS = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss--SSS");// 用于格式化日期,作为日志文件名的一部分
    private static ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();
    private static LOG_MODEL CURRENT_LOG_MODE = LOG_MODEL.DEBUG;

    private static boolean LOOP_BEGIN = false;
    private static File logRootDir;

    public static void init(String logDir) {
        File file = new File(logDir);
        if (logRootDir.exists()) {
            logRootDir = file;
        }
        try {
            FileHelper.createFile(logDir, "");
            logRootDir = file;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void d(String now, final String conent) {
        String tag = generateTag();
        debug(tag, now, conent);
    }

    public static void i(String now, final String conent) {
        String tag = generateTag();
        int strLong = 0;
        boolean flag = true;
        if (conent.length() > 4000) {
            while (flag) {
                if ((conent.length() - strLong) > 4000) {
                    Log.d("log_info====>" + tag, "\n" + now + "   " + conent.substring(strLong, strLong + 4000));
                    strLong += 4000;
                } else {
                    flag = false;
                    Log.d("log_info====>" + tag, "\n" + now + "   " + conent.substring(strLong, conent.length()));
                }
            }
        } else {
            Log.d("log_info====>" + tag, "\n" + now + "   " + conent.substring(strLong, conent.length()));
        }
    }

    public static synchronized void debug(String tag, String now, final String conent) {

        switch (CURRENT_LOG_MODE) {
            case INFO:
                int strLong = 0;
                boolean flag = true;
                if (conent.length() > 4000) {
                    while (flag) {
                        if ((conent.length() - strLong) > 4000) {
                            Log.d("log_debug====>" + tag, "\n" + now + "   " + conent.substring(strLong, strLong + 4000));
                            strLong += 4000;
                        } else {
                            flag = false;
                            Log.d("log_debug====>" + tag, "\n" + now + "   " + conent.substring(strLong, conent.length()));
                        }
                    }
                } else {
                    Log.d("log_debug====>" + tag, "\n" + now + "   " + conent.substring(strLong, conent.length()));
                }
                break;
            case DEBUG:
                if (conent.length() > 4000) {
                    Log.d("log_debug===>" + tag, "\n" + now + "   " + conent + "......more");

                } else {
                    Log.d("log_debug===>" + tag, "\n" + now + "   " + conent);
                }
                break;
            case SAVELOG:
                queue.offer(String.format("%s --- %s --- %s --- %s ", formatSS.format(System.currentTimeMillis()), tag, now, conent));
                beginLoop();

                break;


        }
    }


    public static void writeLog(String msg) {

        if (logRootDir == null)
            return;
        String fileName = getLogFileName(logRootDir);

        try {
            FileHelper.createFile(logRootDir.getAbsolutePath(), fileName);
            //写入文件
            FileHelper.appendFile(logRootDir.getAbsolutePath() + File.separator + fileName, msg);

            //log 文件夹中维持在logFiles个文件数量
            File[] files = logRootDir.listFiles();

            if (files.length > logFiles) {
                while (logRootDir.listFiles().length > 0) {
                    logRootDir.listFiles()[0].delete();
                }
            }

            File[] files1 = logRootDir.listFiles();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < files1.length; i++) {
                sb.append("文件 " + i + " 大小" + files1[i].length() / 1024 + "kb   ");
            }
            Log.i("当前文件大小", sb.toString());


        } catch (Exception e) {
            e.printStackTrace();
            return;
        }


    }


    private static String getLogFileName(File file) {
        String fileName;
        File[] files = file.listFiles();
        if (files == null) {
            String time = format.format(new Date());
            long timetamp = System.currentTimeMillis();
            return "log-" + time + "-" + timetamp + ".log";
        }
        if (files != null && files.length == 0) {
            String time = format.format(new Date());
            long timetamp = System.currentTimeMillis();
            return "log-" + time + "-" + timetamp + ".log";
        } else {
            File file1 = files[files.length - 1];
            if (file1.length() > fileSize) {
                String time = format.format(new Date());
                long timetamp = System.currentTimeMillis();
                return "log-" + time + "-" + timetamp + ".log";
            } else {
                return file1.getName();
            }
        }


    }


    private void offer(String msg) {
        queue.offer(msg);
    }

    public String poll() {
        return queue.poll();
    }


    public static void beginLoop() {
        if (LOOP_BEGIN)
            return;

        ThreadManager.getLongPool().execute(new Runnable() {
            @Override
            public void run() {
                while (queue.size() > 0) {
                    LOOP_BEGIN = true;
                    String poll = queue.poll();
                    writeLog(poll);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                LOOP_BEGIN = false;
            }
        });


    }

    public static File zipLog() {
        try {
            String zipfile = logRootDir.getAbsolutePath() + File.separator + "zip";


            FileHelper.createFile(zipfile, "log.zip");
            File fileName = new File(zipfile, "log.zip");
            FileHelper.zipFolder(logRootDir.getAbsolutePath(), fileName.getAbsolutePath());
            File file = new File(zipfile, "log.zip");
            if (file.exists()) {
                return file;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }


    private static String generateTag() {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[4];
        String callerClazzName = stackTraceElement.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        String tag = "%s.%s(L:%d)";
        tag = String.format(tag, new Object[]{callerClazzName, stackTraceElement.getMethodName(), Integer.valueOf(stackTraceElement.getLineNumber())});
        //给tag设置前缀
        tag = TextUtils.isEmpty(tagPrefix) ? tag : tagPrefix + ":" + tag;
        return tag;
    }

    public enum LOG_MODEL {

        INFO(0), DEBUG(1), SAVELOG(2);

        private final int value;

        LOG_MODEL(int value) {
            this.value = value;
        }


        public int getValue() {
            return value;
        }
    }
}
