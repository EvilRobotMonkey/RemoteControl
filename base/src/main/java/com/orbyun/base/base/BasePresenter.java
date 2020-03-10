package com.orbyun.base.base;


import java.lang.ref.Reference;

/**
 * @package com.boci.smart.base
 * @file BasePresenter
 * @date 2018/9/10  下午4:44
 * @autor wangxiongfeng
 */
public abstract class BasePresenter<V extends BaseView> {


    private Reference<V> mViewRef;
    protected V mView;
    protected String mTAG;

    public BasePresenter(V view) {
        mView = view;

    }

    public abstract void netResult(Object object, int code);


    public V getView() {
        return mView;
    }

    public void setView(V view) {
        mView = view;
    }

}
