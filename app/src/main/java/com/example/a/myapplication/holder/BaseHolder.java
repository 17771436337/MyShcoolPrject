package com.example.a.myapplication.holder;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.example.a.myapplication.util.UIUtils;


public abstract class BaseHolder<T> {
    protected View mRootView;    // 根视图
    protected T mData;        // 数据

    public BaseHolder() {
        mRootView = initView();
        // 设置标记
        mRootView.setTag(this);
    }

    /**
     * 实现view的布局
     *
     * @return
     */
    protected abstract View initView();

    /**
     * 让子类根据数据来刷新自己的视图
     *
     * @param data
     */
    protected abstract void refreshUI(T data);

    /**
     * 获取根布局
     *
     * @return
     */
    public View getRootView() {
        return mRootView;
    }

    public void setData(T data) {
        // 保存数据
        this.mData = data;
        // 通过数据来改变UI显示
        refreshUI(data);
    }

    public void initIcon(TextView mTextView) {
        Typeface iconfont = Typeface.createFromAsset(UIUtils.getContext().getAssets(), "iconfont.ttf");
        mTextView.setTypeface(iconfont);
    }
}
