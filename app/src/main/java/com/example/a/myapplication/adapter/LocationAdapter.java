package com.example.a.myapplication.adapter;

import com.example.a.myapplication.bean.LocationMoldel;
import com.example.a.myapplication.holder.BaseHolder;
import com.example.a.myapplication.holder.LocationHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/12/14.
 */
public class LocationAdapter extends SuperBaseAdapter<LocationMoldel.Location>{
    public LocationAdapter(List<LocationMoldel.Location> datas) {
        super(datas);
    }

    @Override
    protected BaseHolder<LocationMoldel.Location> getItemHolder(int position) {
        return new LocationHolder();
    }
}
