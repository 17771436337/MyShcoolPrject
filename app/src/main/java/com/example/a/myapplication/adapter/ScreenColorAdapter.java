package com.example.a.myapplication.adapter;

import com.example.a.myapplication.bean.ScreenColorModel;
import com.example.a.myapplication.holder.BaseHolder;
import com.example.a.myapplication.holder.ScreenColorHolder;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

import java.util.List;

/**
 * Created by Administrator on 2016/12/28.
 */
public class ScreenColorAdapter extends SuperBaseAdapter<ScreenColorModel.OBean> {
    public ScreenColorAdapter(PullToRefreshBase listView, List<ScreenColorModel.OBean> datas) {
        super(listView, datas);
    }

    @Override
    protected BaseHolder<ScreenColorModel.OBean> getItemHolder(int position) {
        return new ScreenColorHolder();
    }
}
