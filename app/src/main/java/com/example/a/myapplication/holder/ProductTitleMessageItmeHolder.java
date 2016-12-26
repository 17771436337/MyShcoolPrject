package com.example.a.myapplication.holder;

import android.view.View;

import com.example.a.myapplication.R;
import com.example.a.myapplication.bean.ProductTitleMessageModel;
import com.example.a.myapplication.util.UIUtils;

import butterknife.ButterKnife;

/**
 * Created by a on 2016/12/23.
 */

public class ProductTitleMessageItmeHolder extends BaseHolder<ProductTitleMessageModel.OBean>  {

    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_test_list, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    protected void refreshUI(ProductTitleMessageModel.OBean peo) {
    }

}
