package com.example.a.myapplication.adapter;

import com.example.a.myapplication.bean.ShopModel;
import com.example.a.myapplication.holder.BaseHolder;
import com.example.a.myapplication.holder.ShopHolder;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

import java.util.List;

/**
 * Created by Administrator on 2016/12/8.
 */
public class ShopAdapter extends SuperBaseAdapter<ShopModel.Shop>{
    public ShopAdapter(PullToRefreshBase listView, List<ShopModel.Shop> datas) {
        super(listView, datas);
    }

    @Override
    protected BaseHolder<ShopModel.Shop> getItemHolder(int position) {
        return new ShopHolder();
    }
}
