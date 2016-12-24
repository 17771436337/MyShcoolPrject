package com.example.a.myapplication.adapter;

import com.example.a.myapplication.bean.OrderDetailModel;
import com.example.a.myapplication.holder.BaseHolder;
import com.example.a.myapplication.holder.OrederDeatilsContentHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/12/24.
 */
public class OrederDeatilsContentAdapter extends SuperBaseAdapter<OrderDetailModel.Shop.ShopsBean> {
    public OrederDeatilsContentAdapter(List<OrderDetailModel.Shop.ShopsBean> datas) {
        super(datas);
    }

    @Override
    protected BaseHolder<OrderDetailModel.Shop.ShopsBean> getItemHolder(int position) {
        return new OrederDeatilsContentHolder();
    }
}
