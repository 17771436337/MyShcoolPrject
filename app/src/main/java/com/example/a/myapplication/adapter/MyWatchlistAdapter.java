package com.example.a.myapplication.adapter;

import com.example.a.myapplication.bean.WatchlistModel;
import com.example.a.myapplication.holder.BaseHolder;
import com.example.a.myapplication.holder.WatchlistHolder;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

import java.util.List;

/**
 * Created by Administrator on 2016/12/22.
 */
public class MyWatchlistAdapter extends SuperBaseAdapter<WatchlistModel.OBean> {
    public MyWatchlistAdapter(PullToRefreshBase listView, List<WatchlistModel.OBean> datas) {
        super(listView, datas);
    }

    @Override
    protected BaseHolder<WatchlistModel.OBean> getItemHolder(int position) {
        return new WatchlistHolder();
    }
}
