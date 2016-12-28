package com.example.a.myapplication.adapter;

import com.example.a.myapplication.bean.ScreenBrandModel;
import com.example.a.myapplication.holder.BaseHolder;
import com.example.a.myapplication.holder.ScreenBrandHolder;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

import java.util.List;

/**
 * Created by Administrator on 2016/12/9.
 */
public class ScreenBrandAdapter extends SuperBaseAdapter<ScreenBrandModel.Brand> {
    public ScreenBrandAdapter(PullToRefreshBase listView, List<ScreenBrandModel.Brand> datas) {
        super(listView, datas);
    }

    @Override
    protected BaseHolder<ScreenBrandModel.Brand> getItemHolder(int position) {
        return new ScreenBrandHolder();
    }
}
