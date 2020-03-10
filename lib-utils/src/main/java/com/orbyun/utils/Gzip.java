/*
 * 文 件 名: Gzip.java
 * 描    叙: 使用GZIP对将要发送的字符串进行处理
 * 新 建 人: 王敬
 * 新建时间: Jul 16, 2015 1:36:54 PM
 * 公    司: 中电长城(长沙)信息技术有限公司
 * 版    权: Copyright 2010-2015 GWI Tech. Co. Ltd. All Rights Reserved.
 * 修 改 人: vim
 * 修改时间: May 26, 2016 9:36:54 AM
 */

package com.orbyun.utils;

import android.text.TextUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;


/**
 * @author vim
 * @ClassName: Gzip
 * @copyright:Copyright by GWI Tech. Co. Ltd. All Rights Reserved.
 * @Description:Gzip压缩与zip压缩
 * @date:2016年5月26日 上午9:16:41
 * @since:
 */
public class Gzip {
    /**
     * 默认编码
     */
    protected static final String DEF_ZIPSTR_ENCODE = "UTF-8";

    /**
     *
     * 使用gzip进行压缩
     */
    /**
     * 使用gzip压缩字符串（包含base64编码）
     *
     * @param primStr -- 待压缩字符串
     * @return --返回压缩后字符串
     */
    public static String gzip(String primStr) {
        return gzip(primStr, DEF_ZIPSTR_ENCODE);
    }

    /**
     * 使用gzip按编码压缩字符串
     *
     * @param primStr -- 待压缩字符串
     * @param encode  -- 编码
     * @return --返回压缩后字符串
     */
    public static String gzip(String primStr, String encode) {
        if (primStr == null || primStr.length() == 0) {
            return primStr;
        }
        encode = TextUtils.isEmpty(encode) ? DEF_ZIPSTR_ENCODE : encode;

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        GZIPOutputStream gzip = null;
        try {
            gzip = new GZIPOutputStream(out);
            byte[] bytes = primStr.getBytes(encode);
            gzip.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (gzip != null) {
                try {
                    gzip.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        byte[] resultByte = out.toByteArray();
        return new BASE64Encoder().encode(resultByte);
    }

    /**
     * 使用gzip解压缩 字符串（包含base64解码，默认编码utf-8）
     *
     * @param compressedStr 待解压缩字符串
     * @return 返回解压缩后字符串
     */
    public static String gunzip(String compressedStr) {
        return gunzip(compressedStr, DEF_ZIPSTR_ENCODE);
    }

    /**
     * 使用gzip解压缩 字符串（包含base64解码）
     *
     * @param compressedStr 待解压缩字符串
     * @param encode        编码 如 utf-8,gbk,iso8859-1等等
     * @return 返回解压缩后字符串
     */
    public static String gunzip(String compressedStr, String encode) {
        if (compressedStr == null) {
            return null;
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = null;
        GZIPInputStream ginzip = null;
        byte[] compressed = null;
        String decompressed = null;
        try {
            compressed = new BASE64Decoder().decodeBuffer(compressedStr);
            in = new ByteArrayInputStream(compressed);
            ginzip = new GZIPInputStream(in);

            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = ginzip.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            decompressed = out.toString(encode);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ginzip != null) {
                try {
                    ginzip.close();
                } catch (IOException e) {
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }

        return decompressed;
    }

    /**
     * 使用zip进行压缩
     *
     * @param str 压缩前的文本
     * @return 返回压缩后的文本
     */
    public static final String zip(String str) {
        if (str == null) {
            return null;
        }
        byte[] compressed;
        ByteArrayOutputStream out = null;
        ZipOutputStream zout = null;
        String compressedStr = null;
        try {
            out = new ByteArrayOutputStream();
            zout = new ZipOutputStream(out);
            zout.putNextEntry(new ZipEntry("0"));
            zout.write(str.getBytes());
            zout.closeEntry();
            compressed = out.toByteArray();
            compressedStr = new BASE64Encoder().encodeBuffer(compressed);
        } catch (IOException e) {
            compressed = null;
        } finally {
            if (zout != null) {
                try {
                    zout.close();
                } catch (IOException e) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }
        return compressedStr;
    }

    /**
     * 使用zip进行解压缩
     *
     * @param compressedStr 压缩后的文本
     * @return 解压后的字符串
     */
    public static final String unzip(String compressedStr) {
        if (compressedStr == null) {
            return null;
        }

        ByteArrayOutputStream out = null;
        ByteArrayInputStream in = null;
        ZipInputStream zin = null;
        String decompressed = null;
        try {
            byte[] compressed = new BASE64Decoder().decodeBuffer(compressedStr);
            out = new ByteArrayOutputStream();
            in = new ByteArrayInputStream(compressed);
            zin = new ZipInputStream(in);
            ZipEntry entry = zin.getNextEntry();
            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = zin.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            decompressed = out.toString();
        } catch (IOException e) {
            decompressed = null;
        } finally {
            if (zin != null) {
                try {
                    zin.close();
                } catch (IOException e) {
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }
        return decompressed;
    }
}

