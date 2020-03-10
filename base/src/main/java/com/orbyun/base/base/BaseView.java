package com.orbyun.base.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * @package com.boci.smart.base
 * @file BaseView
 * @date 2018/9/10  下午4:45
 * @autor wangxiongfeng
 */
public interface BaseView {
    /** 显示加载框 **/
    void  showLoading();
    /** 隐藏加载框 **/
    void hideLoading();
    /** 更新View **/
    void updateView(String result);
    /** 120秒倒计时 0起动，1停止**/
    void workService(int type);

}
