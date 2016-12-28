package com.example.a.myapplication.adapter;

import com.example.a.myapplication.bean.OrderDetailModel;
import com.example.a.myapplication.holder.BaseHolder;
import com.example.a.myapplication.holder.OrderDetailHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/12/24.
 */
public class OrederAdapter extends SuperBaseAdapter<OrderDetailModel.Shop> {
    public OrederAdapter(List<OrderDetailModel.Shop> datas) {
        super(datas);
    }



    @Override
    protected BaseHolder<OrderDetailModel.Shop> getItemHolder(int position) {
        return new OrderDetailHolder();
    }
}
