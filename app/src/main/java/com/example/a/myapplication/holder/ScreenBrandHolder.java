package com.example.a.myapplication.holder;

import android.view.View;

import com.example.a.myapplication.R;
import com.example.a.myapplication.bean.ScreenBrandModel;
import com.example.a.myapplication.util.UIUtils;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/9.
 */
public class ScreenBrandHolder extends BaseHolder<ScreenBrandModel.Brand> {
    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_brand_list,null);
        ButterKnife.inject(this,view);
        return view;
    }

    @Override
    protected void refreshUI(ScreenBrandModel.Brand data) {

    }
}
