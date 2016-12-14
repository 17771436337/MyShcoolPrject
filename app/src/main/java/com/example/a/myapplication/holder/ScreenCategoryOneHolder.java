package com.example.a.myapplication.holder;

import android.view.View;

import com.example.a.myapplication.R;
import com.example.a.myapplication.bean.ScreenCategoryOneModel;
import com.example.a.myapplication.util.UIUtils;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/12.
 */
public class ScreenCategoryOneHolder extends BaseHolder<ScreenCategoryOneModel.Category> {
    @Override
    protected View initView() {
       View view = View.inflate(UIUtils.getContext(), R.layout.item_categoryone,null);
        ButterKnife.inject(this,view);
        return view;
    }

    @Override
    protected void refreshUI(ScreenCategoryOneModel.Category data) {

    }
}
