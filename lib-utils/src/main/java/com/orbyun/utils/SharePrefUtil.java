package com.orbyun.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @package com.boci.smart.utils
 * @file SharePrefUtil
 * @date 2018/8/23  上午9:36
 * @autor wangxiongfeng
 */

public class SharePrefUtil {
    public final static String CURRENT_USER_SHARE_NAME = "basemoudle";

    public final static String HAS_LOGIN = "has_login";
    public final static String NEW_CUSTOMER = "new_customer";

    public final static String PAD_INFO = "pad_info";

    public final static String LOGIN_MODE = "login_mode";


    public static void saveString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(CURRENT_USER_SHARE_NAME, 0);
        sp.edit().putString(key, value).commit();
    }

    public static SharedPreferences getSharePre_Name(Context context) {
        SharedPreferences sp = context.getSharedPreferences(CURRENT_USER_SHARE_NAME, 0);
        return sp;
    }

    public static String getString(Context context, String key, String defValue) {
        synchronized (SharePrefUtil.class) {
            SharedPreferences sp = context.getSharedPreferences(CURRENT_USER_SHARE_NAME, 0);
            return sp.getString(key, defValue);
        }

    }

    public static int getInt(Context context, String key, int defValue) {
        SharedPreferences sp = context.getSharedPreferences(CURRENT_USER_SHARE_NAME, 0);
        return sp.getInt(key, defValue);
    }

    public static void saveInt(Context context, String key, int value) {
        SharedPreferences sp = context.getSharedPreferences(CURRENT_USER_SHARE_NAME, 0);
        sp.edit().putInt(key, value).commit();
    }

    /**
     * 将对象进行bitmap编码后保存到SharePref中
     *
     * @param context
     * @param key
     * @param bitmap
     */
    public static void saveBitmap(Context context, String key, Bitmap bitmap) {
        SharedPreferences sp = context.getSharedPreferences(CURRENT_USER_SHARE_NAME, 0);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        String imageBase64 = new String(Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT));
        sp.edit().putString(key, imageBase64).commit();
    }

    /**
     * 获取保存的图片
     *
     * @param context
     * @param key
     */
    public static Bitmap getBitmap(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(CURRENT_USER_SHARE_NAME, 0);
        String objBase64 = sp.getString(key, null);
        if (TextUtils.isEmpty(objBase64)) {
            return null;
        }
        byte[] base64Bytes = Base64.decode(objBase64.getBytes(), Base64.DEFAULT);
        ByteArrayInputStream baos = new ByteArrayInputStream(base64Bytes);
        return BitmapFactory.decodeStream(baos);
    }

    public static void saveObj(Context context, String key, Object object) {
        SharedPreferences sp = context.getSharedPreferences(CURRENT_USER_SHARE_NAME, 0);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            oos.close();
            String objBase64 = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
            sp.edit().putString(key, objBase64).commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveObj(Context context, String name, String key, Object object) {
        SharedPreferences sp = context.getSharedPreferences(name, 0);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            oos.close();
            String objBase64 = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
            sp.edit().putString(key, objBase64).commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Long getLong(Context context, String name, String key, long defValue) {
        SharedPreferences sp = context.getSharedPreferences(name, 0);
        return sp.getLong(key, defValue);
    }

    public static void saveLong(Context context, String name, String key, long value) {
        SharedPreferences sp = context.getSharedPreferences(name, 0);
        sp.edit().putLong(key, value).commit();
    }

    public static void saveInt(Context context, String name, String key, int value) {
        SharedPreferences sp = context.getSharedPreferences(name, 0);
        sp.edit().putInt(key, value).commit();
    }

    public static int getInt(Context context, String name, String key, int defValue) {
        SharedPreferences sp = context.getSharedPreferences(name, 0);
        return sp.getInt(key, defValue);
    }


    public static Object getObj(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(CURRENT_USER_SHARE_NAME, 0);
        String objBase64 = sp.getString(key, null);
        if (TextUtils.isEmpty(objBase64)) {
            return null;
        }
        byte[] base64Bytes = Base64.decode(objBase64.getBytes(), Base64.DEFAULT);
        ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);

        ObjectInputStream ois;
        Object obj = null;
        try {
            ois = new ObjectInputStream(bais);
            obj = (Object) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static Object getObj(Context context, String name, String key) {
        SharedPreferences sp = context.getSharedPreferences(name, 0);
        String objBase64 = sp.getString(key, null);
        if (TextUtils.isEmpty(objBase64)) {
            return null;
        }
        byte[] base64Bytes = Base64.decode(objBase64.getBytes(), Base64.DEFAULT);
        ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);

        ObjectInputStream ois;
        Object obj = null;
        try {
            ois = new ObjectInputStream(bais);
            obj = (Object) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static boolean removeKey(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(CURRENT_USER_SHARE_NAME, 0);
        if (sp.contains(key)) {
            sp.edit().remove(key).commit();
        }
        return true;
    }

    public static boolean removeKey(Context context, String name, String key) {
        SharedPreferences sp = context.getSharedPreferences(name, 0);
        if (sp.contains(key)) {
            return sp.edit().remove(key).commit();
        }
        return true;
    }

    public static boolean existKey(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(CURRENT_USER_SHARE_NAME, 0);
        return sp.contains(key);
    }

    public static boolean existKey(Context context, String name, String key) {
        SharedPreferences sp = context.getSharedPreferences(name, 0);
        return sp.contains(key);
    }

    public static void saveBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences(CURRENT_USER_SHARE_NAME, 0);
        sp.edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        SharedPreferences sp = context.getSharedPreferences(CURRENT_USER_SHARE_NAME, 0);
        return sp.getBoolean(key, defValue);
    }

    public static void saveBoolean(Context context, String name, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences(name, 0);
        sp.edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(Context context, String name, String key, boolean defValue) {
        SharedPreferences sp = context.getSharedPreferences(name, 0);
        return sp.getBoolean(key, defValue);
    }

    public static void saveString(Context context, String name, String key, String value) {
        synchronized (SharePrefUtil.class) {
            SharedPreferences sp = context.getSharedPreferences(name, 0);
            sp.edit().putString(key, value).commit();
        }
    }

    public static String getString(Context context, String name, String key, String defValue) {
        SharedPreferences sp = context.getSharedPreferences(name, 0);
        return sp.getString(key, defValue);
    }

    public static void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences(CURRENT_USER_SHARE_NAME, 0);
        sp.edit().clear().commit();
    }


    public static void clear(Context context, String name) {
        SharedPreferences sp = context.getSharedPreferences(CURRENT_USER_SHARE_NAME, 0);
        sp.edit().clear().commit();
    }


}
