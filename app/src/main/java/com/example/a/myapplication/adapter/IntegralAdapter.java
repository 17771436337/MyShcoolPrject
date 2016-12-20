package com.example.a.myapplication.adapter;

import com.example.a.myapplication.bean.IntegralModel;
import com.example.a.myapplication.holder.BaseHolder;
import com.example.a.myapplication.holder.IntegralHplder;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

import java.util.List;

/**
 * Created by Administrator on 2016/12/8.
 */
public class IntegralAdapter extends SuperBaseAdapter<IntegralModel.OBean>{

    public IntegralAdapter(PullToRefreshBase listView, List<IntegralModel.OBean> datas) {
        super(listView, datas);
    }


    @Override
    protected BaseHolder<IntegralModel.OBean> getItemHolder(int position) {
        return new IntegralHplder();
    }
}
