package com.example.a.myapplication.adapter;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;

import com.example.a.myapplication.bean.TestModel;
import com.example.a.myapplication.holder.BaseHolder;
import com.example.a.myapplication.holder.TestHolder;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

import java.util.List;


/**
 * Created by a on 2016/12/1.
 */

public class TestAdapter  extends SuperBaseAdapter<TestModel.Peo> implements AdapterView.OnItemClickListener{
    private List<TestModel.Peo> datas;
    public TestAdapter(PullToRefreshBase listView, List<TestModel.Peo> datas) {
        super(listView, datas);
        this.datas = datas;
       // listView.setOnItemClickListener(this);
    }

    @Override
    protected BaseHolder<TestModel.Peo> getItemHolder(int position) {
        return new TestHolder();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    public List<TestModel.Peo> getDatas() {
        return datas;
    }
}
