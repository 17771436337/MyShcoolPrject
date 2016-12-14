package com.example.a.myapplication.adapter;

import com.example.a.myapplication.bean.ScreenCategoryTowModel;
import com.example.a.myapplication.holder.BaseHolder;
import com.example.a.myapplication.holder.ScreenCategoryTowHolder;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

import java.util.List;

/**
 * Created by Administrator on 2016/12/12.
 */
public class ScreenCategoryTowAdapter extends SuperBaseAdapter<ScreenCategoryTowModel.Category>{
    public ScreenCategoryTowAdapter(PullToRefreshBase listView, List<ScreenCategoryTowModel.Category> datas) {
        super(listView, datas);
    }

    @Override
    protected BaseHolder< ScreenCategoryTowModel.Category> getItemHolder(int position) {
        return new ScreenCategoryTowHolder();
    }
}
