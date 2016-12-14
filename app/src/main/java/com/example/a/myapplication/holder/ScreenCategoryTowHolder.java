package com.example.a.myapplication.holder;

import android.view.View;

import com.example.a.myapplication.R;
import com.example.a.myapplication.bean.ScreenCategoryTowModel;
import com.example.a.myapplication.util.UIUtils;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/12.
 */
public class ScreenCategoryTowHolder extends BaseHolder< ScreenCategoryTowModel.Category> {
    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_brand_list,null);
        ButterKnife.inject(this,view);
        return view;
    }

    @Override
    protected void refreshUI(ScreenCategoryTowModel.Category data) {

    }
}
