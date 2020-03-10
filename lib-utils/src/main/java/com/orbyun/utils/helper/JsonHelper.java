package com.orbyun.utils.helper;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.orbyun.utils.LOG;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class JsonHelper {
    private static final String TAG = "JsonHelper";
    private static Gson gson = new GsonBuilder().registerTypeAdapterFactory(new MyTypeAdapterFactory()).create();

    public static class MyTypeAdapterFactory<T> implements TypeAdapterFactory {
        @SuppressWarnings("unchecked")
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            Class<T> rawType = (Class<T>) type.getRawType();
            if (rawType == Float.class || rawType == float.class) {
                return (TypeAdapter<T>) new FloatlNullAdapter();
            } else if (rawType == Integer.class || rawType == int.class) {
                return (TypeAdapter<T>) new IntNullAdapter();
            }
            return null;
        }
    }

    public static class IntNullAdapter extends TypeAdapter<Number> {

        @Override
        public void write(JsonWriter out, Number value) throws IOException {
            out.value(value);
        }

        @Override
        public Number read(JsonReader reader) throws IOException {
            if (reader.peek() == JsonToken.NULL) {
                reader.nextNull();
                return null;
            }
            try {
                return reader.nextInt();
            } catch (NumberFormatException e) {
                //这里解析int出错，那么捕获异常并且返回默认值，因为nextInt出错中断了方法，没有完成位移，所以调用nextString()方法完成位移。
                reader.nextString();

                return 0;
            }
        }
    }

    public static class FloatlNullAdapter extends TypeAdapter<Float> {
        @Override
        public Float read(JsonReader reader) throws IOException {
            // TODO Auto-generated method stub
            if (reader.peek() == JsonToken.STRING) {
                reader.skipValue(); //跳过当前
                return -1f;
            }
            BigDecimal bigDecimal = new BigDecimal(reader.nextString());
            return bigDecimal.floatValue();
        }

        @Override
        public void write(JsonWriter writer, Float value) throws IOException {
            // TODO Auto-generated method stub
            writer.value(value);
        }
    }

    public static <T> T String2Object(Class<T> tClass, String json) {
        try {
            if (TextUtils.isEmpty(json))
                return null;
            return gson.fromJson(json, tClass);
        } catch (Exception e) {
            LOG.d(TAG, "String2Object 异常 " + tClass.getSimpleName() + "||" + json);

            return null;
        }


    }

    public static <T> List<T> String2List(Class<T> tClass, String json) {

        try {
            JSONArray jsonArray = new JSONArray(json);
            int length = jsonArray.length();
            List<T> list = new ArrayList(length);

            for (int i = 0; i < length; ++i) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                T bean = jsonObjToBean(jsonObject, tClass);
                list.add(bean);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            LOG.d(TAG, "String2List 异常 " + json);
            return new ArrayList<T>();
        }


    }

    public static <T> T jsonObjToBean(JSONObject obj, Class<T> beanCalss) {

        try {
            String s = obj.toString();

            T t = String2Object(beanCalss, s);
            return t;
        } catch (Exception e) {
            LOG.d(TAG, "jsonObjToBean 异常 " + beanCalss.getSimpleName());
            return null;
        }
    }

    public static String Object2String(Object object) {
        try {
            return gson.toJson(object);
        } catch (Exception e) {
            LOG.d(TAG, "Object2String 异常 " + object.toString());
            return "";
        }

    }

    public static String key2String(String json, String key) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            String string = jsonObject.optString(key);
            return string;
        } catch (Exception e) {
            e.printStackTrace();
            LOG.d(TAG, "key2String 异常 " + key + " || " + json);

            return "";
        }

    }

    public static int key2int(String json, String key) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            int value = jsonObject.optInt(key);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            LOG.d(TAG, "key2int 异常 " + key + " || " + json);

            return 0;
        }

    }
}
