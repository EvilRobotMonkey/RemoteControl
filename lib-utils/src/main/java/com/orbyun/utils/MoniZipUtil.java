
package com.orbyun.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;


public class MoniZipUtil {
    protected static final String DEF_ZIPSTR_ENCODE = "UTF-8";


    public static String zipStr(String str, String encode) {
        // 无效值则返回
        if (str == null || str.length() == 0) {
            return str;
        }

        encode = encode == null ? DEF_ZIPSTR_ENCODE : encode;

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzipOut = null;

        // 捕获异常
        try {
            // 编码
            gzipOut = new GZIPOutputStream(out);
            gzipOut.write(str.getBytes(encode));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭GZIP流
            if (gzipOut != null) {
                try {
                    gzipOut.close();
                } catch (IOException ignore) {
                    ignore.printStackTrace();
                }
            }
        }

        // 返回字节数组
        // BASE64
        return new BASE64Encoder().encode(out.toByteArray());
    }



    public static String unzipStr(String zipStr, String encode) {
        // 防空
        if (zipStr == null || zipStr.length() == 0) {
            return null;
        }

        encode = encode == null ? DEF_ZIPSTR_ENCODE : encode;

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = null;
        GZIPInputStream gzipIn = null;
        byte[] zipBs = null;
        String upzipStr = null;

        // 捕获异常
        try {
            // BASE64
            zipBs = new BASE64Decoder().decodeBuffer(zipStr);
            in = new ByteArrayInputStream(zipBs);
            gzipIn = new GZIPInputStream(in);

            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = gzipIn.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }

            // 转码
            upzipStr = out.toString(encode);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭ZIP流
            if (gzipIn != null) {
                try {
                    gzipIn.close();
                } catch (IOException ignore) {
                    ignore.printStackTrace();
                }
            }

            // 关闭输入流
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ignore) {
                    ignore.printStackTrace();
                }
            }

            // 关闭输出流
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ignore) {
                    ignore.printStackTrace();
                }
            }
        }

        return upzipStr;
    }


}

