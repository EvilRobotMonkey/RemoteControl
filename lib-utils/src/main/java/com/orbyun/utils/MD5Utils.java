package com.orbyun.utils;

import java.security.MessageDigest;

/**
 * Created by dong on 2015/11/13.
 */
public class MD5Utils {
    // MD5加密，32位
    public static String MD5(String str) {
        LOG.i("MD5Utils", str);
        MessageDigest md5 = null;
        char[] charArray = null;
        byte[] byteArray = null;
        byte[] md5Bytes = null;
        StringBuffer hexValue = null;
        String retValue = "";
        try {

            md5 = MessageDigest.getInstance("MD5");

            byte[] bytes = str.getBytes("utf-8");
            md5.update(bytes);

            md5Bytes = md5.digest();
            hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
            retValue = hexValue.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != byteArray && byteArray.length > 0) {
                byteArray = ArryUtils.resetArray(byteArray);
                byteArray = null;
            }
            if (null != md5Bytes && md5Bytes.length > 0) {
                md5Bytes = ArryUtils.resetArray(md5Bytes);
                md5Bytes = null;
            }
            return retValue.toUpperCase();
        }

    }

    // 可逆的加密算法
    public static String encryptmd5(String str) {
        char[] a = str.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 'l');
        }
        String s = new String(a);
        return s;
    }


    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes(charsetname)));
        } catch (Exception exception) {
        }
        return resultString;
    }

    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};


}
