package com.example.a.myapplication.adapter;

import com.example.a.myapplication.bean.ScreenCategoryOneModel;
import com.example.a.myapplication.holder.BaseHolder;
import com.example.a.myapplication.holder.ScreenCategoryOneHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/12/12.
 */
public class ScreenCategoryOneAdapter extends SuperBaseAdapter<ScreenCategoryOneModel.Category>{
    public ScreenCategoryOneAdapter(List<ScreenCategoryOneModel.Category> datas) {
        super(datas);
    }

    @Override
    protected BaseHolder<ScreenCategoryOneModel.Category> getItemHolder(int position) {
        return new ScreenCategoryOneHolder();
    }
}
