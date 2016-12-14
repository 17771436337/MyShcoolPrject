package com.example.a.myapplication.adapter;

import com.example.a.myapplication.bean.ScreenFashionModel;
import com.example.a.myapplication.holder.BaseHolder;
import com.example.a.myapplication.holder.ScreenFashionHolder;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

import java.util.List;

/**
 * Created by Administrator on 2016/12/12.
 */
public class ScreenFashionAdapter extends SuperBaseAdapter<ScreenFashionModel.Fashion>{
    public ScreenFashionAdapter(PullToRefreshBase listView, List<ScreenFashionModel.Fashion> datas) {

        super(listView, datas);
    }

    @Override
    protected BaseHolder<ScreenFashionModel.Fashion> getItemHolder(int position) {
        return new ScreenFashionHolder();
    }
}
