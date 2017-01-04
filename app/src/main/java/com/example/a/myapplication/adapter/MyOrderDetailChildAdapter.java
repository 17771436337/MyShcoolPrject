package com.example.a.myapplication.adapter;

import com.example.a.myapplication.bean.MyOrderDetailModel;
import com.example.a.myapplication.holder.BaseHolder;
import com.example.a.myapplication.holder.MyOrderDetailChildHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/1/3.
 */
public class MyOrderDetailChildAdapter extends SuperBaseAdapter<MyOrderDetailModel.OrderDetails.Order>{

    public MyOrderDetailChildAdapter(List<MyOrderDetailModel.OrderDetails.Order> datas) {
        super(datas);
    }

    @Override
    protected BaseHolder<MyOrderDetailModel.OrderDetails.Order> getItemHolder(int position) {
        return new MyOrderDetailChildHolder();
    }
}
