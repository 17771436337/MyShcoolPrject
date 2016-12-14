package com.example.a.myapplication.holder;

import android.view.View;

import com.example.a.myapplication.R;
import com.example.a.myapplication.bean.ScreenFashionModel;
import com.example.a.myapplication.util.UIUtils;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/12.
 */
public class ScreenFashionHolder extends BaseHolder<ScreenFashionModel.Fashion> {
    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_fashion_list,null);
        ButterKnife.inject(this,view);
        return view;
    }

    @Override
    protected void refreshUI(ScreenFashionModel.Fashion data) {

    }
}
