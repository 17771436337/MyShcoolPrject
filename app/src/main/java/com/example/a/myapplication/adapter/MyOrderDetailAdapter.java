package com.example.a.myapplication.adapter;

import com.example.a.myapplication.bean.MyOrderDetailModel;
import com.example.a.myapplication.holder.BaseHolder;
import com.example.a.myapplication.holder.MyOrderDetailGroupHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/1/3.
 */
public class MyOrderDetailAdapter extends SuperBaseAdapter<MyOrderDetailModel.OrderDetails> {
    public MyOrderDetailAdapter(List<MyOrderDetailModel.OrderDetails> datas) {
        super(datas);
    }

    @Override
    protected BaseHolder<MyOrderDetailModel.OrderDetails> getItemHolder(int position) {
        return new MyOrderDetailGroupHolder();
    }
}
