package com.example.a.myapplication.holder;

import android.view.View;

import com.example.a.myapplication.R;
import com.example.a.myapplication.bean.IntegralModel;
import com.example.a.myapplication.util.UIUtils;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/8.
 */
public class IntegralHplder extends BaseHolder<IntegralModel.OBean>{
    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_integral_list,null);
        ButterKnife.inject(this,view);
        return view;
    }

    @Override
    protected void refreshUI(IntegralModel.OBean data) {

    }
}
