package com.example.a.myapplication.adapter;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.a.myapplication.bean.MyOrderModer;
import com.example.a.myapplication.holder.BaseHolder;
import com.example.a.myapplication.holder.MyOrderHolder;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/8.
 */
public class MyOrderAdapter extends SuperBaseAdapter<MyOrderModer.Order> implements OnItemClickListener {
    List<MyOrderModer.Order> datas = new ArrayList<MyOrderModer.Order>();

    public List<MyOrderModer.Order> getDatas() {
        return datas;
    }

    public void setDatas(List<MyOrderModer.Order> datas) {
        this.datas = datas;
    }

    public MyOrderAdapter(PullToRefreshBase listView, List<MyOrderModer.Order> datas) {
        super(listView, datas);
        this.datas = datas;
    }

    @Override
    protected BaseHolder<MyOrderModer.Order> getItemHolder(int position) {
        return new MyOrderHolder();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

}
