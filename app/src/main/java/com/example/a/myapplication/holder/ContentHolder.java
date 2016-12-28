package com.example.a.myapplication.holder;

import android.view.View;

import com.example.a.myapplication.R;
import com.example.a.myapplication.bean.ShopModel;
import com.example.a.myapplication.util.UIUtils;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/8.
 */
public class ContentHolder extends BaseHolder<ShopModel.Shop.Content> {
    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.itme_shop_content_list,null);
        ButterKnife.inject(this,view);
        return view;
    }

    @Override
    protected void refreshUI(ShopModel.Shop.Content data) {

    }
}
