package com.example.a.myapplication.adapter;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.a.myapplication.bean.MyCollecModer;
import com.example.a.myapplication.holder.BaseHolder;
import com.example.a.myapplication.holder.MyCollectHolder;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

import java.util.List;

/**
 * Created by Administrator on 2016/12/7.
 */
public class MyCollecAdapter extends SuperBaseAdapter<MyCollecModer.Colledt> implements OnItemClickListener {

    private List<MyCollecModer.Colledt> datas;

    public MyCollecAdapter(PullToRefreshBase listView, List<MyCollecModer.Colledt> datas) {
        super(listView, datas);
        datas = datas;
    }

    public List<MyCollecModer.Colledt> getDatas() {
        return datas;
    }

    @Override
    protected BaseHolder<MyCollecModer.Colledt>  getItemHolder(int position) {
        return new MyCollectHolder() ;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
