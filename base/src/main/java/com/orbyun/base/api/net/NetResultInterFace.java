package com.orbyun.base.api.net;

/**
 * @package com.orbyun.base.net
 * @file NetWorkingResultInterFace
 * @date 2019/4/29  3:06 PM
 * @autor wangxiongfeng
 */
public interface NetResultInterFace<T> {
    /**
     * 回调接口
     *
     * @param backResult
     */
    public void netWork(ResultInfo<T> backResult, String tag);



}
