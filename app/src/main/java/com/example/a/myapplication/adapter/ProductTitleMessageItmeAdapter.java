package com.example.a.myapplication.adapter;

import com.example.a.myapplication.bean.ProductTitleMessageModel;
import com.example.a.myapplication.bean.ShopModel;
import com.example.a.myapplication.holder.BaseHolder;
import com.example.a.myapplication.holder.ContentHolder;
import com.example.a.myapplication.holder.ProductTitleMessageItmeHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/12/8.
 */
public class ProductTitleMessageItmeAdapter extends SuperBaseAdapter<ProductTitleMessageModel.OBean> {
    public ProductTitleMessageItmeAdapter(List<ProductTitleMessageModel.OBean> datas) {
        super(datas);
    }

    @Override
    protected BaseHolder<ProductTitleMessageModel.OBean>  getItemHolder(int position) {
        return new ProductTitleMessageItmeHolder();
    }
}
