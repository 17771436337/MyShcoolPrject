package com.example.a.myapplication.adapter;

import com.example.a.myapplication.bean.ScreenStyleModel;
import com.example.a.myapplication.holder.BaseHolder;
import com.example.a.myapplication.holder.ScreenStyleHolder;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

/**
 * Created by Administrator on 2016/12/28.
 */
public class ScreenStyleAdapter extends SuperBaseAdapter<ScreenStyleModel.Brand> {
    public ScreenStyleAdapter(PullToRefreshListView listView, List<ScreenStyleModel.Brand> datas) {
        super(listView, datas);
    }

    @Override
    protected BaseHolder<ScreenStyleModel.Brand> getItemHolder(int position) {
        return new ScreenStyleHolder();
    }
}
