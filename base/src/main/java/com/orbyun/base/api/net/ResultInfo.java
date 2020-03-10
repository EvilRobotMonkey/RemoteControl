package com.orbyun.base.api.net;

import java.util.Map;

/**
 * Created by ltyhome on 23/06/2017.
 * Email: ltyhome@yahoo.com.hk
 * Describe: bean info
 */

public class ResultInfo<T> {
   public static String state;//"OK" 成功
    public int code;
   private String tag;//标记
   private String message;//标记
   private T data;//数据
   private Map<String,String> addtion;
   private int iTotalRecords;
   private int isEmpty;
   private int type; //消息类型
   private int total; //

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //    private String error;//错误提示

//    public String getError() {
//        return error;
//    }
//
//    public void setError(String error) {
//        this.error = error;
//    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ResultInfo(T data) {
        this.data = data;
    }

    public int getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(int iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }
    //    public ResultInfo(String code){
//        this.code = code;
//   }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getState(){
       return state;
   }

    public void setState(String state) {
        this.state = state;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public Map<String, String> getAddtion() {
        return addtion;
    }
    public void setAddtion(Map<String, String> addtion) {
        this.addtion = addtion;
    }


}
