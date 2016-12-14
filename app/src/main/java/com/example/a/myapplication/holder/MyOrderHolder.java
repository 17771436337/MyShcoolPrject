package com.example.a.myapplication.holder;

import android.view.View;

import com.example.a.myapplication.R;
import com.example.a.myapplication.bean.MyOrderModer;
import com.example.a.myapplication.util.UIUtils;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/8.
 */
public class MyOrderHolder extends  BaseHolder<MyOrderModer.Order>{
    @Override
    protected View initView() {

        View view = View.inflate(UIUtils.getContext(), R.layout.item_myorder_list, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    protected void refreshUI(MyOrderModer.Order data) {

    }
}
