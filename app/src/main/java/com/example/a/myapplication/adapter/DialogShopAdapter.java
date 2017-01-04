package com.example.a.myapplication.adapter;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.a.myapplication.bean.MyOrderModer;
import com.example.a.myapplication.holder.BaseHolder;
import com.example.a.myapplication.holder.DialogShopHolder;
import com.example.a.myapplication.holder.MyOrderHolder;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/8.
 */
public class DialogShopAdapter extends SuperBaseAdapter<String> implements OnItemClickListener {
    List<String> datas = new ArrayList<String>();

    public List<String> getDatas() {
        return datas;
    }

    public void setDatas(List<String> datas) {
        this.datas = datas;
    }

    public DialogShopAdapter(PullToRefreshBase listView, List<String> datas) {
        super(listView, datas);
        this.datas = datas;
    }

    @Override
    protected BaseHolder<String> getItemHolder(int position) {
            return new DialogShopHolder(position);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

}
