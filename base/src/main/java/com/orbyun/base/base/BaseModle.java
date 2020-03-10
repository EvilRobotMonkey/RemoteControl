package com.orbyun.base.base;

/**
 * @package com.boci.smart.base
 * @file BaseModle
 * @date 2018/9/14  下午8:26
 * @autor wangxiongfeng
 */
public abstract class BaseModle <V extends BasePresenter> {
    protected V mPresenter;


    public BaseModle(V presenter) {
        mPresenter = presenter;
    }
}
