package com.example.a.myapplication.adapter;

import android.view.View;
import android.widget.AdapterView;

import com.example.a.myapplication.bean.FansModel;
import com.example.a.myapplication.holder.BaseHolder;
import com.example.a.myapplication.holder.MyFansHolder;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

import java.util.List;


/**
 * Created by Administrator on 2016/12/7.
 */
public class MyFansAdapter extends SuperBaseAdapter<FansModel.MyFans> implements AdapterView.OnItemClickListener {

    private List<FansModel.MyFans> datas;

    public List<FansModel.MyFans> getDatas() {
        return datas;
    }

    public MyFansAdapter(PullToRefreshBase listView, List<FansModel.MyFans> datas) {
        super(listView, datas);
        this.datas = datas;
    }

    @Override
    protected BaseHolder<FansModel.MyFans> getItemHolder(int position) {

        return new MyFansHolder();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }


}
