package com.orbyun.base.api.net;


import java.util.HashMap;

/**
 * @package com.guocuhui.gch.network
 * @file NetAPi
 * @date 2017/12/7  下午3:33
 * @autor wangxiongfeng
 * @org www.miduo.com
 */

public interface NetRequestAPI {

    /**
     * get
     *
     * @param tag
     * @param url
     * @param parameter
     * @param prompt
     * @param mNetResultInterFace
     */
    void doGet(final String tag, String url, HashMap<String, String> parameter, boolean prompt,
               final NetResultInterFace mNetResultInterFace);

    /**
     * post
     *
     * @param tag
     * @param url
     * @param params
     * @param prompt
     * @param mNetResultInterFace
     */
    void doPost(final String tag, String url, HashMap<String, String> params, final Boolean prompt,
                final NetResultInterFace mNetResultInterFace);


    /**
     * 上传 json文件
     *
     * @param tag
     * @param url
     * @param json
     * @param prompt
     * @param showText
     * @param mNetResultInterFace
     */
    void doJson(final String tag, String url, String json, final Boolean prompt, String showText,
                final NetResultInterFace mNetResultInterFace);

    /**
     * 文件下载
     *
     * @param tag
     * @param url
     * @param loadFielType
     * @param mNetResultInterFace
     */
    void doFileLoad(final String tag, String url, int loadFielType,  final String path, final String name, final NetResultInterFace mNetResultInterFace);


    /**
     * 文件上传
     */

    void doFileUpLoad(final String tag, String url, int loadFielType, String fileName,final NetResultInterFace mNetResultInterFace);

}
