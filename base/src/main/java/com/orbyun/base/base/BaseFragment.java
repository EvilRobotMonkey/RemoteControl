package com.orbyun.base.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.orbyun.utils.LOG;

/**
 * 作者：Liq on 2018/8/9 15:59
 * <p>
 * 邮箱：18513215341@139.com
 */
public abstract class BaseFragment extends Fragment {
    protected Activity mActivity;
    protected Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mActivity = getActivity();
        mContext = getContext();
        initView(view);
        initData();
        LOG.d(setTAG(), "当前页面");
        //隐藏状态栏
//        SystemUtils.NavigationBarStatusBar(mActivity,true);
    }
    protected abstract String setTAG();

    protected abstract void initData();

    public abstract int getLayoutId();

    public abstract void initView(View view);
}
