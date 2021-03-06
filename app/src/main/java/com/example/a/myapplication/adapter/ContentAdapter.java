package com.example.a.myapplication.adapter;

import com.example.a.myapplication.bean.ShopModel;
import com.example.a.myapplication.holder.BaseHolder;
import com.example.a.myapplication.holder.ContentHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/12/8.
 */
public class ContentAdapter extends SuperBaseAdapter<ShopModel.Shop.Content> {
    ShopAdapter shopAdapter;
    public ContentAdapter(List<ShopModel.Shop.Content> datas, ShopAdapter shopAdapter) {
        super(datas);
       this.shopAdapter = shopAdapter;
    }

    @Override
    protected BaseHolder<ShopModel.Shop.Content>  getItemHolder(int position) {
        return new ContentHolder(this,shopAdapter);
    }
}
